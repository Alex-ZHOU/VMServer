package mysql.biz;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import mysql.MySQL;

public class StoreRecordDbBiz {
	
	public boolean insert(entities.StoreDbRecord storeRecordDb){
		
		boolean isSucceed = false;
		
		MySQL mysql = new MySQL();
		
		String sql = "insert into store_record_db (store_id,usr_id,year,month,day,reocrd_status,db,latitude,longitude,time,timekeeper)values(?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement ps = mysql.getPreparedStatement(sql);
		
		try {
			// store_id
			ps.setInt(1, storeRecordDb.getStoreId());
			// usr_id
			ps.setInt(2, storeRecordDb.getUserId());
			
			Calendar c = Calendar.getInstance();
			//year
			ps.setInt(3, c.get(Calendar.YEAR));
			//month
			ps.setInt(4, c.get(Calendar.MONTH)+1);
			//day
			ps.setInt(5, c.get(Calendar.DAY_OF_YEAR));
			//reocrd_status
			ps.setInt(6, 0);
			
			StringBuffer stringBufferDB = new StringBuffer();
			StringBuffer stringBufferLatitude = new StringBuffer();
			StringBuffer stringBufferLongitude = new StringBuffer();
			StringBuffer stringBufferTime = new StringBuffer();
			StringBuffer stringBufferTimekeeper = new StringBuffer();
			
			List<entities.StoreDbRecord.Data> list = storeRecordDb.getDbData();
			
			for (int i = 0; i < list.size(); i++) {
				entities.StoreDbRecord.Data data = list.get(i);
				// db = db+"|"+recordDB.getDb();
				stringBufferDB.append("|" + data.getDB());
				// latitude = latitude+"|"+recordDB.getLatitude();
				stringBufferLatitude.append("|" + data.getLatitude());
				// longitude = longitude+"|"+recordDB.getLongitude();
				stringBufferLongitude.append("|" + data.getLongitude());
				// time = time+"|"+recordDB.getTime();
				stringBufferTime.append("|" + data.getTime());

				stringBufferTimekeeper.append("|" + data.getTimekeeper());
			}
			//db
			ps.setString(7, stringBufferDB.toString());
			//latitude
			ps.setString(8, stringBufferLatitude.toString());
			//longitude
			ps.setString(9, stringBufferLongitude.toString());
			//time
			ps.setString(10, stringBufferTime.toString());
			//timekeeper
			ps.setString(11, stringBufferTimekeeper.toString());
			
			if(ps.executeUpdate()==1){
				isSucceed = true;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			mysql.closeAll();
		}
		
		return isSucceed;
	}
}
