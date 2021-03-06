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

import entities.BaseInfo;
import mysql.MySQL;
import utils.EncapsulateParseJson;

/**
 * Servlet implementation class Android_GetBaseInfo
 */
@WebServlet("/Android_GetBaseInfo")
public class Android_GetBaseInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//private int userId;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Android_GetBaseInfo() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId = Integer.valueOf(request.getParameter("UserId"));

		System.out.println("Android_GetBaseInfo:" + userId);

		BaseInfo baseInfo = getBaseInfo(userId);

		String json = EncapsulateParseJson.encapsulate(baseInfo);

		System.out.println(json);
		// 这句话的意思，是让浏览器用utf8来解析返回的数据
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		// 这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
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
				baseInfo.setHeadProtrait(rs.getInt("head_protrait"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysql.closeAll();
		}
		return baseInfo;
	}

}
