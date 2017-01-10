package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import entities.RecordDB;
import mysql.MySQL;

//http://blog.csdn.net/zhai56565/article/details/9800331
public class TestRecordDBDataSave {
	public static void main(String[] args) {
		RecordDB recordDB = new RecordDB();

		for (int i = 0; i < 60 * 60 * 24; i++) {
			RecordDB.Data data = recordDB.instancesData();
			data.setDb(i % 100);
			data.setLatitude(23.121231);
			data.setLongitude(24.123131);
			data.setTime("22:11:59");
			recordDB.getRecordList().add(data);
		}

		StringBuffer stringBufferDB = new StringBuffer();
		StringBuffer stringBufferLatitude = new StringBuffer();
		StringBuffer stringBufferLongitude = new StringBuffer();
		StringBuffer stringBufferTime = new StringBuffer();
		StringBuffer stringBufferTimekeeper = new StringBuffer();

		// stringBuffer.append(str)

		String db = "";

		String latitude = "";

		String longitude = "";

		String time = "";

		String timekeeper = "";

		List<RecordDB.Data> list = recordDB.getRecordList();

		for (int i = 0; i < list.size(); i++) {
			RecordDB.Data data = list.get(i);
			// db = db+"|"+recordDB.getDb();
			stringBufferDB.append("|" + data.getDb());
			// latitude = latitude+"|"+recordDB.getLatitude();
			stringBufferLatitude.append("|" + data.getLatitude());
			// longitude = longitude+"|"+recordDB.getLongitude();
			stringBufferLongitude.append("|" + data.getLongitude());
			// time = time+"|"+recordDB.getTime();
			stringBufferTime.append("|" + data.getTime());

			stringBufferTimekeeper.append("|" + data.getTimekeeper());
		}
		db = stringBufferDB.toString();
		latitude = stringBufferLatitude.toString();
		longitude = stringBufferLongitude.toString();
		time = stringBufferTime.toString();
		timekeeper = stringBufferTimekeeper.toString();
		// 数据库测试
		MySQL mysql = new MySQL();

		Connection connection = mysql.getConnection();

		String sql = "insert into record_db (usr_id,times,db,longitude,latitude,time,timekeeper) values (?,?,?,?,?,?,?)";

		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(sql);
			// usr_id
			ps.setInt(1, 1);
			// times
			ps.setInt(2, 5);
			// db
			ps.setString(3, db);
			// longitude
			ps.setString(4, longitude);
			// latitude
			ps.setString(5, latitude);
			// time
			ps.setString(6, time);
			// timekeeper
			ps.setString(7, timekeeper);

			int row = ps.executeUpdate();

			System.out.println("row:" + row);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
