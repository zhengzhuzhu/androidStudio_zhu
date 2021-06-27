package com.example.usesqlite;


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
        db.execSQL("create table login(id integer primary key autoincrement, name varchar(20), pwd varchar(8))");
        db.execSQL("insert into login values(null,'张三','123abc')");
        db.execSQL("insert into login values(null,'李四','abc123')");
        db.execSQL("insert into login values(null,'王五','123123')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 数据库版本号升级时执行
        db.execSQL("ALTER TABLE login ADD mymeno VARCHAR(10)");  // 更改表结构
        db.execSQL("update login set pwd='aaaaaa'");  // 重置表密码
    }
}
