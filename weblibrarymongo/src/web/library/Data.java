package web.library;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import web.library.*;

/**
 * Servlet implementation class Data
 */
@WebServlet("/data")
public class Data extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MongoClient mc = ConnectionManager.getMongo();
	MongoDatabase db = ConnectionManager.getDb("Library");
	MongoCollection<Document> books = db.getCollection("Books");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		MongoClient mc = ConnectionManager.getMongo();
//		MongoDatabase db = ConnectionManager.getDb("Library");
//		MongoCollection<Document> books = db.getCollection("Books");
		String srch = request.getParameter("srch");
		MongoCursor<Document> cursor;
		if (srch == null) {
			srch = "";
		}
		if ( srch.length() != 0) {
			cursor = books.find(Filters.eq("title", srch)).limit(30).iterator();
		} else {

			cursor = books.find().iterator();
		}
		List<Datac> dataList = new LinkedList<>();

		while (cursor.hasNext()) {
			Document d = (Document) cursor.next();

			Datac data = new Datac(d.getInteger("Book ID"), d.getString("title"), d.getString("author"),
					d.getInteger("price"), d.getString("descr"));
			dataList.add(data);
		}
		// ConnectionManager.close();

		request.setAttribute("list", dataList);
		request.getRequestDispatcher("update.jsp").forward(request, response);
		
//		String btn = request.getParameter("btn");
//		String btn1 = request.getParameter("btn1");
//		if(btn!=null) {
//			request.setAttribute("id", btn);
//			request.getRequestDispatcher("/review").forward(request, response);
//		}
//		else {
//			request.setAttribute("id1", btn1);
//			request.getRequestDispatcher("/review").forward(request, response);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int c = 0;
		int c1 = 0;
		int id, idi = 0;
		MongoCursor<Document> cursor = books.find().iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			idi = doc.getInteger("Book ID");
			c++;
		}
		if (c != 0) {
			id = idi;
		} else {
			id = 100;
		}
		String tit = request.getParameter("title");
		String auna = request.getParameter("auna");
		int price = Integer.parseInt(request.getParameter("price"));
		String desc = request.getParameter("desc");
		Document document = new Document("Book ID", id + 1).append("title", tit).append("author", auna)
				.append("price", price).append("descr", desc).append("img", "").append("review", Arrays.asList(""));
		books.insertOne(document);
		c1++;
		request.setAttribute("counter", c1);
		request.getRequestDispatcher("add.jsp").forward(request, response);
		ConnectionManager.close();
	}

}
