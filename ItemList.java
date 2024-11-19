import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;

class ItemList{
	public  Order[] orderArray=new Order[10];//()
	String [] size_con={"XS","S","M","L","XL","XXL"};
	double [] price_con={600,800,900,1000,1100,1200};
	private int nextIndex;
	private int ID;
	private static ItemList instance;
	private ItemList(){
		try{
			Scanner scan=new Scanner(new File("Orders.txt"));
			while(scan.hasNext()){
				String line=scan.nextLine();
				String[] rowData=line.split(" ");
				String id=rowData[0];
				String phoneNo=rowData[1];
				int qty=Integer.parseInt(rowData[2]);
				String size=rowData[3];
				int status=Integer.parseInt(rowData[4]);
				double amt=Double.parseDouble(rowData[5]);
				addOrderFromTxt(new Order(id,phoneNo,qty,size,status,amt));
				//System.out.println(Arrays.toString(orderArray));
			}
		}catch(IOException ex){
				
		}	
	}
	
	public static ItemList createItemList(){
		if(instance==null){
			instance =new ItemList();
		}
		return instance;
	}
	
	private boolean isFull(){
		if (orderArray.length<=nextIndex){
			return true;
		}
		return false;
	}
	
	public void addOrderFromTxt(Order order){
		if(isFull()){
			extend();
		}
		orderArray[nextIndex]=order;
		nextIndex++;
		ID++;
	}
	
	public void addOrder(Order order){
		if(isFull()){
			extend();
		}
		orderArray[nextIndex]=order;
		try{
			FileWriter fw=new FileWriter("Orders.txt",true);
			fw.	write(order.getOrderId()+" "+order.getPhoneno()+" "+order.getQty()+" "+order.getSize()+" "+order.getStatus()+" "+order.getAmount());
			fw.write("\n");
			fw.flush();
			
		}catch(IOException ex){
			
		}
		nextIndex++;
		ID++;
	}
	
	private void extend(){
		Order[] temp=new Order[(int)(orderArray.length*1.25)];
		for (int i = 0; i <nextIndex; i++){
			temp[i]=orderArray[i];
		}
		//System.out.println("Here");
		orderArray=temp;
	}
	
	public int select(String s){
		for (int i=0;i<size_con.length;i++){
			if(size_con[i].equals(s)){
				return i;
			}	
		}return -1;
	}
	
	public int selectphoneno(String s){
		for (int i=0;i<nextIndex;i++){
			if(orderArray[i].getPhoneno().equals(s)){
				return i;
			}	
		}return -1;
	}
	
	public int selectOrderID(String s){
		for (int i=0;i<nextIndex;i++){
			if(orderArray[i].getOrderId().equals(s)){
				//System.out.println("Here");
				return i;
				
			}	
		}return -1;
	}
	
	public void delete(int index){
		Order[] temp=new Order[orderArray.length];
		int k=0;
		for(int i=0;i<nextIndex;i++){
			if(i!=index){
				temp[k]=orderArray[i];
				k++;
			}
		}
		nextIndex--;
		orderArray=temp;
		for (int i = 0; i <nextIndex; i++){
			try{
				FileWriter fw=new FileWriter("Orders.txt");
				fw.	write(orderArray[i].getOrderId()+" "+orderArray[i].getPhoneno()+" "+orderArray[i].getQty()+" "+orderArray[i].getSize()+" "+orderArray[i].getStatus()+" "+orderArray[i].getAmount());
				fw.write("\n");
				fw.flush();
			
			}catch(IOException ex){
				//
			}
		}
	}
	
	public String getOrderID()throws IOException{
		Scanner scan=new Scanner(new File("Orders.txt"));
		String line=null;
		while(scan.hasNext()){
			line=scan.nextLine();
		}
		
		if(line==null){
			return "ODR#00001";
		}
		String lastPart=line.substring(4,9);
		int lastDigits=Integer.parseInt(lastPart);
		lastDigits++;
		String id=String.format("%4s%05d","ODR#",(lastDigits));
		return id;
	}
	
