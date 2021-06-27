package com.example.mosy3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnwrite, btnread, btnreadall;
    private TextView Textview1, Textview2, Textview3, Textview4;
    private EditText editText1, editText2;
    private RadioButton Radio1, Radio2;
    private RadioGroup radioGroup;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor spEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnwrite = (Button) findViewById(R.id.Btn1);
        btnread = (Button) findViewById(R.id.Btn2);
        btnreadall = (Button) findViewById(R.id.Btn3);
        Textview1 = (TextView) findViewById(R.id.textview1);
        Textview2 = (TextView) findViewById(R.id.textview2);
        Textview3 = (TextView) findViewById(R.id.textview3);
        Textview4 = (TextView) findViewById(R.id.textview4);
        editText1 = (EditText) findViewById(R.id.edittext1);
        editText2 = (EditText) findViewById(R.id.edittext2);
        Radio1 = (RadioButton) findViewById(R.id.RadioButton1);
        Radio2 = (RadioButton) findViewById(R.id.RadioButton2);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final List<SharedPreferences.Editor> data=new ArrayList<>();

        sharedPreferences = getSharedPreferences("my_data", MODE_PRIVATE);//读取
        spEditor = sharedPreferences.edit();//写入

        btnwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText1.getText()==null){
                    Toast toast1=Toast.makeText(getApplicationContext(),"姓名不得为空",Toast.LENGTH_SHORT);
                    toast1.show();

                }else if(editText1.getText()!=null){
                    spEditor.putString("saveName", editText1.getText().toString());
                }
                if(editText2.getText()==null){
                    Toast toast2=Toast.makeText(getApplicationContext(),"手机不得为空",Toast.LENGTH_SHORT);
                    toast2.show();
                }else if(editText2.getText()!=null){
                    spEditor.putString("saveNumber", editText2.getText().toString());
                }
                radioGroup.setOnCheckedChangeListener(mradiogroup);
                data.add(spEditor);
                spEditor.apply();//提交
                editText1.setText("");//存完清空文本框
                editText2.setText("");

            }
        });

        btnread.setOnClickListener(new View.OnClickListener() {
            @Override
            //读取数据
            public void onClick(View v) {//读取
                Textview1.setText(Textview2.getText() + sharedPreferences.getString("saveName", "")
                        +"\n" +Textview3.getText() + sharedPreferences.getString("saveNumber", "")
                        +"\n"+ Textview4.getText() + sharedPreferences.getString("saveSex", ""));
            }
        });
    }

    private RadioGroup.OnCheckedChangeListener mradiogroup = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == Radio1.getId()) {
//                spEditor.putString("saveSex", Radio1.getText().toString());
                spEditor.putString("saveSex", Radio1.getText().toString());
            } else if (checkedId == Radio2.getId()) {
                spEditor.putString("saveSex", Radio2.getText().toString());
            }
        }
    };
}
