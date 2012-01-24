package de.smeky.android.pizzasize.pizza;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public abstract class Pizza implements Parcelable, Serializable, Comparable<Pizza> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8756388094912197617L;
	private String pizzaName = "";
	private double  sellingPrice = 0;
	
	public Pizza(){
		pizzaName = "Pizza";
		sellingPrice = 4.20;
	}
	
	public double getPrize() {
		return sellingPrice;
	}

	public void setPrize(double price) {
		this.sellingPrice = price;
	}

	public abstract double getSquareSize();
	
	public abstract String getDimension();
	
	public double getSquarePirce(){
		return Math.round(sellingPrice/(this.getSquareSize()*0.0001)*100.)/100.;
		
	}

	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}

	public String getPizzaName() {
		return pizzaName;
	}
	
	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {	
		Log.i("PizzaEdit", "Schreibe Name"  + pizzaName + " und Preis " + sellingPrice);
		dest.writeString(pizzaName);
		dest.writeDouble(sellingPrice);
	}
	


	protected Pizza(Parcel in) {
		
		pizzaName = in.readString();
		sellingPrice = in.readDouble();
		
		Log.i("PizzaEdit", "Lese Name"  + pizzaName + " und Preis " + sellingPrice);
	}
	
    // Die Methode wird durch Comparable vorgeschrieben.
    // Wenn "this < argument" dann muss die Methode irgendetwas < 0 zur�ckgeben
    // Wenn "this = argument" dann muss die Methode 0 (irgendetwas = 0) zur�ckgeben
    // Wenn "this > argument" dann muss die Methode irgendetwas > 0 zur�ckgeben        
    public int compareTo( Pizza argument ) {
        if( getSquarePirce() < argument.getSquarePirce() )
            return -1;
        if( getSquarePirce() > argument.getSquarePirce() )
            return 1;
        
        return 0;
    }

}
