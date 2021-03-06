package com.example.sy4_2lsit;
//
//import android.hardware.Sensor;
//import android.hardware.SensorEventListener;
//import android.hardware.SensorManager;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ListView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
////https://blog.csdn.net/sandyran/article/details/80298467
//public class MainActivity extends AppCompatActivity {
//    private static final String TAG = "MainActivity";
//    private ListView mListView;
//    private VideoListAdapter mAdapter;
//    private SensorEventListener mSensorEventListener;
//    private SensorManager mSensorManager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mListView = (ListView) findViewById(R.id.list_view);
//        mAdapter = new VideoListAdapter(this);
//        mListView.setAdapter(mAdapter);
//        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        mSensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (JCVideoPlayer.backPress()) {
//            return;
//        }
//        super.onBackPressed();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Sensor mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        mSensorManager.registerListener(mSensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
//        Log.e(TAG, "onResume");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mSensorManager.unregisterListener(mSensorEventListener);
//        JCVideoPlayer.releaseAllVideos();
//        Log.e(TAG, "onPause");
//    }
//}


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private VideoView videoView;
    private static final int FILE_SELECT_CODE=1;
    private static final String TAG="VideoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView=(VideoView)findViewById(R.id.video_view);
        Button play=(Button)findViewById(R.id.play);
        Button pause=(Button)findViewById(R.id.pause);
        Button replay=(Button)findViewById(R.id.replay);
        Button choice=(Button)findViewById(R.id.choice) ;//??????????????????
        choice.setOnClickListener(this);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);//??????????????????
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);//?????????????????????
        }
        else {
            inintVideoPath();
        }
    }
    private void inintVideoPath(){
//        File file=new File(Environment.getExternalStorageDirectory(),"movie.mp4");//??????????????????????????????????????????movie.mp4
//        videoView.setVideoPath(file.getPath());//???????????????????????????
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.movie;
        videoView.setVideoPath(uri);
    }
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    inintVideoPath();
                }
                else {
                    Toast.makeText(this,"?????????????????????????????????",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }
    public void onClick(View v){//??????????????????
        switch (v.getId()){
            case R.id.play:
                if(!videoView.isPlaying()){//??????
                    videoView.start();
                }
                break;
            case R.id.pause:
                if(videoView.isPlaying()){//??????
                    videoView.pause();
                }
                break;
            case R.id.replay:
                if(videoView.isPlaying()){
                    videoView.resume();//????????????
                }
                break;
            case R.id.choice://????????????
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");//?????????????????????????????????
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,1);
        }
    }
    public void onDestroy(){//????????????
        super.onDestroy();
        if(videoView!=null){
            videoView.suspend();
        }
    }
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode== Activity.RESULT_OK){
            Uri uri=data.getData();
            videoView.setVideoURI(uri);//????????????????????????????????????
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (requestCode == FILE_SELECT_CODE) {
            Uri uri = data.getData();
            Log.i(TAG, "------->" + uri.getPath());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    //public void choseFile(){
    ///    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
    //    intent.setType("*/*");
    //    intent.addCategory(Intent.CATEGORY_OPENABLE);
    //    try {
    //        startActivityForResult(Intent.createChooser(intent, "????????????"), FILE_SELECT_CODE);
    //    } catch (android.content.ActivityNotFoundException ex) {
    //        Toast.makeText(this, "??????????????????????????????-_-!!", Toast.LENGTH_SHORT).show();
    //    }

    // }

}