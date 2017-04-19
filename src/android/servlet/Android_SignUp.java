package android.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Login;
import mysql.MySQL;
import utils.EncapsulateParseJson;

/**
 * Servlet implementation class Android_SignUp
 */
@WebServlet("/Android_SignUp")
public class Android_SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Login mLogin;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Android_SignUp() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account = request.getParameter("loginAccount");
		String password = request.getParameter("loginPwd");

		mLogin = new Login();

		MySQL mysql = new MySQL();

		Connection connection = (Connection) mysql.getConnection();

		String sql = "SELECT * FROM usr_info where usr_account=?";

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, account);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// 账号已经存在
				mLogin.setReturn("account");
			} else {

				MySQL mysql2 = new MySQL();
				Connection connection2 = (Connection) mysql2.getConnection();
				sql = "insert into usr_info (usr_account,usr_password) values (?,?);";
				PreparedStatement ps2 = connection2.prepareStatement(sql);
				ps2.setString(1, account);
				ps2.setString(2, password);
				int row = ps2.executeUpdate();
				if (row==1) {
					sql = "SELECT * FROM usr_info where usr_account=?";
					ps2 = connection2.prepareStatement(sql);
					ps2.setString(1, account);
					ResultSet rs2 = ps2.executeQuery();
					if(rs2.next()){
						int usr_id = rs2.getInt("usr_id");
						sql = "insert into base_info (usr_id,usr_account,nickname,record_times,average_db,max_db,min_db,record_minter,head_protrait) values (?,?,?,0,0,0,0,0,3);";
						ps2 = connection2.prepareStatement(sql);
						ps2.setInt(1, usr_id);
						ps2.setString(2, account);
						ps2.setString(3, account);
						row = ps2.executeUpdate();
						if(row ==1){
							mLogin.setReturn("success");
						}
					}
					
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			mysql.closeAll();
		}

		System.out.println(EncapsulateParseJson.encapsulate(mLogin));
		response.getWriter().append(EncapsulateParseJson.encapsulate(mLogin).toString()).close();
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
