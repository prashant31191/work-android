package com.fileopen.activity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FileOpenActivity extends Activity {
	Button butRead;
	Button butWrit;
	TextView paramView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        butRead = (Button) findViewById(R.id.butOpen);
        butWrit = (Button) findViewById(R.id.butWrit);
        
        Intent intent = this.getIntent();//得到激活该组件的意图
        String contnt = intent.getStringExtra("FileContent");//从意图中获取前面Activity传递过来的参数
        paramView = (TextView) this.findViewById(R.id.textView1);
        paramView.setText(contnt);//显示数据
        
        butRead.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent readFile = new Intent(FileOpenActivity.this, FileReadActivity.class);
				startActivity(readFile);
			}
		});
        
        butWrit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String content = paramView.getText().toString();
				FileOutputStream outStream;
				try {
					//FileOpenActivity.this.openFileOutput(name, mode);
					outStream = FileOpenActivity.this.openFileOutput("sharpandroid.txt", Context.MODE_PRIVATE);
					outStream.write(content.getBytes());
					outStream.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
