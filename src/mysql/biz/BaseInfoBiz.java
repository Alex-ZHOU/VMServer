package mysql.biz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mysql.MySQL;
import mysql.entities.BaseInfo;

public class BaseInfoBiz {

	public static void main(String[] args) {
		System.out.println(new BaseInfoBiz().getHeadPortraitByUserId(2));
	}

	public int getHeadPortraitByUserId(int userId) {
		int imageId = 0;
		MySQL mysql = new MySQL();
		PreparedStatement ps = mysql.getPreparedStatement("SELECT * FROM base_info where usr_id = ?");
		try {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				imageId = rs.getInt("head_protrait");
			}
			rs.close();
			ps.cancel();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysql.closeAll();
		}
		return imageId;
	}

	public BaseInfo getByUserId(int userId) {
		BaseInfo baseInfo = new BaseInfo();

		MySQL mysql = new MySQL();

		PreparedStatement ps = mysql.getPreparedStatement("SELECT * FROM base_info where usr_id = ?");
		try {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				baseInfo.setUsr_id(userId);
				baseInfo.setUsr_account(rs.getString("usr_account"));
				baseInfo.setNickname(rs.getString("nickname"));
				baseInfo.setRecord_times(rs.getInt("record_times"));
				baseInfo.setAverage_db(rs.getDouble("average_db"));
				baseInfo.setMax_db(rs.getInt("max_db"));
				baseInfo.setMin_db(rs.getInt("min_db"));
				baseInfo.setRecord_minter(rs.getDouble("record_minter"));
				baseInfo.setHead_protrait(rs.getInt("head_protrait"));
			}
			rs.close();
			ps.cancel();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysql.closeAll();
		}
		return baseInfo;
	}

}
