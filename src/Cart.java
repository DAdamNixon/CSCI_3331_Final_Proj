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
		this.view = new CartView();
		this.flag = false;
	}

	public CartView getView() {
		return this.view;
	}

	public void addItem(int itemNum) {
		Merchandise item = invCont.get(itemNum);
		if (item != null) {
			cart.add(item);
			view.makeCards(this);
		}
	}

	public void removeItem(int itemNum) {
		this.cart.remove(invCont.get(itemNum));
		view.makeCards(this);
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
}
