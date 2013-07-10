package com.lakehub.beinafu.data;
import android.net.Uri;
import android.provider.BaseColumns;

public class BeiNafuProviderMetaData {
	public static final String AUTHORITY = "com.lakehub.beinafu.data.BeiNafuProvider";
	
	public static final String DATABASE_NAME = "beinafu.db";
	public static final int DATABASE_VERSION = 1;
	public static final String ITEMS_TABLE_NAME = "items";
	
	private BeiNafuProviderMetaData(){
		
	}
	
	//inner class describing items table
	public static final class ItemsTableMetaData implements BaseColumns{
		private ItemsTableMetaData(){
			
		}
		public static final String TABLE_NAME = "items";
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/items");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.lakehub.item";
		public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.lakehub.item";		
		public static final String DEFAULT_SORT_ORDER = "category ASC";
		
		//Additional columns start here
		//string type
		public static final String ITEM_NAME = "item_name";
		public static final String ITEM_SELLER = "item_seller";
		public static final String ITEM_SELLER_CONTACT = "item_seller_location";
		public static final String ITEM_PRICE = "item_price";
		public static final String ITEM_STATUS = "item_status";
		public static final String ITEM_CATEGORY = "item_category";
		public static final String ITEM_LOCATION = "item_location";
		
	}
}
