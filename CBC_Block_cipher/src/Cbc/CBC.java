package Cbc;

import java.io.ObjectOutputStream;

public class CBC {
	static String c = "";
	static int j=0;
	static int pad;
	public static String padding(String pt,int pad)
	{
		for(int i=1;i<=pad;i++)
			pt=pt+'0';
		return pt;
	}
	public static String cbc(String pt,String key){
		int l = key.length();
		if(pt.length()%l !=0)
		{
			pad=l-pt.length()%l;
			pt=padding(pt,pad);
			System.out.println("\nAfter padding:"+pt);
		}
		String result = "";
		StringBuilder stringBuilder = new StringBuilder(result);
		if(j<pt.length())
		{
		String s = pt.substring(j, j+l);
		{
			for(int i=0 ; i<(l) ; ++i){
				int cc = Integer.parseInt(String.valueOf(s.charAt(i)),16) ^ Integer.parseInt(String.valueOf(key.charAt(i)),16);
				stringBuilder.append(String.valueOf(Integer.toHexString(cc)));
				result = stringBuilder.toString();
			}
			c = c+result;
			j = j+l;
			return cbc(pt,result);
		}
		}
		else
			return c;
	}
	public static void main(String[] args)
	{
		String pt = "1c0111001f010100061a024b53535009181";
		String iv = "1c01";
		System.out.println("Plain text length:"+pt.length());
		System.out.println("IV length:"+iv.length());
		String ct = cbc(pt,iv);
		System.out.println(ct);
	}

	/*
	 * Cloning is disabled for security reasons.
	 * (non-Javadoc)
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
