package com.fileopen.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FileReadActivity extends Activity {
	private static final String _DirRoot = "/";
	private static final String _SDCARD = android.os.Environment
			.getExternalStorageDirectory().getAbsolutePath();
	private final String _BACK_TO_ROOT = "Back To /";
	public static final String _TAG = "TAG";
	String _currPath;
	ListView lv;
	ArrayList<String> arrl = new ArrayList<String>();
	ArrayAdapter<String> aa;
	TextView tv;
	Context ct;
	public OnItemClickListener _itemClickListener;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file);
		lv = (ListView) findViewById(R.id.ListView01);
		tv = (TextView) findViewById(R.id.tv);
		ct = this;
		getDir(new File(_SDCARD));
		_itemClickListener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String _selectItem = parent.getItemAtPosition(position)
						.toString();
				String _fileName = null;
				if (_selectItem.equals(_BACK_TO_ROOT)) {
					_fileName = _DirRoot;
				} else if (_currPath.equals(_DirRoot)) {
					_fileName = _currPath + _selectItem;
				} else {
					_fileName = _currPath + "/" + _selectItem;
				}
				File f = new File(_fileName);
				if (f.canRead()) {
					if (f.isDirectory()) {
						getDir(f);
					} else if (f.getName().toLowerCase().endsWith(".txt")) {
						readText(f);
					} else {
						Toast.makeText(ct,
								"ERROR! You Have No Right To Access!",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		};
		lv.setOnItemClickListener(_itemClickListener);
	}

	public void getDir(File f) {
		// File f = new File(fileName);
		_currPath = f.getPath();
		tv.setText(_currPath);
		if (arrl.size() != 0) {
			arrl.removeAll(arrl);
		}
		arrl.add(_BACK_TO_ROOT);
		File[] _f = f.listFiles();
		for (File _temFile : _f) {
			arrl.add(_temFile.getName());
		}
		aa = new ArrayAdapter<String>(this, R.layout.hinfo, arrl);
		lv.setAdapter(aa);
	}
	
	public byte[] readFile(InputStream inStream) throws Throwable {
		int len = 0;
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		return outStream.toByteArray();
	}
	
	public void readText(File f) {
		try {
			byte[] data;
			File file = new File(f.getPath());
			FileInputStream inStream = new FileInputStream(file);
			data = readFile(inStream);
			String content = new String(data);
			Intent it = new Intent();
			it.setClass(FileReadActivity.this, FileOpenActivity.class);
			Bundle bd = new Bundle();
			bd.putString("FileContent", content);
			it.putExtras(bd);
			startActivity(it);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
