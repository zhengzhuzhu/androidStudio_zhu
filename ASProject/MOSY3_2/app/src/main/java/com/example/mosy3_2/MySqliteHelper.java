package com.example.mosy3_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

public class MySqliteHelper extends SQLiteOpenHelper {
    public MySqliteHelper(@Nullable MainActivity context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super((Context) context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Contact(id integer primary key autoincrement,name varchar(20),number varchar(20),email varchar(20),sex varchar(10) )");
        db.execSQL("INSERT INTO Contact values(null,'张三','1786546780','1786546780@163.com','男')");
        db.execSQL("INSERT INTO Contact values(null,'李四','1786786780','1786786780@163.com','男')");
        db.execSQL("INSERT INTO Contact values(null,'小红','1786786568','1786786567@163.com','女')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
