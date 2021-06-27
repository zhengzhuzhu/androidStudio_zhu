package com.example.sy3_2administrator;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper {

    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 只在无数据库时执行，其它情况不执行
        db.execSQL("create table Userinfo(id integer primary key autoincrement, name varchar(20), phone varchar(8))");
        db.execSQL("insert into Userinfo values(1,'张三','110')");
        db.execSQL("insert into Userinfo values(2,'李四','120')");
        db.execSQL("insert into Userinfo values(3,'王五','119')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 数据库版本号升级时执行
        db.execSQL("ALTER TABLE Userinfo ADD mymeno VARCHAR(10)");  // 更改表结构
        db.execSQL("update Userinfo set pwd='000000'");  // 重置电话
    }
}
