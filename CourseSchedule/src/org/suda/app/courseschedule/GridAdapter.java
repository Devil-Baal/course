package org.suda.app.courseschedule;

import java.util.ArrayList;

import android.app.*;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class GridAdapter extends BaseAdapter {
	private Context viewContext;
	Activity activity;
	private ArrayList<TextView> list = new ArrayList<TextView>();
	private SQLiteDatabase db;
	private MyDBHelper dbhelper;

	public GridAdapter(Context context) {
		dbhelper = new MyDBHelper(context, "myschedule.db3", 1);
		viewContext = context;
		initSchedule();
	}

	public ArrayList<TextView> getList() {
		return list;
	}
	private void initSchedule() {
		initTitle();
		initCourse();
	}
	private void initTitle() {
		activity = (Activity) viewContext;
		for (int k = 0; k < 54; k++) {
			TextView text = new TextView(viewContext);
			text.setBackgroundColor(Color.rgb(228, 247, 174));
			text.setTextColor(Color.BLACK);
			text.setTextSize(10);
			text.setHeight(100);
			text.setGravity(Gravity.CENTER);
			text.setPadding(1, 1, 1, 1);
			if (k > 0 && k < 6 || k % 6 == 0) {
				text.setTextColor(Color.rgb(214, 130, 3));
				text.setTextSize(10);
			}
			list.add(text);
		}
		list.get(1).setText("周一");
		list.get(2).setText("周二");
		list.get(3).setText("周三");
		list.get(4).setText("周四");
		list.get(5).setText("周五");
		
		list.get(6).setText("第一节");
		list.get(12).setText("第二节");
		list.get(18).setText("第三节");
		list.get(24).setText("第四节");
		list.get(30).setText("第五节");
		list.get(36).setText("第六节");
		list.get(42).setText("第七节");
		list.get(48).setText("第八节");
	}
	private void initCourse() {
		db = dbhelper.getReadableDatabase();
		String sql = "select * from schedule;";
		Cursor cur = db.rawQuery(sql, null);
		if (cur.moveToFirst()) {
			int dayIdx = cur.getColumnIndex("day");
			int oneIdx = cur.getColumnIndex("one");
			int twoIdx = cur.getColumnIndex("two");
			int threeIdx = cur.getColumnIndex("three");
			int fourIdx = cur.getColumnIndex("four");
			int fiveIdx = cur.getColumnIndex("five");
			int sixIdx = cur.getColumnIndex("six");
			int sevenIdx = cur.getColumnIndex("seven");
			int eightIdx = cur.getColumnIndex("eight");
			String date = "";
			String first = "";
			String second = "";
			String third = "";
			String forth = "";
			String fifth = "";
			String sixth = "";
			String seventh = "";
			String eighth = "";
			do {
				date = cur.getString(dayIdx);
				first = cur.getString(oneIdx);
				second = cur.getString(twoIdx);
				third = cur.getString(threeIdx);
				forth = cur.getString(fourIdx);
				fifth = cur.getString(fiveIdx);
				sixth = cur.getString(sixIdx);
				seventh = cur.getString(sevenIdx);
				eighth = cur.getString(eightIdx);
				Schedule s = new Schedule();
				s.setDate(date);
				s.setOne(first);
				s.setTwo(second);
				s.setThree(third);
				s.setFour(forth);
				s.setFive(fifth);
				s.setSix(sixth);
				s.setSeven(seventh);
				s.setEight(eighth);
				DayCourseName(s, date);
			} while (cur.moveToNext());
		}
		db.close();
	}
	private void DayCourseName(Schedule s, String date) {
		if (date.equals("周一")) {
			list.get(7).setText(s.getOne());
			list.get(13).setText(s.getTwo());
			list.get(19).setText(s.getThree());
			list.get(25).setText(s.getFour());
			list.get(31).setText(s.getFive());
			list.get(37).setText(s.getSix());
			list.get(43).setText(s.getSeven());
			list.get(49).setText(s.getEight());
		}
		if (date.equals("周二")) {
			list.get(8).setText(s.getOne());
			list.get(14).setText(s.getTwo());
			list.get(20).setText(s.getThree());
			list.get(26).setText(s.getFour());
			list.get(32).setText(s.getFive());
			list.get(38).setText(s.getSix());
			list.get(44).setText(s.getSeven());
			list.get(50).setText(s.getEight());
		}
		if (date.equals("周三")) {
			list.get(9).setText(s.getOne());
			list.get(15).setText(s.getTwo());
			list.get(21).setText(s.getThree());
			list.get(27).setText(s.getFour());
			list.get(33).setText(s.getFive());
			list.get(39).setText(s.getSix());
			list.get(45).setText(s.getSeven());
			list.get(51).setText(s.getEight());
		}
		if (date.equals("周四")) {
			list.get(10).setText(s.getOne());
			list.get(16).setText(s.getTwo());
			list.get(22).setText(s.getThree());
			list.get(28).setText(s.getFour());
			list.get(34).setText(s.getFive());
			list.get(40).setText(s.getSix());
			list.get(46).setText(s.getSeven());
			list.get(52).setText(s.getEight());
		}
		if (date.equals("周五")) {
			list.get(11).setText(s.getOne());
			list.get(17).setText(s.getTwo());
			list.get(23).setText(s.getThree());
			list.get(29).setText(s.getFour());
			list.get(35).setText(s.getFive());
			list.get(41).setText(s.getSix());
			list.get(47).setText(s.getSeven());
			list.get(53).setText(s.getEight());
		}
		
	}
	public int getCount() {
		return list.size();
	}
	public Object getItem(int position) {
		return position;
	}
	public long getItemId(int position) {
		return position;
	}
	public View getView(int position, View view, ViewGroup parent) {
		TextView textview;
		if (view == null) {
			textview = list.get(position);
		} else {
			textview = (TextView) view;
		}
		return textview;
	}
}
