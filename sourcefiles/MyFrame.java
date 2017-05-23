package pkg3;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class MyFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6305651265167705776L;
	Dimension dim = new Dimension(300, 500);
	JScrollPane pane;
	Vector<String> columNames = new Vector<>();
	Vector<Vector<Object>> dataVector;
	
	public MyFrame(JTable table) {
		pane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		setSize(300, 500);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setTitle("Players in server");
		setBackground(Color.BLACK);
		add(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
