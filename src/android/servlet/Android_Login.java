package android.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Login;

import mysql.MySQL;
import utils.EncapsulateParseJson;


@WebServlet("/Android_Login")
public class Android_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String mAccount;

	private String mPassword;

	private Login mLogin;

	public Android_Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		mAccount = request.getParameter("loginAccount");
		mPassword = request.getParameter("loginPwd");

		System.out.println("Account:" + mAccount + ",Passowd:" + mPassword);
		mLogin = new Login();
		loginInCheck(mAccount, mPassword);
		System.out.println(EncapsulateParseJson.encapsulate(mLogin));
		response.getWriter().append(EncapsulateParseJson.encapsulate(mLogin).toString()).close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 登陆账号密码比对，正确则登陆成功
	private boolean loginInCheck(String account, String passwoed) {
		MySQL mysql= new MySQL();
		
		Connection connection = (Connection) mysql.getConnection();

		String sql = "SELECT * FROM usr_info where usr_account=?";

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, account);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				if (rs.getString("usr_password").equals(passwoed)) {
					mLogin.setReturn("success");
					Login.User user = mLogin.getUser();
					user.setUserName(rs.getString("usr_account"));
					user.setUserId(rs.getInt("usr_id"));
					return true;
				} else {
					mLogin.setReturn("password");
				}
			} else {
				mLogin.setReturn("account");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			mysql.closeAll();
		}

		return false;

	}

}
