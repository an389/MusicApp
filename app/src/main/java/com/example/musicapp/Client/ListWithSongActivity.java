package com.example.musicapp.Client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.musicapp.Client.Adapter.MusicListAdapter;
import com.example.musicapp.Client.Adapter.MusicListAdapterUrl;
import com.example.musicapp.Model.AudioModel;
import com.example.musicapp.Model.GetSongs;
import com.example.musicapp.Model.Upload;
import com.example.musicapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListWithSongActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView noMusicTextView;
    ArrayList<GetSongs> mUpload;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    Boolean checkIn = false;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_with_song);

        mUpload = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_viewURL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

     //   noMusicTextView = findViewById(R.id.no_songs_textURL);


        databaseReference = FirebaseDatabase.getInstance().getReference("songs");
        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (!mUpload.isEmpty())
//                    mUpload.clear();

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    GetSongs upload = dataSnapshot.getValue(GetSongs.class);
                    mUpload.add(upload);
//                    GetSongs getSongs = dss.getValue(GetSongs.class);
//                    getSongs.setmKey(dss.getKey());
//                    currentIndex = 0;
//                    final String s = getIntent().getExtras().getString("songsCategory");
//                    noMusicTextView.setText(getSongs.getSongsCategory());
//
//                    mUpload.add(getSongs);
//                    if (s.equals(getSongs.getSongsCategory())){
//                        mUpload.add(getSongs);
//                        checkIn = true;
//                        noMusicTextView.setText("e muzica");
//                    }
                }
               // noMusicTextView.setText(mUpload.get(2).name.toString());
                recyclerView.setAdapter(new MusicListAdapterUrl(mUpload,getApplicationContext()));
//                if (mUpload.size() == 0){
//                    noMusicTextView.setVisibility(View.VISIBLE);
//                }else {
//                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}