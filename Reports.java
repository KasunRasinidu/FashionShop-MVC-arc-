import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Reports extends JFrame{
	private JButton Back;
	
	private JButton [] Customer;
	private JButton [] Products;
	private JButton [] Orders;
	private ItemList itemList;
	
	Reports(ItemList itemList){
		this.itemList=itemList;
		setSize(650,350);
		setTitle("Reports");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel NorthPan=new JPanel(new FlowLayout(FlowLayout.LEFT));
		Back=new JButton("Back");
		Back.setFont(new Font("Ariel",1,15));
		Back.setHorizontalAlignment(JButton.CENTER);
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
		
		JPanel WestPanel_tem=new JPanel(new FlowLayout(FlowLayout.CENTER,10,30));
		JPanel WestPanel=new JPanel(new GridLayout(3,1));
		Customer=new JButton[3];
		String[] GreenNames={"View Customers","Best in Customers","All Customers"};
		
		for (int i = 0; i < 3; i++){
			JPanel temp1=new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
			Customer[i]=new JButton(GreenNames[i]);
			Customer[i].setFont(new Font("Ariel",1,15));
			Customer[i].setPreferredSize(new Dimension(170,50));
			Customer[i].setHorizontalAlignment(JButton.CENTER);
			Customer[i].setBackground(new Color(13,198,129));
			Customer[i].setForeground(Color.WHITE);
			temp1.add(Customer[i]);
			WestPanel.add(temp1);
		}
		
		WestPanel_tem.add(WestPanel);
		add("West",WestPanel_tem);
		
		JPanel CenterPanel_tem=new JPanel(new FlowLayout(FlowLayout.CENTER,10,70));
		JPanel CenterPanel=new JPanel(new GridLayout(2,1));
		Products=new JButton[2];
		String[] BlueNames={"Categorized By QTY","Categorized By Amount"};
		
		
		for (int i = 0; i < 2; i++){
			JPanel temp1=new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
			Products[i]=new JButton(BlueNames[i]);
			Products[i].setFont(new Font("Ariel",1,15));
			Products[i].setPreferredSize(new Dimension(200,50));
			Products[i].setHorizontalAlignment(JButton.CENTER);
			Products[i].setBackground(new Color(41,134,204));
			Products[i].setForeground(Color.WHITE);
			temp1.add(Products[i]);
			CenterPanel.add(temp1);
		}
		CenterPanel_tem.add(CenterPanel);
		add("Center",CenterPanel_tem);
		
		JPanel EastPanel_tem=new JPanel(new FlowLayout(FlowLayout.CENTER,10,70));
		JPanel EastPanel=new JPanel(new GridLayout(2,1));
		Orders=new JButton[2];
		String[] BlackNames={"Order By Amount","All Orders"};
		
		for (int i = 0; i < 2; i++){
			JPanel temp1=new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
			Orders[i]=new JButton(BlackNames[i]);
			Orders[i].setFont(new Font("Ariel",1,15));
			Orders[i].setHorizontalAlignment(JButton.CENTER);
			Orders[i].setPreferredSize(new Dimension(170,50));
			Orders[i].setBackground(Color.GRAY);
			Orders[i].setForeground(Color.WHITE);
			temp1.add(Orders[i]);
			EastPanel.add(temp1);
		}
		EastPanel_tem.add(EastPanel);
		add("East",EastPanel_tem);
		
		Customer[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new ViewCustomer(itemList).setVisible(true);
			}
		});
		
		Customer[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new BestInCustomer(itemList).setVisible(true);
			}
		});
		
		Customer[2].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new AllCustomer(itemList).setVisible(true);
			}
		});
		
		Products[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new ProductsByQty(itemList).setVisible(true);
			}
		});
		
		Products[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new ProductsByAmount(itemList).setVisible(true);
			}
		});
		Orders[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new OrderByAmount(itemList).setVisible(true);
			}
		});
		
		Orders[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new AllOrders(itemList).setVisible(true);
			}
		});
		
	}
	
	
	public static void main(String args[]){
		
	}
}
