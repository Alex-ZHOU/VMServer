package android.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Return;
import mysql.MySQL;
import utils.EncapsulateParseJson;

@WebServlet("/Android_ChangNickname")
public class Android_ChangNickname extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Android_ChangNickname() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId = Integer.valueOf(request.getParameter("UserId"));

		String nickname = request.getParameter("NewNickname");
		
		Return returnJson = new Return();

		System.out.println("Android_GetBaseInfo: UserId=" + userId + ",NewNickname=" + nickname);

		MySQL mysql = new MySQL();
		
		String sql = "UPDATE base_info set nickname=? WHERE usr_id=?";
		
		PreparedStatement ps = null;
		
		try {
			ps = mysql.getConnection().prepareStatement(sql);
			ps.setString(1, nickname);
			ps.setInt(2, userId);
			int row = ps.executeUpdate();
			if(row==1){
				returnJson.setSucceed(true);
			}else{
				returnJson.setSucceed(false);
			}
		} catch (SQLException e) {
			returnJson.setSucceed(false);
			returnJson.setStr(e.toString());
			e.printStackTrace();
		}finally {
			mysql.closeAll();
		}
		// 这句话的意思，是让浏览器用utf8来解析返回的数据
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		// 这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(EncapsulateParseJson.encapsulate(returnJson)).close();
		
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
