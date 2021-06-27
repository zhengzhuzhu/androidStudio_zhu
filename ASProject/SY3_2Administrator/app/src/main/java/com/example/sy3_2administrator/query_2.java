package com.example.sy3_2administrator;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//运行模拟机后，View-toolwindow-device file explorer查看数据库目录
//目录：data/data/com.example.buttonchage/databases.mySqlite.db
public class query_2 extends AppCompatActivity {
    private Button button_query;
    private EditText InputName;
    private TextView name;
    private TextView phone;
    private TextView others;
    private MySqliteHelper helper;
    private SQLiteDatabase db;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.queryresult);
//
//        button_query = (Button) findViewById(R.id.button_query);
//
//        InputName = (EditText) findViewById(R.id.InputName);
//        name = (TextView) findViewById(R.id.name);
//        phone = (TextView) findViewById(R.id.phone);
//        others = (TextView) findViewById(R.id.others);
//
//        helper = new MySqliteHelper(query_2.this, "SY3mySqlite.db", null, 1);
//        db = helper.getWritableDatabase();  // 获得SQLiteDatabase数据库对象
//        Toast.makeText(query_2.this, "数据库打开完毕", Toast.LENGTH_LONG).show();
//
//        button_query.setOnClickListener(new View.OnClickListener() {  // 插入(新增)
//            @Override
//            public void onClick(View v) {
//                String strQurey = InputName.getText().toString();
//                if (strQurey == null || strQurey.isEmpty()) {  // 姓名不能为空
//                    InputName.setText("请先输入姓名");
//                    return;
//                }
//                Cursor cursor = db.rawQuery("select * from Userinfo where name =?", new String[]{strQurey});
//                //Cursor cursor = db.rawQuery("select * from login ",null);  // 全部查询
//
//                // 方法二：封装方法
//                // 不如不用
//                String strName = "";
//                String strphone = "";
//                while (cursor.moveToNext()) {
//                    strName = cursor.getString(cursor.getColumnIndex("name"));
//                    strphone = cursor.getString(cursor.getColumnIndex("phone"));
//                }
//                if (strName == null || strName.isEmpty()) {
//                    Toast.makeText(query_2.this, "无该用户", Toast.LENGTH_LONG).show();
//                }
//                name.setText(strName);
//                phone.setText(strphone);
//                db.close();
//            }
//        });
//    }
//}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queryresult);
        button_query = (Button) findViewById(R.id.button_query);
        InputName = (EditText) findViewById(R.id.InputName);
        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        others = (TextView) findViewById(R.id.others);

        helper = new MySqliteHelper(query_2.this, "SY3mySqlite.db", null, 1);
        db = helper.getWritableDatabase();  // 获得SQLiteDatabase数据库对象
        Toast.makeText(query_2.this, "数据库打开完毕", Toast.LENGTH_LONG).show();
        name.setText("默认无");
        phone.setText("默认无");
        others.setText("默认无");

        button_query.setOnClickListener(new View.OnClickListener() {  // 插入(新增)
            String strName = "";
            String strphone = "";
            public void onClick(View v) {
                String queryName= InputName.getText().toString();

                Cursor cursor = db.rawQuery("select * from Userinfo  where name=?", new String[]{queryName});

                while (cursor.moveToNext()) {
                    strName = cursor.getString(cursor.getColumnIndex("name"));
                    strphone = cursor.getString(cursor.getColumnIndex("phone"));
                }
                if (strName !=null ) {
                    Toast.makeText(query_2.this, "查找成功！", Toast.LENGTH_LONG).show();
                    name.setText(strName);
                    phone.setText(strphone);
                    db.close();

                } else {
                    Toast.makeText(query_2.this, "无该用户", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void onclick_exit(View view)//退出按钮响应
    {
        finish();
    }
}