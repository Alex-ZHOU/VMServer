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

import entities.StoreInfo;
import mysql.MySQL;
import utils.EncapsulateParseJson;

/**
 * Servlet implementation class Android_StroeInfo
 */
@WebServlet("/Android_StroeInfo")
public class Android_StroeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StoreInfo mStoreInfo;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Android_StroeInfo() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int advertisementId = Integer.valueOf(request.getParameter("AdvertisementId"));

		System.out.println("Android_StroeInfo: AdvertisementId=" + advertisementId);

		getStoreInfo(advertisementId);

		System.out.println("Android_StroeInfo return:" + EncapsulateParseJson.encapsulate(mStoreInfo));
		// 这句话的意思，是让浏览器用utf8来解析返回的数据
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		// 这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(EncapsulateParseJson.encapsulate(mStoreInfo)).close();
	}

	private void getStoreInfo(int advertisementId) {

		mStoreInfo = new StoreInfo();

		MySQL mysql = new MySQL();

		String sql = "SELECT * FROM store_info where advertisement_id=?";

		PreparedStatement ps = null;

		try {
			ps = mysql.getConnection().prepareStatement(sql);
			ps.setInt(1, advertisementId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				mStoreInfo.setAdvertisementId(rs.getInt("advertisement_id"));

				mStoreInfo.setImageId(rs.getInt("image_id"));

				mStoreInfo.setStoreId(rs.getInt("store_id"));

				mStoreInfo.setTitle(rs.getString("title"));

				mStoreInfo.setLatitude(rs.getDouble("latitude"));

				mStoreInfo.setLongitude(rs.getDouble("longitude"));

				mStoreInfo.setSummary(rs.getString("summary"));

				mStoreInfo.setAverageDb(rs.getInt("average_db"));
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
