package com.lakehub.beinafu.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper{
	// All  variables
	// Database Version
	protected  final static int DATABASE_VERSION = 1;

	// Database Name
	protected  final static String DATABASE_NAME = "beinafu_db";

	// tables
	protected  final String TABLE_ITEMS= "beinafu_items";
	protected  final String TABLE_METADATA= "beinafu_metadata";
	
	//Items table column names
	public static final String ITEM_ID = "item_id";
	public static final String ITEM_NAME = "item_name";
	public static final String ITEM_SELLER = "item_seller";
	public static final String ITEM_SELLER_CONTACT = "item_seller_location";
	public static final String ITEM_PRICE = "item_price";
	public static final String ITEM_STATUS = "item_status";
	public static final String ITEM_CATEGORY = "item_category";
	public static final String ITEM_LOCATION = "item_location";
	
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
				+ ITEM_LOCATION + " TEXT" + ")";
		db.execSQL(CREATE_ITEMS_TABLE);
	}
	
	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 if (newVersion > oldVersion)
		        Log.v("Database Upgrade", "Database version higher than old.");
		
		 // Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_METADATA);

		// Create tables again
		onCreate(db);
	}
	
	//adding a new item
	
	
}
