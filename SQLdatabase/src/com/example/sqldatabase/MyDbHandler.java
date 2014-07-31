package com.example.sqldatabase;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDbHandler extends SQLiteOpenHelper  {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "address1.db";
	public static final String TABLE_PRODUCTS = "Person";

	public static final String COLUMN_ID = "fname";
	public static final String COLUMN_PRODUCTNAME = "lname";
	public static final String COLUMN_QUANTITY = "email";
	
	
	public MyDbHandler(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION );
		
	}

	public void onCreate(SQLiteDatabase db) {
		String create = "CREATE TABLE " +
	             TABLE_PRODUCTS + "("
	             + COLUMN_ID + " TEXT," + COLUMN_PRODUCTNAME 
	             + " TEXT," + COLUMN_QUANTITY + " TEXT" + ")";
	      db.execSQL(create);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void addData(String fname, String lname, String email){ 		//on click will call this method for add data
		SQLiteDatabase db = this.getWritableDatabase();
		//db.execSQL("INSERT INTO Person VALUES('"+fname+"', '"+lname+"', '"+email+"');");  //reason for double quotes: if specify in single quotes know as character, also displays the quotation marks
		db.execSQL("INSERT INTO TABLE_PRODUCTS VALUES(\""+COLUMN_ID+"\", \""+COLUMN_PRODUCTNAME+"\", \""+COLUMN_QUANTITY+"\");");  //reason for double quotes: if specify in single quotes know as character, tells to also displays the quotaion marks
			
		db.close();
			
	}
	
	public SQLiteDatabase showTable(){							//on click will call showTable to show the table 
		SQLiteDatabase db = this.getWritableDatabase();				
		return db;
		
	}
	
	public boolean deleteAll(String lastname){
		boolean result = false;
		
		String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + " =  \"" + lastname + "\"";

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		
		DisplayActivity active  = new DisplayActivity();
		
		if (cursor.moveToFirst()) {
			active.setID(Integer.parseInt(cursor.getString(0)));
			db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?",
		            new String[] { String.valueOf(active .getID()) });
			cursor.close();
			result = true;
		}
		db.close();
		return result;
	}
	

}

