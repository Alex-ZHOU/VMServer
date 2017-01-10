package android.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.RecordDB;
import mysql.MySQL;
import utils.EncapsulateParseJson;

/**
 * Servlet implementation class RecordDB
 */
@WebServlet("/Android_RecordDB")
public class Android_RecordDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		InputStream in = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str = br.readLine();

		RecordDB recordDB = EncapsulateParseJson.parse(RecordDB.class, str);

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

		String sql = "insert into record_db (usr_id,times,db,longitude,latitude,time,timekeeper) values (?,?,?,?,?,?,?)";

		PreparedStatement ps;
		int row = 0;
		try {
			ps = connection.prepareStatement(sql);
			// usr_id
			ps.setInt(1, recordDB.getUserId());
			// times
			ps.setInt(2, recordDB.getTimes());
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

		response.getWriter().append(EncapsulateParseJson.encapsulate(back)).close();
		;
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

		public boolean isSucceed() {
			return isSucceed;
		}

		public void setSucceed(boolean succeed) {
			isSucceed = succeed;
		}
	}

}
