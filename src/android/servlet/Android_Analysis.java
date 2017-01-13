package android.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Analysis;
import entities.BaseInfo;
import mysql.MySQL;
import utils.EncapsulateParseJson;

/**
 * Servlet implementation class Android_Analysis
 */
@WebServlet("/Android_Analysis")
public class Android_Analysis extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Analysis analysis;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Android_Analysis() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId = Integer.valueOf(request.getParameter("UserId"));
		
		System.out.println("Android_Analysis:"+userId);

		analysis = new Analysis();

		analysis(userId);

		System.out.println(EncapsulateParseJson.encapsulate(analysis));

		response.getWriter().append(EncapsulateParseJson.encapsulate(analysis)).close();;
	}

	private void analysis(int userId) {

		MySQL mysql = new MySQL();

		String sql = "SELECT * FROM record_db where usr_id=?";

		PreparedStatement ps = null;

		try {
			ps = mysql.getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				analysis.setTimes(analysis.getTimes() + 1);
				
				String[] db = rs.getString("db").substring(1, rs.getString("db").length()).split("\\|");
				for (int i = 0; i < db.length; i++) {
					int thisDb = Integer.valueOf(db[i]);
					
					if (thisDb<=20) {
						analysis.set_20Times(analysis.get_20Times() + 1);
					}else if (thisDb<=40) {
						analysis.set_40Times(analysis.get_40Times() + 1);
					}else if (thisDb<=60) {
						analysis.set_60Times(analysis.get_60Times() + 1);
					}else if (thisDb<=70) {
						analysis.set_70Times(analysis.get_70Times() + 1);
					}else if (thisDb<=90) {
						analysis.set_90Times(analysis.get_90Times() + 1);
					}else if (thisDb<=100) {
						analysis.set_100Times(analysis.get_100Times() + 1);
					}else if (thisDb<=120) {
						analysis.set_120Times(analysis.get_120Times() + 1);
					}else {
						analysis.set_120UpTimes(analysis.get_120UpTimes() + 1);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mysql.closeAll();
		}
		
		BaseInfo baseInfo = getBaseInfo(userId);
		
		analysis.setMaxDb(baseInfo.getMaxDb());
		analysis.setMinDb(baseInfo.getMinDb());
		analysis.setAverageDb((int) baseInfo.getAverageDb());
		analysis.setRecordMinter((int) baseInfo.getRecordMinter());

	}
	
	private BaseInfo getBaseInfo(int userId) {

		BaseInfo baseInfo = new BaseInfo();

		MySQL mysql = new MySQL();

		String sql = "SELECT * FROM base_info where usr_id=?";

		PreparedStatement ps = null;

		try {
			ps = mysql.getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				baseInfo.setUserId(userId);
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
		doGet(request, response);
	}

}
