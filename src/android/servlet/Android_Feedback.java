package android.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Feedback;
import entities.Return;
import mysql.MySQL;
import utils.EncapsulateParseJson;

/**
 * Servlet implementation class Android_Feedback
 */
@WebServlet("/Android_Feedback")
public class Android_Feedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Android_Feedback() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		InputStream in = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str = br.readLine();
		System.out.println("Android_Feedback:" + str);
		
		Return returnJson = new Return();
		
		Feedback feedback = EncapsulateParseJson.parse(Feedback.class,str);
		
		MySQL mysql = new MySQL();

		Connection connection = mysql.getConnection();
		
		String sql = "insert into feedback (type,time,usr_id,description) values (?,?,?,?);";
		
		PreparedStatement ps;
		int row = 0;

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setString(2,feedback.getTime());
			ps.setInt(3, feedback.getUserId());
			ps.setString(4, feedback.getDescription());
			row = ps.executeUpdate();
			if (row==1) {
				returnJson.setSucceed(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			mysql.closeAll();
		}
		
		response.getWriter().append(EncapsulateParseJson.encapsulate(returnJson)).close();		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
