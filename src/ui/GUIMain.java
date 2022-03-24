package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

public class GUIMain {
	private JFrame frame;
	
	public static void main(String[] args) {
		new GUIMain();
	}
	
	public GUIMain() {
		frame = new JFrame();
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Sales Software");
		frame.setLayout(new BorderLayout());
		
		JPanel pageChooser = new JPanel();
		frame.add(pageChooser,BorderLayout.NORTH);
		pageChooser.setPreferredSize(new Dimension(800,40));
		pageChooser.setBackground(new Color(0x808080));
		pageChooser.setLayout(new GridLayout(1,0));
		JButton ordersButton = new JButton("Orders");
		JButton productsButton = new JButton("Products");
		JButton customersButton = new JButton("Customers");
		JButton suppliersButton = new JButton("Suppliers");
		pageChooser.add(ordersButton);
		pageChooser.add(productsButton);
		pageChooser.add(customersButton);
		pageChooser.add(suppliersButton);
		
		customersButton.addActionListener(e -> {
			frame.add(new customersPage(),BorderLayout.CENTER);
		});
		ordersButton.addActionListener(f -> {
			frame.add(new ordersPage(),BorderLayout.CENTER);
		});
		frame.add(new ordersPage(),BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	private class customersPage extends TablePanel{
		@Override
		public void setTable() {
			String[] tableColumns = {"Name","Address","City","Phone No.","Type"};
			String[][] tableContent = {{"","","","",""}};
			datapoints = tableColumns;
			this.tableContent = tableContent;
		}
	}
	
	private class ordersPage extends TablePanel{
		@Override
		public void setTable() {
			String[] tableColumns = {"Customer Name", "Order Date", "Delivery Date", "Delivery Status", "Amount", "Customer Type"};
			String[][] tableContent = {{"","","","","",""}};
			datapoints = tableColumns;
			this.tableContent = tableContent;
;		}
		
		@Override
		public void drawFields() {
			super.drawFields();
			remove(textFields.get(5));
			String[] types = {"Private","Club"};
			JComboBox swap = new JComboBox<String>(types);
			swap.setBounds(160, 500, 275, 30);
			swap.doLayout();
			add(swap);
		}
	}
}
