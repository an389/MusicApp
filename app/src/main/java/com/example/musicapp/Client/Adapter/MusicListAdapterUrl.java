package com.example.musicapp.Client.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Model.AudioModel;
import com.example.musicapp.Model.GetSongs;
import com.example.musicapp.Model.MyMediaPlayer;
import com.example.musicapp.Client.MusicPlayerActivity;
import com.example.musicapp.Model.Upload;
import com.example.musicapp.R;

import java.io.IOException;
import java.util.ArrayList;

public class MusicListAdapterUrl extends RecyclerView.Adapter<MusicListAdapterUrl.ViewHolder>{

    ArrayList<GetSongs> songsList;
    Context context;
    MediaPlayer mediaPlayer;

    public MusicListAdapterUrl(ArrayList<GetSongs> songsList, Context context) {
        this.songsList = songsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        GetSongs songData = songsList.get(position);
        holder.titleTextView.setText(songData.getSongTitle());

        if(MyMediaPlayer.currentIndex==position){
            holder.titleTextView.setTextColor(Color.parseColor("#FF0000"));
        }else{
            holder.titleTextView.setTextColor(Color.parseColor("#000000"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //navigate to another acitivty
//
//                MyMediaPlayer.getInstance().reset();
//                MyMediaPlayer.currentIndex = 0;
//                Intent intent = new Intent(context, MusicPlayerActivity.class);
//                intent.putExtra("LIST",songsList);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);

                //play music
                Toast.makeText(context, "Melodia" + songData.getSongLink(), Toast.LENGTH_SHORT).show();

                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                try {
                    mediaPlayer.setDataSource(songData.getSongLink());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(context, "Playing audio", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(context, "Error occured = " + e, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        ImageView iconImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.music_title_text);
            iconImageView = itemView.findViewById(R.id.icon_view);
        }
    }
}