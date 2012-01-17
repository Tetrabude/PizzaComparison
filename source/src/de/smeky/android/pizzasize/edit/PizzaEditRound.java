package de.smeky.android.pizzasize.edit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import de.smeky.android.pizzasize.R;
import de.smeky.android.pizzasize.pizza.Pizza;
import de.smeky.android.pizzasize.pizza.PizzaRound;

public class PizzaEditRound extends PizzaEdit {
	
	private EditText txtDiameter;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setContentView(R.layout.pizza_edit_round);
    	super.onCreate(savedInstanceState);
        
        txtDiameter = (EditText) findViewById(R.id.txtDiameter);

        PizzaRound temp = (PizzaRound) pizza;
        
        txtDiameter.setText(""+temp.getDiameter());
        
    }

    @Override
	public void onClick(View v) {
		
		if(v.equals(btnSave)){
			newPizza = new PizzaRound();
			((PizzaRound) newPizza).setDiameter(Double.valueOf(txtDiameter.getText().toString()));
		}

		super.onClick(v);
	}

	@Override
	protected Pizza getNewPizzaStore() {
		return new PizzaRound();
	}
	
}