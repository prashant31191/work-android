package com.Addressbook.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonService implements IPersonService{
	private DatabaseHelper databaseHelper;
	private Context context;
	
	public PersonService(Context context) {
		this.context = context;
		databaseHelper = new DatabaseHelper(context);
	}

	@Override
	public void save(Person person) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.execSQL("insert into person(name, age) values(?,?)", new String[] {
				person.getName(), person.getAge().toString()
		});
	}

	@Override
	public void update(Person person) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.execSQL("update person set name=?,age=? where personid=?", new Object[] {
				person.getName(), person.getAge(), person.getId()
		});
	}

	@Override
	public Person find(Integer id) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select personid,name,age from person where personid=?", new String[] {
				String.valueOf(id)
		});
		if (cursor.moveToNext()) {//迭代记录集
			Person person = new Person();//实例化person
			person.setId(cursor.getInt(cursor.getColumnIndex("personid")));
			person.setName(cursor.getString(1));
			person.setAge(cursor.getInt(2));//将查到的字段，放入person
			return person;
		}
		cursor.close();//游标关闭
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.execSQL("delete from person where personid=?", new String[] {
				id.toString()
		});
	}

	@Override
	public List<Person> getScrollData(int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select personid,name,age from person limit ?,?", new String[] {
				String.valueOf(firstResult),//firstResult开始索引 
				String.valueOf(maxResult)//maxResult每页获取的记录数
		});
		while (cursor.moveToNext()) {
			Person person = new Person();
			person.setId(cursor.getInt(cursor.getColumnIndex("personid")));
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
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from person", null);//没有占位符参数的话，直接用null
		cursor.moveToFirst();
		long count = cursor.getLong(0);
		cursor.close();
		return count;
	}
}
