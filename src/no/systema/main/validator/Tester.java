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
		
		DateTimeValidator validator = new DateTimeValidator();
		System.out.println(validator.validateDate("20180228", "uuuuMMdd"));
		System.out.println("");
		DateValidator validatorOld = new DateValidator();
		System.out.println(validatorOld.validateDate("20180228", "yyyyMMdd"));
	}
	
}
