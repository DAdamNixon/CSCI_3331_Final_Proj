package main.java;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Cart {
	
	MainPage main;
	InventoryController invCont;

	private CartView view;
	private LinkedList<Merchandise> cart;

	private boolean flag;

	public Cart(MainPage mainPage, InventoryController iController) {
		this.main = mainPage;
		this.invCont = iController;
		this.cart = new LinkedList<>();
		this.view = new CartView(this);
		this.flag = false;
	}

	public CartView getView() {
		return this.view;
	}

	public void addItem(int itemNum) {
		Merchandise item = invCont.get(itemNum);
		if (item != null) {
			cart.add(item);
			view.makeCards();
		}
	}

	public void removeItem(int itemNum) {
		this.cart.remove(invCont.get(itemNum));
		view.makeCards();
	}

	public float getSubtotal() {
		float subtotal = 0f;
		for (Merchandise merchandise : cart) {
			subtotal += merchandise.getPrice();
		}
		return subtotal;
	}
	public LinkedList<Merchandise> getCart() {
		return this.cart;
	}

	public boolean getFlag() {
		return this.flag;
	}

	public void setFlag() {
		this.flag = !flag;
	}

	public void clear() {
		this.cart.clear();
		view.makeCards();
	}

	public void purchase() {
		writeOrder();
		clear();
		//updateInventory();
	}

	private void writeOrder() {
		File orders = new File(Resources.dataPath("orders.csv"));
		try(FileWriter fw = new FileWriter(orders, true)){
			fw.append(String.format("%s\n", main.getUser().username));
			for (Merchandise merchandise : cart) {
				fw.append(String.format("%d\n", merchandise.itemNumber));
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
