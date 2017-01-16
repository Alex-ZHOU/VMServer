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

import entities.AdvertisingColumn;
import mysql.MySQL;
import utils.EncapsulateParseJson;

/**
 * Servlet implementation class Android_AdvertisingColumn
 */
@WebServlet("/Android_AdvertisingColumn")
public class Android_AdvertisingColumn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Android_AdvertisingColumn() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<AdvertisingColumn> list = new ArrayList<>();

		MySQL mysql = new MySQL();

		String sql = "SELECT * FROM advertising_column";

		PreparedStatement ps = null;

		try {
			ps = mysql.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AdvertisingColumn advertisingColumn = new AdvertisingColumn();

				advertisingColumn.setAdvertisement_Id(rs.getInt("advertisement_id"));
				advertisingColumn.setImageId(rs.getInt("image_id"));
				advertisingColumn.setTitle(rs.getString("title"));
				System.out.println(rs.getString("title"));
				
				list.add(advertisingColumn);
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
		//这句话的意思，是让浏览器用utf8来解析返回的数据 
		response.setHeader("Content-type", "text/html;charset=UTF-8"); 
		//这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859  
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
