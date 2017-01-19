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

import entities.StoreRecordDb;
import mysql.MySQL;
import utils.EncapsulateParseJson;

/**
 * Servlet implementation class Android_StoreRecordDb
 */
@WebServlet("/Android_StoreRecordDb")
public class Android_StoreRecordDb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Android_StoreRecordDb() {
		super();
	}

	private List<StoreRecordDb> mList;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int storeId = Integer.valueOf(request.getParameter("StoreId"));

		System.out.println("Android_StoreRecordDb: StoreId=" + storeId);

		getData(storeId);

		System.out.println(EncapsulateParseJson.encapsulate(mList));

		response.getWriter().append(EncapsulateParseJson.encapsulate(mList)).close();
	}

	private void getData(int storeRecordId) {
		mList = new ArrayList<>();

		MySQL mysql = new MySQL();

		String sql = "SELECT * FROM store_record_db where store_id = ? && reocrd_status=1";

		PreparedStatement ps = null;

		try {
			ps = mysql.getConnection().prepareStatement(sql);
			ps.setInt(1, storeRecordId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				StoreRecordDb storeRecordDb = new StoreRecordDb();

				storeRecordDb.setUserId(rs.getInt("usr_id"));

				storeRecordDb.setYear(rs.getInt("year"));

				storeRecordDb.setMonth(rs.getInt("month"));

				storeRecordDb.setDay(rs.getInt("day"));

				mList.add(storeRecordDb);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
