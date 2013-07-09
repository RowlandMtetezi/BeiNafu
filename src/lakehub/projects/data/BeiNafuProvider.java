package lakehub.projects.data;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import lakehub.projects.data.BeiNafuProviderMetaData.ItemsTableMetaData;

public class BeiNafuProvider extends ContentProvider {
	//Create a Projection Map for Coloumns
	//Projection maps are similar to "as" construct in sql
	//statement whereby you can rename the 
	//coloumns
	private static final HashMap<String, String> sItemsProjectionMap;
	static {
		sItemsProjectionMap = new HashMap<String, String>();
		sItemsProjectionMap.put(ItemsTableMetaData._ID,ItemsTableMetaData._ID);
		sItemsProjectionMap.put(ItemsTableMetaData.ITEM_NAME,ItemsTableMetaData.ITEM_NAME);
		sItemsProjectionMap.put(ItemsTableMetaData.ITEM_SELLER,ItemsTableMetaData.ITEM_SELLER);
		sItemsProjectionMap.put(ItemsTableMetaData.ITEM_SELLER_CONTACT,ItemsTableMetaData.ITEM_SELLER_CONTACT);
		sItemsProjectionMap.put(ItemsTableMetaData.ITEM_PRICE,ItemsTableMetaData.ITEM_PRICE);
		sItemsProjectionMap.put(ItemsTableMetaData.ITEM_STATUS,ItemsTableMetaData.ITEM_STATUS);
		sItemsProjectionMap.put(ItemsTableMetaData.ITEM_CATEGORY,ItemsTableMetaData.ITEM_CATEGORY);
		sItemsProjectionMap.put(ItemsTableMetaData.ITEM_LOCATION,ItemsTableMetaData.ITEM_CATEGORY);
	}	
	
	//Provide a mechanism to identify all incoming data
	private static final UriMatcher sUriMatcher;
	private static final int INCOMING_ITEM_COLLECTION_URI_INDICATOR = 1;
	private static final int INCOMING_SINGLE_ITEM_URI_INDICATOR = 2;
	static{
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(BeiNafuProviderMetaData.AUTHORITY, "items",INCOMING_ITEM_COLLECTION_URI_INDICATOR);
		sUriMatcher.addURI(BeiNafuProviderMetaData.AUTHORITY, "items",INCOMING_SINGLE_ITEM_URI_INDICATOR);
	}
	
	
	//Deal with OnCreate call back
	private DatabaseHelper mOpenHelper;
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context){
			super(context, BeiNafuProviderMetaData.DATABASE_NAME,null,BeiNafuProviderMetaData.DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + BeiNafuProviderMetaData.ITEMS_TABLE_NAME + " ("
					+ BeiNafuProviderMetaData.ItemsTableMetaData._ID
					+ "INTEGER PRIMARY KEY,"
					+ BeiNafuProviderMetaData.ItemsTableMetaData.ITEM_NAME + " TEXT,"
					+ BeiNafuProviderMetaData.ItemsTableMetaData.ITEM_SELLER + " TEXT,"
					+ BeiNafuProviderMetaData.ItemsTableMetaData.ITEM_SELLER_CONTACT + " TEXT,"
					+ BeiNafuProviderMetaData.ItemsTableMetaData.ITEM_PRICE + " TEXT,"
					+ BeiNafuProviderMetaData.ItemsTableMetaData.ITEM_STATUS + " TEXT,"
					+ BeiNafuProviderMetaData.ItemsTableMetaData.ITEM_CATEGORY + " TEXT,"
					+ BeiNafuProviderMetaData.ItemsTableMetaData.ITEM_LOCATION + " TEXT"
					+ ");");			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(null,"Upgrading database from verion " + oldVersion + " to" + newVersion + ",which will destroy all data");
			
		}
		
	}
	
	
	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count;
		switch(sUriMatcher.match(uri)){
		case INCOMING_ITEM_COLLECTION_URI_INDICATOR:
			count = db.delete(ItemsTableMetaData.TABLE_NAME, where, whereArgs);
			break;
		case INCOMING_SINGLE_ITEM_URI_INDICATOR:
			String rowId = uri.getPathSegments().get(1);
			count = db.delete(ItemsTableMetaData.TABLE_NAME, ItemsTableMetaData._ID + "=" + rowId 
					+ (!TextUtils.isEmpty(where)? " AND (" + where + ')':""), whereArgs);
			break;
		default: 
			throw new IllegalArgumentException("Unknown Uri " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	
	
	@Override
	public String getType(Uri uri) {
		switch(sUriMatcher.match(uri)){
		case INCOMING_ITEM_COLLECTION_URI_INDICATOR:
			return ItemsTableMetaData.CONTENT_TYPE;
		case INCOMING_SINGLE_ITEM_URI_INDICATOR:
			return ItemsTableMetaData.CONTENT_TYPE_ITEM;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		//validate the request uri
		if(sUriMatcher.match(uri) != INCOMING_ITEM_COLLECTION_URI_INDICATOR){
			throw new IllegalArgumentException("Unknown Uri "+uri);
		}
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long rowId = db.insert(ItemsTableMetaData.TABLE_NAME, ItemsTableMetaData.ITEM_NAME, values);
		
		if(rowId > 0){
			Uri insertedItemIdUri = ContentUris.withAppendedId(ItemsTableMetaData.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(insertedItemIdUri, null);
			return insertedItemIdUri;
		}
		throw new SQLException("Failed to insert row into "+uri);
	}
	 
	
	@Override
	public boolean onCreate() {
		mOpenHelper = new DatabaseHelper(getContext());
		return true;
	}
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		switch(sUriMatcher.match(uri)){
		case INCOMING_ITEM_COLLECTION_URI_INDICATOR:
			qb.setTables(ItemsTableMetaData.TABLE_NAME);
			qb.setProjectionMap(sItemsProjectionMap);
			break;
		case INCOMING_SINGLE_ITEM_URI_INDICATOR:
			qb.setTables(ItemsTableMetaData.TABLE_NAME);
			qb.setProjectionMap(sItemsProjectionMap);
			qb.appendWhere(ItemsTableMetaData._ID + "=" + uri.getPathSegments().get(1));
			break;
		default:
			throw new IllegalArgumentException("Unknown URI "+ uri);
		}
		//If no sort order is specified use the default
		@SuppressWarnings("unused")
		String orderBy;
		if(TextUtils.isEmpty(sortOrder)){
			orderBy = ItemsTableMetaData.DEFAULT_SORT_ORDER;
		}
		else{
			orderBy = sortOrder;
		}
		
		//Get the Database and run the query
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		@SuppressWarnings("unused")
		int i = c.getCount();
		
		//Tell the cursor which uri to watch
		//so it knows when its source changes
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}
	
	
	@Override
	public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count;
		switch(sUriMatcher.match(uri)){
		case INCOMING_ITEM_COLLECTION_URI_INDICATOR:
			count = db.update(ItemsTableMetaData.TABLE_NAME, values, where, whereArgs);
			break;
		case INCOMING_SINGLE_ITEM_URI_INDICATOR:
			String rowId = uri.getPathSegments().get(1);
			count = db.update(ItemsTableMetaData.TABLE_NAME, values, ItemsTableMetaData._ID + "=" + rowId
					+ (!TextUtils.isEmpty(where)? " AND (" + where + ')':""), whereArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
}
