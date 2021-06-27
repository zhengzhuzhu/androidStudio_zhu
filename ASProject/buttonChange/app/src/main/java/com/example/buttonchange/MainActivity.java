package com.example.buttonchange;


import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    ImageView i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView)findViewById(R.id.T1);
        i1=(ImageView)findViewById(R.id.I1);
    }

    public void myB1Click(View v){
        String aStr;
        aStr = t1.getText().toString();
        //t1.setText(aStr+"abc"); //按钮更换文本内容方法一：直接法
//按钮更换文本内容方法二：通过xml文件获取变更文本
        Resources res=getResources();
        String[] myClassName =res.getStringArray(R.array.class_name);//class_name:计科117X
       t1.setText(myClassName[2]);//更换文本框内容

        Log.e("run台输出：Error","abc1");//abc.jpg

        Toast.makeText(this,"change the picture and the text !", Toast.LENGTH_LONG).show();//弹出框

        i1.setImageResource(R.drawable.abc);
    }
}