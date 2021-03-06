package DH;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class diff_hellman_client {
	Socket ser;

	public static int mod(int a, int b) {
		int c = a % b;
		return (c < 0) ? -c : c;
	}

	public diff_hellman_client() {
		try {
			ser = new Socket("localhost", 2013);
			System.out.println("USER B");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void here() throws Exception {
		int key, g, p, y, Y, X;
		DataOutputStream dsend = new DataOutputStream(ser.getOutputStream());
		DataInputStream din = new DataInputStream(ser.getInputStream());
		p = Integer.parseInt(din.readUTF());
		g = Integer.parseInt(din.readUTF());
		Random r1 = new Random();
		y = r1.nextInt(10000) + 1;
		System.out.println("\n y = " + y);
		Y = mod(((int) (Math.pow(g, y))), p);
		System.out.println("\n Y = " + Y);
		X = Integer.parseInt(din.readUTF());
		dsend.writeUTF(String.valueOf(Y));
		key = mod(((int) (Math.pow(X, y))), p);
		System.out.println("\nkey generated at B:" + key);
		System.out.println("User B has following information: ");
		System.out.println("Y= " + Y + " y: " + y + " X: " + X);
		System.out.println("Key generated at B is " + key);
	}

	public static void main(String[] args) throws Exception {
		diff_hellman_client obj = new diff_hellman_client();
		obj.here();
	}

	/*
	 * Cloning is disabled for security reasons. (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public final Object clone() throws java.lang.CloneNotSupportedException {
		throw new java.lang.CloneNotSupportedException();
	}

	/*
	 * Object Serialization is disabled for security reasons.
	 */
	private final void writeObject(ObjectOutputStream out) throws java.io.IOException {
		throw new java.io.IOException("Object cannot be serialized");
	}
}
