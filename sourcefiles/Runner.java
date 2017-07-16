package pkg3;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Runner implements Runnable{
	Communicator com;
	int[] message = {0, 1, -1};
	Vector<String> columns;
	Vector<Vector<Object>> list;
	MyTableModel model;
	TableRowSorter<MyTableModel> sorter;
	Thread thread;
	private volatile boolean running;
	
	public Runner(MyTableModel model, Communicator c) {
		com = c;		
		this.model = model;
		sorter = new TableRowSorter<>(this.model);
		running = false;
	}

	@Override
	public void run() {
		while(running) {
			list = com.send(message);
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					model.setData(list);
					System.out.println("honk");
				}
			});
		}
		
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	
	public void stop() {
		thread.interrupt();
		running = false;
	}
	
}
