import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;

public class Main {
	
	Scanner scan = new Scanner(System.in);
	Random r = new Random();
	Vector<Products> prod = new Vector<Products>();
	User user;
	Shipment thread;
	Vector<Shipment> threads = new Vector<Shipment>();
	
	
	void clr() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}
	
	void welcomeMenu() {
		boolean tf;
		String name;
		
		System.out.println("Weclome to Vi Shop! ^-^");
		System.out.println();
		System.out.println("To create an order, please input your name.");
		do {
			tf = true;
			System.out.print("Name [Min. 3 characters | Must contin two words]: ");
			name = scan.nextLine();
			if(name.length() < 3) {
				System.out.println("Name must be 3 characters or more");
				tf = false;
			}else if(!name.contains(" ")) {
				System.out.println("Name must be two words!");
				tf = false;
			}
		}while(!tf);
		user = new User(name);
	}
	
	void viewPurchaseHistory() {
		if(user.getFinished().isEmpty()) {
			System.out.println("There are no finished orders yet!");
			scan.nextLine();
			return;
		}
		clr();
		System.out.println("Finished Orders");
		System.out.println("===============");
		System.out.println();
		for (int i = 0; i < user.getFinished().size(); i++) {
			System.out.println("Shipping ID   : " + user.getFinished().get(i).getShipId());
			System.out.println("Address       : " + user.getFinished().get(i).getAddress());
			System.out.println("Total Price   : " + user.getFinished().get(i).getTotalPrice());
			System.out.println("Item(s):");
			for (String str : user.getFinished().get(i).getItems()) {
				System.out.println(str);
			}
			System.out.println();
		}
		System.out.print("Press enter to continue..");
		scan.nextLine();
	}
	
	void viewOngoing() {
		if(user.getOngoing().isEmpty()) {
			System.out.println("No purchase history.");
			System.out.println();
			return;
		}
		for (int i = 0; i < user.getOngoing().size(); i++) {
			System.out.println(user.getOngoing().get(i).getShipType());
			System.out.println("-------");
			System.out.println("Shipping ID        : " + user.getOngoing().get(i).getShipId());
			System.out.println("Shipping Status    : " + user.getOngoing().get(i).getShipStatus());
			if(user.getOngoing().get(i).getShipStatus().equalsIgnoreCase("Failed")) {
				System.out.println("Sorry for the inconvenience, your packet can't be traced.");
				System.out.println("Insurance fee will be sent directly to the according address.");
			}
			System.out.println();
		}
	}
	
	void viewProduct() {
		for (int i = 0; i < prod.size(); i++) {
			System.out.println("No     : " + (i+1));
			System.out.println("Name   : " + prod.get(i).getName());
			System.out.println("Price  : " + prod.get(i).getPrice());
			System.out.println("Stocks : " + prod.get(i).getStocks());
			System.out.println();
		}
	}
	
	void buyItem() {
		int pick = 0;
		int qty = 0;
		String buyMore = "N";
		boolean tf;
		Shipping temp = null;
		Vector<String> lists = new Vector<String>();
		
		if(prod.isEmpty()){
			System.out.println("Sorry all of our products are currently out of stock!");
			scan.nextLine();
			return;
		}
		
		do {
			clr();
			viewProduct();
			do {
				System.out.print("Pick product to buy [1.." + prod.size() + "]: ");
				try {
					pick = scan.nextInt();
					scan.nextLine();
				} catch (Exception e) {
					scan.nextLine();
				}
			}while(pick < 1 || pick > prod.size());
			do {
				tf = true;
				System.out.print("Input quantity [1.." + prod.get(pick-1).getStocks() + "]: ");
				try {
					qty = scan.nextInt();
					scan.nextLine();
				} catch (Exception e) {
					scan.nextLine();
				}
				if(qty < 1 || qty > prod.get(pick-1).getStocks()) {
					tf = false;
					System.out.println("Insufficient product stock. Maximum purchase of this product is only "+ prod.get(pick-1).getStocks());
				}
			}while(!tf);
			user.getCart().add(new Products(prod.get(pick-1).getName(), (prod.get(pick-1).getPrice() * qty), qty));
			if(prod.get(pick-1).getStocks() - qty == 0) {
				prod.remove(pick-1);
			}else {
				prod.get(pick-1).setStocks((prod.get(pick-1).getStocks() - qty));
			}
			do {
				System.out.print("Would you like to add more product into your cart? [Y/N]: ");
				buyMore = scan.nextLine();
			}while(!buyMore.equals("Y") && !buyMore.equals("N"));
			if(prod.isEmpty()) {
				System.out.println("Sorry all of our products are currently out of stock!");
				break;
			}
		}while(buyMore.equals("Y"));
		String address;
		String shipType;
		int totalPrice = 0;
		do {
			System.out.print("Input shipping address [must begin with'Jl. ' (case-sensitive)]: ");
			address = scan.nextLine();
		}while(!address.startsWith("Jl. ") || address.length() < 11);
		
		do {
			System.out.print("Input Shipping Service [VeDex | ViCepat (case-insensitive)]: ");
			shipType = scan.nextLine();
		}while(!shipType.equalsIgnoreCase("VeDex") && !shipType.equalsIgnoreCase("ViCepat"));
		
		for (int i = 0; i < user.getCart().size(); i++) {
			totalPrice += user.getCart().get(i).getPrice();
		}
		
		System.out.println("Product's Prices     : Rp. " + totalPrice);
		String str;
		for (Products p1 : user.getCart()) {
			str = "- " + p1.getStocks() + "pcs " + p1.getName();
			lists.add(str);
		}
		int shippingFee  = 0;
		if(shipType.equalsIgnoreCase("VeDex")) {
			temp = new VeDex("VeDex", UUID.randomUUID().toString(), "Ongoing", address, totalPrice, 20, (Vector<String>) lists.clone());
		}else {
			temp = new ViCepat("ViCepat", UUID.randomUUID().toString(), "Ongoing", address, totalPrice, 10, (Vector<String>) lists.clone());
		}
		shippingFee = temp.generateShippingFee(temp);
		System.out.println("Shipping Fee         : Rp. " + shippingFee);
		totalPrice += shippingFee;
		temp.setTotalPrice(totalPrice);
		System.out.println("Grand Total          : Rp. " + totalPrice);
		user.getOngoing().add(temp);
		
		if(user.getOngoing().lastElement().getShipType().equalsIgnoreCase("VeDex")) {
			if(r.nextInt(10) == 0) {
				user.getOngoing().lastElement().setShipStatus("Failed");
			}
		}
		if(!user.getOngoing().lastElement().getShipStatus().equals("Failed")) {
			thread = new Shipment(user.getOngoing().lastElement(), user);
			threads.add(thread);
		}
		System.out.print("Press enter to continue..");
		scan.nextLine();
		
		user.getCart().clear();;
		lists.clear();
	
	}
	
	void refresh() {
		for (int i = 0; i < user.getToDelete().size(); i++) {
			user.getFinished().add(user.getToDelete().get(i));
			user.getOngoing().remove(user.getToDelete().get(i));
		}
		user.getToDelete().clear();
		
	}
	
	void mainMenu() {
		int choose = 0;
		
		do {
			clr();
			viewOngoing();
			System.out.println("Hello, " + user.getName());
			System.out.println("1. Shop at Vi");
			System.out.println("2. Refresh My Shipping Status");
			System.out.println("3. View Purchase History");
			System.out.println("4. Exit");
			System.out.print(">> ");
			try {
				choose = scan.nextInt();
				scan.nextLine();
			} catch (Exception e) {
				scan.nextLine();
			}
			switch(choose){
				case 1:
					buyItem();
					break;
				case 2:
					refresh();
					break;
				case 3:
					viewPurchaseHistory();
					break;
			}
		}while(choose != 4);
		
	}

	public Main() {
		prod.add(new Products("The Aubree Niacinamide Serum", 99000, (r.nextInt(31)+20)));
		prod.add(new Products("Tiff Body Cacao Coffee Scrub", 150000, (r.nextInt(31)+20)));
		prod.add(new Products("Kleveru Glass Skin Serum", 143000, (r.nextInt(31)+20)));
		prod.add(new Products("Sensatia Botanicals Unscented Facial-C Serum", 180000, (r.nextInt(31)+20)));
		prod.add(new Products("Mineral Botanicals Vanila Lip Scrub", 55000, (r.nextInt(31)+20)));
		prod.add(new Products("Think Hale Let's Mask", 129000, (r.nextInt(31)+20)));
		prod.add(new Products("Faith In Face Cica Jelly Mask", 29000, (r.nextInt(31)+20)));
		prod.add(new Products("Lacoco Swallow Facial Foam", 165000, (r.nextInt(31)+20)));
		prod.add(new Products("Everwhite Brightening Essence Serum", 125000, (r.nextInt(31)+20)));
		welcomeMenu();
		mainMenu();
	}

	public static void main(String[] args) {
		new Main();

	}

}
