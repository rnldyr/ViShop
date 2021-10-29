import java.util.Vector;

public class VeDex extends Shipping {
	private int minimumCost;

	public VeDex(String shipType, String shipId, String shipStatus, String address, int totalPrice, int shipTime,
			Vector<String> items) {
		super(shipType, shipId, shipStatus, address, totalPrice, shipTime, items);
		this.minimumCost = 150000;
	}

	@Override
	public int generateShippingFee(Shipping tes) {
		if(tes.getTotalPrice()<minimumCost) {
			return 10000;
		}
		return 0;
		
	}

	

	

	

	

}
