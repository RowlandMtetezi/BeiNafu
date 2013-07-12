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
			 TextView itemName = (TextView)vi.findViewById(id.itemElectronicName);
			 TextView itemSeller = (TextView)vi.findViewById(R.id.itemElectronicSeller);
			 TextView itemPrice = (TextView)vi.findViewById(R.id.itemElectronicPrice);
			 TextView itemSellerContact = (TextView)vi.findViewById(R.id.sellerElectronicContact);
			 TextView itemLocation = (TextView)vi.findViewById(R.id.sellerElectronicLocation);
			 
			 HashMap<String, String> items = new HashMap<String, String>();
             items = data.get(position);
             
             itemName.setText(items.get(ElectronicsTechnology.ITEM_NAME));
             itemSeller.setText(items.get(ElectronicsTechnology.ITEM_SELLER));
             itemPrice.setText(items.get(ElectronicsTechnology.ITEM_PRICE));
             itemSellerContact.setText(items.get(ElectronicsTechnology.ITEM_SELLER_CONTACT));
             itemLocation.setText(items.get(ElectronicsTechnology.ITEM_LOCATION));
		 }
		return vi;
	}

}
