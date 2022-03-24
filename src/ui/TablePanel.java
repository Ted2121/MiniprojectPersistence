package ui;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.util.ArrayList;

public abstract class TablePanel extends JPanel{
	protected String[] datapoints;
	protected String[][] tableContent;
	protected ArrayList<JLabel> labels;
	protected ArrayList<Component> textFields;
	
	public TablePanel() {
		setLayout(null);
		setBackground(new Color(0xfdf6d6));
		setTable();
		drawPage();
	}
	
	public void drawPage() {
		drawTable();
		drawFields();
		drawButtons();
	}
	
	public void drawTable() {
		JTextField searchField = new JTextField("Type here to search");
		searchField.setBounds(50, 50, 600, 30);
		add(searchField);
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(660, 50, 90, 30);
		add(searchButton);
		
		JTable table = new JTable(tableContent, datapoints); /*needs to get table from controller*/
		JScrollPane scrollPane = new JScrollPane(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setBounds(50, 90, 700, 200);
		add(scrollPane);
	}
	
	public void drawFields() {
		labels = new ArrayList<>();
		textFields = new ArrayList<>();
		for(int i=0; i<datapoints.length; i++) {
			labels.add(new JLabel(datapoints[i]));
			textFields.add(new JTextField());
			add(labels.get(i));
			add(textFields.get(i));
			labels.get(i).setBounds(50, 300+(40*i), 130, 30);
			textFields.get(i).setBounds(160, 300+(40*i), 275, 30);
		}
	}
	
	public void drawButtons() {
		JButton newButton = new JButton("New");
		JButton editButton = new JButton("Edit");
		JButton deleteButton = new JButton("Delete");
		JButton listButton = new JButton("List");
		add(newButton);
		add(editButton);
		add(deleteButton);
		add(listButton);
		deleteButton.setForeground(new Color(0xff0000));
		newButton.setBounds(660, 300, 90, 30);
		editButton.setBounds(660, 340, 90, 30);
		deleteButton.setBounds(660, 380, 90, 30);
		listButton.setBounds(660, 460, 90, 30);
	}
	
	public abstract void setTable();
}
