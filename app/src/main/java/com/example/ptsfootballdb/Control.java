package com.example.ptsfootballdb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class Control extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("userid", "").isEmpty()) {
            Intent in = new Intent(getApplicationContext(), login.class);
            startActivity(in);
            finish();

        } else {
            Intent in = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(in);
            finish();
        }

    }
}
