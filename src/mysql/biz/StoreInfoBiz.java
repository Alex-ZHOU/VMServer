package mysql.biz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mysql.MySQL;
import mysql.entities.StoreInfo;

public class StoreInfoBiz {

	public List<StoreInfo> getStoreListByType(String type) {
		List<StoreInfo> list = new ArrayList<>();
		MySQL mysql = new MySQL();
		PreparedStatement ps = mysql.getPreparedStatement("SELECT * FROM store_info where type=?");
		try {
			ps.setString(1, type);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StoreInfo storeInfo = new StoreInfo();
				storeInfo.setStore_id(rs.getInt("store_id"));
				storeInfo.setAdvertisement_id(rs.getInt("advertisement_id"));
				storeInfo.setImage_id(rs.getInt("image_id"));
				storeInfo.setTitle(rs.getString("title"));
				storeInfo.setAverage_db(rs.getInt("average_db"));
				storeInfo.setSummary(rs.getString("summary"));
				storeInfo.setLongitude(rs.getDouble("longitude"));
				storeInfo.setLatitude(rs.getDouble("latitude"));
				storeInfo.setType(rs.getString("type"));
				list.add(storeInfo);
			}
			rs.close();
			ps.cancel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
