package com.example.taxcalculator;

import java.text.NumberFormat;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
public class MainActivity extends Activity 
implements OnEditorActionListener, OnClickListener{
	
	// define variables for the widgets
    private EditText PriceEditText;
    private TextView PriceTextView;   
    private EditText TaxEditText;
    private TextView TaxTextView;  
    private EditText TotalEditText;
    private TextView TotalTextView; 


    private Button   CalculateButton;
    private Button   ClearButton;
    
    // define the SharedPreferences object
    private SharedPreferences savedValues;
    
    // define instance variables that should be saved
    private String PriceString = "";
    private String TaxString = "";
    private String TotalString = "";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
        // get references to the widgets
        PriceEditText = (EditText) findViewById(R.id.PriceEdit);
        PriceTextView = (TextView) findViewById(R.id.textView1);
        TaxEditText = (EditText) findViewById(R.id.taxEdit);
        TaxTextView = (TextView) findViewById(R.id.textView2);
        TotalEditText = (EditText) findViewById(R.id.totalEdit);
        TotalTextView = (TextView) findViewById(R.id.textView3);
        CalculateButton = (Button) findViewById(R.id.calculateButton);
        ClearButton = (Button) findViewById(R.id.clearButton);

        // set the listeners
        PriceEditText.setOnEditorActionListener(this);
        TaxEditText.setOnEditorActionListener(this);
        TotalEditText.setOnEditorActionListener(this);
        ClearButton.setOnClickListener(this);
        CalculateButton.setOnClickListener(this);
        
        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);   
		
	}
	
    @Override
    public void onPause() {
        // save the instance variables       
        Editor editor = savedValues.edit();        
        editor.putString("PriceString", PriceString);
        editor.putString("TaxString", TaxString);
        editor.putString("TotalString", TotalString);

        
        editor.commit();        

        super.onPause();      
    }
    
    @Override
    public void onResume() {
        super.onResume();
        
        // get the instance variables
        PriceString = savedValues.getString("Pricestring", "");
        TaxString = savedValues.getString("TaxString", "");
        TotalString = savedValues.getString("TotalString", "");
        
       

        // set the Loan amount on its widget
        PriceEditText.setText(PriceString);
        TaxEditText.setText(TaxString);

        
        // calculate and display
        calculateAndDisplay();
    }
    public void calculateAndDisplay() {        

        // get the Loan amount
        PriceString = PriceEditText.getText().toString();
        float LoanAmount; 
        if (PriceString.equals("")) {
            LoanAmount = 0;
        }
        else {
            LoanAmount = Float.parseFloat(PriceString);
        }
        
     // get the Loan amount
        TaxString = TaxEditText.getText().toString();
        float APR; 
        if (TaxString.equals("")) {
            APR = 0;
        }
        else {
            APR = Float.parseFloat(TaxString);
        }
        
        
        
        
        
        // calculate tip and total 
        float r = APR/100/12;
        float s = APR/100/12 +1;
        double t = APR/100/12;
        double u = (double) s;
        double v = APR * 12;
        double w = Math.pow(u, v)-1;
        double x = t/w;
        float y = (float) x;
        
        
        
        float total= (LoanAmount * APR/100) + LoanAmount;

        
        // display the other results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        TotalEditText.setText(currency.format(total));
  

      
    }
    
    


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
    		actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            calculateAndDisplay();
        }        
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.clearButton:
        	PriceEditText.setText("");
        	TaxEditText.setText("");
        	TotalEditText.setText("");
            break;

        case R.id.calculateButton:
            calculateAndDisplay();
            break;
        }
    }
	
}