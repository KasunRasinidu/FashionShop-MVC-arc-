import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;

class ViewCustomer extends JFrame{
	private JButton Back;
	private DefaultTableModel dtm;
	private JTable ViewCustomerTable;
	private int[] tem_qty;
	private double[]tem_amt;
	private String[] no_dup;
	private ItemList itemList;
	
	ViewCustomer(ItemList itemList){
		this.itemList=itemList;
		setSize(500,520);
		setTitle("View Customers");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Back
		
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
				new Reports(itemList).setVisible(true);
				 				 
			}
		});
		add("North",NorthPan);
		
		String [] colNames={"Customer ID","QTY","Amount"};
		dtm =new DefaultTableModel(colNames,0);
		
		ViewCustomerTable=new JTable(dtm);
		ViewCustomerTable.setRowHeight(40);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		ViewCustomerTable.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane TablePane=new JScrollPane(ViewCustomerTable);
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
		
		ViewCustomerTable.getColumnModel(). getColumn(1). setPreferredWidth(30);
		
		String[] no_dup=new String[0];
		Order[] or=itemList.getItemListArray();
		for (int i = 0; i <itemList.size(); i++){
			if(!itemList.check(no_dup,or[i].getPhoneno())){
				no_dup=itemList.append(no_dup,or[i].getPhoneno());
			}
		}
		//System.out.println("Here");
		tem_amt=new double[no_dup.length];
		tem_qty=new int[no_dup.length];
		
		for (int i = 0; i <no_dup.length; i++){
			int [] is=itemList.selectGroupPhoneno(no_dup[i]);				
			for (int j = 0; j <is.length; j++){
				tem_amt[i]+=or[is[j]].getAmount();
				tem_qty[i]+=or[is[j]].getQty();
			}
		}
		
		
		//System.out.println("Here");
		for (int i = 0; i <no_dup.length; i++){
			Object[] rowData={no_dup[i],tem_qty[i],String.format("%12.2f",tem_amt[i])};
			dtm.addRow(rowData);
		}
	}
}
