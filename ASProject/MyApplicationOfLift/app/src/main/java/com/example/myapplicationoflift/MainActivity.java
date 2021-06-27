package com.example.myapplicationoflift;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.*;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView t1;
   private Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=(TextView)findViewById(R.id.TextView);
        b1=(Button)findViewById(R.id.button);

        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
        AppManager.getAppManager().addActivity(this);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t1.setText("您好");
                Toast.makeText(MainActivity.this,R.string.default_button,
                        Toast.LENGTH_SHORT).show();  //Toast输出信息
                btn(v);
            }
        });
    }

//    public void b1_onClick(View v){
//        t1.setText("您好！");
//    }
    public void btn(View v) {
        // 除零错误
        int c = 1/0;
    }
}





