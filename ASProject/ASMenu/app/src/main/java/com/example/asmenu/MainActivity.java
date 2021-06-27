package com.example.asmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    // 注意要实现PopupMenu.OnMenuItemClickListener接口

    private TextView tvShowOpt,tvShowCtx,tvShowPop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvShowOpt = (TextView)findViewById(R.id.tvShowOpt);
        tvShowCtx = (TextView)findViewById(R.id.tvShowCtx);
        tvShowPop = (TextView)findViewById(R.id.tvShowPop);

        registerForContextMenu(tvShowCtx);  //  注册应与上下文菜单关联的View
    }

    // 选项菜单：创建
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();  // 获取菜单填充器
        inflater.inflate(R.menu.optionmene,menu);  // 填充菜单
        return true;
    }
    // 选项菜单：处理点击事件
    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//            case R.id.muOpt1:
//                tvShowOpt.setText("选中：选项菜单一");
//                tvShowOpt.setTextColor(this.getResources().getColor(R.color.green));
//                return true;
//            case R.id.muOpt2:
//                tvShowOpt.setText("选中：选项菜单二");
//                return true;
//            case R.id.muOpt2_1:
//                tvShowOpt.setText("选中：子菜单1");
//                return true;
//            case R.id.muOpt2_2:
//                tvShowOpt.setText("选中：子菜单2");
//                return true;
//            case R.id.muOpt3:
//                tvShowOpt.setText("选中：选项菜单三");
//                return true;
//            default:
//                return  super.onOptionsItemSelected(item);
//        }
        switch (item.getItemId()){
            case R.id.opt1:
                tvShowOpt.setText("绿色");
                tvShowOpt.setTextColor(this.getResources().getColor(R.color.green));
                return true;
            case R.id.opt1_1:
                tvShowOpt.setText("黄绿色");
                tvShowOpt.setTextColor(this.getResources().getColor(R.color.yellowgreen));
                return true;
            case R.id.opt1_2:
                tvShowOpt.setText("蓝绿色");
                tvShowOpt.setTextColor(this.getResources().getColor(R.color.bluegreen));
                return true;
            case R.id.opt2:
                tvShowOpt.setText("黄色");
                tvShowOpt.setTextColor(this.getResources().getColor(R.color.yellow));
                return true;
            case R.id.opt3:
                tvShowOpt.setText("红色");
                tvShowOpt.setTextColor(this.getResources().getColor(R.color.red));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    // 上下文菜单：创建
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("请选择：");
        MenuInflater inflater = getMenuInflater();  // 获取菜单填充器
        inflater.inflate(R.menu.my_menu_ctx, menu);  // 填充菜单
    }
    // 上下文菜单：处理选择菜单事件
    public  boolean onContextItemSelected(MenuItem item){
        // 注意AdapterContextMenuInfo的类导入（Alt+Enter)
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.muCtx1:
                tvShowCtx.setText("选中：上下文菜单一");
                return true;
            case R.id.muCtx2:
                tvShowCtx.setText("选中：上下文菜单二");
                return true;
            case R.id.muCtx3:
                tvShowCtx.setText("选中：上下文菜单三");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    // 弹出菜单：创建
    public void showPop(View v){ //自定义方法，在布局文件的控件onClick="showPop"中关联
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();  // 获取菜单填充器
        inflater.inflate(R.menu.my_menu_pop,popup.getMenu());  // 填充菜单
        // 将点击监听注册到菜单对象
        // 注意要在最上面的Activiety中实现OnMenuItemClickListener 接口
        popup.setOnMenuItemClickListener(this);
        popup.show();
    }
    // 弹出菜单：处理点击事件
    public boolean onMenuItemClick(MenuItem item){
        switch (item.getItemId()){
            case R.id.muPop1:
                tvShowPop.setText("选中：弹出菜单一");
                return true;
            case R.id.muPop2:
                tvShowPop.setText("选中：弹出菜单二");
                return true;
            case R.id.muPop3:
                tvShowPop.setText("选中：弹出菜单三");
                return true;
            default:
                return false;
        }
    }
}
