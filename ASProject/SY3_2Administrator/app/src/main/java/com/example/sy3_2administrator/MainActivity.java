package com.example.sy3_2administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
public Button button_add_info;
public Button button_query;
public  Button button_del;
public  Button button_update;
//public DBHelper db;
    //定义Button，ListView，list数据集合，simpleAdapter适配器
private  Button create,query;
private  ListView listView=null;
private List<Map<String,Object>>list=new ArrayList<Map<String,Object>>();
private  SimpleAdapter simpleAdapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        query=(Button)findViewById(R.id.query_phone);
        create=(Button)findViewById(R.id.add_phone_name);
    }
    public void button_add(View view){
        Intent intent_add=new Intent(MainActivity.this,addrelation.class);
        startActivity(intent_add);
    }

    public void button_query(View view) {
        Intent intent_query=new Intent(MainActivity.this,query_2.class);
        startActivity(intent_query);
    }

    public void button_del(View view) {
        Intent intent_del=new Intent(MainActivity.this,delete_activity.class);
        startActivity(intent_del);
    }

    public void button_update(View view) {
        Intent intent_update=new Intent(MainActivity.this,update_activity.class);
        startActivity(intent_update);
    }
    public  void delete_table(){
//        db.exesql_w();
        Toast.makeText(MainActivity.this,"删除成功！",Toast.LENGTH_LONG).show();
    }
}
