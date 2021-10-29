import java.util.Vector;

public abstract class Shipping {
	private String shipType;
	private String shipId;
	private  String shipStatus;
	private String address;
	private int totalPrice;
	private int shipTime;
	private Vector<String> items = new Vector<String>();
	
	public abstract int generateShippingFee(Shipping tes);
	
	public String getShipType() {
		return shipType;
	}
	public void setShipType(String shipType) {
		this.shipType = shipType;
	}
	public String getShipId() {
		return shipId;
	}
	public void setShipId(String shipId) {
		this.shipId = shipId;
	}
	public String getShipStatus() {
		return shipStatus;
	}
	public void setShipStatus(String shipStatus) {
		this.shipStatus = shipStatus;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Vector<String> getItems() {
		return items;
	}
	public void setItems(Vector<String> items) {
		this.items = items;
	}
	public Shipping(String shipType, String shipId, String shipStatus, String address, int totalPrice, int shipTime, Vector<String> items) {
		this.shipType = shipType;
		this.shipId = shipId;
		this.shipStatus = shipStatus;
		this.address = address;
		this.totalPrice = totalPrice;
		this.shipTime = shipTime;
		this.items = items;
	}
	public int getShipTime() {
		return shipTime;
	}
	public void setShipTime(int shipTime) {
		this.shipTime = shipTime;
	}
	
	
}
