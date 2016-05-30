package lijuntao.asmtest.demo;

import java.io.UnsupportedEncodingException;

public class TestCharSet {
	public static void main(String[] args) throws Exception {
		String s = "李";
		byte[] bs = s.getBytes("utf-8");
		System.out.println("length:"+bs.length);
		char[] cs = new char[bs.length];
		for(int i=0;i<bs.length;i++){
			cs[i] = (char)(bs[i]&0xff);
		}
		String string = new String(cs);
		System.out.println("string:"+string);
		String s2 = "李";
		char c = s2.charAt(0);
		System.out.println("c:"+c);
		System.out.println("c:"+(int)c);
		int i = 26446;
		char c2 = (char)i;
		System.out.println("c2:"+c2);
		int i2 = (((bs[0]&0x0f)<<6) | ((bs[1]&0x3f))<<6) | (bs[2]&0x3f);
		char c3 = (char)i2;
		System.out.println("c3"+c3);
		
	}
}
