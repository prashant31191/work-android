package com.example.sharedpreferencesdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreferencesActivity extends Activity {
	EditText mName;
	EditText mPasswd;
	Button mSave;
	Button mRead;
	TextView mInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        mName = (EditText) findViewById(R.id.name);
        mPasswd = (EditText) findViewById(R.id.passwd);
        mSave = (Button) findViewById(R.id.save);
        mRead = (Button) findViewById(R.id.read);
        mInfo = (TextView) findViewById(R.id.info);
        
        mSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WriteSharedPreferences();
			}
		});
        
        mRead.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ReadSharedPreferences();
			}
		});
    }
    
    void ReadSharedPreferences() {
    	String strName, strPassword;
    	SharedPreferences user = getSharedPreferences("user_info", 0);
    	strName = user.getString("NAME", "none");
    	strPassword = user.getString("PASSWORD", "none");
    	mInfo.setText("Your Info is name : " + strName + " , passwd : " + strPassword);
    }
    
    void WriteSharedPreferences() {
    	SharedPreferences user = getSharedPreferences("user_info", 0);
    	Editor editor = user.edit();
    	editor.putString("NAME", mName.getText().toString());
    	editor.putString("PASSWORD", mPasswd.getText().toString());
    	editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_shared_preferences, menu);
        return true;
    }
}
