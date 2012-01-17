package de.smeky.android.pizzasize.pizza;

import de.smeky.android.pizzasize.Helper;
import android.os.Parcel;
import android.os.Parcelable;

public class PizzaRound extends Pizza {

	double diameter = 0;

	public PizzaRound() {

	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	@Override
	public double getSquareSize() {
		return Math.round(Math.PI * (diameter / 2) * (diameter / 2) * 100.) / 100.;
	}

	@Override
	public String getDimension() {
		return "Ø " + Helper.doubleToRealNumberString(diameter);
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeDouble(diameter);
	}

	public static final Parcelable.Creator<Pizza> CREATOR = new Parcelable.Creator<Pizza>() {
		public Pizza createFromParcel(Parcel in) {
			return new PizzaRound(in);
		}

		public Pizza[] newArray(int size) {
			return new Pizza[size];
		}
	};

	public PizzaRound(Parcel in) {
		super(in);
		diameter = in.readDouble();
	}
}
