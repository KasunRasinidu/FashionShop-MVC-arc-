class Main{
	public static void main(String args[]){
		ItemList itemList=ItemList.createItemList();
		new Home(itemList).setVisible(true);
		/*int [] oid={1,2,3,4,5,6,7,8};
		String [] soid={"ODR#00001","ODR#00002","ODR#00003","ODR#00004","ODR#00005","ODR#00006","ODR#00007","ODR#00008"};
		String [] no={"0776198410","0703859852","0712190023","0776198410","0703859852","0775544336","0703859852","0712002200"};
		int [] qty={3,4,1,3,6,2,1,3};
		String [] size={"XS","XL","XXL","M","M","S","XXL","M"};
		int [] status={0,1,2,1,0,2,0,2};
		double[] amts={1800,4400,1200,2700,5400,1600,1200,2700};
		for (int i = 0; i < oid.length; i++){
			itemList.addOrder(new Order(soid[i],no[i],qty[i],size[i],status[i],amts[i]));
		}*/
		//new Home().setVisible(true);
	}
}
