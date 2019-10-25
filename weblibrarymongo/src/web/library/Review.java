package web.library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

/**
 * Servlet implementation class Review
 */
@WebServlet("/review")
public class Review extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MongoClient mc = ConnectionManager.getMongo();
	MongoDatabase db = ConnectionManager.getDb("Library");
	MongoCollection<Document> books = db.getCollection("Books");
	String id;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Review() {
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
		String id = request.getParameter("id");
		String name, auna;
		this.id = id;
		MongoCursor<Document> c = books.find(Filters.eq("Book ID", id)).iterator();
		List<String> details = new ArrayList<>();
		while (c.hasNext()) {
			Document d = (Document) c.next();
			name = d.getString("title");
			auna = d.getString("author");
			details.add(name);
			details.add(auna);
		}
		request.setAttribute("det", details);
		request.setAttribute("id", id);
		request.getRequestDispatcher("addreview.jsp").forward(request, response);

//		String id1 = request.getParameter("id1");
//		MongoCursor<Document> c1 = books.find(Filters.eq("Book ID", id1)).iterator();
//		while (c1.hasNext()) {
//			Document d = (Document) c1.next();
//			Datac data = new Datac(d.getInteger("Book ID"), d.getString("title"), d.getString("author"),
//					d.getInteger("price"), d.getString("descr"));
//			List<Document> rev = (List<Document>) d.get("review");
//			List<String> rs = new ArrayList<>();
//			for (Document r : rev) {
//				rs.add(r.toString());
//			}
//			data.setReview(rs);
//			request.setAttribute("id", id1);
//			
//			request.setAttribute("data", data);
//			request.getRequestDispatcher("review.jsp").forward(request, response);
//		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String review = request.getParameter("txt");
		String id = request.getParameter("id").trim();
		MongoCursor<Document> c = books.find(Filters.eq("Book ID", Integer.parseInt(id))).iterator();
		System.out.println(c.hasNext());
		List<String> rl = new ArrayList<>();
		Document d = c.next();
		System.out.println("R1 "+d.getString("review"));
//		while (c.hasNext()) {
//			System.out.println("inside the loop....");
//			Document d = c.next();
//			List<Document> rev = (List<Document>) d.get("review");
//			if (rev.size() != 0) {
//				System.out.println("inside the if....");
//				for (Document r : rev) {
//					rl.add(r.toString());
//					System.out.println("inside the for....");
//				}
//			} else {
//				rl.add(review);
//				System.out.println("inside the elser....");
//			}
//		}
		System.out.println("review " + review);
		UpdateResult ur =   books.updateOne(Filters.eq("Book ID", Integer.parseInt(id)), Updates.set("review", review));
		System.out.println(ur.getModifiedCount());

	}

}
