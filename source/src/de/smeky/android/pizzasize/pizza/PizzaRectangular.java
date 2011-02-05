package de.smeky.android.pizzasize.pizza;

public class PizzaRectangular extends Pizza {


	private double length = 0;
	private double width = 0;

	@Override
	public double getAreaSize() {
		return length*width;
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


}
