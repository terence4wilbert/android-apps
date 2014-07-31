package com.example.sqldatabase;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	TextView idView;
	EditText edittext1;
	EditText quantityBox;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
	
	public void data (View v) {
		MyDbHandler dbHandler = new MyDbHandler(this, null, null, 1);
		EditText edittext1 =(EditText)findViewById(R.id.firstNameEdit);
		EditText edittext2 =(EditText)findViewById(R.id.lastNameEdit);
		EditText edittext3 =(EditText)findViewById(R.id.emailEdit);
		String fname = edittext1.getText().toString();
		String lname = edittext2.getText().toString();
		String email = edittext3.getText().toString();
		dbHandler.addData(fname, lname, email);
			Toast.makeText(getApplicationContext(), "Data Added", Toast.LENGTH_SHORT).show();
			
			
			edittext1.setText("");
			edittext2.setText("");
			edittext3.setText("");
	}
	
	public void showTable(View v){
		Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
		startActivity(intent);
	}
	
	public void deleteNow(View v){
		MyDbHandler dbHandler = new MyDbHandler(this, null, null, 1);
		dbHandler.deleteAll(null);
			Toast.makeText(getApplicationContext(), "Data Deleted", Toast.LENGTH_SHORT).show();
		
	}
	public void removeProduct (View view) {
	    MyDbHandler dbHandler = new MyDbHandler(this, null, null, 1);
	
	     boolean result = dbHandler.deleteAll(
	     edittext1.getText().toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
}
}
