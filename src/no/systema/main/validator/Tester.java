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
		System.out.println(validator.validateDate("300418", "ddMMyy"));
	}
	
}
