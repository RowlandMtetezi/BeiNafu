package com.lakehub.beinafu;

import java.util.Random;

import com.lakehub.beinafu.data.DBHandler;
import com.lakehub.beinafu.data.Items;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewItem extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_item);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		setContentView(R.layout.activity_create_item);
		
		//spinner element
		final Spinner spinner  = (Spinner) findViewById(R.id.spinnerCategory);
		final Spinner spinnerLocation = (Spinner) findViewById(R.id.spinnerLocation);
		Button saveItem = (Button) findViewById(R.id.item_create_save_button); 
		
		// database handler
        final DBHandler db = new DBHandler(this);
        
		final EditText txtItemName = (EditText) findViewById(R.id.item_name);  
	    final EditText txtItemSeller = (EditText)  findViewById(R.id.seller_name); 
	    final EditText txtItemSellerContact = (EditText) findViewById(R.id.seller_contact); 
	    final EditText txtItemPrice = (EditText) findViewById(R.id.item_price); 
	    
	    String[]items = {"---Choose Category---",getResources().getString(R.string.electronics),
			             getResources().getString(R.string.houselifestyle),
			             getResources().getString(R.string.mobilephones),
			             getResources().getString(R.string.mapretails),
			             getResources().getString(R.string.miscellaneous)};
	    String[] kisumuEstates = {"---Choose Estate---","Nyalenda","Nyamasaria","Tom Mboya","Milimani","Migosi",
	    						  "Manyatta","Kondele","Kisumu CBD","Airport Area","Mamboleo"};	    
	    // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, items);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, kisumuEstates);
        
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinnerLocation.setAdapter(locationAdapter);
        
        saveItem.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		int itemID = new Random().nextInt(500000 - 100000) + 100000;
        		String name = txtItemName.getText().toString();
        		String seller = txtItemSeller.getText().toString();
        		String sellerContact = txtItemSellerContact.getText().toString();
        		String status = "Not Sold";
        		String price = txtItemPrice.getText().toString();
        		//String category = String.valueOf(spinner.getSelectedItemPosition() + 1);
        		//String location = String.valueOf(spinnerLocation.getSelectedItemPosition() + 1);
        		
        		TextView sellerLocationString = (TextView)spinnerLocation.getSelectedView();
        		String sellerLocation = (String) sellerLocationString.getText();
        		TextView itemCategoryString = (TextView)spinner.getSelectedView();
        		String itemCategory = (String)itemCategoryString.getText();
        		
        		db.addItem(new Items(itemID,name,seller,sellerContact,price,status,itemCategory,sellerLocation));
        		Toast.makeText(getApplicationContext(),"New Item added sucessfully, thank you", Toast.LENGTH_LONG).show();
        	}
        });
	}
}
