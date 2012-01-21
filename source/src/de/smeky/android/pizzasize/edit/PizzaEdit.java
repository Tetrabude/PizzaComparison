package de.smeky.android.pizzasize.edit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import de.smeky.android.pizzasize.R;
import de.smeky.android.pizzasize.pizza.Pizza;

public abstract class PizzaEdit extends Activity implements OnClickListener {
	
	protected EditText txtName;
	protected EditText txtSellingPrice;
	//EditText txtDiameter;
	protected Button btnSave;
	protected Pizza pizza;
	protected Pizza newPizza;
	protected int id; 
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        txtName = (EditText) findViewById(R.id.txtName);
        txtSellingPrice = (EditText) findViewById(R.id.txtSellingPrice);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        pizza = (Pizza) getIntent().getExtras().get("pizza");
        
        if(pizza == null){
        	pizza = getNewPizzaStore();
        }
        
        txtName.setText(pizza.getPizzaName());
        txtSellingPrice.setText(""+pizza.getPrize());
        id = getIntent().getExtras().getInt("id", -1);
        
        
        
    }

	protected abstract Pizza getNewPizzaStore();		

	public void onClick(View v) {
		if(v.equals(btnSave)){
			Intent intent = new Intent();
			
			newPizza.setPizzaName(txtName.getText().toString());
			Log.i("PizzaEdit","Pizza Name: " + txtName.getText().toString());
			newPizza.setPrize(Double.valueOf(txtSellingPrice.getText().toString()));
			//piz.setDiameter(Double.valueOf(txtDiameter.getText().toString()));
			
			intent.putExtra("id", id);
			intent.putExtra("pizza", newPizza);

			setResult(Activity.RESULT_OK, intent);
			finish();
		}
		
	}
		
}