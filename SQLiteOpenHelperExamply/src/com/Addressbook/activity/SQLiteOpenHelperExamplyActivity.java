package com.Addressbook.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SQLiteOpenHelperExamplyActivity extends Activity {
	/** Called when the activity is first created. */
	EditText nameEditText;
	EditText ageEditText;
	EditText selectEditText;
	Button addButton;
	Button selectButton;
	Button delButton;
	PersonService personService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        personService = new PersonService(this);//传入上下文
        nameEditText = (EditText) findViewById(R.id.nameeditText);
        ageEditText = (EditText) findViewById(R.id.ageeditText);
        selectEditText = (EditText) findViewById(R.id.selecteditText);
        addButton = (Button) findViewById(R.id.addbut);
        selectButton = (Button) findViewById(R.id.selectbut);
        delButton = (Button) findViewById(R.id.delbut);
        
        addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] addstr = new String[] {
						nameEditText.getText().toString().trim(),
						ageEditText.getText().toString().trim()
				};
				Person person = new Person(addstr[0], Integer.parseInt(addstr[1]));
				personService.save(person);
				
				Toast.makeText(SQLiteOpenHelperExamplyActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
			}
		});
        
        selectButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Cursor cursor = database.rawQuery("select * from person where name=?",
				Integer selectid = Integer.parseInt(selectEditText.getText().toString().trim());
				Person person = personService.find(selectid);
				
				String selString = "";
				if (person != null) {
					selString += "id为: " + person.getId() + ";" + "姓名为: " 
							+ person.getName() + ";" + "年龄为: " + person.getAge() + "";
					delButton.setVisibility(View.VISIBLE);
				}
				Toast.makeText(SQLiteOpenHelperExamplyActivity.this, selString, Toast.LENGTH_SHORT).show();
			}
		});
        
        delButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Integer selectid = Integer.parseInt(selectEditText.getText().toString().trim());
				personService.delete(selectid);
				
				Toast.makeText(SQLiteOpenHelperExamplyActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
			}
		});
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 0, 0, "退出");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 0:
			// database.close();
			this.finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
    
}
