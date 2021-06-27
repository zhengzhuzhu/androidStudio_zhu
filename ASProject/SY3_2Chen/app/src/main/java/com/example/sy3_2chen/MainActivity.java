package com.example.sy3_2chen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.SearchEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btadd,btsearch;
    private EditText edSearch;
    private ListView lvshow;

    private MySqliteHelper helper ;
    private SQLiteDatabase db ;

    private List<Map<String,String>> dataList = new ArrayList<Map<String,String>>();
    private MySimpleAdapter mySimpleAdapter;

    private boolean SearchOrNot = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btadd = (Button)this.findViewById(R.id.btadd);
        btsearch = (Button)this.findViewById(R.id.btsearch);
        edSearch = (EditText) this.findViewById(R.id.edSearch);

        helper = new MySqliteHelper(MainActivity.this);
        db =  helper.getWritableDatabase();
        updateListView(SearchOrNot);       //更新listview

        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("AddorNot", true);
                intent.putExtras(bundle);
                int requestCode = 1;
                startActivityForResult(intent, requestCode);
            }
        });
        btsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean Search = true;
                updateListView(Search);
            }
        });
        lvshow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                HashMap<String, String> map = (HashMap<String, String>)listView.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("AddorNot", false);
                bundle.putString("oldName", map.get("name"));
                bundle.putString("oldPhone", map.get("phone"));
                bundle.putString("oldEmail", map.get("email"));
                bundle.putString("oldShortcall", map.get("shortcall"));
                intent.putExtras(bundle);
                int requestCode = 2;
                startActivityForResult(intent, requestCode);
            }
        });
        lvshow.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                final HashMap<String, String> map = (HashMap<String, String>)listView.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("确认删除吗？");
                builder.setTitle("提示");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            helper.delete(new People(map.get("name"), map.get("phone"),map.get("email"), map.get("shortcall")));
                            updateListView(SearchOrNot);
                        } catch (Exception e) {
                            Log.d("Hint", "Remove failed!");
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // nothing to do
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void setData(List<Map<String, String>> mDataList) {
        Map<String, String> mData;
        Cursor c = helper.queryAll();
        while (c.moveToNext()) {
            mData = new HashMap<String, String>();
            mData.put("name", c.getString(c.getColumnIndex("name")));
            mData.put("phone", c.getString(c.getColumnIndex("phone")));
            mData.put("email", c.getString(c.getColumnIndex("email")));
            mData.put("shortcall", c.getString(c.getColumnIndex("shortcall")));
            mDataList.add(mData);
        }

    }
    private void setSearchData(List<Map<String, String>> mDataList) {
        Map<String, String> mData;
        Cursor c = helper.query(new People(edSearch.getText().toString()));
        while (c.moveToNext()) {
            mData = new HashMap<String, String>();
            mData.put("name", c.getString(c.getColumnIndex("name")));
            mData.put("phone", c.getString(c.getColumnIndex("phone")));
            mData.put("email", c.getString(c.getColumnIndex("email")));
            mData.put("shortcall", c.getString(c.getColumnIndex("shortcall")));
            mDataList.add(mData);
        }
    }

    private void updateListView(boolean SearchOrNot) {
        dataList.clear();
        if (SearchOrNot) setSearchData(dataList);
        else setData(dataList);
        mySimpleAdapter = new MySimpleAdapter(this, dataList, R.layout.contanct_item,
                new String[] { "name", "phone" ,"email","shortcall"},
                new int[] { R.id.contact_name, R.id.contact_phone,R.id.contact_email, R.id.contact_short});
        lvshow = (ListView) this.findViewById(R.id.lvshow);
        lvshow.setAdapter(mySimpleAdapter);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Hint", "requestCode = " + requestCode);
        Log.d("Hint", "resultCode = " + resultCode);
        if (resultCode == 0)
            return;
        String newName = data.getStringExtra("newName");
        String newPhone = data.getStringExtra("newPhone");
        String newEmail = data.getStringExtra("newEmail");
        String newShortcall = data.getStringExtra("newShortcall");
        switch (requestCode) {
            case 1:
                helper.insert(new People(newName, newPhone,newEmail,newShortcall));
                break;
            case 2:
                helper.update(new People(newName, newPhone,newEmail,newShortcall));
                break;
            default:
                break;
        }
        updateListView(SearchOrNot);
    }
}
