package com.Addressbook.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class otherPersonService implements IPersonService{
	private DatabaseHelper databaseHelper;
	private Context context;
	
	public otherPersonService(Context context) {
		this.context = context;
		databaseHelper = new DatabaseHelper(context);
	}

	@Override
	public void save(Person person) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", person.getName());
		values.put("age", person.getAge());
		database.insert("person", "name", values);
	}

	@Override
	public void update(Person person) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", person.getName());
		values.put("age", person.getAge());
		database.update("person", values, "personid=?", new String[]{
				String.valueOf(person.getId())
		});
	}

	@Override
	public Person find(Integer id) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		Cursor cursor = database.query("person", new String[] { "personid",
				"name", "age" }, "personid=?",
				new String[] { String.valueOf(id) }, null, null, null);
		if (cursor.moveToNext()) {//迭代记录集
			Person person = new Person();//实例化person
			person.setId(cursor.getInt(cursor.getColumnIndex("personid")));
			person.setName(cursor.getString(1));
			person.setAge(cursor.getInt(2));//将查到的字段，放入person
			return person;
		}
		cursor.close();//关闭游标
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		database.delete("person", "personid=?", new String[] {
				String.valueOf(id)
		});
	}

	@Override
	public List<Person> getScrollData(int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		List<Person> persons = new ArrayList<Person>();
		
		Cursor cursor = database.query("person", new String[] { "personid",
				"name", "age" }, null, null, null, null, "personid desc",
				firstResult + "," + maxResult);
		while (cursor.moveToNext()) {//迭代添加到persons
			Person person = new Person();
			person.setId(cursor.getInt(0));
			person.setName(cursor.getString(1));
			person.setAge(cursor.getInt(2));
			persons.add(person);
		}
		cursor.close();
		return persons;
	}

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		Cursor cursor = database.query("person", new String[] { "count(*)" },
				null, null, null, null, null);
		if (cursor.moveToNext()) {
			return cursor.getLong(0);
		}
		return 0;
	}

}
