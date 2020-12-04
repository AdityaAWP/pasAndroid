package com.example.ptsfootballdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressDialog dialog;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    CardView menu1;
     ArrayList<ModelData> DataArrayList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu1 = (CardView)findViewById(R.id.menu1);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), List_Favourite.class));
            }
        });
        dialog = new ProgressDialog(MainActivity.this);
        recyclerView=findViewById(R.id.rc_view);
        layoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();
        addDataOnline();



    }
    public void addDataOnline(){
        dialog.setMessage("Loading");
        dialog.show();
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        try {
                            ModelData data;
                            JSONArray team =response.getJSONArray("teams");
                            for (int i=0;i<team.length();i++){
                                data=new ModelData();
                                JSONObject object = team.getJSONObject(i);
                                data.setTeam_name(object.getString("strTeam"));
                                data.setTeam_detail(object.getString("strDescriptionEN"));
                                data.setTeam_logo(object.getString("strTeamBadge"));
                                data.setTeam_jersey(object.getString("strTeamJersey"));
                                data.setTeam_stadium_img(object.getString("strStadiumThumb"));
                                data.setTeam_stadium_name(object.getString("strStadium"));
                                data.setTeam_year(object.getString("intFormedYear"));
                                data.setTeam_web(object.getString("strWebsite"));
                                data.setFanart_1(object.getString("strTeamFanart1"));
                                DataArrayList.add(data);
                                Log.d("sizze",DataArrayList.get(i).team_logo);
                            }

                            adapter=new Adapter_rc(DataArrayList);
                            recyclerView.setAdapter(adapter);
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }

                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}