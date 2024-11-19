import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.event.*;
class PlaceOrder extends JFrame{
	private JButton Back;
	private JLabel[] Labels;
	private JLabel Orderid;
	private JTextField [] Textbox;
	private JLabel Amount;
	private JLabel sizes;
	private JButton Place;
	private int qty;
	private double amt;
	private int count=0;
	private boolean Check=true;
	private ItemList itemList;
	private String s_orderID=null;
	
	
	PlaceOrder(ItemList itemList){
		this.itemList=itemList;
		setSize(500,520);
		setTitle("Place Order");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		JPanel NorthPan=new JPanel(new FlowLayout(FlowLayout.LEFT));
		Back=new JButton("Back");
		Back.setFont(new Font("Ariel",1,15));
		Back.setHorizontalAlignment(JButton.LEFT);
		Back.setBackground(Color.RED);
		Back.setForeground(Color.WHITE);
		
		NorthPan.add(Back);
		Back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new Home(itemList).setVisible(true);
				 				 
			}
		});
		add("North",NorthPan);
		
		//WEST LAbels 		
		JPanel WestPan=new JPanel(new GridLayout(5,1,1,1));
		WestPan.setPreferredSize(new Dimension(130,1));
		String [] names={"Order ID : ","Customer ID : ","Size : ","QTY : ","Amount : "};
		Labels=new JLabel[5];
		
		JPanel tem_panel=new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
		Labels[0]=new JLabel(names[0]);
		Labels[0].setFont(new Font("Ariel",1,15));
		Labels[0].setHorizontalAlignment(JLabel.CENTER);
		tem_panel.add(Labels[0]);
		WestPan.add(tem_panel);
		
		
		/*JPanel tem_panel1=new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
		JLabel tem=new JLabel("");
		tem_panel1.add(tem);
		WestPan.add(tem_panel1);*/
		
		for (int i = 1; i <4; i++){
			JPanel tempPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,30,1));
			Labels[i]=new JLabel(names[i]);
			Labels[i].setFont(new Font("Ariel",1,15));
			Labels[i].setHorizontalAlignment(JLabel.CENTER);
			tempPanel.add(Labels[i]);
			WestPan.add(tempPanel);
		}
		JPanel tempPanel2=new JPanel(new FlowLayout(FlowLayout.LEFT,30,17));
		Labels[4]=new JLabel(names[4]);
		Labels[4].setFont(new Font("Ariel",1,15));
		Labels[4].setHorizontalAlignment(JLabel.CENTER);
		tempPanel2.add(Labels[4]);
		WestPan.add(tempPanel2);
		
		add("West",WestPan);
		
		//order I1D
		JPanel CenterPan=new JPanel(new GridLayout(5,1,1,1));
		JPanel OrderPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,10,1));
		try{
			s_orderID=itemList.getOrderID();
		}catch(IOException ex){
			
		}
		Orderid=new JLabel(s_orderID);
		Orderid.setFont(new Font("Ariel",1,15));
		Orderid.setHorizontalAlignment(JLabel.LEFT);
		OrderPanel.add(Orderid);
		CenterPan.add(OrderPanel);
		
		/*JLabel tem1=new JLabel("");
		tem1.setPreferredSize(new Dimension(0,0));
		CenterPan.add(tem1);*/
		
		// Text BOX
		Textbox=new JTextField[3];
		for (int i = 0; i <3; i++){
			JPanel tempPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,10,1));
			Textbox[i]=new JTextField(15);
			Textbox[i].setPreferredSize(new Dimension(200,35));
			Textbox[i].setFont(new Font("Ariel",1,15));
			tempPanel.add(Textbox[i]);
			CenterPan.add(tempPanel);
		}
		
		JPanel tempPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,10,1));
		Amount =new JLabel(String.format("%8.2f",amt));
		Amount.setFont(new Font("Ariel",1,15));
		Amount.setHorizontalAlignment(JLabel.LEFT);
		Amount.setPreferredSize(new Dimension(200,0));
		tempPanel.add(Amount);
		CenterPan.add(Amount);
		add("Center",CenterPan);
		
		//Check if Number is Valid
		Textbox[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(Textbox[0].getText().charAt(0)=='0' && Textbox[0].getText().length()==10){
					KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
				}
				else{
					JOptionPane.showMessageDialog(null,"Invalid Customer ID Try Agian...","Error",0);
				}	
				
			}
		});
		//Check if Size is Valid
		Textbox[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				for (int i = 0; i <itemList.size_con.length; i++){
					if((!(Textbox[1].getText().equals(""))&&Textbox[1].getText().equals(itemList.size_con[i]))){
						count=1;
						KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
					}
					
				}
				if(count!=1){
					JOptionPane.showMessageDialog(null,"Invalid Size Try Agian...","Error",0);
				}
							 
			}
		});
		
		Textbox[2].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				String temp = Textbox[2].getText();
				for (int i = 0; i <temp.length(); i++){
					   if(!Character.isDigit(temp.charAt(i))){
						Check=false;
					}
				}
				
				
				
				if((!Textbox[2].getText().equals(""))&&Check){
					qty=Integer.parseInt(Textbox[2].getText());	
					amt=itemList.price_con[itemList.select(Textbox[1].getText())]*qty;
					Amount.setText(String.format("%8.2f",amt));	
				}else{
					JOptionPane.showMessageDialog(null,"Invalid Qty Try Agian...","Error",0);
				}	 
			}
		});
		
		//Sizes
		JPanel sizePanel = new JPanel(new GridLayout(1,1));
		sizes=new JLabel("(XS/S/M/L/XL/XXL)");
		sizes.setFont(new Font("Ariel",1,15));
		sizes.setHorizontalAlignment(JLabel.CENTER);
		sizes.setPreferredSize(new Dimension(150,325));
		sizePanel.add(sizes);
		//JLabel j1=new JLabel();
		//sizePanel.add(j1);
		add("East",sizePanel);
		
		// SOUTH PLACE BUTTON
		JPanel SouthPan=new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
		Place=new JButton("Place");
		Place.setFont(new Font("Ariel",1,15));
		Place.setHorizontalAlignment(JButton.CENTER);
		Place.setBackground(new Color(72,201,176));
		Place.setForeground(Color.WHITE);
		Place.setPreferredSize(new Dimension(100,30));
		pack();
		
		
		
		
		Place.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				
				/*Validity check QTY is A Digit*/{
					String temp = Textbox[2].getText();
					for (int i = 0; i <temp.length(); i++){
						
						if(!Character.isDigit(temp.charAt(i))){
							Check=false;
						}
					}
				
					for (int i = 0; i <itemList.size_con.length; i++){
						if((!(Textbox[1].getText().equals(""))&&Textbox[1].getText().equals(itemList.size_con[i]))){
							count=1;
							KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
						}
					
					}
				}
				
				System.out.println(Check);
				if(Textbox[0].getText().charAt(0)=='0' && Textbox[0].getText().length()==10&&count==1&&Check){
					//itemList.or=itemList.extend();
					qty=Integer.parseInt(Textbox[2].getText());
					amt=itemList.price_con[itemList.select(Textbox[1].getText())]*qty;
					//System.out.println(amt+"    "+qty+"   "+s_orderID+"   "+Textbox[1].getText());
					itemList.addOrder(new Order(s_orderID,Textbox[0].getText(),qty,Textbox[1].getText(),0,amt));
					JOptionPane.showMessageDialog(null,"Order Placed","Information",1);
					dispose();
					new PlaceOrder(itemList).setVisible(true);
					
				}else{
					if(count!=1){
						JOptionPane.showMessageDialog(null,"Invalid Size Try Agian...","Error",0);
					}else if(!Check){
						JOptionPane.showMessageDialog(null,"Invalid Qty Try Agian...","Error",0);
					}else{
						JOptionPane.showMessageDialog(null,"Invalid Customer ID Try Agian...","Error",0);
					}
				}	
				
			}
		});
		
		SouthPan.add(Place);
		add("South",SouthPan);
	}
	
	public static void main(String args[]){
		//new PlaceOrder(itemList).setVisible(true);
	}
}
