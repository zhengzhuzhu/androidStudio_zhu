package com.example.sy3_2chen;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqliteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Contacts.db";
    private static final String TABLE_NAME = "Contacts";
    private static final int DB_VERSION = 1;

    public MySqliteHelper(@Nullable Context context) {
        super(context, DB_NAME , null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table " + TABLE_NAME
                + " (id integer primary key autoincrement, "
                + "name varchar(20) not null, "
                + "phone varchar(11),"
                + "email varchar(20),"
                + "shortcall varchar(9));";
        db.execSQL(CREATE_TABLE);

//        String INSERT = "INSERT INTO " + TABLE_NAME + "values(null,'张大','12345678910')";
//        db.execSQL(INSERT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP_TABLE = "drop table " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
    //插入
    public long insert(People entity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", entity.getName());
        values.put("phone", entity.getPhone());
        values.put("email", entity.getEmail());
        values.put("shortcall", entity.getShortcall());
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }
    //更新
    public int update(People entity) {
        String whereClause = "name = ?";
        String[] whereArgs = { entity.getName() };

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", entity.getName());
        values.put("phone", entity.getPhone());
        values.put("email", entity.getEmail());
        values.put("shortcall", entity.getShortcall());

        int rows = db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();
        return rows;
    }
    //删除
    public int delete(People entity) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "name = ?";
        String[] whereArgs = { entity.getName() };

        int rows = db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
        return rows;
    }

    public Cursor queryAll() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public Cursor query(People entity){
        SQLiteDatabase db = getReadableDatabase();
        String[] whereNames = { "name","phone","email","shortcall" };
        String[] whereArgs = { "%"+entity.getName()+"%" };
        return db.query(TABLE_NAME,whereNames, "name like ?",whereArgs,null,null,null);
    }
}
