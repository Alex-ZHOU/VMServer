package android.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.History;
import entities.RecordDB.Data;
import mysql.MySQL;
import utils.EncapsulateParseJson;

/**
 * Servlet implementation class Android_History
 */
@WebServlet("/Android_History")
public class Android_History extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Android_History() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId = Integer.valueOf(request.getParameter("UserId"));

		System.out.println("Android_History:" + userId);

		getHistory(userId);

		response.getWriter().append(EncapsulateParseJson.encapsulate(list)).close();
		
	}

	
	private List<History> list = new ArrayList<>();
	
	private void getHistory(int userId) {

		MySQL mysql = new MySQL();

		String sql = "SELECT * FROM record_db where usr_id=?  order by record_db_id desc;";

		PreparedStatement ps = null;

		try {
			ps = mysql.getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int record_db_id = rs.getInt("record_db_id");
				int times = rs.getInt("times");
				int year = rs.getInt("year");
				int month = rs.getInt("month");
				int day = rs.getInt("day");
				String dbStr = rs.getString("db");
				String[] db = dbStr.substring(1, dbStr.length()).split("\\|");
				String latitudeStr = rs.getString("latitude");
				String[] latitude = latitudeStr.substring(1, latitudeStr.length()).split("\\|");
				String longitudeStr = rs.getString("longitude");
				String[] longitude = longitudeStr.substring(1, longitudeStr.length()).split("\\|");
				String timeStr = rs.getString("time");
				String[] time = timeStr.substring(1, timeStr.length()).split("\\|");
				String timekeeperStr = rs.getString("timekeeper");
				String[] timekeeper = timekeeperStr.substring(1, timekeeperStr.length()).split("\\|");

				History history = new History();
				List<Data> recordList = history.getRecordList();
				history.setUserId(userId);
				history.setTimes(times);
				history.setDay(day);
				history.setYear(year);
				history.setMonth(month);
				history.setRecordId(record_db_id);
				for (int i = 0; i < db.length; i++) {
					Data data = history.instancesData();
					data.setDb(Integer.valueOf(db[i]));
					data.setLatitude(Double.valueOf(latitude[i]));
					data.setLongitude(Double.valueOf(longitude[i]));
					data.setTime(time[i]);
					data.setTimekeeper(timekeeper[i]);
					recordList.add(data);
				}
				list.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.cancel();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mysql.closeAll();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
