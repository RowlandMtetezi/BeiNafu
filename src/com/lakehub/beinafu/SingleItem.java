package com.lakehub.beinafu;

import com.lakehub.beinafu.data.DBHandler;
import com.lakehub.beinafu.data.Items;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleItem extends Activity {
	private static final String TAG_ITEMID = "item_id";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item);

        final DBHandler db = new DBHandler(this);
        
        //getting intent data
        Intent in = getIntent();
        
        // Get XML values from previous intent
        final String itemID = in.getStringExtra(TAG_ITEMID);
        
        Items itm = db.getItem(Integer.parseInt(itemID));
        
        String itemName = itm.getItemName();
        String itemSeller = itm.getItemSeller();
        String itemSellerContact = itm.getItemSellerContact();
        String itemPrice = itm.getItemPrice();
        String itemCategory = itm.getItemCategory();
        String itemLocation = itm.getItemLocation();
        String itemStatus = itm.getStatus();
        
        // Displaying all values on the screen
        TextView lblItemId = (TextView) findViewById(R.id.view_item_id);
        TextView lblItemName = (TextView) findViewById(R.id.view_item_name);
        TextView lblItemSeller = (TextView) findViewById(R.id.view_item_seller);
        TextView lblItemSellerContact = (TextView) findViewById(R.id.view_item_seller_contact);
        TextView lblItemPrice = (TextView) findViewById(R.id.view_item_price);
        TextView lblItemCategory = (TextView) findViewById(R.id.view_item_category);
        TextView lblItemLocation = (TextView) findViewById(R.id.view_item_location);
        TextView lblItemStatus = (TextView) findViewById(R.id.view_item_status);
        
        lblItemId.setText("Item Number: " +itemID);
        lblItemName.setText("Item Name: "+itemName);
        lblItemSeller.setText("Item Seller: " +itemSeller);
        lblItemSellerContact.setText("Item Seller Contact: " +itemSellerContact);
        lblItemPrice.setText("Item Price (Kshs): " +itemPrice);
        lblItemStatus.setText("Item Status: " +itemStatus);
        lblItemCategory.setText("Item Category: " +itemCategory);
        lblItemLocation.setText("Item Location: " +itemLocation);
	}
}
