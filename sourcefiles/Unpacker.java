package pkg3;
import java.util.ArrayList;

public class Unpacker {
	
	private ArrayList<Byte> list;
	int[] stringArray;
	StringBuilder builder = new StringBuilder();
	
	public Unpacker() {
		list = new ArrayList<Byte>();
	}
	
	public Unpacker(byte[] b) {
		list = new ArrayList<Byte>();
		for(byte byt: b) {
			list.add(byt);
		}
	}
	
	public int unpackInt() {
		int n;
		n = getInitial();
		if(n == -128) {
			n = get();
			n |= get()<<8;
			return n; 
		}
		else if(n == -127) {
			n = get();
			n |= (get()<<8);
			n |= (get()<<16);
			n |= (get()<<24);
			return n;
		}
		else {
			return n;
		}
	}
	
	public String unpackString() {
		builder = new StringBuilder();
		int temp;
		char c;
		while(true) {
			temp = unpackInt();
			if(temp == 0) {
				break;
			}
			else {
				c = (char) temp;
				builder.append(c);
			}
		}
		
		return builder.toString();
	}
	
	private int getInitial() {
		int n = list.get(0);
		list.remove(0);
		return n;
	}
	
	private int get() {
		int n = list.get(0);
		list.remove(0);
		n &= 0xff;
		return n;
	}

}
