package com.example.sy4_1;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

public class MainActivity extends Activity {
    private VideoView mVideoView;
    private Button playBtn, pauseBtn,stopBtn,listbutton;
    MediaController mMediaController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);//判断你是否授权
        }
        else {
            initView();
        }

    }
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initView();
                }
                else {
                    Toast.makeText(this,"拒绝权限将无法访问程序",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }
    public void list_onclick(View view){
        Intent intent_add=new Intent(MainActivity.this,video_list.class);
        startActivity(intent_add);

    }

    private void initView() {

        playBtn = (Button) findViewById(R.id.playbutton);
        pauseBtn = (Button) findViewById(R.id.pausebutton);
        stopBtn = (Button) findViewById(R.id.stopbutton);
        listbutton=(Button)findViewById(R.id.listbutton);
//        playBtn.setOnClickListener(new mClick());
//        pauseBtn.setOnClickListener(new mClick());
//        stopBtn.setOnClickListener(new mClick());
        //各按钮的功能
        playBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    play_mp4();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(mVideoView.isPlaying()){
                    mVideoView.pause();
                }
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mVideoView.stopPlayback();
            }
        });
    }
    //播放本地视频
    private  void play_mp4()throws IOException {
        mVideoView = new VideoView(this);
        mVideoView = (VideoView) findViewById(R.id.video);
        //设置有进度条可以拖动快进
        mMediaController = new MediaController(this);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.ex1;
        mVideoView.setVideoURI(Uri.parse(uri));
        mMediaController.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(mMediaController);
        mVideoView.requestFocus();
        if(!mVideoView.isPlaying()) {
            mVideoView.start();
        }
    }
    private void play_rtsp() throws IOException {
        // 网络RTSP视频的播放；
        String videoUrl2 = "https://flv2.bn.netease.com/videolib1/1811/26/OqJAZ893T/HD/OqJAZ893T-mobile.mp4";
        Uri uri = Uri.parse( videoUrl2 );
        mVideoView = (VideoView)this.findViewById(R.id.video );
        //videoView.setVideoPath(path);
        mVideoView.setVideoPath(videoUrl2);
        mVideoView.requestFocus();
        if(!mVideoView.isPlaying()) {
            mVideoView.start();
        }
    }
    //播放网络视频
    private void initNetVideo()throws IOException  {
        //设置有进度条可以拖动快进
        MediaController localMediaController = new MediaController(this);
        mVideoView.setMediaController(localMediaController);
        String url = "https://flv2.bn.netease.com/videolib1/1811/26/OqJAZ893T/HD/OqJAZ893T-mobile.mp4";
        mVideoView.setVideoPath(url);
        mVideoView.start();
    }
}


// 视频资源存放在res资源包raw文件夹下面
//    class mClick implements OnClickListener {
//        @Override
//        public void onClick(View v) {
//            String uri = "android.resource://" + getPackageName() + "/" + R.raw.ex1;
//            mVideoView.setVideoURI(Uri.parse(uri));
//            mMediaController.setMediaPlayer(mVideoView);
//            mVideoView.setMediaController(mMediaController);
//            if (v == playBtn) {
//                mVideoView.start();
//            }else if (v==pauseBtn){
//                mVideoView.pause();
//            }else if(v == stopBtn){
//                mVideoView.stopPlayback();
//            }
//        }
//    }

