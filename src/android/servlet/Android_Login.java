package android.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import android.JSONObject;
import mysql.MySQL;

@WebServlet("/Android_Login")
public class Android_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String mAccount;

	private String mPassword;
	
	JSONObject obj = new JSONObject();

	public Android_Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		mAccount = request.getParameter("loginAccount");
		mPassword = request.getParameter("loginPwd");
		System.out.println("Account:" + mAccount + "Passowd" + mPassword);
		System.out.println(loginInCheck(mAccount, mPassword));
		loginInCheck(mAccount, mPassword);
		obj.clear();
		loginInCheck(mAccount, mPassword);
		
		response.getWriter().append(obj.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 登陆账号密码比对，正确则登陆成功
	private boolean loginInCheck(String account, String passwoed) {

		MySQL mMySQL2048 = new MySQL();

		String sql = "SELECT * FROM usr_info where usr_account='" + account + "';";
		ResultSet rs = mMySQL2048.executeQuery(sql);

		try {
			if (rs.next()) {
				if (rs.getString("usr_password").equals(passwoed)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

}
