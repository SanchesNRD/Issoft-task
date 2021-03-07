package orders;

public class ProductInfo {
	private String name;
	private String id;
	private int quantity;
	private int pricePerUnit;
	
	public ProductInfo(){
		name = null;
		id = null;
		quantity = 0;
		pricePerUnit = 0;
	};
	
	public ProductInfo(String name, String id, int quantity, int pricePerUnit) {
		this.name = name;
		this.id = id;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
	};
	
	//Name
	public String getName() { return name; }
	public void setName(String value) { name = value; }
	
	
	//id
	public String getId() { return id; }
	public void setId(String value) { id = value; }
	
	//quantity
	public int getQuantity() {	return quantity; }
	public void setQuantity(int value) { quantity = value; }
	
	
	//price
	public int getPrice() { return pricePerUnit; }
	public void setPrice(int value) { pricePerUnit = value; }
	
	public int getFullPrice() { return (pricePerUnit * quantity); }
	
	
	
	
}
