package de.smeky.android.pizzasize;

import java.util.ArrayList;
import java.util.List;

import de.smeky.android.pizzasize.pizza.Pizza;
import de.smeky.android.pizzasize.pizza.PizzaRectangular;
import de.smeky.android.pizzasize.pizza.PizzaRound;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
/* I will be back  (Egoist) */
public class MainActivity extends ListActivity {
    /** Called when the activity is first created. */
	private List<Pizza> pizzaList;
	private PizzaListAdapter pizzaAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        pizzaList = new ArrayList<Pizza>();
        
        pizzaList = createTestPizzaList();
        
        pizzaAdapter = new PizzaListAdapter(this, pizzaList);
        
        setListAdapter(pizzaAdapter);
        
        pizzaAdapter.notifyDataSetChanged();
        
        
        
    }

	private List<Pizza> createTestPizzaList() {
		List<Pizza> list = new ArrayList<Pizza>();
		
		Pizza pizza1 = new PizzaRectangular();
		pizza1.setPizzaName("Pizza1");
        list.add(pizza1);
        
		Pizza pizza2 = new PizzaRound();
		pizza1.setPizzaName("Pizza1");
		list.add(pizza2);
		
		Pizza pizza3 = new PizzaRectangular();
		pizza1.setPizzaName("Pizza1");
		list.add(pizza3);
		
		Pizza pizza4 = new PizzaRound();
		pizza1.setPizzaName("Pizza4");
		list.add(pizza4);
		
		return list;
	}
}