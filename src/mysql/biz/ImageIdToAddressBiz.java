package mysql.biz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mysql.MySQL;

public class ImageIdToAddressBiz {

	public static void main(String[] args) {
		System.out.println(new ImageIdToAddressBiz().getAddressByImageId(1));
	}
	
	public String getAddressByImageId(int imageId) {
		String address = "/Image/pic_lost.png";

		MySQL mysql = new MySQL();

		PreparedStatement ps = mysql.getPreparedStatement("SELECT * FROM image_id_to_address where image_id = ?");
		try {
			ps.setInt(1, imageId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				address = rs.getString("address");
			}
			rs.close();
			ps.cancel();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysql.closeAll();
		}
		return address;
		
	}
}
