package org.suda.app.courseschedule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.InputEvent;
import android.view.View;
import android.widget.*;

public class CourseDialog extends Dialog implements
		android.view.View.OnClickListener {
	private Button cancelButton;
	private Button saveButton;
	private EditText course;
	private EditText teacher;
	private EditText time;
	private String coursename;
	private CourseInterface interfaces;
	private MyDBHelper dbhelper;
	private SQLiteDatabase db;
	Context c;

	public CourseDialog(Context context, CourseInterface e) {
		super(context);
		c = context;
		interfaces = e;
	}

	public void setCouseName(String name) {
		coursename = name;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coursedialog);
		saveButton = (Button) findViewById(R.id.saveButton);
		cancelButton = (Button) findViewById(R.id.cancelButton);
		saveButton.setOnClickListener(this);
		cancelButton.setOnClickListener(this);
		course = (EditText) findViewById(R.id.name);
		teacher = (EditText) findViewById(R.id.teacher);
		time = (EditText) findViewById(R.id.location_edittext);
		dbhelper = new MyDBHelper(c, "myschedule.db3", 1);
		db = dbhelper.getReadableDatabase();
		if (!coursename.equals("")) {
			course.setText(coursename);
			String sql = "select * from course where coursename='" + coursename
					+ "'";

			Cursor cur = db.rawQuery(sql, null);
			if (cur.moveToFirst()) {
				teacher.setText(cur.getString(cur.getColumnIndex("teacher")));
				time.setText(cur.getString(cur.getColumnIndex("coursetime")));
				course.setText(coursename);
			}
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cancelButton:
			db.close();
			cancel();
			break;
		case R.id.saveButton:
			interfaces.saveClick(course.getText().toString().trim());
			saveCourse();
			db.close();
			cancel();
			break;
		}
	}

	public void saveCourse() {
		String new_teacher = teacher.getText().toString().trim();
		String new_time = time.getText().toString().trim();
		String new_course = course.getText().toString().trim();
		String sql = "select * from course where coursename ='" + new_course
				+ "';";
		Cursor cursor = db.rawQuery(sql, null);
		if (!cursor.moveToFirst()) {
			String sql2 = "insert into course (coursename,teacher,coursetime) values ('"
					+ new_course
					+ "','"
					+ new_teacher
					+ "','"
					+ new_time
					+ "');";
			db.execSQL(sql2);
		} else {
			if (new_teacher.equals("") && new_time.equals("")) {
				return;
			} else {
				String updatesql = "update course set teacher='" + new_teacher
						+ "',coursetime='" + new_time + "' where coursename='"
						+ new_course + "'";
				db.execSQL(updatesql);
			}
		}
	}
}
