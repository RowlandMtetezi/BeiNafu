package com.lakehub.beinafu.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.List;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper{
	// All  variables
	// Database Version 
	protected  final static int DATABASE_VERSION = 1;

	// Database Name
	protected  final static String DATABASE_NAME = "beinafu_db";

	// tables
	protected  final String TABLE_ITEMS= "beinafu_items";
	
	//Items table column names
	public static final String ITEM_ID = "item_id";
	public static final String ITEM_NAME = "item_name";
	public static final String ITEM_SELLER = "item_seller";
	public static final String ITEM_SELLER_CONTACT = "item_seller_location";
	public static final String ITEM_PRICE = "item_price";
	public static final String ITEM_STATUS = "item_status";
	public static final String ITEM_CATEGORY = "item_category";
	public static final String ITEM_LOCATION = "item_location";
	public static final String ITEM_DATE_CREATED = "item_date_created";
	
	//Call to the super class to initialize properly
	public DBHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
				+ ITEM_ID + " INTEGER,"
				+ ITEM_NAME + " TEXT,"
				+ ITEM_SELLER + " TEXT,"
				+ ITEM_SELLER_CONTACT + " TEXT,"
				+ ITEM_PRICE + " TEXT,"
				+ ITEM_STATUS + " TEXT,"
				+ ITEM_CATEGORY + " TEXT,"
				+ ITEM_LOCATION + " TEXT," 
				+ ITEM_DATE_CREATED + " TEXT" + ")";
		db.execSQL(CREATE_ITEMS_TABLE);
	}
	
	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 if (newVersion > oldVersion)
		        Log.v("Database Upgrade", "Database version higher than old.");
		
		 // Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);

		// Create tables again
		onCreate(db);
	}
	
	//adding a new item
	public void addItem(Items item){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ITEM_ID , item.getItemID()); 
		values.put(ITEM_NAME, item.getItemName()); 
		values.put(ITEM_SELLER, item.getItemSeller()); 
		values.put(ITEM_SELLER_CONTACT, item.getItemSellerContact()); 
		values.put(ITEM_PRICE, item.getItemPrice()); 
		values.put(ITEM_STATUS, item.getStatus()); 
		values.put(ITEM_CATEGORY, item.getItemCategory()); 
		values.put(ITEM_LOCATION, item.getItemLocation()); 
		values.put(ITEM_DATE_CREATED, item.getItemDateCreated());
		
		// Inserting Row
		db.insert(TABLE_ITEMS, null, values);
		db.close(); // Closing database connection
	}
	
	//Getting a single item
	public Items getItem(int itemID){
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_ITEMS, new String[] { ITEM_ID,  
				ITEM_NAME, 
				ITEM_SELLER,
				ITEM_SELLER_CONTACT, 
				ITEM_PRICE,
				ITEM_STATUS,
				ITEM_CATEGORY,
				ITEM_LOCATION,
				ITEM_DATE_CREATED
				}, ITEM_ID + "=?",
				new String[] { String.valueOf(itemID) }, null, null, null,null);
		if (cursor != null)
			cursor.moveToFirst();
		Items item = new Items(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), 
				cursor.getString(2), 
				cursor.getString(3),
				cursor.getString(4), 
				cursor.getString(5), 
				cursor.getString(6), 
				cursor.getString(7),
				cursor.getString(8));
		
		db.close(); // Closing database connection
		// return issue
		return item;
	}
	
	//A List of all Items
	public List<Items> getAllItems(){
		List<Items> itemsList = new ArrayList<Items>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ITEMS;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Items item = new Items();
				item.setItemID(Integer.parseInt(cursor.getString(0)));
				item.setItemName(cursor.getString(1));
				item.setItemSeller(cursor.getString(2));
				item.setItemSellerContact(cursor.getString(3));
				item.setItemPrice(cursor.getString(4));
				item.setItemStatus(cursor.getString(5));
				item.setItemCategory(cursor.getString(6));
				item.setItemLocation(cursor.getString(7));
				item.setItemDateCreated(cursor.getString(8));
				// Adding issue to list
				itemsList.add(item);
			} while (cursor.moveToNext());
		}
		db.close(); // Closing database connection
		// return issues list
		return itemsList;
	}
	
	// Updating single issue
	public int updateItem(Items item) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(ITEM_SELLER_CONTACT, item.getItemSellerContact());		
		values.put(ITEM_CATEGORY, item.getItemCategory());

		// updating row
		int rowUpdated = db.update(TABLE_ITEMS, values, ITEM_ID + " = ?",
				new String[] { String.valueOf(item.getItemID()) });
		
		db.close();
		return rowUpdated;
	}
	
}
