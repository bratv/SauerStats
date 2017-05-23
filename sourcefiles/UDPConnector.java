package pkg3;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class UDPConnector {
	private String ip;
	private int port;
	private InetAddress address;
	private DatagramSocket socket;
	private ArrayList<DatagramPacket> packetList = new ArrayList<>();
	private DatagramPacket outgoing;
	private DatagramPacket incoming;
	private boolean flag = true;
	
	public UDPConnector(String ip, int port) {
		this.ip = ip;
		this.port = port;
		inetAddressCreator();
		socketCreator();
	}
	
	private void inetAddressCreator() {
		try {
			address = InetAddress.getByName(this.ip);
		}
		catch(UnknownHostException e) {
			e.printStackTrace();
			System.out.println("couldnt get that ip address!");
		}
	}
	
	private void socketCreator() {
		try {
			socket = new DatagramSocket();
			socket.setSoTimeout(1000);
			socket.connect(address, port);
		}
		catch(SocketException e) {
			e.printStackTrace();
			System.out.println("could not create socket!");
		}
	}
	
	public void send(byte[] b) {
		flag = true;
		packetList = new ArrayList<DatagramPacket>();
		outgoing = new DatagramPacket(b, b.length, address, port);
		try {
			socket.send(outgoing);
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("socket failed in sending DatagramPacket!");
		}
		while(flag) {
			byte[] bytes = new byte[512];
			incoming = new DatagramPacket(bytes, bytes.length);
			try {
				socket.receive(incoming);
				packetList.add(incoming);
			} catch (IOException e) {
				flag = false;
			}
		}
	}
	
	public ArrayList<DatagramPacket> getAnswer() {
		return packetList;
	}

}
