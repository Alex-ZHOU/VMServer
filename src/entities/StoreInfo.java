package entities;

public class StoreInfo {
	
	private int StoreId;
	
	private int AdvertisementId;
	
	private int ImageId;
	
	private String Title;

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
}
