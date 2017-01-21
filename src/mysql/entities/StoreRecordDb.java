package mysql.entities;

public class StoreRecordDb {
	private int store_record_id;
	
	private int store_id;
	
	private  int usr_id;
	
	private int reocrd_status;
	
	private int times;
	
	private int year;
	
	private int month;
	
	private int day;
	
	private String db;
	
	private String latitude;
	
	private String longitude;
	
	private String time;
	
	private String timekeeper;

	public int getStore_record_id() {
		return store_record_id;
	}

	public void setStore_record_id(int store_record_id) {
		this.store_record_id = store_record_id;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public int getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(int usr_id) {
		this.usr_id = usr_id;
	}

	public int getReocrd_status() {
		return reocrd_status;
	}

	public void setReocrd_status(int reocrd_status) {
		this.reocrd_status = reocrd_status;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimekeeper() {
		return timekeeper;
	}

	public void setTimekeeper(String timekeeper) {
		this.timekeeper = timekeeper;
	}
	
}
