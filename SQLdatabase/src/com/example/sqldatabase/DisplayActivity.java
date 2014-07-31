package com.example.sqldatabase;


import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		setContentView(R.layout.activity_display);
		 MyDbHandler dbHandler = new MyDbHandler(this, null, null, 1);
		 SQLiteDatabase db = dbHandler.showTable();
		 Cursor c=db.rawQuery("SELECT * from Person", null);
		 int count= c.getCount();
		 c.moveToFirst();
		 //TableLayout tableLayout = new TableLayout(getApplicationContext());
		 TableLayout tableLayout = new TableLayout(this);
		 tableLayout.setVerticalScrollBarEnabled(true);
		 TableRow tableRow;
		 TextView textView,textView1,textView2,textView3,textView4,textView5;
		 tableRow = new TableRow(getApplicationContext());
		 textView=new TextView(getApplicationContext());
		 textView.setText("Firstname");
		 textView.setTextColor(Color.RED);
	     textView.setTypeface(null, Typeface.BOLD);
		 textView.setPadding(20, 20, 20, 20);
		 tableRow.addView(textView);
		 textView4=new TextView(getApplicationContext());
		 textView4.setText("LastName");
		 textView4.setTextColor(Color.RED);
		 textView4.setTypeface(null, Typeface.BOLD);
		 textView4.setPadding(20, 20, 20, 20);
		 tableRow.addView(textView4);
		 textView5=new TextView(getApplicationContext());
		 textView5.setText("Email");
		 textView5.setTextColor(Color.RED);
		 textView5.setTypeface(null, Typeface.BOLD);
		 textView5.setPadding(20, 20, 20, 20);
		 tableRow.addView(textView5);
		 tableLayout.addView(tableRow);
		 for (int j = 0; j < count; j++){
			tableRow = new TableRow(getApplicationContext());
			textView1 = new TextView(getApplicationContext());
			textView1.setText(c.getString(c.getColumnIndex("fname")));
			textView2 = new TextView(getApplicationContext());
			textView2.setText(c.getString(c.getColumnIndex("lname")));
			textView3 = new TextView(getApplicationContext());
			textView3.setText(c.getString(c.getColumnIndex("email")));
			textView1.setPadding(20, 20, 20, 20);
			textView2.setPadding(20, 20, 20, 20);
			textView3.setPadding(20, 20, 20, 20);
			tableRow.addView(textView1);
			tableRow.addView(textView2);
			tableRow.addView(textView3);
			tableLayout.addView(tableRow);
			c.moveToNext() ;
		}
		setContentView(tableLayout);
		db.close();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void setID(int parseInt) {
		// TODO Auto-generated method stub
		
	}

	public char[] getID() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
