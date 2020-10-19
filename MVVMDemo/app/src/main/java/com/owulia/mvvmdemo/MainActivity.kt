package com.owulia.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.owulia.mvvmdemo.adapter.OnSellListAdapter
import com.owulia.mvvmdemo.viewmodel.OnSellViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mViewModel by lazy {
        ViewModelProvider(this).get(OnSellViewModel::class.java)
    }

    private val mAdapter by lazy {
        OnSellListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView ()
        initObserver()
    }

    /**
     * 观察数据变化
     * */
    private fun initObserver() {
        mViewModel.run {
            contentList.observe(this@MainActivity, Observer {
                // 内容更新
                // 更新数据
                mAdapter.setData(it)
            })
        }
    }

    /**
     * 初始化控件
     * */
    private fun initView() {
        contentListRv.run{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }
    }
}
