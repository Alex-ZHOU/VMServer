package entities;

public class AdvertisingColumn {
	/**
	 * 广告的Id号
	 */
	private int Advertisement_Id;

	/**
	 * 广告对应图片的Id号
	 */
	private int Image_Id;

	/**
	 * 广告标题
	 */
	private String Title;

	public int getAdvertisement_Id() {
		return Advertisement_Id;
	}

	public void setAdvertisement_Id(int advertisement_Id) {
		Advertisement_Id = advertisement_Id;
	}

	public int getImage_Id() {
		return Image_Id;
	}

	public void setImageId(int image_id) {
		Image_Id = image_id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}
}
