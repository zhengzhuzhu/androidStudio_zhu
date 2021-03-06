package com.coderpig.mediacodecs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import info.hoang8f.widget.FButton;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FButton play;
    private FButton stop;
    private FButton replay;
    private FButton next;
    private FButton up;
    private VideoView videoView;
    private RecyclerView recyclerView;

    ArrayList<String> filePath;
    private MyRecyclerViewAdapter adapter;

    private int media_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.play);
        stop = findViewById(R.id.pause);
        replay = findViewById(R.id.replay);
        next = findViewById(R.id.next);
        up = findViewById(R.id.up);

        videoView = findViewById(R.id.Video_view);
        recyclerView = findViewById(R.id.recyclerview);

        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        replay.setOnClickListener(this);
        next.setOnClickListener(this);
        up.setOnClickListener(this);

        filePath = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(filePath);
        recyclerView.setAdapter(adapter);


        //????????????
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
             != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            initVideoPath();//?????????videoview
        }

    }

    private void initVideoPath() {
        filePath.addAll(getFilesAllName("/storage/emulated/0/Pictures"));
        videoView.setVideoPath(filePath.get(media_index));//????????????????????????
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initVideoPath();
                }else{
                    Toast.makeText(this, "?????????????????????????????????", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                videoView.start();
                break;
            case R.id.pause:
                videoView.pause();;
                break;
            case R.id.replay:
                videoView.resume();
                break;
            case R.id.next:
                if (media_index == filePath.size()-1){
                    Toast.makeText(this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    break;
                }
                videoView.setVideoPath(filePath.get(++media_index));//????????????????????????
                videoView.start();
                Toast.makeText(this, "????????????"+media_index+"?????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.up:
                if (media_index == 0){
                    Toast.makeText(this, "???????????????????????????", Toast.LENGTH_SHORT).show();
                    break;
                }
                videoView.setVideoPath(filePath.get(--media_index));
                videoView.start();
                Toast.makeText(this, "????????????"+media_index+"?????????", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null){
            videoView.suspend();
        }
    }

    /**
     * ????????????
     * @param path
     * @return
     */
    public ArrayList<String> getFilesAllName(String path) {
        File file=new File(path);
        File[] files=file.listFiles();
        if (files == null){
            Log.e("error","?????????");return null;}
        ArrayList<String> s = new ArrayList<>();
        for(int j = 0;j<12;j++){
           for(int i =0;i<files.length;i++) {
               s.add(files[i].getPath());
           }
        }
        return s;
    }
}
