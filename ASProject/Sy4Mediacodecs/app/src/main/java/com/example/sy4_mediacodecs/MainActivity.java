package com.example.sy4_mediacodecs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button play;
    private Button stop;
    private Button replay;
    private Button next;
    private Button up;
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


        //权限申请
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            initVideoPath();//初始化videoview
        }

    }

    private void initVideoPath() {
        filePath.addAll(getFilesAllName("D:/360安全浏览器下载/ajianji/MediaEditor/VideoClip"));
        videoView.setVideoPath(filePath.get(media_index));//指定视频文件路径
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
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(this, "已经到最后一个视频了", Toast.LENGTH_SHORT).show();
                    break;
                }
                videoView.setVideoPath(filePath.get(++media_index));//指定视频文件路径
                videoView.start();
                Toast.makeText(this, "当前是第"+media_index+"个视频", Toast.LENGTH_SHORT).show();
                break;
            case R.id.up:
                if (media_index == 0){
                    Toast.makeText(this, "已经是第一个视频了", Toast.LENGTH_SHORT).show();
                    break;
                }
                videoView.setVideoPath(filePath.get(--media_index));
                videoView.start();
                Toast.makeText(this, "当前是第"+media_index+"个视频", Toast.LENGTH_SHORT).show();
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
     * 读取文件
     * @param path
     * @return
     */
    public ArrayList<String> getFilesAllName(String path) {
        File file=new File(path);
        File[] files=file.listFiles();
        if (files == null){
            Log.e("error","空目录");return null;}
        ArrayList<String> s = new ArrayList<>();
        for(int j = 0;j<12;j++){
            for(int i =0;i<files.length;i++) {
                s.add(files[i].getPath());
            }
        }
        return s;
    }
}
