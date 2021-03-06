package android.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysql.biz.ImageIdToAddressBiz;

/**
 * Servlet implementation class Android_GetPic
 */
@WebServlet("/Android_GetPic")
public class Android_GetPic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Android_GetPic() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int imageId = Integer.valueOf(request.getParameter("ImageId"));
		
		System.out.println("Android_GetPic: ImageId=" + imageId);
			
		String picAddress = new ImageIdToAddressBiz().getAddressByImageId(imageId);
		
		FileInputStream fis = null;
		
		response.setContentType("image/gif");
		try {
			OutputStream out = response.getOutputStream();
			File file = new File(getServletContext().getRealPath("/") + picAddress);
			fis = new FileInputStream(file);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			out.write(b);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
