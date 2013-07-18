package com.lakehub.beinafu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Main extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView menuList = (ListView) findViewById(R.id.ListView_Menu);
        String[] items = {getResources().getString(R.string.electronics),
        		          getResources().getString(R.string.houselifestyle),
        		          getResources().getString(R.string.mobilephones),
        		          getResources().getString(R.string.mapretails),
        		          getResources().getString(R.string.miscellaneous)};
         
        ArrayAdapter<String> adpt = new ArrayAdapter<String>(this, R.layout.menu_item,items);
        menuList.setAdapter(adpt);
        
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View itemClicked,int position, long id){
        		TextView textView = (TextView) itemClicked;
        		String strText = textView.getText().toString();
        		if (strText.equalsIgnoreCase(getResources().getString(R.string.electronics))){
        			//Launch the electronics UI
        			startActivity(new Intent(Main.this,ElectronicsTechnology.class));
        		}
        		else if(strText.equalsIgnoreCase(getResources().getString(R.string.houselifestyle))){
        			//Launch the House UI
        			startActivity(new Intent(Main.this,HouseLifestyle.class));
        		}
        		else if(strText.equalsIgnoreCase(getResources().getString(R.string.mobilephones))){
        			//Launch the House UI
        			startActivity(new Intent(Main.this,MobilePhones.class));
        		}
        		else if(strText.equalsIgnoreCase(getResources().getString(R.string.mapretails))){
        			//Launch the House UI
        			startActivity(new Intent(Main.this,MapRetail.class));
        		}
        		else if(strText.equals(getResources().getString(R.string.miscellaneous))){
        			startActivity(new Intent(Main.this,NewItem.class));
        		}
        	}
		}); 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
