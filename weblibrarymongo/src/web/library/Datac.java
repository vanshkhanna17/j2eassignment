package web.library;

import org.bson.types.ObjectId;
import java.util.*;

public class Datac {

	private int id;
	private String name;
	private String auna;
	private int price;
	private String desc;
	private String img="";
	private List<String> review = new ArrayList<>();
	public Datac() {

	}

	public Datac(int id, String name, String auna, int price, String desc) {
		super();
		this.id = id;
		this.name = name;
		this.auna = auna;
		this.price = price;
		this.desc = desc;
	}
	public Datac(int id, String name, String auna, int price, String desc,String review) {
		super();
		this.id = id;
		this.name = name;
		this.auna = auna;
		this.price = price;
		this.desc = desc;
		this.review.add(review);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAuna() {
		return auna;
	}

	public void setAuna(String auna) {
		this.auna = auna;
	}
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setReview(List r) {
		this.review = r;
	}
}
