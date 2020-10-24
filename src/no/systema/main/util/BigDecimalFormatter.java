package no.systema.main.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class BigDecimalFormatter {

	/**
	 * 
	 * @param first
	 * @param second
	 * @param decimal parameter to round down to. E.g 135.443 with decimal 2 will give: 135.44 - with decimal 0 will truncate all decimals, etc
	 * @return
	 */
	public int compareBigDecimals(String first, String second, int decimal){
		int retval = 0;
		
		if(StringUtils.isNotEmpty(first) && StringUtils.isNotEmpty(second)){
			//check for 1000 separator as in 1.000,55
			//remove it
			first = this.cleanFormattedAspects(first);
			second = this.cleanFormattedAspects(second);
			
			first = first.replace(",", ".");
			second = second.replace(",", ".");
			
			BigDecimal x = new BigDecimal(first);
			BigDecimal y = new BigDecimal(second);
			x = x.setScale(decimal, BigDecimal.ROUND_DOWN);
			y = y.setScale(decimal, BigDecimal.ROUND_DOWN);
			retval = x.compareTo(y);
			
		}
		return retval;
	}
	/**
	 * returns only the integer part of the big decimal number
	 * @param value
	 * @return
	 */
	public int getBigDecimalIntegerPart(String value){
		int retval = 0;
		
		if(StringUtils.isNotEmpty(value)){
			//check for 1000 separator as in 1.000,55
			//remove it
			value = this.cleanFormattedAspects(value);
			value = value.replace(",", ".");
			BigDecimal x = new BigDecimal(value);
			retval = x.intValue();
		}
		return retval;
	}
	
	/**
	 * returns only the integer part of the big decimal number
	 * @param value
	 * @return
	 */
	public BigDecimal getBigDecimal(String value){
		BigDecimal retval = new BigDecimal("0");
		if(StringUtils.isNotEmpty(value)){
			//check for 1000 separator as in 1.000,55
			//remove it
			value = this.cleanFormattedAspects(value);
			value = value.replace(",", ".");
			BigDecimal x = new BigDecimal(value);
			retval = x;
		}
		return retval;
	}
	
	private String cleanFormattedAspects(String value){
		String retval = value;
		if(retval.contains(".") && retval.contains(",")){
			retval = retval.replace(".", "");
		}
		
		return retval;
		
	}
}
