import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Delete extends JFrame{
	private JButton Back;
	private JLabel[] Lables;
	private JButton Search;
	private JLabel CustomerID;
	private JLabel Size;
	private JLabel QTY;
	private JLabel Amount;
	private JLabel status;
	private JTextField Order_ID;
	private JButton DeleteOrder;
	private int index;
	private String[] options={"Yes","NO"};
	private int user_input;
	private ItemList itemList;
	private int count=0;
	
	Delete(ItemList itemList){
		this.itemList=itemList;
		setSize(500,520);
		setTitle("Delete");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Back button
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
		
		JPanel LabelPanel=new JPanel(new GridLayout(6,1,1,1));
		Lables=new JLabel[6];
		String[] names={"Enter Order ID : ","Customer ID : ","Size : ","QTY : ","Amount : ","Status : "};
		
		JPanel temp_panel=new JPanel(new FlowLayout(FlowLayout.LEFT,30,4));
		Lables[0]=new JLabel(names[0]);
		Lables[0].setFont(new Font("Ariel",1,15));
		Lables[0].setHorizontalAlignment(JButton.LEFT);
		temp_panel.add(Lables[0]);
		LabelPanel.add(temp_panel);
		
		
		
		for (int i = 1; i<6; i++){
			JPanel temp_panel1=new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
			Lables[i]=new JLabel(names[i]);
			Lables[i].setFont(new Font("Ariel",1,15));
			Lables[i].setHorizontalAlignment(JButton.LEFT);
			temp_panel1.add(Lables[i]);
			LabelPanel.add(temp_panel1);
		}
		add("West",LabelPanel);
		//pack();
		JPanel Deletepan=new JPanel(new FlowLayout(FlowLayout.RIGHT,20,10));
		DeleteOrder =new JButton("Delete");
		DeleteOrder.setFont(new Font("Ariel",1,15));
		DeleteOrder.setBackground(new Color(161,6,6));
		DeleteOrder.setForeground(Color.WHITE);
		Deletepan.add(DeleteOrder);
		add("South",Deletepan);
		
		
		//Search
		JPanel searchpan=new JPanel(new FlowLayout(FlowLayout.LEFT,20,1));
		Search =new JButton("Search");
		Search.setFont(new Font("Ariel",1,15));
		Search.setBackground(new Color(72,201,176));
		Search.setForeground(Color.WHITE);
		searchpan.add(Search);
		add("East",searchpan);
		
		// TO GET Labels
		JPanel Panel=new JPanel(new GridLayout(6,1));{
		
			JPanel tem_panel=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
			Order_ID=new JTextField(12);
			Order_ID.setFont(new Font("Ariel",1,20));
			Order_ID.setPreferredSize(new Dimension(200,30));
			tem_panel.add(Order_ID);
			Panel.add(tem_panel);
		
			JPanel temp_panel1=new JPanel(new FlowLayout(FlowLayout.LEFT,0,10));
			CustomerID=new JLabel();
			CustomerID.setFont(new Font("Ariel",1,15));
			CustomerID.setHorizontalAlignment(JButton.LEFT);
			temp_panel1.add(CustomerID);
			Panel.add(temp_panel1);
		
			JPanel temp_panel2=new JPanel(new FlowLayout(FlowLayout.LEFT,0,10));
			Size=new JLabel();
			Size.setFont(new Font("Ariel",1,15));
			Size.setHorizontalAlignment(JButton.LEFT);
			temp_panel2.add(Size);
			Panel.add(temp_panel2);
		
			JPanel temp_panel3=new JPanel(new FlowLayout(FlowLayout.LEFT,0,10));
			QTY=new JLabel();
			QTY.setFont(new Font("Ariel",1,15));
			QTY.setHorizontalAlignment(JButton.LEFT);
			temp_panel3.add(QTY);
			Panel.add(temp_panel3);
		
			JPanel temp_panel4=new JPanel(new FlowLayout(FlowLayout.LEFT,0,12));
			Amount=new JLabel();
			Amount.setFont(new Font("Ariel",1,15));
			Amount.setHorizontalAlignment(JButton.LEFT);
			temp_panel4.add(Amount);
			Panel.add(temp_panel4);
		
			JPanel temp_panel5=new JPanel(new FlowLayout(FlowLayout.LEFT,0,10));
			status=new JLabel();
			status.setFont(new Font("Ariel",1,15));
			status.setHorizontalAlignment(JButton.LEFT);
			temp_panel5.add(status);
			Panel.add(temp_panel5);
		}
		
		add("Center",Panel);
		pack();
		
		Search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				delete();
			}
		});
		
		Order_ID.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				delete();
			}
		});
	}
	
	private void delete(){
		Order[] or= itemList.getItemListArray();
		index=itemList.selectOrderID(Order_ID.getText());
		if(index!=-1){
			String tem_Status=or[index].getStatus()==0?"PROCESSING":or[index].getStatus()==1?"DELIVERING":"DELIVERED";
			CustomerID.setText(or[index].getPhoneno());
			Size.setText(or[index].getSize());
			QTY.setText(Integer.toString(or[index].getQty()));
			Amount.setText(String.format("%8.2f",or[index].getAmount()));
			status.setText(tem_Status);
			
			DeleteOrder.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					user_input=JOptionPane.showOptionDialog(null,"Do you want to delete this order?","Delete Confirmation",0,2,null,options,options[0]);
					if(user_input==0&&count==0){
						JOptionPane.showMessageDialog(null,"Order Deleted...","Delete Information",1);
						itemList.delete(index);
						count++;
						dispose();
						new Delete(itemList).setVisible(true);
					}
					else{
						dispose();
						new Delete(itemList).setVisible(true);
					}
				}
			});					
		}
		else{
			JOptionPane.showMessageDialog(null,"Invalid Order ID Try Agian...","Error",0);
			dispose();
			new Delete(itemList).setVisible(true);
		}
				
	}
	public static void main(String args[]){
		
	}
}
