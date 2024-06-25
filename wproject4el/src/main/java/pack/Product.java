package pack;

public class Product {
	private String name;
	private double price;
	private String description; //상품 설명
	private java.util.Date releaseDate; //출시일
	
	public Product(String name, double price,String description,java.util.Date releaseDate) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.releaseDate = releaseDate;
		
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public java.util.Date getReleaseDate() {
		return releaseDate;
	}
}
