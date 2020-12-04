package com.example.ptsfootballdb;

import android.content.Intent;
import android.os.Bundle;

import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class List_Favourite extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    DataAdapterFavourite adapter;
    List<modelrealm> DataArrayList; //kit add kan ke adapter
    private ImageView tambah_data;
    TextView tvnodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__favourite);
        tvnodata = (TextView) findViewById(R.id.tvnodata);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        DataArrayList = new ArrayList<>();
        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        DataArrayList =(List<modelrealm>) realmHelper.getAllMovie();

        if (DataArrayList.size() == 0) {
            tvnodata.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvnodata.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            for (int i=0;i<DataArrayList.size();i++){
                Log.d("loopp",String.valueOf(DataArrayList.get(i).getJudul()));
                Log.d("loopp",String.valueOf(DataArrayList.get(i).getPath()));
                Log.d("loopp",String.valueOf(DataArrayList.get(i).getReleaseDate()));
                Log.d("loopp",String.valueOf(DataArrayList.get(i).getDesc()));
            }
            adapter = new DataAdapterFavourite(DataArrayList, new DataAdapterFavourite.Callback() {
                @Override
                public void onClick(int position) {
                    Intent move = new Intent(getApplicationContext(), DetailFavourite.class);
                    move.putExtra("judul", DataArrayList.get(position).getJudul());
                    // picture, desc, release, dll
                    move.putExtra("path", DataArrayList.get(position).getPath());
                    move.putExtra("date", DataArrayList.get(position).getReleaseDate());
                    move.putExtra("deskripsi", DataArrayList.get(position).getDesc());
                    move.putExtra("jersey", DataArrayList.get(position).getJersey());
                    move.putExtra("web", DataArrayList.get(position).getWeb());
                    startActivity(move);

                }

                @Override
                public void test() {

                }
            });
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(List_Favourite.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        }
    }

}
