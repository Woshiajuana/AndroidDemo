package com.owulia.gallery

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_pager_photo.*

/**
 * A simple [Fragment] subclass.
 */

const val REQUEST_WRITE_EXTERNAL_STORAGE = 1

class PagerPhotoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val photoList: ArrayList<PhotoItem>? = arguments?.getParcelableArrayList("PHOTO_LIST")
        PagerPhotoListAdapter().apply {
            viewPager2.adapter = this
            submitList(photoList)
        }

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                photoTag.text = getString(R.string.photo_tag, position + 1, photoList?.size)
            }
        })

        viewPager2.setCurrentItem(arguments?.getInt("PHOTO_POSITION")?:0, false)

        saveButton.setOnClickListener {
            if (Build.VERSION.SDK_INT < 29 && ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_WRITE_EXTERNAL_STORAGE)
            } else {
                savePhoto()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    savePhoto()
                } else {
                    Toast.makeText(requireContext(), "存储失败", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun savePhoto () {
        Toast.makeText(requireContext(), "存储成功", Toast.LENGTH_SHORT).show()
    }

}
