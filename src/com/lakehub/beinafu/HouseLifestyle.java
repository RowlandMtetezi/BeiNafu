package com.lakehub.beinafu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lakehub.beinafu.data.DBHandler;
import com.lakehub.beinafu.data.Items;

public class HouseLifestyle extends Activity {

	static final String ITEM_ID = "item_id";
	static final String ITEM_NAME = "item_name";
	static final String ITEM_SELLER = "item_seller";
	static final String ITEM_SELLER_CONTACT = "item_seller_location";
	static final String ITEM_PRICE = "item_price";
	static final String ITEM_STATUS = "item_status";
	static final String ITEM_CATEGORY = "item_category";
	static final String ITEM_LOCATION = "item_location"; 
	
	//private static final String TAG_ITEMID = "item_id";
	ListView list;
    LazyAdapter adapter;
    
    @Override
	public void onResume() {
    	super.onResume();
		setContentView(R.layout.activity_house_lifestyle);
		
		DBHandler db = new DBHandler(this);
		ArrayList<HashMap<String, String>> lifestyleItems = new ArrayList<HashMap<String, String>>();
		
		// Reading all issues
        Log.d("Reading: ", "Reading all items..");
        List<Items> l_Items = db.getAllItems(); 
        
     // looping through all issue nodes <issue>
        for (Items it : l_Items) {
        	// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			// adding each child node to HashMap key => value
			map.put(ITEM_ID,String.valueOf(it.getItemID()));
			map.put(ITEM_NAME, it.getItemName());
			map.put(ITEM_SELLER, it.getItemSeller());
			map.put(ITEM_SELLER_CONTACT,it.getItemSellerContact());		
			map.put(ITEM_PRICE,it.getItemPrice());
			map.put(ITEM_STATUS, it.getStatus());
			map.put(ITEM_CATEGORY, it.getItemCategory());
			map.put(ITEM_LOCATION, it.getItemLocation());
			lifestyleItems.add(map);
        }    
        
        list = (ListView) findViewById(R.id.LifestyleView);
		adapter = new LazyAdapter(this, lifestyleItems,"house_lifestyle");        
        list.setAdapter(adapter);
        
      //Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
			}
        	
        });
    }
    
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_house_lifestyle);
	}*/

}
