package com.example.list;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	public void addData(View v){
	       MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
	       EditText edittext1=(EditText )findViewById(R.id.firstEditText);
	       EditText edittext2=(EditText )findViewById(R.id.secondEditText);
	       EditText edittext3=(EditText )findViewById(R.id.emailEditText);
	       String fname=edittext1.getText().toString();
	       String lname=edittext2.getText().toString();
	       String email=edittext3.getText().toString();
	       dbHandler.addData(fname, lname, email);
	       		Toast.makeText(getApplicationContext(), "Data Added", Toast.LENGTH_SHORT).show();
	       		edittext1.setText("");
				edittext2.setText("");
				edittext3.setText("");
	}
	public void showTable(View v){
			Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
			startActivity(intent);
				Toast.makeText(getApplicationContext(), "Showing Record", Toast.LENGTH_SHORT).show();
			
		}
		public void deleteNow(View v){
			MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
			dbHandler.deleteOne();
				Toast.makeText(getApplicationContext(), "Data Deleted", Toast.LENGTH_SHORT).show();
			
		}
		public void reset(View v){
			MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
			dbHandler.delete();
				Toast.makeText(getApplicationContext(), "Reset Record", Toast.LENGTH_SHORT).show();
			
		}
	
}
