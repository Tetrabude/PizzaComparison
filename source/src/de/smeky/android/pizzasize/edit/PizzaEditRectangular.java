package de.smeky.android.pizzasize.edit;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import de.smeky.android.pizzasize.R;
import de.smeky.android.pizzasize.pizza.Pizza;
import de.smeky.android.pizzasize.pizza.PizzaRectangular;
import de.smeky.android.pizzasize.pizza.PizzaRound;

public class PizzaEditRectangular extends PizzaEdit {
	
	private EditText txtWidth;
	private EditText txtHeight;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setContentView(R.layout.pizza_edit_rectangular);
    	super.onCreate(savedInstanceState);
        
        txtWidth = (EditText) findViewById(R.id.txtWidth);
        txtHeight = (EditText) findViewById(R.id.txtHeight);

        PizzaRectangular temp = (PizzaRectangular) pizza;
        
        txtWidth.setText(""+temp.getWidth());
        txtHeight.setText(""+temp.getLength());
    }

    
    
    @Override
	public void onClick(View v) {
		
		if(v.equals(btnSave)){
			newPizza = new PizzaRectangular();
			((PizzaRectangular) newPizza).setWidth(Double.valueOf(txtWidth.getText().toString()));
			
			((PizzaRectangular) newPizza).setLength(Double.valueOf(txtHeight.getText().toString()));
		}

		super.onClick(v);
	}



	@Override
	protected Pizza getNewPizzaStore() {
		return new PizzaRectangular();
	}



	
	
}