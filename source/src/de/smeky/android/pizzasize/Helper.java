package de.smeky.android.pizzasize;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class  Helper {
	
	private Helper(){
		
	}

	static public String doubleToRealNumberString( double dbl ){
		NumberFormat number = new DecimalFormat( "0" );
		
		return 	number.format( dbl );
	}
	
	static public String doubleToCurrencyString( double dbl ){
		NumberFormat number = new DecimalFormat( "0.00" );
		
		return 	number.format( dbl );
	}
	
}
