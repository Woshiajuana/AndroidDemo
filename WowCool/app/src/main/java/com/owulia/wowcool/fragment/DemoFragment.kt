package com.owulia.wowcool.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.owulia.wowcool.R
import com.owulia.wowcool.adapter.DemoAdapter
import com.owulia.wowcool.bean.DemoItemBean
import kotlinx.android.synthetic.main.fragment_demo.*

/**
 * A simple [Fragment] subclass.
 */
class DemoFragment : Fragment() {


    val arrDemoItem = mutableListOf<DemoItemBean>(
        DemoItemBean(R.string.string_demo_tabbar_icon, R.string.string_demo_tabbar_text)
    )

//    val

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        new DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL);

        rvDemoMain.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = DemoAdapter(arrDemoItem)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            addItemDecoration(DividerItemDecoration(context,  DividerItemDecoration.HORIZONTAL))
        }
    }

}
