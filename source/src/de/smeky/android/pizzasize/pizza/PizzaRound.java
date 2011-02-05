package de.smeky.android.pizzasize.pizza;

public class PizzaRound extends Pizza {
	
	double diameter = 0;
	
	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	@Override
	public double getAreaSize() {
		return Math.PI*(diameter/2)*(diameter/2);
	}


}
