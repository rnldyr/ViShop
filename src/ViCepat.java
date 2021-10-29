import java.util.Vector;

public class ViCepat extends Shipping {

	public ViCepat(String shipType, String shipId, String shipStatus, String address, int totalPrice, int shipTime,
			Vector<String> items) {
		super(shipType, shipId, shipStatus, address, totalPrice, shipTime, items);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int generateShippingFee(Shipping tes) {
		return 12000;
		
	}

	

	

	

	

	
	

}
