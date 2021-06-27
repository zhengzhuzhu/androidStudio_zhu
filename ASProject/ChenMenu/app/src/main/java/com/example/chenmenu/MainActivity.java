package com.example.chenmenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    // 注意要实现PopupMenu.OnMenuItemClickListener接口
    private TextView option, show, pop;
    private TextView mBottomSheetDialog;
    private static final String Tag = "MainActivity";
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        option = (TextView) findViewById(R.id.optionMenu);
        show = (TextView) findViewById(R.id.showMenu);
        pop = (TextView) findViewById(R.id.popMenu);
        button1 = (Button) findViewById(R.id.button1);
        registerForContextMenu(show);//  注册应与上下文菜单关联的View
    }

    // 选项菜单：创建
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();  // 获取菜单填充器
        inflater.inflate(R.menu.options_menu, menu);  // 填充菜单
        return true;
    }

    // 选项菜单：处理点击事件
    public boolean onOptionsItemSelected(MenuItem item) {

        if (!item.hasSubMenu()) {
            String itemid = Integer.toString(item.getItemId());
            String title = item.getTitle().toString();
            String message = String.format("选项ID：%s \n 标题：%s", itemid, title);
            ShowAlertDialog(message);
        }
        return super.onOptionsItemSelected(item);
    }

    private void ShowAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择选项");
        builder.setMessage(message);
        builder.setPositiveButton("关闭", null);
        builder.show();
    }

    //上下文菜单
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("请选择：");
        MenuInflater inflater = getMenuInflater();  // 获取菜单填充器
        inflater.inflate(R.menu.contextmenu, menu);  // 填充菜单
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    // 上下文菜单：处理选择菜单事件
    public boolean onContextItemSelected(MenuItem item) {
        // 注意AdapterContextMenuInfo的类导入（Alt+Enter)
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.show1:
                show.setText("已经打开");
                String message = String.format("已经打开");
                ShowAlertDialog(message);
                return true;
            case R.id.show2:
                show.setText("已经关闭");
                String message1 = String.format("已经关闭");
                ShowAlertDialog(message1);
                return true;
            case R.id.show3:
                show.setText("已经退出");
                String message2 = String.format("已经退出");
                ShowAlertDialog(message2);
                return true;
            default:
                return super.onContextItemSelected(item); // 对没有处理的事件，交给父类来处理
        }
    }

    //弹出菜单：
    public void showPop(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.popmenu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pop1:
                pop.setText("发起群聊");
                setContentView(R.layout.one);
                return true;
            case R.id.pop2:
                pop.setText("添加好友");
                return true;
            case R.id.pop3:
                pop.setText("扫一扫");
                return true;
            default:
                return false;
        }
    }
    public  void button_onClick(View v){
        setContentView(R.layout.activity_main);
    }
}