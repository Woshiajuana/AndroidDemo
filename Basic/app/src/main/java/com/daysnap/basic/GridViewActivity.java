package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.daysnap.basic.adapter.PlantGridAdapter;
import com.daysnap.basic.bean.Planet;

import java.util.List;

public class GridViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        GridView gvList = findViewById(R.id.gv_list);
        List<Planet> planetList = Planet.getDefaultList();
        PlantGridAdapter adapter = new PlantGridAdapter(this, planetList);
        gvList.setAdapter(adapter);
    }
}