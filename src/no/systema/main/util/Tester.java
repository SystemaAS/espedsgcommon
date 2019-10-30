package no.systema.main.util;

public class Tester {

	public static void main(String[] args) {
		/*AWBValidator awbValidator = new AWBValidator();
		if(awbValidator.validateMod7("58981140743")){
			System.out.println("VALID!");
		}*/
		/*
		StringManager strMgr = new StringManager();
		String tmpStr = "12345678901";
		String newTmp = strMgr.leadingStringWithNumericFiller(tmpStr, 11, "0"); 
		System.out.println(strMgr.leadingStringWithNumericFiller(newTmp, 15, "X"));
		*/
		DateTimeManager mgr = new DateTimeManager();
		System.out.println(mgr.validTwoDatesWithinSpanISO("20190101", "20180101", 15));
	}

}
