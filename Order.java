class Order{
	private String orderid;
	private String phoneno;
	private int qty;
	private String size;
	private int status;
	private double amt;
	static int DELIVERED=2;
	static int DELIVERING=1;
	
	public Order(String orderid,String phoneno,int qty,String size,int status,double amt){
		this.orderid=orderid;
		this.phoneno=phoneno;
		this.qty=qty;
		this.size=size;
		this.status=status;
		this.amt=amt;
	}
	
	public void setStatus(int no){
		this.status=no;
	}
	public String getPhoneno(){
		return phoneno;
	}
	public String getOrderId(){
		return orderid;
	}
	public int getQty(){
		return qty;
	}
	public int getStatus(){
		return status;
	}
	public double getAmount(){
		return amt;
	}
	public String getSize(){
		return size;
	}
}
