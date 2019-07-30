package org.suda.app.courseschedule;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	MyDBHelper dbhelper;
	GridView gv;
	GridAdapter ga;
	ArrayList<TextView> courselist;
	SQLiteDatabase db;
	int index = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbhelper = new MyDBHelper(this, "myschedule.db3", 1);
		gv = (GridView) findViewById(R.id.gvSchedule);
		// registerForContextMenu(gv);
		db = dbhelper.getReadableDatabase();
		ga = new GridAdapter(this);
		gv.setAdapter(ga);
		gv.setOnItemClickListener(new CellListener(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class CellListener implements OnItemClickListener {
		Context context;

		public CellListener(Context x) {
			context = x;
		}

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			if (arg2 == 0 || arg2 == 1 || arg2 == 2 || arg2 == 3 || arg2 == 4
					|| arg2 == 5 || arg2 == 6 || arg2 == 12 || arg2 == 18
					|| arg2 == 24 || arg2 == 30 || arg2 == 36 || arg2 == 42
					|| arg2 == 48) {
				return;
			} else {
				index = arg2;
				courselist = ga.getList();
				GetDialogReturnListener listener = new GetDialogReturnListener();
				CourseDialog dia = new CourseDialog(context, listener);
				dia.setCouseName(courselist.get(arg2).getText().toString()
						.trim());
				dia.setTitle("课程信息");
				dia.show();
			}
		}

		class GetDialogReturnListener implements CourseInterface {
			public void saveClick(String name) {
				db = dbhelper.getReadableDatabase();
				courselist.get(index).setText(name);
				if(index == 7 || index == 13 || index == 19 || index == 25 
						|| index == 31 || index == 37 || index == 43 
						|| index == 49 ){
					String sql = "select * from schedule where day ='周一'";
					Cursor cur = db.rawQuery(sql, null);
					try {
						if (cur.moveToFirst() ) {
							String update="";
							switch (index) {
							case 7:
								update = "update schedule set one = '"+name
								+"'where day = '周一';";
								break;
							case 13:
								update = "update schedule set two = '"+name
								+"'where day = '周一';";
								break;
							case 19:
								update = "update schedule set three = '"+name
								+"'where day = '周一';";
								break;
							case 25:
								update = "update schedule set four = '"+name
								+"'where day = '周一';";
								break;
							case 31:
								update = "update schedule set five = '"+name
								+"'where day = '周一';";
								break;
							case 37:
								update = "update schedule set six = '"+name
								+"'where day = '周一';";
								break;
							case 43:
								update = "update schedule set seven = '"+name
								+"'where day = '周一';";
								break;
							case 49:
								update = "update schedule set eight = '"+name
								+"'where day = '周一';";
								break;
							}
							db.execSQL(update);
						}else {
							String insert = "";
							switch (index) {
							case 7:
								insert = "insert into schedule(one,day)values('"
								+name+"','周一');";
								break;
							case 13:
								insert = "insert into schedule(two,day)values('"
								+name+"','周一');";
								break;
							case 19:
								insert = "insert into schedule(three,day)values('"
								+name+"','周一');";
								break;
							case 25:
								insert = "insert into schedule(four,day)values('"
								+name+"','周一');";
								break;
							case 31:
								insert = "insert into schedule(five,day)values('"
								+name+"','周一');";
								break;
							case 37:
								insert = "insert into schedule(six,day)values('"
								+name+"','周一');";
								break;
							case 43:
								insert = "insert into schedule(seven,day)values('"
								+name+"','周一');";
								break;
							case 49:
								insert = "insert into schedule(eight,day)values('"
								+name+"','周一');";
								break;
							}
							db.execSQL(insert);
						}
					} catch (SQLiteException x) {
						Log.i("COURSEACTIVITY", x.getMessage());
					}
				}
				if(index == 8 || index == 14 || index == 20 || index == 26 
						|| index == 32 || index == 38 || index == 44 
						|| index == 50 ){
					// 周二课程
					String sql = "select * from schedule where day='周二';";
					Cursor cur = db.rawQuery(sql, null);
					try {
						if (cur.moveToFirst() ) {
							String update="";
							switch (index) {
							case 8:
								update = "update schedule set one = '"+name
								+"'where day = '周二';";
								break;
							case 14:
								update = "update schedule set two = '"+name
								+"'where day = '周二';";
								break;
							case 20:
								update = "update schedule set three = '"+name
								+"'where day = '周二';";
								break;
							case 26:
								update = "update schedule set four = '"+name
								+"'where day = '周二';";
								break;
							case 32:
								update = "update schedule set five = '"+name
								+"'where day = '周二';";
								break;
							case 38:
								update = "update schedule set six = '"+name
								+"'where day = '周二';";
								break;
							case 44:
								update = "update schedule set seven = '"+name
								+"'where day = '周二';";
								break;
							case 50:
								update = "update schedule set eight = '"+name
								+"'where day = '周二';";
								break;
							}
							db.execSQL(update);
						}else {
							//insert
							String insert = "";
							switch (index) {
							case 8:
								insert = "insert into schedule(one,day)values('"
								+name+"','周二');";
								break;
							case 14:
								insert = "insert into schedule(two,day)values('"
								+name+"','周二');";
								break;
							case 20:
								insert = "insert into schedule(three,day)values('"
								+name+"','周二');";
								break;
							case 26:
								insert = "insert into schedule(four,day)values('"
								+name+"','周二');";
								break;
							case 32:
								insert = "insert into schedule(five,day)values('"
								+name+"','周二');";
								break;
							case 38:
								insert = "insert into schedule(six,day)values('"
								+name+"','周二');";
								break;
							case 44:
								insert = "insert into schedule(seven,day)values('"
								+name+"','周二');";
								break;
							case 50:
								insert = "insert into schedule(eight,day)values('"
								+name+"','周二');";
								break;
							}
							db.execSQL(insert);
						}
					} catch (SQLiteException x) {
						Log.i("COURSEACTIVITY", x.getMessage());
					}
				}
				if(index == 9 || index == 15 || index == 21 || index == 27 
						|| index == 33 || index == 39 || index == 45 
						|| index == 51 ){
					// 周三课程
					String sql = "select * from schedule where day='周三';";
					Cursor cur = db.rawQuery(sql, null);
					try {
						if (cur.moveToFirst() ) {
							//update
							String update="";
							switch (index) {
							case 9:
								update = "update schedule set one = '"+name
								+"'where day = '周三';";
								break;
							case 15:
								update = "update schedule set two = '"+name
								+"'where day = '周三';";
								break;
							case 21:
								update = "update schedule set three = '"+name
								+"'where day = '周三';";
								break;
							case 27:
								update = "update schedule set four = '"+name
								+"'where day = '周三';";
								break;
							case 33:
								update = "update schedule set five = '"+name
								+"'where day = '周三';";
								break;
							case 39:
								update = "update schedule set six = '"+name
								+"'where day = '周三';";
								break;
							case 45:
								update = "update schedule set seven = '"+name
								+"'where day = '周三';";
								break;
							case 51:
								update = "update schedule set eight = '"+name
								+"'where day = '周三';";
								break;
							}
							db.execSQL(update);
						}else {
							//insert
							String insert = "";
							switch (index) {
							case 9:
								insert = "insert into schedule(one,day)values('"
								+name+"','周三');";
								break;
							case 15:
								insert = "insert into schedule(two,day)values('"
								+name+"','周三');";
								break;
							case 21:
								insert = "insert into schedule(three,day)values('"
								+name+"','周三');";
								break;
							case 27:
								insert = "insert into schedule(four,day)values('"
								+name+"','周三');";
								break;
							case 33:
								insert = "insert into schedule(five,day)values('"
								+name+"','周三');";
								break;
							case 39:
								insert = "insert into schedule(six,day)values('"
								+name+"','周三');";
								break;
							case 45:
								insert = "insert into schedule(seven,day)values('"
								+name+"','周三');";
								break;
							case 51:
								insert = "insert into schedule(eight,day)values('"
								+name+"','周三');";
								break;
							}
							db.execSQL(insert);
						}
					} catch (SQLiteException x) {
						Log.i("COURSEACTIVITY", x.getMessage());
					}
				}
				if(index == 10 || index == 16 || index == 22 || index == 28 
						|| index == 34 || index == 40 || index == 46 
						|| index == 52 ){
					// 周四课程
					String sql = "select * from schedule where day='周四';";
					Cursor cur = db.rawQuery(sql, null);
					try {
						if (cur.moveToFirst() ) {
							// update
							String update="";
							switch (index) {
							case 10:
								update = "update schedule set one = '"+name
								+"'where day = '周四';";
								break;
							case 16:
								update = "update schedule set two = '"+name
								+"'where day = '周四';";
								break;
							case 22:
								update = "update schedule set three = '"+name
								+"'where day = '周四';";
								break;
							case 28:
								update = "update schedule set four = '"+name
								+"'where day = '周四';";
								break;
							case 34:
								update = "update schedule set five = '"+name
								+"'where day = '周四';";
								break;
							case 40:
								update = "update schedule set six = '"+name
								+"'where day = '周四';";
								break;
							case 46:
								update = "update schedule set seven = '"+name
								+"'where day = '周四';";
								break;
							case 52:
								update = "update schedule set eight = '"+name
								+"'where day = '周四';";
								break;
							}
							db.execSQL(update);
						}else {
							String insert = "";
							switch (index) {
							case 10:
								insert = "insert into schedule(one,day)values('"
								+name+"','周四');";
								break;
							case 16:
								insert = "insert into schedule(two,day)values('"
								+name+"','周四');";
								break;
							case 22:
								insert = "insert into schedule(three,day)values('"
								+name+"','周四');";
								break;
							case 28:
								insert = "insert into schedule(four,day)values('"
								+name+"','周四');";
								break;
							case 34:
								insert = "insert into schedule(five,day)values('"
								+name+"','周四');";
								break;
							case 40:
								insert = "insert into schedule(six,day)values('"
								+name+"','周四');";
								break;
							case 46:
								insert = "insert into schedule(seven,day)values('"
								+name+"','周四');";
								break;
							case 52:
								insert = "insert into schedule(eight,day)values('"
								+name+"','周四');";
								break;
						}
							db.execSQL(insert);
						}
					} catch (SQLiteException x) {
						Log.i("MAINACTIVITY", x.getMessage());
					}
				}
				if(index == 11 || index == 17 || index == 23 || index == 29 
						|| index == 35 || index == 41 || index == 47 
						|| index == 53 ){
					String sql = "select * from schedule where day ='周五'";
					Cursor cur = db.rawQuery(sql, null);
					try {
						if (cur.moveToFirst() ) {
							String update="";
							switch (index) {
							case 11:
								update = "update schedule set one = '"+name
								+"'where day = '周五';";
								break;
							case 17:
								update = "update schedule set two = '"+name
								+"'where day = '周五';";
								break;
							case 23:
								update = "update schedule set three = '"+name
								+"'where day = '周五';";
								break;
							case 29:
								update = "update schedule set four = '"+name
								+"'where day = '周五';";
								break;
							case 35:
								update = "update schedule set five = '"+name
								+"'where day = '周五';";
								break;
							case 41:
								update = "update schedule set six = '"+name
								+"'where day = '周五';";
								break;
							case 47:
								update = "update schedule set seven = '"+name
								+"'where day = '周五';";
								break;
							case 53:
								update = "update schedule set eight = '"+name
								+"'where day = '周五';";
								break;
							}
							db.execSQL(update);
						}else {
							String insert = "";
							switch (index) {
							case 11:
								insert = "insert into schedule(one,day)values('"
								+name+"','周五');";
								break;
							case 17:
								insert = "insert into schedule(two,day)values('"
								+name+"','周五');";
								break;
							case 23:
								insert = "insert into schedule(three,day)values('"
								+name+"','周五');";
								break;
							case 29:
								insert = "insert into schedule(four,day)values('"
								+name+"','周五');";
								break;
							case 35:
								insert = "insert into schedule(five,day)values('"
								+name+"','周五');";
								break;
							case 41:
								insert = "insert into schedule(six,day)values('"
								+name+"','周五');";
								break;
							case 47:
								insert = "insert into schedule(seven,day)values('"
								+name+"','周五');";
								break;
							case 53:
								insert = "insert into schedule(eight,day)values('"
								+name+"','周五');";
								break;
							}
							db.execSQL(insert);
						}
					}catch (SQLiteException  x) {
						// TODO: handle exception
						Log.i("COURSEACTIVITY", x.getMessage());
					}
				}
						
				db.close();
			}

			@Override
			public void cancelClick() {
				// TODO Auto-generated method stub

			}
		}
	}

}
