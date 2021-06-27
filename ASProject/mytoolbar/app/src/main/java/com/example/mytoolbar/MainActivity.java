package com.example.mytoolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvTitle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbToolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);//设置logo
        toolbar.inflateMenu(R.menu.my_menu_option);  // 显示菜单

        //toolbar.setNavigationIcon(R.drawable.back);//左上方 多了一个返回按钮
        // 设置菜单项监听
        //为返回按钮设置点击事件

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.muOpt1:
                        tvTitle.setText("选中：菜单项一");
                        break;
                    case R.id.muOpt2:
                        tvTitle.setText("选中：菜单项二");
                        break;
                    case R.id.muOpt3:
                        tvTitle.setText("选中：菜单项三");
                        break;
                }
                return false;
            }
        });
    }
    public void onClick (View v){
        Toast.makeText(MainActivity.this, "返回", Toast.LENGTH_SHORT).show();
    }
}