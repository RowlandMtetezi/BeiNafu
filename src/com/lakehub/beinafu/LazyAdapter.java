package com.lakehub.beinafu;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lakehub.beinafu.R.id;

public class LazyAdapter extends BaseAdapter{
	private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    private String which_list = "";
    
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d, String list) {
        activity = a;
        data = d;
        which_list = list;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 View vi = convertView;
		 if(which_list == "electronics"){
			 if(convertView == null)
                 vi = inflater.inflate(R.layout.electronics_item, null);
			 TextView itemId = (TextView)vi.findViewById(id.item_id);
			 TextView itemName = (TextView)vi.findViewById(id.itemElectronicName);
			 TextView itemSeller = (TextView)vi.findViewById(R.id.itemElectronicSeller);
			 TextView itemPrice = (TextView)vi.findViewById(R.id.itemElectronicPrice);
			 TextView itemSellerContact = (TextView)vi.findViewById(R.id.sellerElectronicContact);
			 TextView itemLocation = (TextView)vi.findViewById(R.id.sellerElectronicLocation);
			 
			 HashMap<String, String> items = new HashMap<String, String>();
             items = data.get(position);
             
             itemName.setText("Item Name: " + items.get(ElectronicsTechnology.ITEM_NAME));
             itemSeller.setText("Item Seller: " + items.get(ElectronicsTechnology.ITEM_SELLER));
             itemPrice.setText("Item Price(Kshs): " + items.get(ElectronicsTechnology.ITEM_PRICE));
             itemSellerContact.setText("Item Seller Contact: " + items.get(ElectronicsTechnology.ITEM_SELLER_CONTACT));
             itemLocation.setText("Item Location: " + items.get(ElectronicsTechnology.ITEM_LOCATION));
             itemId.setText(items.get(ElectronicsTechnology.ITEM_ID));
		 }
		 if(which_list == "house_lifestyle"){
			 if(convertView == null)
                 vi = inflater.inflate(R.layout.lifestyle_item, null);
			 TextView itemLifestyleNumber = (TextView)vi.findViewById(R.id.item_id_lifestyle);
			 TextView itemNameLifestyle = (TextView)vi.findViewById(id.itemLifestyleName);
			 TextView itemSellerLifestyle = (TextView)vi.findViewById(R.id.itemLifestyleSeller);
			 TextView itemPriceLifestyle = (TextView)vi.findViewById(R.id.itemLifestylePrice);
			 TextView itemSellerContactLifestyle = (TextView)vi.findViewById(R.id.sellerLifestyleContact);
			 TextView itemLocationLifestyle = (TextView)vi.findViewById(R.id.sellerLifestyleLocation);
			 
			 HashMap<String, String> lifeStyleitems = new HashMap<String, String>();
			 lifeStyleitems = data.get(position);
             
			 itemLifestyleNumber.setText(lifeStyleitems.get(HouseLifestyle.ITEM_ID));
             itemNameLifestyle.setText("Item Name: " + lifeStyleitems.get(HouseLifestyle.ITEM_NAME));
             itemSellerLifestyle.setText("Item Seller: " + lifeStyleitems.get(HouseLifestyle.ITEM_SELLER));
             itemPriceLifestyle.setText("Item Price(Kshs): " + lifeStyleitems.get(HouseLifestyle.ITEM_PRICE));
             itemSellerContactLifestyle.setText("Item Seller Contact: " + lifeStyleitems.get(HouseLifestyle.ITEM_SELLER_CONTACT));
             itemLocationLifestyle.setText("Item Location: " + lifeStyleitems.get(HouseLifestyle.ITEM_LOCATION));
		 }
		 if(which_list == "mobilePhones"){
			 if(convertView == null)
                 vi = inflater.inflate(R.layout.mobile_phone_item, null);
			 TextView itemNumberMobile = (TextView)vi.findViewById(R.id.item_id_Mobile);
			 TextView itemNameMobile = (TextView)vi.findViewById(id.itemMobileName);
			 TextView itemSellerMobile = (TextView)vi.findViewById(R.id.itemMobileSeller);
			 TextView itemPriceMobile = (TextView)vi.findViewById(R.id.itemMobilePrice);
			 TextView itemSellerContactMobile = (TextView)vi.findViewById(R.id.sellerMobileContact);
			 TextView itemLocationMobile = (TextView)vi.findViewById(R.id.sellerMobileLocation);
			 
			 HashMap<String, String> mobilePhoneitems = new HashMap<String, String>();
			 mobilePhoneitems = data.get(position);
             
			 itemNumberMobile.setText(mobilePhoneitems.get(MobilePhones.ITEM_ID));
             itemNameMobile.setText("Item Name: " + mobilePhoneitems.get(MobilePhones.ITEM_NAME));
             itemSellerMobile.setText("Item Seller: " + mobilePhoneitems.get(MobilePhones.ITEM_SELLER));
             itemPriceMobile.setText("Item Price(Kshs): " + mobilePhoneitems.get(MobilePhones.ITEM_PRICE));
             itemSellerContactMobile.setText("Item Seller Contact: " + mobilePhoneitems.get(MobilePhones.ITEM_SELLER_CONTACT));
             itemLocationMobile.setText("Item Location: " + mobilePhoneitems.get(MobilePhones.ITEM_LOCATION));
		 }
		 if(which_list == "map_retails"){
			 
		 }
		return vi;
	}

}
