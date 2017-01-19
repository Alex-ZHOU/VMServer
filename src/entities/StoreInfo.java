package entities;

public class StoreInfo {
	
	private int StoreId;
	
	private int AdvertisementId;
	
	private int ImageId;
	
	private String Title;
	
	private int AverageDb;
	
	private String Summary;
	
	private double Longitude;
	
	private double Latitude;

	public int getStoreId() {
		return StoreId;
	}

	public void setStoreId(int storeId) {
		StoreId = storeId;
	}

	public int getAdvertisementId() {
		return AdvertisementId;
	}

	public void setAdvertisementId(int advertisementId) {
		AdvertisementId = advertisementId;
	}

	public int getImageId() {
		return ImageId;
	}

	public void setImageId(int imageId) {
		ImageId = imageId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}
	
    public int getAverageDb() {
        return AverageDb;
    }

    public void setAverageDb(int averageDb) {
        AverageDb = averageDb;
    }

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) {
		Summary = summary;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
}
