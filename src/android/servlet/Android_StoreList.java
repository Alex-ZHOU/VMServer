package android.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysql.biz.StoreInfoBiz;
import mysql.entities.StoreInfo;
import utils.EncapsulateParseJson;

/**
 * Servlet implementation class Android_StoreList
 */
@WebServlet("/Android_StoreList")
public class Android_StoreList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Android_StoreList() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter("Type");

		List<StoreInfo> list = new StoreInfoBiz().getStoreListByType(type);

		List<entities.StoreInfo> returnList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			entities.StoreInfo returnStoreInfo = new entities.StoreInfo();
			returnStoreInfo.setStoreId(list.get(i).getStore_id());
			returnStoreInfo.setImageId(list.get(i).getImage_id());
			returnStoreInfo.setTitle(list.get(i).getTitle());
			returnStoreInfo.setSummary(list.get(i).getSummary());
			returnStoreInfo.setAverageDb(list.get(i).getAverage_db());
			returnList.add(returnStoreInfo);
		}
		// 这句话的意思，是让浏览器用utf8来解析返回的数据
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		// 这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(EncapsulateParseJson.encapsulate(returnList)).close();
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
