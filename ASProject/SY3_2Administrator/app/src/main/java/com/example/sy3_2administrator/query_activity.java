package com.example.sy3_2administrator;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

public class query_activity extends AppCompatActivity
{
    public ListView listview;
    public SimpleAdapter simpleAdapter;
    private MySqliteHelper helper;
    private SQLiteDatabase  db;
    private  Cursor mCursor;//游标，查询后返回的对象
    private int id;//当前游标Cursor所在的字段值
    private Cursor mCursor_query;

    public List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_info);
        ListView listV=findViewById(R.id.list);
        //获取查询结果
        ArrayList<HashMap<String, Object>> listData=fillList();
        //获取适配器
        SimpleAdapter adapter=fillAdapter(listData);
        //将适配器内容添加并且显示
        listV.setAdapter(adapter);
    }

    public  ArrayList<HashMap<String, Object>> fillList(){
        //生成动态数组，并且转载数据
        ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();

        helper=new MySqliteHelper(query_activity.this,"SY3mySqlite.db",null,1);
//        db=helper.getReadableDatabase();
        db = helper.getWritableDatabase();  // 获得SQLiteDatabase数据库对象
        Toast.makeText(query_activity.this,"数据库打开完毕", Toast.LENGTH_LONG).show();
        try{
            Cursor cursor=db.rawQuery("select * from Userinfo",null);
            cursor.moveToFirst();

            if(cursor.moveToFirst()) {
                String appId=cursor.getString(cursor.getColumnIndex("id"));
                String appname = cursor.getString(cursor.getColumnIndex("username"));
                String appphone = cursor.getString(cursor.getColumnIndex("userPhone"));
//                String appDescription = cursor.getString(cursor.getColumnIndex("attribute"));

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("id_num",appId);
                map.put("name",appname);
                map.put("phone", appphone);
//                map.put("other", appDescription);
                dataList.add(map);
            }
            while(cursor.moveToNext()) {
                String appId=cursor.getString(cursor.getColumnIndex("id"));
                String appname = cursor.getString(cursor.getColumnIndex("username"));
                String appphone = cursor.getString(cursor.getColumnIndex("userPhone"));
//                String appDescription = cursor.getString(cursor.getColumnIndex("attribute"));

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("id_num",appId);
                map.put("name",appname);
                map.put("phone", appphone);
//                map.put("other", appDescription);
                dataList.add(map);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(db.isOpen()){
                db.close();
            }
        }
        return dataList;
    }
    /**
     * 填充数据，取得数据适配器.
     * @param listData
     * @return
     */
    public SimpleAdapter fillAdapter(ArrayList<HashMap<String, Object>> listData){
        //生成适配器，数组===》ListItem
        // mCursor=db.searchAllData();
        // mCursor=mCursor_query;
        if (mCursor != null && mCursor.getCount() >= 0)
        {}
        SimpleAdapter adapter = new SimpleAdapter(query_activity.this,
                listData,//数据来源
                R.layout.list,//ListItem的XML实现
                //动态数组与ListItem对应的子项
                new String[] {"id_num","name", "phone"},
                //ListItem的XML文件里面的四个个TextView ID
//                new int[] {R.id.id_num,R.id.name,R.id.phone,R.id.other});
        new int[] {R.id.id_num,R.id.name,R.id.phone});
        return adapter;
    }
}