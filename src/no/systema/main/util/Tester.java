package no.systema.main.util;

public class Tester {

	public static void main(String[] args) {
		StringManager strMgr = new StringManager();
		String tmp = "";
		String s= strMgr.trailingStringWithFiller(tmp, 9, "0");
		System.out.println(s + "X");
	}

}
