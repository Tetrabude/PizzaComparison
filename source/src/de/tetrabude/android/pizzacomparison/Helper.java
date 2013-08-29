package de.tetrabude.android.pizzacomparison;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.content.Context;
import android.widget.Toast;

public class Helper {

	private Helper() {

	}

	static public String doubleToRealNumberString(double dbl) {
		NumberFormat number = new DecimalFormat("0");

		return number.format(dbl);
	}

	static public String doubleToCurrencyString(double dbl) {
		NumberFormat number = new DecimalFormat("0.00");

		return number.format(dbl);
	}

	static public boolean checkIntegerIsPositive(String txt, int failureMessage, Context context) {

		if (txt.length() <= 0 || Integer.valueOf(txt) <= 0) {

			Toast.makeText(context, context.getString(failureMessage), Toast.LENGTH_SHORT).show();
			return false;
		}

		return true;
	}

	static public boolean checkDoubleIsPositive(String txt, int failureMessage, Context context) {

		if (txt.length() <= 0 || Double.valueOf(txt) <= 0) {

			Toast.makeText(context, context.getString(failureMessage), Toast.LENGTH_SHORT).show();
			return false;
		}

		return true;
	}
	
	static public String getEuroString(double value){
		return doubleToRealNumberString(Math.floor(value));
	}
	
	static public String getCentString(double value){
		// return doubleToRealNumberString(Math.floor((value - Math.floor(value) ) * 100));
		// Modulo ist unser Freund:
		return doubleToRealNumberString((value * 100.0) % 100);
	}

	public static boolean checkPrice(String euro, String cent, int failureMessage, Context context) {
		
		
		if (euro.length() <= 0 || cent.length() <= 0) {

			Toast.makeText(context, context.getString(failureMessage), Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if (Double.valueOf(euro)<=0 && Double.valueOf(cent)<=0){

			Toast.makeText(context, context.getString(failureMessage), Toast.LENGTH_SHORT).show();
			return false;
		}
			
			
		return true;
	}
}
