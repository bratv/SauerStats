package pkg3;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Runner implements Runnable{
	//JTable table;
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
		//table.setRowSorter(sorter);
		//sorter.setSortsOnUpdates(true);		
		running = false;
	}

	@Override
	public void run() {
		while(running) {
			list = com.send(message);
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
//					for (int x = 0; x < list.size(); x++) {
//			            model.addRow(new Vector<Object>());
//			            for (int y = 0; y < table.getColumnCount(); y++) {			            	
//			                model.setValueAt(list.get(x).get(y), x, y);
//			            }
//					}
//					model.setRowCount(list.size());
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
