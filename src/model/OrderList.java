package model;
public class OrderList {

	private String productCode;
	private int quantity;
	
	public OrderList(String productCode, int quantity) {
		this.productCode = productCode;
		this.quantity = quantity;
	}
	
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getInfo() {
		String info = "";
		info += "Product code: "+productCode+"\nQuantity: "+quantity;
		return info;
	}
}