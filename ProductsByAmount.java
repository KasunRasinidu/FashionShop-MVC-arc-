import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;

class ProductsByAmount extends JFrame{
	private JButton Back;
	private DefaultTableModel dtm;
	private JTable ProductTable;
	private double [] tem_amt;
	private int[] tem_qty;
	private String [] size_tem;
	private double tot=0;
	private ItemList itemList;
	
	ProductsByAmount(ItemList itemList){
		this.itemList=itemList;
		setSize(500,520);
		setTitle("Products by Amount");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//
		
		size_tem=itemList.copy(itemList.size_con);
		tem_qty=new int[itemList.size_con.length];
		tem_amt=new double[itemList.size_con.length];
		
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
		JLabel j1=new JLabel("                    ");
		add("East",j1);
		JLabel j2=new JLabel("                    ");
		add("West",j2);
		JLabel j3=new JLabel("         ");
		j3.setFont(new Font("Ariel",1,68));
		add("South",j3);
		
		String [] colNames={"Size","QTY","Amount"};
		dtm =new DefaultTableModel(colNames,0);
		
		ProductTable=new JTable(dtm);
		ProductTable.setRowHeight(50);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		ProductTable.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane TablePane=new JScrollPane(ProductTable);
		JPanel temx=new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
		temx.add(TablePane);
		add("Center",TablePane);
		
		Order[] or=itemList.getItemListArray();
		
		for(int j=0;j<itemList.size();j++){
			for(int x=0; x<size_tem.length; x++){	
				if(or[j].getSize().equals(size_tem[x])){
					tot+=or[j].getAmount();
					tem_amt[x]+=or[j].getAmount();
					tem_qty[x]+=or[j].getAmount();
				}
			}	
		}
		itemList.setDecOrder(tem_amt,tem_qty,size_tem);
		
		for (int i = 0; i <6; i++){
			
			Object[] rowData={size_tem[i],tem_qty[i],String.format("%12.2f",tem_amt[i])};
			dtm.addRow(rowData);
		}
	}
}
