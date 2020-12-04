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

public class dataClub extends AppCompatActivity {
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
        setContentView(R.layout.activity_data_club);
        team = findViewById(R.id.name_team);
        tahun1 = findViewById(R.id.tahun_team);
        web1=findViewById(R.id.web);
        logoteam=findViewById(R.id.logo_team);
        btnFavourite=findViewById(R.id.btnFavourite);
        imgJersey=findViewById(R.id.jersey);

        Realm.init(dataClub.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        ekstra = getIntent().getExtras();
        if (ekstra == null) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("ekstra", ekstra.getString("name"));
            name = ekstra.getString("name");
            team.setText(name);
            jersey = ekstra.getString("jersey");
            logo = ekstra.getString("logo");
            tahun = ekstra.getString("tahun");
            deskripsi=ekstra.getString("deskripsi");
            web = ekstra.getString("web");
            web1.setText(web);


        }
        Glide.with(this).load(jersey).into(imgJersey);
        Glide.with(this).load(logo).into(logoteam);




        btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelRealm = new modelrealm();
                ModelRealm.setDesc(deskripsi);
                ModelRealm.setJudul(name);
                ModelRealm.setPath(logo);
                ModelRealm.setReleaseDate(tahun);
                ModelRealm.setJersey(jersey);
                ModelRealm.setWeb(web);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(ModelRealm);
                Toast.makeText(dataClub.this, "Success Add To Favourite", Toast.LENGTH_SHORT).show();

            }
        });


    }

}