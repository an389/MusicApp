package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.musicapp.Client.ClientActivity;
import com.example.musicapp.Client.LocalMusicActivity;
import com.example.musicapp.Server.ServerActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void uploadActivity(View view) {
        Intent intent = new Intent(FirstActivity.this, ServerActivity.class);
        startActivity(intent);
    }

    public void musicActivity(View view) {
        Intent intent = new Intent(FirstActivity.this, ClientActivity.class);
        startActivity(intent);
    }

    public void loclaMusicActivity(View view) {
        Intent intent = new Intent(FirstActivity.this, LocalMusicActivity.class);
        startActivity(intent);
    }
}