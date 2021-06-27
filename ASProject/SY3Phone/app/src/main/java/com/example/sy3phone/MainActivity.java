package com.example.sy3phone;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText nameText;
    private EditText ageText;

    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText = (EditText) this.findViewById(R.id.et1);
        ageText = (EditText) this.findViewById(R.id.et2);
        Button button = (Button) this.findViewById(R.id.bt1);
        Button showButton = (Button) this.findViewById(R.id.bt2);
        button.setOnClickListener(listener);
        showButton.setOnClickListener(listener);

        tvData = (TextView)findViewById(R.id.tvData);

        // 回显
        SharedPreferences sharedPreferences = getSharedPreferences("王艳", Activity.MODE_PRIVATE);
        String nameValue = sharedPreferences.getString("name", "");
        int ageValue = sharedPreferences.getInt("age", 18);
        nameText.setText(nameValue);
        ageText.setText(String.valueOf(ageValue));
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            Button button = (Button) v;
            //ljq123文件存放在/data/data/<package name>/shared_prefs目录下
            SharedPreferences sharedPreferences = getSharedPreferences("王艳",
                    Activity.MODE_PRIVATE);
            switch (button.getId()) {
                case R.id.bt1:
                    String name = nameText.getText().toString();
                    int age = Integer.parseInt(ageText.getText().toString());
                    Editor editor = sharedPreferences.edit(); //获取编辑器
                    editor.putString("name", name);
                    editor.putInt("age", age);
                    editor.commit();//提交修改
                    Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_LONG).show();
                    tvData.setText("显示读取数据");
                    break;
                case R.id.bt2:
                    String nameValue = sharedPreferences.getString("name", "");
                    int ageValue = sharedPreferences.getInt("age", 1);
//                    Toast.makeText(MainActivity.this, "姓名：" + nameValue + "，年龄：" + ageValue, Toast.LENGTH_LONG).show();

                    tvData.setText("姓名：" + nameValue + "，年龄：" + ageValue);
                    break;
            }
        }
    };
}
