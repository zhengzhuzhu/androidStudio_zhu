package com.example.sy3_2administrator;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.*;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class addrelation extends AppCompatActivity {
    public EditText username;
    public EditText userphone;
    public Spinner userattribute;
    private MySqliteHelper helper;
    private SQLiteDatabase db;

    private static int ch=0;
//    DBHelper db=new DBHelper(this,null,null,0);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrelation);
        init();
        Button save=(Button)findViewById(R.id.save);
//打开数据库
        helper = new MySqliteHelper(addrelation.this,"SY3mySqlite.db",null,1);
        db = helper.getWritableDatabase();  // 获得SQLiteDatabase数据库对象
        Toast.makeText(addrelation.this,"数据库打开完毕", Toast.LENGTH_LONG).show();
    }
    private void init() {
        username = (EditText) findViewById(R.id.addName);
        userphone = (EditText) findViewById(R.id.addTel);
    }
    public void onclick_save(View view) //这个方法是弹出一个对话框
    {
        initEvent();
    }
    public void onclick_exit(View view)//退出按钮响应
    {
        finish();
    }
    private void initEvent() {	//初始化事件，即：对话框的显示
        showDialog();
    }

    private void showDialog(){
        String name=username.getText().toString();
        String phone=userphone.getText().toString();
        if (name == null || name.isEmpty()){  // 姓名不能为空
            Toast.makeText(addrelation.this,"温馨提示:请输入联系人名称", Toast.LENGTH_LONG).show();
        }
        else if(phone.equals("")){
            Toast.makeText(addrelation.this,"温馨提示:请输入联系人号码", Toast.LENGTH_LONG).show();
        }
        else{
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            // builder.setIcon(R.drawable..);
            builder.setTitle("提示");
            builder.setMessage("是否保存?");
            builder.setPositiveButton("保存",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ch=1;
                            insert_db(ch);
                            ch=0;
                        }
                    });
            builder.setNegativeButton("放弃",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialogInterface,int i){
                    ch=0;
                    insert_db(ch);
                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();
        }
    }

    public void insert_db(int ch){

        String name=username.getText().toString();
        String phone=userphone.getText().toString();
//        String attribute=(String)spinner1.getSelectedItem();
        if(ch==1){
            String sql="insert into Userinfo values(?,?,?)";
            db.execSQL(sql, new Object[]{null,name,phone});
//            db.execSQL("insert into Userinfo values(?,?, ?)", new Object[]{null,name, phone});
            Toast.makeText(addrelation.this,"保存成功",Toast.LENGTH_LONG).show();
            db.close();// 关闭数据库
//            Toast.makeText(addrelation.this,"数据库已关闭", Toast.LENGTH_LONG).show();
        }
        else{
            return;
        }
    }
}