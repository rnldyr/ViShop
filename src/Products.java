
public class Products {
	private String prodName;
	private int price;
	private int stocks;
	
	public String getName() {
		return prodName;
	}
	public void setName(String prodName) {
		this.prodName = prodName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStocks() {
		return stocks;
	}
	public void setStocks(int stocks) {
		this.stocks = stocks;
	}
	public Products(String prodName, int price, int stocks) {
		super();
		this.prodName = prodName;
		this.price = price;
		this.stocks = stocks;
	}
	
}
