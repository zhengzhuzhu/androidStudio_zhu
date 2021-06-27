package com.example.sy3phone2;

import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
    private static final String FILENAME = "people";
    private static final int MODE = MODE_PRIVATE;
    EditText name,phone=null;
    Button find,add,refresh,delate=null;
    EditText show=null;
    SharedPreferences shared=null;
    People people=new People();
    String showInformation=new String("");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText) findViewById(R.id.name);
        phone=(EditText) findViewById(R.id.phone);
        find=(Button) findViewById(R.id.find);
        add=(Button) findViewById(R.id.add);
        refresh=(Button) findViewById(R.id.refresh);
        delate=(Button) findViewById(R.id.delate);
        show=(EditText) findViewById(R.id.show);
        shared=getSharedPreferences(FILENAME, MODE);
        people.names=new ArrayList();
        people.phones=new ArrayList();

        //点击添加按钮，响应响应的动作事件
        add.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                if(name.getText()!=null && phone.getText()!=null)
                {
                    if(!"".equals(name.getText().toString()) &&!"".equals(phone.getText().toString()))
                    {
                        people.names.add(name.getText().toString());
                        people.phones.add(phone.getText().toString());
                        ByteArrayOutputStream baos=new ByteArrayOutputStream();
                        try
                        {
                            ObjectOutputStream oos=new ObjectOutputStream(baos);
                            oos.writeObject(people);
                            String peopleBase64=new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
                            Editor editor=shared.edit();
                            editor.putString("people", peopleBase64);
                            editor.commit();

                            if("".equals(showInformation))
                            {
                                showInformation=name.getText().toString()+" "+phone.getText().toString()+"\n";
                            }
                            else
                            {
                                showInformation +=name.getText().toString()+" "+phone.getText().toString()+"\n";
                            }

                            show.setText(showInformation);
                            name.setText("");
                            phone.setText("");
                            Toast.makeText(MainActivity.this, "信息添加成功！", Toast.LENGTH_LONG).show();

                        }
                        catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "请填写完整！", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        //点击查询按钮，响应相应的动作事件
        find.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                String peopleBase64=shared.getString("people", "none");

                if(name.getText()!=null && phone.getText()!=null)
                {
                    if(!"".equals(name.getText().toString()) && !"".equals(phone.getText().toString()))
                    {
                        String tempName=name.getText().toString();
                        String tempPhone=phone.getText().toString();
                        int flag1=0;
                        int flag2=0;

                        for(int i=0;i<people.names.size();i++)
                        {
                            String temp=people.names.get(i);
                            if(temp.equals(tempName))
                                flag1=1;
                        }
                        for(int i=0;i<people.phones.size();i++)
                        {
                            String temp=people.phones.get(i);
                            if(temp.equals(tempPhone))
                                flag2=1;
                        }

                        if(flag1==1 && flag2==1)
                            Toast.makeText(MainActivity.this, "查询成功！", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "查询失败！", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "请输入查询内容！", Toast.LENGTH_LONG).show();
                }
            }
        });

        //为删除添加动作事件
        delate.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                String tempName=name.getText().toString();
                String tempPhone=phone.getText().toString();

                if(name.getText()!=null && phone.getText()!=null)
                {
                    if(!"".equals(name.getText().toString()) && !"".equals(phone.getText().toString()))
                    {
                        int flag=0;

                        for(int i=0;i<people.names.size();i++)
                        {
                            String temp1=people.names.get(i);
                            String temp2=people.phones.get(i);
                            if(temp1.equals(tempName) && temp2.equals(tempPhone))
                            {
                                people.names.remove(i);
                                people.phones.remove(i);
                                flag=1;
                            }
                        }

                        if(flag==1)
                            Toast.makeText(MainActivity.this, "删除成功！", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "删除失败！", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "删除的内容不存在！", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "删除的内容不能为空！", Toast.LENGTH_LONG).show();
                }
            }
        });

        //为更新按钮添加动作事件
        refresh.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                String tempShow="";
                for(int i=0;i<people.names.size();i++)
                {
                    tempShow=tempShow+people.names.get(i)+" "+people.phones.get(i)+"\n";
                }

                show.setText(tempShow);
            }
        });
    }



    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.layout.activity_main, menu);
        return true;
    }

}
