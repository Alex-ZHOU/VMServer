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

import entities.ShareRecordDb;
import mysql.MySQL;
import utils.EncapsulateParseJson;

/**
 * Servlet implementation class Android_ShareRecord
 */
@WebServlet("/Android_ShareRecord")
public class Android_ShareRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Android_ShareRecord() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Android_ShareRecord");

		List<ShareRecordDb> list = new ArrayList<>();

		MySQL mysql = new MySQL();

		String sql = "SELECT * from store_record_db where reocrd_status = 1;";

		PreparedStatement ps = null;

		try {
			ps = mysql.getConnection().prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				MySQL mysql2 = new MySQL();
				String sql2 = "SELECT * FROM base_info where usr_id=?;";
				PreparedStatement ps2 = null;
				ps2 = mysql2.getConnection().prepareStatement(sql2);
				ps2.setInt(1, rs.getInt("usr_id"));
				ResultSet rs2 = ps2.executeQuery();
				int head_protrait = 0;
				while (rs2.next()) {
					head_protrait = rs2.getInt("head_protrait");

				}

				MySQL mysql3 = new MySQL();
				String sql3 = "SELECT * FROM store_info where store_id=?;";
				PreparedStatement ps3 = null;
				ps3 = mysql3.getConnection().prepareStatement(sql3);
				ps3.setInt(1, rs.getInt("store_id"));
				ResultSet rs3 = ps3.executeQuery();

				String title = null;
				while (rs3.next()) {
					title = rs3.getString("title");
				}
				if (title == null) {

				}

				ShareRecordDb shareRecordDb = new ShareRecordDb();

				shareRecordDb.setUserHeadPortraitImageId(head_protrait);
				shareRecordDb.setTime(rs.getInt("year") + "-" + rs.getInt("month") + "-" + rs.getInt("day"));
				shareRecordDb.setStoreName(title);

				list.add(shareRecordDb);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		// 这句话的意思，是让浏览器用utf8来解析返回的数据
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		// 这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(EncapsulateParseJson.encapsulate(list)).close();

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
