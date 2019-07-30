package org.suda.app.courseschedule;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
	public MyDBHelper(Context context, String name, int version) {
		super(context, name, null, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CreateTableSql="create table schedule ("
				+ "sid integer PRIMARY KEY autoincrement, " 
		        + " day  text, " 
		        +" one text, "
		        +" two text, "
		        +"three text,"
		        + "four text,"+"five text,"+"six text,"+"seven text,"+"eight text);";
		db.execSQL(CreateTableSql);
		CreateTableSql="create table course(id integer PRIMARY KEY autoincrement,coursename text,teacher text,coursetime text);";
		db.execSQL(CreateTableSql);
	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}
}
