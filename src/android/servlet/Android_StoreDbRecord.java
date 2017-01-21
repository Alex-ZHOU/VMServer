package android.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.StoreDbRecord;
import mysql.biz.StoreRecordDbBiz;
import utils.EncapsulateParseJson;

/**
 * Servlet implementation class Android_StoreDbRecord
 */
@WebServlet("/Android_StoreDbRecord")
public class Android_StoreDbRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Android_StoreDbRecord() {
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
		System.out.println("Android_StoreDbRecord:" + str);
		StoreDbRecord recordDB = EncapsulateParseJson.parse(StoreDbRecord.class, str);
		
		Return back = new Return();
		
		if (new StoreRecordDbBiz().insert(recordDB)) {
			back.setSucceed(true);
		} else {
			back.setSucceed(false);
		}
		System.out.println(EncapsulateParseJson.encapsulate(back));
		response.getWriter().append(EncapsulateParseJson.encapsulate(back)).close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private class Return {
		private boolean isSucceed;

		@SuppressWarnings("unused")
		public boolean isSucceed() {
			return isSucceed;
		}

		public void setSucceed(boolean succeed) {
			isSucceed = succeed;
		}
	}

}
