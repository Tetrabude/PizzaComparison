package de.tetrabude.android.pizzacomparison.pizza;

import android.os.Parcel;
import android.os.Parcelable;
import de.tetrabude.android.pizzacomparison.Helper;

public class PizzaRectangular extends Pizza {



	private static final long serialVersionUID = 6749523652191602371L;
	private double length = 0;
	private double width = 0;

	public PizzaRectangular(){
		
		length = 23;
		width = 42;
		
	}
	

	@Override
	public double getSquareSize() {
		return Math.round(length*width*100.) / 100.;
	}
	
	@Override
	public String getDimension() {
		return "" + Helper.doubleToRealNumberString(length) + " × " + Helper.doubleToRealNumberString(width);
	}
	

	
	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {	
		super.writeToParcel(dest, flags);
		dest.writeDouble(length);
		dest.writeDouble(width);
	}
	
	public static final Parcelable.Creator<Pizza> CREATOR
	= new Parcelable.Creator<Pizza>() {
		public Pizza createFromParcel(Parcel in) {
			return new PizzaRectangular(in);
		}

		public Pizza[] newArray(int size) {
			return new Pizza[size];
		}
	};

	public PizzaRectangular(Parcel in) {
		super(in);
		length = in.readDouble();
		width = in.readDouble();
	}


}
