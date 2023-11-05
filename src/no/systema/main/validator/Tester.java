/**
 * 
 */
package no.systema.main.validator;

/**
 * @author oscardelatorre
 *
 */
public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DateValidator validator = new DateValidator();
		System.out.println(validator.validateDateIso203_YYYY_MM_DD("20231201"));
		
		
		/*
		String absoluteFilePath = "ftp://xxx";
		if(!new IPAddressValidator().isValidAbsoluteFilePathFor_RenderFile(absoluteFilePath)){
			System.out.println("MATCH!");
		}*/
	}
	
}
