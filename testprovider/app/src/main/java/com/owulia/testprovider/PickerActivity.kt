package com.owulia.testprovider

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_picker.*

class PickerActivity : AppCompatActivity() {

    companion object {

        const val LOADER_ID = 1

    }

    var mImageList = arrayListOf<ImageItem>()

    val imageListAdapter by lazy { ImageListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picker)

//        val imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//        val cursor = contentResolver.query(imageUri, null, null, null, null)
//        val columnNames = cursor?.columnNames
//        while (cursor?.moveToNext() == true) {
//            println("===========================")
//            columnNames?.forEach {
//                val value = cursor.getString(cursor.getColumnIndex(it))
//                println("Name: $it  <======>  Value: $value")
//            }
//            println("===========================")
//        }
//        cursor?.close()

        vRecyclerView.apply {
            layoutManager = GridLayoutManager(this@PickerActivity, 3)
            adapter = imageListAdapter
        }

        initLoaderManager()

    }

    private fun initLoaderManager() {
        mImageList.clear()
        val loaderManager = LoaderManager.getInstance(this)
        loaderManager.initLoader(LOADER_ID, null, object : LoaderManager.LoaderCallbacks<Cursor> {
            override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
//                if (id == LOADER_ID) {
                    return CursorLoader(
                        this@PickerActivity,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        arrayOf("_data", "_display_name", "date_added"),
                        null,
                        null,
                        "date_added DESC"
                    )
//                }
//                return null
            }

            override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor?) {
//                val columnNames = cursor?.columnNames
                while (cursor?.moveToNext() == true) {
//                    println("===========================")
//                    columnNames?.forEach {
//                        val value = cursor.getString(cursor.getColumnIndex(it))
//                        println("Name: $it  <======>  Value: $value")
//                    }
//                    println("===========================")
                    mImageList.add(ImageItem(
                        path = cursor.getString(0),
                        title = cursor.getString(1),
                        date = cursor.getLong(2)
                    ))
                }
                cursor?.close()
                mImageList.forEach {
                    println("it => $it")
                }
                imageListAdapter.setData(mImageList)
            }

            override fun onLoaderReset(loader: Loader<Cursor>) {
            }
        })
    }
}
