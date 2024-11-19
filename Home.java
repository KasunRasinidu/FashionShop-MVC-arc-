import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.awt.Image;

class Home extends JFrame{
	private JLabel Title;
	private ImageIcon homeImg;
	private JButton[] button;
	private String[] ButtonNames={"Search","Status","Reports","Delete","Place Order"};
	private JLabel copyright;
	private String [] options={"Search Customer","Search Order","Cancel"};
	private int user_input;
	private ItemList itemList;
	
	Home(ItemList itemList){
		this.itemList=itemList;
		setSize(500,520);
		setTitle("Fashion Shop");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Tittle
		Title=new JLabel("Fashion Shop");
		Title.setFont(new Font("Serif",1,40));
		Title.setHorizontalAlignment(JLabel.CENTER);
		Title.setBackground(new Color(137, 207, 240));
		Title.setOpaque(true);
		Title.setForeground(Color.WHITE);
		add("North",Title);
		
		copyright=new JLabel("Copyrights "+((char)169)+" iCET 2023");
		copyright.setFont(new Font("Ariel",1,15));
		copyright.setHorizontalAlignment(JLabel.CENTER);
		JPanel jcopy=new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
		jcopy.add(copyright);
		add("South",jcopy);

		//IMG
		JPanel p1=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		homeImg=new ImageIcon("Img\\file.PNG");
		homeImg.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		JLabel jimg=new JLabel(homeImg);
		jimg.setPreferredSize(new Dimension(200,400));
		p1.add(jimg);
		add("East",p1);
		
		JPanel buttonPanel=new JPanel(new GridLayout(5,1));
		
		button=new JButton[5];
		for (int i = 0; i <4 ; i++){
			JPanel temp_panel=new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
			button[i]=new JButton(ButtonNames[i]);
			button[i].setFont(new Font("Ariel",1,15));
			button[i].setHorizontalAlignment(JLabel.CENTER);
			button[i].setPreferredSize(new Dimension(200,50));
			temp_panel.add(button[i]);
			buttonPanel.add(temp_panel);
		}
		button[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){;
				
				user_input=JOptionPane.showOptionDialog(null,"Please Select the Option","Search Options",0,3,null,options,options[0]);
				if(user_input==0){
					dispose();
					new SearchCustomer(itemList).setVisible(true);
					
				}
				else if(user_input==1){
					dispose();
					new SearchOrder(itemList).setVisible(true);
				}
				else if(user_input==2){
					
				}
			}
		});
		
		button[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new Status(itemList).setVisible(true);
			}
		});
		
		button[2].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new Reports(itemList).setVisible(true);
			}
		});
		
		button[3].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new Delete(itemList).setVisible(true);
			}
		});
		/*JPanel temp_panel1=new JPanel(new FlowLayout(FlowLayout.LEFT,20,20));
		JLabel tem=new JLabel("");
		temp_panel1.add(tem);
		tem.setPreferredSize(new Dimension(200,50));
		buttonPanel.add(temp_panel1);*/
		
		
		JPanel temp_panel2=new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
		button[4]=new JButton(ButtonNames[4]);
		button[4].setFont(new Font("Serif",1,30));
		button[4].setHorizontalAlignment(JLabel.CENTER);
		button[4].setBackground(new Color(72,201,176));
		button[4].setForeground(Color.BLACK);
		button[4].setPreferredSize(new Dimension(200,75));
		temp_panel2.add(button[4]);
		buttonPanel.add(temp_panel2);
		
		
		button[4].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new PlaceOrder(itemList).setVisible(true);
			}
		});		
		
		add("West",buttonPanel);
	}
	
	public static void main(String args[]){
		//new Home().setVisible(true);
	}
}
