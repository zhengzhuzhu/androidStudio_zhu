package com.example.machronometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
        private int startTime = 0;
        private Chronometer recordChronometer;
        private long recordingTime = 0;// 记录下来的总时间
        private Context context ;
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            context = this;
            final Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
            Button btnStart = (Button) findViewById(R.id.btnStart);
            Button btnWait=(Button)findViewById(R.id.btnWait);
            Button btnStop = (Button) findViewById(R.id.btnStop);
            Button btnRest = (Button) findViewById(R.id.btnReset);
            final EditText edtSetTime = (EditText) findViewById(R.id.edt_settime);

        btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast t = Toast.makeText(context,"开始记时", Toast.LENGTH_LONG);
                    t.show();
                    String ss = edtSetTime.getText().toString();
                    if (!(ss.equals("") && ss != null)) {
                        startTime = Integer.parseInt(edtSetTime.getText()
                                .toString());
                    }
                    // 跳过已经记录了的时间，起到继续计时的作用
                    chronometer.setBase(SystemClock.elapsedRealtime()-recordingTime);
                    // 开始记时
                    chronometer.start();
                }
            });
            btnWait.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast t = Toast.makeText(context,"暂停记时", Toast.LENGTH_LONG);
                    t.show();
                    chronometer.stop();
                    // 保存这次记录了的时间
                    //SystemClock.elapsedRealtime()是系统启动到现在的毫秒数
                    recordingTime=SystemClock.elapsedRealtime()-chronometer.getBase();//getBase():返回时间
                }
            });
            btnStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 停止
                    Toast t = Toast.makeText(context,"停止记时", Toast.LENGTH_LONG);
                    t.show();
                    recordingTime=0;
                    chronometer.stop();
//                    recordChronometer.setBase(SystemClock.elapsedRealtime());
                }
            });
            // 重置
            btnRest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast t = Toast.makeText(context,"重置计时", Toast.LENGTH_LONG);
                    t.show();
                    recordingTime=0;
                    chronometer.setBase(SystemClock.elapsedRealtime());// setBase(long base):设置计时器的起始时间
                    edtSetTime.setText(null);//输入框清空
                }
            });
            chronometer
                    .setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                        @Override
                        public void onChronometerTick(Chronometer chronometer) {
                            // 如果开始计时到现在超过了startime秒
                            if (SystemClock.elapsedRealtime()
                                    - chronometer.getBase() > startTime * 1000) {
                                chronometer.stop();
                                // 给用户提示
                                showDialog();
                            }
                        }
                    });
        }
        protected void showDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.smell);
            final EditText edtSetTime = (EditText) findViewById(R.id.edt_settime);
            String ss = edtSetTime.getText().toString();
            builder.setTitle("温馨提示：").setMessage("您设置的时间"+ss+"(s)已到~~~")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
