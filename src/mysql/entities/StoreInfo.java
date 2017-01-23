package mysql.entities;

public class StoreInfo {
	
	private int store_id;
	
	private int advertisement_id;
	
	private int image_id;
	
	private String title;
	
	private int average_db;
	
	private String summary;
	
	private double latitude;
	
	private double longitude;
	
	private String type;

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public int getAdvertisement_id() {
		return advertisement_id;
	}

	public void setAdvertisement_id(int advertisement_id) {
		this.advertisement_id = advertisement_id;
	}

	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAverage_db() {
		return average_db;
	}

	public void setAverage_db(int average_db) {
		this.average_db = average_db;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
