package com.intw.program;

import java.util.HashMap;
import java.util.stream.IntStream;

public class RomanToDecimalConversion {
	/**
	 *  Requirement:	 * 
	 * Convert Roman numeral to decimal
	 * 
	 * Use case
	 * Example: 
	 * I/P: Roman String  O/p: Decimal value
	 *  "LCX": 60
	 *  "MMXX" 2020
	 *  "XLVII": 47
	 *  "MXVII": 1017
	 *  "MCMIV": 1904
	 */
	static HashMap<String, Integer> rdMap= null;
	static {
		rdMap= new HashMap<>(7);
		rdMap.put("I", 1); 
		rdMap.put("V", 5);
		rdMap.put("X", 10);
		rdMap.put("L", 50);
		rdMap.put("C", 100);
		rdMap.put("D", 500);
		rdMap.put("M", 1000);	
	}

	public static void main(String[] args) {
		RomanToDecimalConversion rd = new RomanToDecimalConversion();
		//I/P: Roman String
		String romanVal =  "MCMIV";
		int decimalVal = rd.convertRomanToDecimal(romanVal);
		//print output
		System.out.println("decimal for roman: "+ romanVal + " is: " + decimalVal);
	}

	private final  int romanDecimal(String romanChar) {
		return rdMap.get(romanChar).intValue();
	}

	private final int convertRomanToDecimal(String romanString) {
		String[] rsArr = romanString.split("");
		int decimalVal  = IntStream.rangeClosed(0, rsArr.length-1)
				.map(i -> 
				( ((i < rsArr.length-1) && (romanDecimal(rsArr[i]) >= romanDecimal(rsArr[i+1] ))
						|| (i == rsArr.length-1) )
						? (romanDecimal(rsArr[i])): -(romanDecimal(rsArr[i])) ))
				.sum();
		return decimalVal;
	}

}
