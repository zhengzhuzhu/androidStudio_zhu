package com.example.menu2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    private TextView option,show,pop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        option = (TextView)findViewById(R.id.optionMenu);
        show = (TextView)findViewById(R.id.showMenu);
        pop = (TextView)findViewById(R.id.popMenu);

        registerForContextMenu(show);

    }
    // 选项菜单：创建
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();  // 获取菜单填充器
        inflater.inflate(R.menu.optionmene,menu);  // 填充菜单
        return true;
    }
    // 选项菜单：处理点击事件
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.opt1:
                option.setText("绿色");
//                option.setTextColor(this.getResources().getColor(R.color.green));
                option.setBackgroundColor(this.getResources().getColor(R.color.green));
                return true;
            case R.id.opt1_1:
                option.setText("黄绿色");
                option.setBackgroundColor(this.getResources().getColor(R.color.yellowgreen));
                return true;
            case R.id.opt1_2:
                option.setText("蓝绿色");
                option.setBackgroundColor(this.getResources().getColor(R.color.bluegreen));
                return true;
            case R.id.opt2:
                option.setText("黄色");
                option.setBackgroundColor(this.getResources().getColor(R.color.yellow));
                return true;
            case R.id.opt3:
                option.setText("红色");
                option.setBackgroundColor(this.getResources().getColor(R.color.red));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.setHeaderTitle("请选择：");
        MenuInflater inflater = getMenuInflater();  // 获取菜单填充器
        inflater.inflate(R.menu.contextmenu, menu);  // 填充菜单
        super.onCreateContextMenu(menu, v, menuInfo);

    }
    // 上下文菜单：处理选择菜单事件
    public  boolean onContextItemSelected(MenuItem item){
        // 注意AdapterContextMenuInfo的类导入（Alt+Enter)
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.show1:
                show.setText("选中：上下文菜单一");
                return true;
            case R.id.show2:
                show.setText("选中：上下文菜单二");
                return true;
            case R.id.show3:
                show.setText("选中：上下文菜单三");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    public void showPop(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.popmenu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.pop1:
                pop.setText("弹出选项一");
                return true;
            case R.id.pop2:
                pop.setText("弹出选项二");
                return true;
            case R.id.pop3:
                pop.setText("弹出选项三");
                return true;
            default:
                return false;
        }
    }
}
