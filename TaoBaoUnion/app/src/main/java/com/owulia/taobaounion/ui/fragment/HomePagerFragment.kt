package com.owulia.taobaounion.ui.fragment

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.lcodecore.tkrefreshlayout.footer.LoadingView
import com.owulia.taobaounion.R
import com.owulia.taobaounion.base.BaseFragment
import com.owulia.taobaounion.model.domain.Categories
import com.owulia.taobaounion.model.domain.HomePagerContent
import com.owulia.taobaounion.presenter.impl.CategoryPagerPresenterImpl
import com.owulia.taobaounion.ui.activity.TicketActivity
import com.owulia.taobaounion.ui.adapter.HomePagerContentAdapter
import com.owulia.taobaounion.ui.adapter.LoopPagerAdapter
import com.owulia.taobaounion.utils.*
import com.owulia.taobaounion.view.ICategoryPagerCallback
import kotlinx.android.synthetic.main.fragment_home_pager.*
import kotlinx.android.synthetic.main.include_home_pager_title_part.*
import kotlinx.android.synthetic.main.item_home_pager_content.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomePagerFragment : BaseFragment (), ICategoryPagerCallback {

    private var mCategoryPagerPresenterImpl: CategoryPagerPresenterImpl? = null
    private var mMaterialId : Int? = null
    private var mHomePagerContentAdapter : HomePagerContentAdapter? = null
    private var mLoopPagerAdapter : LoopPagerAdapter? = null

    companion object {
        val instant: (Categories.Data) -> HomePagerFragment = {
            val homePagerFragment = HomePagerFragment()
            val bundle = Bundle()
            bundle.putString(Constants.KEY_HOME_PAGER_TITLE, it.title)
            bundle.putInt(Constants.KEY_HOME_PAGER_MATERIAL_ID, it.id)
            homePagerFragment.arguments = bundle
            homePagerFragment
        }
    }

    override fun getRootViewResId() = R.layout.fragment_home_pager

    override fun initView(view: View?) {
        super.initView(view)
        // 设置布局管理器
        // 创建适配器
        // 设置适配器
        mContentList.apply {
            mHomePagerContentAdapter = HomePagerContentAdapter().apply {
                setOnItemListener{ _, data ->
                    // 拿到 Picker
                    val title = data.title
                    var url = data.coupon_click_url
                    if (TextUtils.isEmpty(url)) {
                        url = data.click_url
                    }
                    val cover = data.pict_url
                    val tickPresenterImpl = PresenterManager.instant.tickPresenterImpl
                    tickPresenterImpl.getTicket(title, url, cover)
                    startActivity(Intent(context, TicketActivity::class.java))
                }
            }
            layoutManager = LinearLayoutManager(context)
            adapter = mHomePagerContentAdapter
            addItemDecoration(object : RecyclerView.ItemDecoration () {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.top = 10
                    outRect.bottom = 10
                }
            })
        }

        mLoopPager.apply {
            mLoopPagerAdapter = LoopPagerAdapter()
            adapter = mLoopPagerAdapter
        }

        // 设置 refresh 相关
        twinklingRefreshLayout.apply {
            setEnableRefresh(false)
            setEnableLoadmore(true)
//            setHeaderView(SinaRefreshView(context))
            setBottomView(LoadingView(context))
        }

    }

    // 监听事件
    override fun initListener() {

        mHomePagerParent.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                val headerHeight = mHomePagerHeader.measuredHeight
                tbNestedScrollView.setHeaderHeight(mHomePagerHeader.height)
                val height = mHomePagerParent.measuredHeight
                LogUtil.d(this, "height => $height")
                mContentList.layoutParams.height = height
                if (height > 0 && headerHeight > 0) {
                    mHomePagerParent.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            }
        })

        mLoopPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }
            override fun onPageSelected(position: Int) {
                if (mLoopPagerAdapter!!.data.size == 0) return
                val targetPosition = position % mLoopPagerAdapter!!.data.size
                updateLooperIndicator(targetPosition)
            }
        })

        twinklingRefreshLayout.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                LogUtil.d(this, "加载更多")
                mCategoryPagerPresenterImpl?.loaderMore(mMaterialId!!)
            }

            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
//                mCategoryPagerPresenterImpl?.loaderMore(mMaterialId!!)
            }
        })

    }

    private fun updateLooperIndicator (targetPosition: Int) {
        for (i in 0 until looperPoint.childCount) {
            val child = looperPoint.getChildAt(i)
            child.setBackgroundResource(if (i == targetPosition) R.drawable.shape_indicator_point else R.drawable.shape_indicator_point_normal)
        }
    }

    override fun initPresenter() {
        super.initPresenter()
        mCategoryPagerPresenterImpl = PresenterManager.instant.categoryPagerPresenterImpl
        mCategoryPagerPresenterImpl?.registerViewCallback(this)
    }

    override fun loadData() {
        super.loadData()
        mMaterialId = arguments?.getInt(Constants.KEY_HOME_PAGER_MATERIAL_ID)
        mHomePagerTitle.text = arguments?.getString(Constants.KEY_HOME_PAGER_TITLE)
        // 加载数据
        LogUtil.d(this, "loadData materialId => $mMaterialId ")
        mCategoryPagerPresenterImpl?.getContentByCategoryId(mMaterialId!!)
    }

    override fun onContentLoad(contents: List<HomePagerContent.Data>, categoryId: Int) {
        mHomePagerContentAdapter?.setData(contents)
        setUpState(State.SUCCESS)
    }

    override fun onLoading() {
        setUpState(State.LOADING)
    }

    override fun onNetworkError() {
        setUpState(State.ERROR)
    }

    override fun onEmpty() {
        setUpState(State.EMPTY)
    }

    override fun onLoadMoreError(categoryId: Int) {
        twinklingRefreshLayout?.finishLoadmore()
        ToastUtil.showToast("网络异常，请稍后重试")
    }

    override fun onLoadMoreEmpty(categoryId: Int) {
        twinklingRefreshLayout?.finishLoadmore()
        ToastUtil.showToast("没有更多的商品")
    }

    override fun onLoadMoreLoaded(contents: List<HomePagerContent.Data>, categoryId: Int) {
        // 添加到适配器数据的底部
        mHomePagerContentAdapter?.addData(contents)
        twinklingRefreshLayout?.finishLoadmore()
        ToastUtil.showToast("加载了${contents.size}条数据")
    }

    override fun getCategoryId(): Int? {
        return mMaterialId
    }

    override fun onLooperListLoaded(contents: List<HomePagerContent.Data>, categoryId: Int) {
        mLoopPagerAdapter?.setData(contents)
        val dx = (Int.MAX_VALUE / 2) % contents.size
        mLoopPager.currentItem = Int.MAX_VALUE / 2 - dx
        looperPoint.removeAllViews()
        contents.forEach {  _ ->
            val point = View(context).apply {
                layoutParams = LinearLayout.LayoutParams(SizeUtil.dp2px(context, 8f), SizeUtil.dp2px(context, 8f)).apply {
                    marginEnd = SizeUtil.dp2px(context, 5f)
                    marginStart = SizeUtil.dp2px(context, 5f)
                }
            }
            looperPoint.addView(point)
        }
        updateLooperIndicator(0)
    }

    override fun release() {
        super.release()
        mCategoryPagerPresenterImpl?.unregisterViewCallback(this)
    }
}
