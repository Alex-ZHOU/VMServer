package android.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entities.BaseInfo;
import entities.RecordDB;
import mysql.MySQL;
import utils.AverageAlgorithm;
import utils.EncapsulateParseJson;

/**
 * Servlet implementation class RecordDB
 */
@WebServlet("/Android_RecordDB")
public class Android_RecordDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RecordDB recordDB;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Android_RecordDB() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		InputStream in = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str = br.readLine();
		System.out.println("Android_RecordDB:" + str);
		recordDB = EncapsulateParseJson.parse(RecordDB.class, str);

		BaseInfo baseInfo = getBaseInfo();

		StringBuffer stringBufferDB = new StringBuffer();
		StringBuffer stringBufferLatitude = new StringBuffer();
		StringBuffer stringBufferLongitude = new StringBuffer();
		StringBuffer stringBufferTime = new StringBuffer();
		StringBuffer stringBufferTimekeeper = new StringBuffer();

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

		MySQL mysql = new MySQL();

		Connection connection = mysql.getConnection();

		String sql = "insert into record_db (usr_id,times,db,longitude,latitude,time,timekeeper,year,month,day) values (?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps;
		int row = 0;
		try {
			ps = connection.prepareStatement(sql);
			// usr_id
			ps.setInt(1, recordDB.getUserId());
			// times
			ps.setInt(2, baseInfo.getRecordTimes() + 1);
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
			
			Calendar c = Calendar.getInstance();
			
			ps.setInt(8, c.get(Calendar.YEAR));
			
			ps.setInt(9, c.get(Calendar.MONTH)+1);
			
			ps.setInt(10, c.get(Calendar.DAY_OF_YEAR));

			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysql.closeAll();

		}

		Return back = new Return();

		if (row == 1) {
			back.setSucceed(true);
		} else {
			back.setSucceed(false);
		}

		System.out.println(EncapsulateParseJson.encapsulate(back));

		response.getWriter().append(EncapsulateParseJson.encapsulate(back)).close();

		updataBaseInfo(baseInfo);

	}

	@SuppressWarnings("deprecation")
	private void updataBaseInfo(BaseInfo baseInfo) {

		int maxDb = baseInfo.getMaxDb();

		int minDb = baseInfo.getMinDb();

		double averageDb = baseInfo.getAverageDb();

		double recordMinter = baseInfo.getRecordMinter();

		int recordTimes = baseInfo.getRecordTimes();

		recordTimes = baseInfo.getRecordTimes() + 1;

		List<RecordDB.Data> list = recordDB.getRecordList();

		int a[] = new int[list.size()];

		for (int i = 0; i < a.length; i++) {
			a[i] = list.get(i).getDb();

			maxDb = maxDb > a[i] ? maxDb : a[i];

			if (minDb == 0) {
				minDb = a[i];
			}
			minDb = minDb < a[i] ? minDb : a[i];

			// System.out.println(a[i] + " " + i);
		}

		// System.out.println(averageDb + " " + (recordTimes - 1));
		averageDb = new AverageAlgorithm().getAverage(averageDb, recordTimes - 1, a);
		// System.out.println(averageDb);

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date date;
		try {
			date = sdf.parse(list.get(list.size() - 1).getTimekeeper());
			recordMinter = recordMinter + (date.getHours() * 60 + date.getMinutes() + date.getSeconds() / 60d);
			// System.out.println(list.get(list.size() - 1).getTimekeeper());
			// System.out.println(date.getHours());
			// System.out.println(date.getMinutes());
			// System.out.println(date.getSeconds() + " " + date.getSeconds() /
			// 60d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("maxDb:" + maxDb + ",minDb:" + minDb + ",averageDb:" + averageDb + ",recordMinter:"
				+ recordMinter + ",recordTimes:" + recordTimes);

		MySQL mysql = new MySQL();

		String sql = "UPDATE base_info set record_times=?,average_db=?,max_db=?,min_db=?,record_minter=? WHERE usr_id=?";

		PreparedStatement ps = null;

		try {
			ps = mysql.getConnection().prepareStatement(sql);
			ps.setInt(1, recordTimes);
			ps.setDouble(2, averageDb);
			ps.setInt(3, maxDb);
			ps.setInt(4, minDb);
			ps.setDouble(5, recordMinter);
			ps.setInt(6, recordDB.getUserId());
			int row = ps.executeUpdate();
			System.out.println("row:" + row);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysql.closeAll();
		}
	}

	private BaseInfo getBaseInfo() {

		BaseInfo baseInfo = new BaseInfo();

		MySQL mysql = new MySQL();

		String sql = "SELECT * FROM base_info where usr_id=?";

		PreparedStatement ps = null;

		try {
			ps = mysql.getConnection().prepareStatement(sql);
			ps.setInt(1, recordDB.getUserId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				baseInfo.setUserId(recordDB.getUserId());
				baseInfo.setUsername(rs.getString("usr_account"));
				baseInfo.setNickname(rs.getString("nickname"));
				baseInfo.setRecordTimes(rs.getInt("record_times"));
				baseInfo.setAverageDb((int) rs.getDouble("average_db"));
				baseInfo.setMaxDb(rs.getInt("max_db"));
				baseInfo.setMinDb(rs.getInt("min_db"));
				baseInfo.setRecordMinter(rs.getDouble("record_minter"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mysql.closeAll();
		}
		return baseInfo;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private static class Return {
		private boolean isSucceed;

		@SuppressWarnings("unused")
		public boolean isSucceed() {
			return isSucceed;
		}

		public void setSucceed(boolean succeed) {
			isSucceed = succeed;
		}
	}

}
