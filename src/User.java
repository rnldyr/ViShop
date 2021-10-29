import java.util.Vector;

public class User {
	private String name;
	private Vector<Products> cart = new Vector<Products>();
	private Vector<Shipping> ongoing = new Vector<Shipping>();
	private Vector<Shipping> finished = new Vector<Shipping>();
	private Vector<Shipping> toDelete = new Vector<Shipping>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<Products> getCart() {
		return cart;
	}
	public void setCart(Vector<Products> cart) {
		this.cart = cart;
	}
	public Vector<Shipping> getOngoing() {
		return ongoing;
	}
	public void setOngoing(Vector<Shipping> ongoing) {
		this.ongoing = ongoing;
	}
	public Vector<Shipping> getFinished() {
		return finished;
	}
	public void setFinished(Vector<Shipping> finished) {
		this.finished = finished;
	}
	public Vector<Shipping> getToDelete() {
		return toDelete;
	}
	public void setToDelete(Vector<Shipping> toDelete) {
		this.toDelete = toDelete;
	}
	public User(String name) {
		this.name = name;
	}
	
}
