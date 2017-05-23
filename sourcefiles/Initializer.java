package pkg3;

import java.util.Vector;

import javax.swing.JTable;

public class Initializer {
	
	public static void main(String[] args) {
		String address = null;
		int port = 0;
		if((args.length > 0) && (args.length < 3)) {		
			address = args[0].trim();
			port = Integer.parseInt(args[1]);			
		}
		
		else {
			System.out.println("wrong ip address or port number");
			System.out.println("correct syntax: java -jar sauerstats.jar [xxx.xxx.xxx.xxx] [yyyyy]");
			System.out.println("x = ip, y = portnumber");
			System.out.println("try again!");
			System.exit(0);

		}
		Vector<String> columns = new Vector<>();
		columns.add("cn");
		columns.add("ping");
		columns.add("name");
		columns.add("team");
		columns.add("frags");
		columns.add("flags");
		columns.add("deaths");
		columns.add("teamkills");
		columns.add("accuracy");
		columns.add("health");
		columns.add("armour");
		columns.add("gunselect");
		columns.add("privilege");
		columns.add("state");
		
		Vector<Vector<Object>> list = new Vector<Vector<Object>>();
		JTable table = new JTable(list, columns);
		table.setEnabled(false);
		Communicator com = new Communicator(address, port);
		Runner runner = new Runner(table, com);
		@SuppressWarnings("unused")
		MyFrame frame = new MyFrame(table);
		runner.start();
	}

}
