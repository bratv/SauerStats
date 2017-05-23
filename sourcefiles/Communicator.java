package pkg3;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Vector;

public class Communicator {
	private UDPConnector connector;
	private Packer packer = new Packer();
	private Unpacker unpacker = new Unpacker();
	private ArrayList<DatagramPacket> recivedPackets;
	private int[] infoOut;
	
	public Communicator(String address, int port) {
		connector = new UDPConnector(address, port);
	}
	
	public Vector<Vector<Object>> send(int[] info) {
		infoOut = info;
		packer = new Packer();
		byte[] bytez = packer.pack(infoOut);
		connector.send(bytez);
		recivedPackets = connector.getAnswer();
		Vector<Vector<Object>> vector = new Vector<>(recivedPackets.size());
		
		for(DatagramPacket p: recivedPackets) {
			byte[] bytes = p.getData();
			unpacker = new Unpacker(bytes);
			for(int x = 0; x < 6; x++) {
				unpacker.unpackInt();
			}
			int i = unpacker.unpackInt();
			if(i == -11) {
				Vector<Object> vec = new Vector<>();
				vec.add(unpacker.unpackInt());
				vec.add(unpacker.unpackInt());
				vec.add(unpacker.unpackString());
				vec.add(unpacker.unpackString());
				vec.add(unpacker.unpackInt());
				vec.add(unpacker.unpackInt());
				vec.add(unpacker.unpackInt());
				vec.add(unpacker.unpackInt());
				vec.add(unpacker.unpackInt());
				vec.add(unpacker.unpackInt());
				vec.add(unpacker.unpackInt());
				vec.add(unpacker.unpackInt());
				vec.add(unpacker.unpackInt());
				vec.add(unpacker.unpackInt());
				vector.add(vec);
			}

		}
		return vector;
	}

}
