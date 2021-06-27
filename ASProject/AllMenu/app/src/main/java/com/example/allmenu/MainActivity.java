package com.example.allmenu;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
public class MainActivity extends Activity {

    private int groupId1 = 19880716;
    private int groupId2 = 19880717;
    private ListView lv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);

        ArrayList data = new ArrayList();

        HashMap map1 = new HashMap();
        map1.put("item", "第一个选项");
        HashMap map2 = new HashMap();
        map2.put("item", "第二个选项");
        HashMap map3 = new HashMap();
        map3.put("item", "第三个选项");
        HashMap map4 = new HashMap();
        map4.put("item", "第四个选项");
        HashMap map5 = new HashMap();
        map5.put("item", "第五个选项");

        data.add(map1);
        data.add(map2);
        data.add(map3);
        data.add(map4);
        data.add(map5);

        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item,
                new String[] { "item" }, new int[] { R.id.tv });
        lv.setAdapter(adapter);

        //listview每个项长按时的响应上下文菜单
        lv.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                // TODO Auto-generated method stub

                Toast.makeText(MainActivity.this, "弹出上下文菜单", Toast.LENGTH_SHORT);
                return false;
            }
        });

        //注册上下文菜单
        registerForContextMenu(lv);
    }

    //创建菜单按钮的菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub

        MenuInflater inflater = new MenuInflater(this);

        inflater.inflate(R.menu.menu, menu);
        menu.getItem(0).setIcon(R.drawable.ic_launcher_background);
        menu.getItem(1).setIcon(R.drawable.ic_launcher_background);
        menu.getItem(2).setIcon(R.drawable.ic_launcher_background);
        return super.onCreateOptionsMenu(menu);
    }

    //当按钮菜单被点击时响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {
            case R.id.delete:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.main:
                Toast.makeText(this, "主页", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.zoom:
                Toast.makeText(this, "放大", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //创建上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderIcon(R.drawable.ic_launcher_background);
        SubMenu submenu1 = menu.addSubMenu("动态添加的菜单1");
        submenu1.add(this.groupId1, 0, 0, "动态添加子菜单1");
        submenu1.add(this.groupId1, 0, 0, "动态添加子菜单2");
        submenu1.add(this.groupId1, 0, 0, "动态添加子菜单3");
        submenu1.add(this.groupId1, 0, 0, "动态添加子菜单4");

        // 设置改组子菜单为单选，第三个参数为true时，子菜单单选
        submenu1.setGroupCheckable(this.groupId1, true, true);

        SubMenu submenu2 = menu.addSubMenu("动态添加的菜单2");
        submenu2.add(this.groupId2, 0, 0, "动态添加子菜单1");
        submenu2.add(this.groupId2, 0, 0, "动态添加子菜单2");
        submenu2.add(this.groupId2, 0, 0, "动态添加子菜单3");
        submenu2.add(this.groupId2, 0, 0, "动态添加子菜单4");

        // 设置改组子菜单为多选第三个参数为false时，子菜单多选
        submenu2.setGroupCheckable(this.groupId2, true, false);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {
            case R.id.cmenu1:
                Toast.makeText(this, "上下文菜单1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cmenu2:
                Toast.makeText(this, "上下文菜单2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.csubmenu1:
                Toast.makeText(this, "来自xml,我还有子菜单", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.csubmenu2:
                Toast.makeText(this, "来自xml,我还有单选子菜单", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.csubmenu3:
                Toast.makeText(this, "来自xml,我还有多选子菜单", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.buttonmenu1:
                buildRadioDialog();
                return true;
            case R.id.buttonmenu2:
                builderCheckBoxDialog();
                return true;
            default:
                //当按钮被选中时，设置其选中状态为true,这样才能看到在页面上有选中的radio
                if (item.getGroupId() == this.groupId1) {
                    Toast.makeText(this, "动态添加的菜单1的子菜单", Toast.LENGTH_SHORT).show();
                    if (!item.isChecked()) {
                        item.setChecked(true);
                        Toast.makeText(this, "您选的是" + item.getTitle(),
                                Toast.LENGTH_SHORT).show();
                    }
                }

                if (item.getGroupId() == this.groupId2) {
                    Toast.makeText(this, "动态添加的菜单2的子菜单", Toast.LENGTH_SHORT).show();
                    if (!item.isChecked()) {
                        item.setChecked(true);
                        Toast.makeText(this, "您选的是" + item.getTitle(),
                                Toast.LENGTH_SHORT).show();
                    }
                }


                if (!item.isChecked()) {
                    item.setChecked(true);
                    Toast.makeText(this, "您选的是" + item.getTitle(),
                            Toast.LENGTH_SHORT).show();
                }
                return super.onContextItemSelected(item);

        }

    }

    //上下文菜单关闭时
    @Override
    public void onContextMenuClosed(Menu menu) {
        // TODO Auto-generated method stub
        Toast.makeText(this, "上下文菜单被关", Toast.LENGTH_SHORT).show();
        super.onContextMenuClosed(menu);
    }

    private void buildRadioDialog() {
        Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("单选按钮菜单");
        final String[] titles = new String[] { "白色", "橙色", "蓝色" };

        //-1表示默认没有按钮被选中
        builder.setSingleChoiceItems(titles, -1,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Toast.makeText(MainActivity.this, titles[which],
                                Toast.LENGTH_SHORT).show();
                    }
                });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        builder.create().show();
    }

    private void builderCheckBoxDialog() {
        Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("多选按钮");
        final String[] titles = new String[] { "白色", "橙色", "蓝色" };
        builder.setMultiChoiceItems(titles,
                new boolean[] { false, false, false },
                new DialogInterface.OnMultiChoiceClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        // TODO Auto-generated method stub

                        //当checkbox选中时响应
                        if (isChecked) {
                            Toast.makeText(MainActivity.this,
                                    titles[which] + "被选中", Toast.LENGTH_SHORT)
                                    .show();
                        }

                        //当checkbox未选中时响应
                        if (!isChecked) {
                            Toast.makeText(MainActivity.this,
                                    titles[which] + "被取消", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });

        //确定按钮事件响应
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        //取消按钮 事件响应
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        builder.create().show();
    }
}