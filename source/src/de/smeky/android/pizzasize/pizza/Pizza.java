package de.smeky.android.pizzasize.pizza;

public abstract class Pizza {
	
	private String pizzaName = "";
	private double  prize = 0;
	
	public double getPrize() {
		return prize;
	}

	public void setPrize(double prize) {
		this.prize = prize;
	}

	public abstract double getAreaSize();
	
	public double getPircePerArea(){
		return prize/this.getAreaSize();
		
	}

	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}

	public String getPizzaName() {
		return pizzaName;
	}

}
