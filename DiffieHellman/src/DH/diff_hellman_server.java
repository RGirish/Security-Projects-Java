package DH;

import java.net.*;
import java.util.Random;
import java.io.*;

public class diff_hellman_server {
	ServerSocket ss;
	Socket cli;

	public static int mod(int a, int b) {
		int c = a % b;
		return (c < 0) ? -c : c;
	}

	public diff_hellman_server() {
		try {
			ss = new ServerSocket(2013);
			System.out.println(" USER A");
			cli = ss.accept();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void here() throws Exception {
		int p = 47, g = 3, x, X, Y, key;
		DataOutputStream dsend = new DataOutputStream(cli.getOutputStream());
		DataInputStream din = new DataInputStream(cli.getInputStream());

		dsend.writeUTF(String.valueOf(p));

		dsend.writeUTF(String.valueOf(g));

		Random r1 = new Random();
		x = r1.nextInt(10000) + 1;

		X = mod((int) ((Math.pow(g, x))), p);
		dsend.writeUTF(String.valueOf(X));
		Y = Integer.parseInt(din.readUTF());
		key = mod(((int) (Math.pow(Y, x))), p);
		;
		System.out.println("User A has following information: ");
		System.out.println("x= " + x + " X: " + X + " Y: " + Y);
		System.out.println("Key generated at A is " + key);
	}

	public static void main(String[] args) throws Exception {
		diff_hellman_server obj = new diff_hellman_server();
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
