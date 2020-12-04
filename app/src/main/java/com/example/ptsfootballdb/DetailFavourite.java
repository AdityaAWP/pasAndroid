package com.example.ptsfootballdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailFavourite extends AppCompatActivity {
    Bundle ekstra;


    String name, jersey, logo, tahun, web,deskripsi;
    ImageView logoteam, imgJersey, imgStadium;
    Button btnFavourite;
    TextView team;
    TextView tahun1;
    TextView web1;
    Realm realm;
    RealmHelper realmHelper;
    modelrealm ModelRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favourite);

        team = findViewById(R.id.name_team1);
        tahun1 = findViewById(R.id.tahun_team1);
        web1=findViewById(R.id.web1);
        logoteam=findViewById(R.id.logo_team1);
        imgJersey=findViewById(R.id.jersey1);

        Realm.init(DetailFavourite.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        ekstra = getIntent().getExtras();
        if (ekstra == null) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        } else {

            name = ekstra.getString("judul");
            team.setText(name);
            jersey = ekstra.getString("jersey");
            logo = ekstra.getString("path");
            tahun = ekstra.getString("date");
            deskripsi=ekstra.getString("deskripsi");
            web = ekstra.getString("web");
            web1.setText(web);
            Log.d("jersey",logo);

        }
        Glide.with(this).load(jersey).into(imgJersey);
        Glide.with(this).load(logo).into(logoteam);


    }
}