package com.example.sy3_2chen;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    private EditText edname,edphone,edemail,edshortcall;
    private TextView txPeople;
    private Button btsubmit,btcancle;
    private Spinner spgroup;
    private boolean spgroup_selected = false;
    private static int ch=0;
    private Spinner spinner;

    MySqliteHelper helper = new MySqliteHelper(AddActivity.this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_people);

        edname = (EditText) this.findViewById(R.id.edname);
        edphone = (EditText) this.findViewById(R.id.edphone);
        edemail = (EditText) this.findViewById(R.id.edemail);
        edshortcall= (EditText) this.findViewById(R.id.edshort);
        txPeople = (TextView) this.findViewById(R.id.txPeple);
        btsubmit = (Button) this.findViewById(R.id.btsubmit);
        btcancle = (Button) this.findViewById(R.id.btcancle);

        Bundle bundle = getIntent().getExtras();
        final boolean addOrNot = bundle.getBoolean("AddorNot");
        if (addOrNot) {
            edname.setText("");
            edphone.setText("");
            edemail.setText("");
            edshortcall.setText("");
        } else {
            txPeople.setText(getResources().getString(R.string.titleChange));
            edname.setText(bundle.getString("oldName"));
            edphone.setText(bundle.getString("oldPhone"));
            edemail.setText(bundle.getString("oldEmail"));
            edshortcall.setText(bundle.getString("oldShortcall"));
        }

        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = edname.getText().toString();
                String newPhone = edphone.getText().toString();
                String newEmail = edemail.getText().toString();
                String newShortcall = edshortcall.getText().toString();

                //手机号正则表达式：^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$'
                if(newPhone.length()!=11){
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.phoneNumWarning),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
//                else   if (!newPhone.matches("^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$")){
//                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.phoneWarning),
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
             //姓名非空
                else if (newName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.nameWarning),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //邮箱正则表达式
                else if (!newEmail.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.emailWarning),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("newName", newName);
                intent.putExtra("newPhone", newPhone);
                intent.putExtra("newEmail", newEmail);
                intent.putExtra("newShortcall", newShortcall);
                int resultCode = 0;
                if (addOrNot)   resultCode = 1;
                else            resultCode = 2;

                AddActivity.this.setResult(resultCode, intent);
                AddActivity.this.finish();
            }
        });
        btcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            int resultCode = 0;
            AddActivity.this.setResult(resultCode);
            AddActivity.this.finish();
        }
        return true;
    }
}
