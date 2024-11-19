import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;

class AllOrders extends JFrame{
	private JButton Back;
	private DefaultTableModel dtm;
	private JTable OrderTable;
	private ItemList itemList;
	
	
	AllOrders(ItemList itemList){
		this.itemList=itemList;
		setSize(600,520);
		setTitle("All Orders");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Back
		
		JPanel NorthPan=new JPanel(new FlowLayout(FlowLayout.LEFT,20,20));
		Back=new JButton("Back");
		Back.setFont(new Font("Ariel",1,15));
		Back.setHorizontalAlignment(JButton.CENTER);
		Back.setBackground(Color.RED);
		Back.setForeground(Color.WHITE);
		
		NorthPan.add(Back);
		Back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new Reports(itemList).setVisible(true);
				 				 
			}
		});
		add("North",NorthPan);
		
		String [] colNames={"Order ID","Customer ID","Size","QTY","Amount","Status"};
		dtm =new DefaultTableModel(colNames,0);
		
		OrderTable=new JTable(dtm);
		OrderTable.setRowHeight(30);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		OrderTable.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane TablePane=new JScrollPane(OrderTable);
		JPanel temx=new JPanel(new GridLayout(1,1,100,100));
		temx.add(TablePane);
		add("Center",temx);
		
		JLabel j1=new JLabel("         ");
		add("East",j1);
		JLabel j2=new JLabel("         ");
		add("West",j2);
		
		JLabel j3=new JLabel("         ");
		j3.setFont(new Font("Ariel",1,15));
		add("South",j3);
		
		
		OrderTable. getColumnModel(). getColumn(0). setPreferredWidth(100);
		OrderTable. getColumnModel(). getColumn(1). setPreferredWidth(100);
		OrderTable. getColumnModel(). getColumn(2). setPreferredWidth(30);
		OrderTable. getColumnModel(). getColumn(3). setPreferredWidth(30);
		OrderTable. getColumnModel(). getColumn(5). setPreferredWidth(100);
		
		Order[] or=itemList.getItemListArray();
		String Status_tem[]=new String[itemList.size()];
		for (int i = 0; i <itemList.size(); i++){
			Status_tem[i]=or[i].getStatus()==0?"PROCESSING":or[i].getStatus()==1?"DELIVERING":"DELIVERED";
		}
		
		
		for (int i = itemList.size()-1; i >=0; i--){
			Object[] rowData={or[i].getOrderId(),or[i].getPhoneno(),or[i].getSize(),or[i].getQty(),String.format("%12.2f",or[i].getAmount()),Status_tem[i]};
			dtm.addRow(rowData);
		}
				
	}
	public static void main(String args[]){
		//new AllOrders().setVisible(true);
	}
}
