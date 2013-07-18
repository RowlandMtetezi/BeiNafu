package com.lakehub.beinafu.data;
 
public class Items {
	//private variables
	int item_id;
	String item_name;
	String item_seller;
	String item_seller_contact;
	String item_price;
	String item_status;
	String item_category;  
	String item_location;
	String item_date_created;
	
	//Empty constructor
	public Items(){
		
	}
	 
	//constructor
	public Items(int itemID, String itemName, String itemSeller, String itemSellerContact, String itemPrice, String itemStatus, String itemCategory, String itemLocation, String item_date_created){
		this.item_id = itemID;
		this.item_name = itemName;
		this.item_seller = itemSeller;
		this.item_seller_contact = itemSellerContact;
		this.item_price = itemPrice;
		this.item_status = itemStatus;
		this.item_category = itemCategory;
		this.item_location = itemLocation;
		this.item_date_created = item_date_created;
	}
	
	/* 
	 * Get the variables 
	 */
	public int getItemID(){
		return this.item_id;
	}
	public String getItemName(){
		return this.item_name;
	}
	public String getItemSeller(){
		return this.item_seller;
	}
	public String getItemSellerContact(){
		return this.item_seller_contact;
	}
	public String getItemPrice(){
		return this.item_price;
	}
	public String getStatus(){
		return this.item_status;
	}
	public String getItemCategory(){
		return this.item_category;
	}
	public String getItemLocation(){
		return this.item_location;
	}
	public String getItemDateCreated(){
		return this.item_date_created;
	}
	
	/* 
	 * Set variables 
	 */
	public void setItemID(int itemId){
		this.item_id = itemId;
	}
	public void setItemName(String itemName){
		this.item_name = itemName;
	}
	public void setItemSeller(String itemSeller){
		this.item_seller = itemSeller;
	}
	public void setItemSellerContact(String itemSellerContact){
		this.item_seller_contact = itemSellerContact;
	}
	public void setItemPrice(String itemPrice){
		this.item_price = itemPrice;
	}
	public void setItemStatus(String itemStatus){
		this.item_status = itemStatus;
	}
	public void setItemCategory(String itemCategory){
		this.item_category = itemCategory;
	}
	public void setItemLocation(String itemLocation){
		this.item_location = itemLocation;
	}
	public void setItemDateCreated(String dateCreated){
		this.item_date_created = dateCreated;
	}
}
