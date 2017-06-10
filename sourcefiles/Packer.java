package pkg3;
import java.util.ArrayList;

public class Packer {
	
	private ArrayList<Byte> list;
	
	public Packer() {
		list = new ArrayList<Byte>();	
	}
	
	public byte[] pack(int[] array) {
		for(int x = 0; x < array.length; x++) {
			pack(array[x]);
		}
		return toByteArray(list);
	}
	
public void pack(int n) {
		
		if((n < 128) && (n > -127)) {
			list.add((byte)n);
		}
		else if((n < 0x8000) && (n >= -0x8000)) {
			list.add((byte)0x80);
			list.add((byte)n);
			list.add((byte)(n>>8));
		}
		else {
			list.add((byte)0x81);
			list.add((byte)n); 
			list.add((byte)(n>>8)); 
			list.add((byte)(n>>16));
			list.add((byte)(n>>24));
		}	
	}
	
	private byte[] toByteArray(ArrayList<Byte> l) {
		Byte[] temp = new Byte[list.size()];
		temp = list.toArray(temp);
		byte[] bytez = new byte[temp.length];
		for(int x = 0; x < temp.length; x++) {
			bytez[x] = temp[x].byteValue();
			
		}
		return bytez;
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
	
	public ArrayList<Byte> getPacked() {
		return list;
	}
	

}
