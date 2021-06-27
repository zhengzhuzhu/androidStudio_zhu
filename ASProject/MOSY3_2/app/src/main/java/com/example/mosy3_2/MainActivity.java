package com.example.mosy3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button add,del,updata,query,showalldata,opendata,closedata;
    private TextView textView,inputQuery,name,number,email;
    private RadioGroup radioGroup;
    private EditText inputData,Ename,Enumber,Email;
    private RadioButton mail,femail;

    private MySqliteHelper helper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=(Button)findViewById(R.id.add);
        del=(Button)findViewById(R.id.del);
        updata=(Button)findViewById(R.id.updata);
        query=(Button)findViewById(R.id.query);
        showalldata=(Button)findViewById(R.id.showalldata);
        opendata=(Button)findViewById(R.id.opendata);
        closedata=(Button)findViewById(R.id.closedata);
        textView=(TextView)findViewById(R.id.textView);
        inputQuery=(TextView)findViewById(R.id.inputQuery);
        name=(TextView)findViewById(R.id.name);
        number=(TextView)findViewById(R.id.number);
        email=(TextView)findViewById(R.id.email);
        inputData=(EditText)findViewById(R.id.inputData);
        Ename=(EditText)findViewById(R.id.Ename);
        Enumber=(EditText)findViewById(R.id.Enumber);
        Email=(EditText)findViewById(R.id.Email);
        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);
        mail=(RadioButton) findViewById(R.id.mail);
        femail=(RadioButton) findViewById(R.id.femail);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strname=Ename.getText().toString();
                String strnumber=Enumber.getText().toString();
                String stemail=Email.getText().toString();
                radioGroup.setOnCheckedChangeListener(mradiogroup);
                db.execSQL("INSERT INTO Contact(name,number,email,sex) values(?,?,?,?)",new Object[]{strname,strnumber,stemail,radioGroup});
                Ename.setText("");
                Enumber.setText("");
                Email.setText("");
                textView.setText("添加完成");
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strinput=inputData.getText().toString();
                db.execSQL("DELETE from Contact where name=?",new Object[]{strinput});
                db.execSQL("DELETE from Contact where number=?",new Object[]{strinput});
                db.execSQL("DELETE from Contact where mail=?",new Object[]{strinput});
                Ename.setText("");
                Enumber.setText("");
                Email.setText("");
                textView.setText("删除完成");
            }
        });
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strinput=inputData.getText().toString();
                String stremail=Email.getText().toString();
                String strnumber=Enumber.getText().toString();
                radioGroup.setOnCheckedChangeListener(mradiogroup);

                db.execSQL("update Contact set number=? where name=? ",new Object[]{strnumber,strinput});
                db.execSQL("update Contact set email=? where name=? ",new Object[]{stremail,strinput});
                db.execSQL("update Contact set sex=? where name=? ",new Object[]{radioGroup,strinput});
                Ename.setText("");
                Enumber.setText("");
                Email.setText("");
                textView.setText("修改完成");
            }
        });
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strinput="%"+inputData.getText().toString()+"%";
                Cursor cursor1=db.rawQuery("select * from Contact where name like ?",new String[]{strinput});
                Cursor cursor2=db.rawQuery("select * from Contact where number like ?",new String[]{strinput});
                Cursor cursor3=db.rawQuery("select * from Contact where email like ?",new String[]{strinput});
                String str1="";
                String str2="";
                String str3="";
                while (cursor1.moveToNext()) {
                    String name = cursor1.getString(cursor1.getColumnIndex("name"));
                    String number = cursor1.getString(cursor1.getColumnIndex("number"));
                    String email = cursor1.getString(cursor1.getColumnIndex("email"));
                    String sex = cursor1.getString(cursor1.getColumnIndex("sex"));
                    str1 += name + "\t";
                    str1 += number + "\t";
                    str1 += email + "\t";
                    str1 += sex + "\n";
                }
                while (cursor2.moveToNext()) {
                    String name = cursor2.getString(cursor1.getColumnIndex("name"));
                    String number = cursor2.getString(cursor1.getColumnIndex("number"));
                    String email = cursor2.getString(cursor1.getColumnIndex("email"));
                    String sex = cursor2.getString(cursor1.getColumnIndex("sex"));
                    str2 += name + "\t";
                    str2 += number + "\t";
                    str2 += email + "\t";
                    str2 += sex + "\n";
                }
                while (cursor3.moveToNext()) {
                    String name = cursor3.getString(cursor3.getColumnIndex("name"));
                    String number = cursor3.getString(cursor3.getColumnIndex("number"));
                    String email = cursor3.getString(cursor3.getColumnIndex("email"));
                    String sex = cursor3.getString(cursor3.getColumnIndex("sex"));
                    str3 += name + "\t";
                    str3 += number + "\t";
                    str3 += email + "\t";
                    str3 += sex + "\n";
                }
            }
        });
        showalldata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=db.rawQuery("select * from Contact",null);
                String str="";
                while (cursor.moveToNext()){
                    String name=cursor.getString(cursor.getColumnIndex("name"));
                    String number=cursor.getString(cursor.getColumnIndex("number"));
                    String email=cursor.getString(cursor.getColumnIndex("email"));
                    String sex=cursor.getString(cursor.getColumnIndex("sex"));
                    str+=name+"\t";
                    str+=number+"\t";
                    str+=email+"\t";
                    str+=sex+"\n";
                }
                textView.setText(str);
            }
        });
        opendata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper=new MySqliteHelper(MainActivity.this,"mySqlite.db",null,1);
                db=helper.getWritableDatabase();
                textView.setText("数据库打开成功");
            }
        });
        closedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private RadioGroup.OnCheckedChangeListener mradiogroup = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == mail.getId()) {
                String strSex=mail.getText().toString();
            } else if (checkedId == femail.getId()) {
                String strSex=femail.getText().toString();
            }
        }
    };
}
