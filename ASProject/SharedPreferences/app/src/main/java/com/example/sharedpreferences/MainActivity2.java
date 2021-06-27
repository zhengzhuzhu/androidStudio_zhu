package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
//SharedPreferences存取数据操作
public class MainActivity2 extends AppCompatActivity {

    private Button btnWrite,btnRead;
    private EditText etData;
    private TextView tvData;
    private SharedPreferences sharedPreferences;  // 用于读
    private SharedPreferences.Editor spEditor;  // 用于写
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnWrite = (Button)findViewById(R.id.btnWrite);
        btnRead = (Button)findViewById(R.id.btnRead);
        etData = (EditText)findViewById(R.id.etData);
        tvData = (TextView)findViewById(R.id.tvData);

        // 实例化
        sharedPreferences = getSharedPreferences("my_data",MODE_PRIVATE);
        spEditor = sharedPreferences.edit();

        // 写按钮
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spEditor.putString("saveName", etData.getText().toString());  // 写入
                spEditor.apply();  // 提交
                etData.setText("");  // 存完文本框清空
            }
        });
        // 读按钮
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 读取
                tvData.setText(sharedPreferences.getString("saveName",""));
            }
        });
    }
}