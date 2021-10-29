
public class Shipment implements Runnable {

	private Thread t;
	private Shipping sh;
	private User user;
	private int shipTime;
	
	
	public Shipment(Shipping sh, User user) {
		t = new Thread(this);
		this.sh = sh;
		this.user = user;
		this.shipTime = sh.getShipTime();
		t.start();
	}


	@Override
	public void run() {
		while(shipTime != 0) {
			shipTime--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		user.getToDelete().add(sh);
		
	}

}