	public int size(){
		return nextIndex;
	}
	
	public int getID(){
		return nextIndex;
	}
	
	public Order[] getItemListArray(){
		return orderArray;
	}
	
	public String[] copy(String[] ar){
		String[] temp=new String[ar.length];
		for (int j = 0; j <ar.length; j++){
			temp[j]=ar[j];
		}return temp;
	}
	
	public void changeStatus(int index,int j){
		orderArray[j].setStatus(index);
		for (int i = 0; i <nextIndex; i++){
			try{
				FileWriter fw=new FileWriter("Orders.txt");
				fw.	write(orderArray[i].getOrderId()+" "+orderArray[i].getPhoneno()+" "+orderArray[i].getQty()+" "+orderArray[i].getSize()+" "+orderArray[i].getStatus()+" "+orderArray[i].getAmount());
				fw.write("\n");
				fw.flush();
			
			}catch(IOException ex){
				//
			}
		}
	}
	
	public int[]selectGroupPhoneno(String x){
		int count=0,k=0;
		for (int i=0; i<nextIndex;i++){
			if(orderArray[i].getPhoneno().equals(x)){
				count++;
			}
		}
		int[] is=new int[count];
		for(int i=0;i<nextIndex;i++){
			if(orderArray[i].getPhoneno().equals(x)){
				is[k]=i;
				k++;
			}
		}return is;
	}
	
	public void setDecOrder(double[] ar,int[] br,String[] cr){
		for (int i = 0; i <ar.length ; i++){
			for (int j = ar.length-1; j>i ; j--){
				if(ar[i]<ar[j]){
					//System.out.println(i);
					double tem1=ar[i];
					ar[i]=ar[j];
					ar[j]=tem1;
					int tem2=br[i];
					br[i]=br[j];
					br[j]=tem2;
					String tem3=cr[i];
					cr[i]=cr[j];
					cr[j]=tem3;
				}
			}
		}
	}
	
	public void setDecOrder(int[] ar,String[] cr,double[] br){
		for (int i = 0; i <ar.length ; i++){
			for (int j = 0; j <ar.length ; j++){
				if(ar[i]>ar[j]){
					//System.out.println(i);
					int tem2=ar[i];
					ar[i]=ar[j];
					ar[j]=tem2;
					String tem3=cr[i];
					cr[i]=cr[j];
					cr[j]=tem3;
					double tem1=br[i];
					br[i]=br[j];
					br[j]=tem1;
				}
			}
		}
	}
	
	public void setDecOrder(double[] amt,String[] soid,String[] no,String []size,int[] qty,String[] stat){
		for (int i = 0; i <amt.length ; i++){
			for (int j = amt.length-1; j>i ; j--){
				if(amt[i]<amt[j]){
					//System.out.println(i);
					double tem1=amt[i];
					amt[i]=amt[j];
					amt[j]=tem1;
					String tem2=soid[i];
					soid[i]=soid[j];
					soid[j]=tem2;
					String tem3=no[i];
					no[i]=no[j];
					no[j]=tem3;
					String tem4=size[i];
					size[i]=size[j];
					size[j]=tem4;
					int tem5=qty[i];
					qty[i]=qty[j];
					qty[j]=tem5;
					String tem6=stat[i];
					stat[i]=stat[j];
					stat[j]=tem6;
				}
			}
		}
	}
	
	public boolean check(String [] ar,String x){	
		for (int i = 0; i <ar.length; i++){
			if(ar[i].equals(x)){
				return true;
			}
		}return false;
				
	}
	
	public String[] append(String[] ar,String x){
		String[] tem=new String[ar.length+1];
		int i=0;
		for(i=0;i<ar.length;i++){
			tem[i]=ar[i];
		}
		tem[i]=x;
		return tem;
	}
	
}
