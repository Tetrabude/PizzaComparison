package de.smeky.android.pizzasize;

import java.util.ArrayList;
import java.util.List;

import de.smeky.android.pizzasize.edit.PizzaEditRectangular;
import de.smeky.android.pizzasize.edit.PizzaEditRound;
import de.smeky.android.pizzasize.pizza.Pizza;
import de.smeky.android.pizzasize.pizza.PizzaRectangular;
import de.smeky.android.pizzasize.pizza.PizzaRound;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ListActivity implements OnItemClickListener, OnClickListener, OnCreateContextMenuListener {
	/** Called when the activity is first created. */
	private List<Pizza> pizzaList;
	private PizzaListAdapter pizzaAdapter;
	private static final int PIZZA_REQUEST = 1;
	
	private ImageView addRound;
	private ImageView addRectangular;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Kontextmenü
		registerForContextMenu(getListView());
		
		
		// GUI-Initialisieren:
		
		addRound = (ImageView) findViewById(R.id.imageViewAddRound);
		addRound.setOnClickListener(this);
		
		addRectangular = (ImageView) findViewById(R.id.imageViewAddRectangular);
		addRectangular.setOnClickListener(this);

		getListView().setOnItemClickListener(this);
	
		
		pizzaList = new ArrayList<Pizza>();

		pizzaList = createTestPizzaList();

		pizzaAdapter = new PizzaListAdapter(this, pizzaList);

		setListAdapter(pizzaAdapter);

		java.util.Collections.sort(pizzaList);
		pizzaAdapter.notifyDataSetChanged();

	}

	private List<Pizza> createTestPizzaList() {
		List<Pizza> list = new ArrayList<Pizza>();
		
		PizzaRound pizza2 = new PizzaRound();
		pizza2.setPizzaName("Mini");
		pizza2.setPrize(3.60);
		pizza2.setDiameter(20.0);
		list.add(pizza2);

		PizzaRound pizza4 = new PizzaRound();
		pizza4.setPizzaName("Klein");
		pizza4.setPrize(5.15);
		pizza4.setDiameter(26.0);
		list.add(pizza4);

		PizzaRound pizza6 = new PizzaRound();
		pizza6.setPizzaName("Groß");
		pizza6.setPrize(6.15);
		pizza6.setDiameter(30);
		list.add(pizza6);
		
		PizzaRectangular pizza5 = new PizzaRectangular();
		pizza5.setPizzaName("Family");
		pizza5.setPrize(12.30);
		pizza5.setLength(48.0);
		pizza5.setWidth(32.0);
		list.add(pizza5);
		
		PizzaRectangular pizza9 = new PizzaRectangular();
		pizza9.setPizzaName("Family klein");
		pizza9.setPrize(12.0);
		pizza9.setLength(45.0);
		pizza9.setWidth(30.0);
		list.add(pizza9);

//		PizzaRound pizza7 = new PizzaRound();
//		pizza7.setPizzaName("Logmuschi Pizza");
//		pizza7.setPrize(2.0);
//		pizza7.setDiameter(4.0);
//		list.add(pizza7);
//
//		PizzaRound pizza8 = new PizzaRound();
//		pizza8.setPizzaName("Pizza8");
//		pizza8.setPrize(4.0);
//		pizza8.setDiameter(2.0);
//		list.add(pizza8);
//
//
//		PizzaRectangular pizza10 = new PizzaRectangular();
//		pizza10.setPizzaName("Neunte Pizza plus eins");
//		pizza10.setPrize(2.0);
//		pizza10.setLength(3.0);
//		pizza10.setWidth(4.0);
//		list.add(pizza10);

		return list;
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Pizza intentPizza = pizzaList.get(position);

		Intent intent;

		Log.i("MainActivity", intentPizza.getClass().toString());
		Log.i("MainActivity", "Position: " + position + " id: " + id);

		if (intentPizza.getClass() == PizzaRound.class) {
			Log.i("MainActivity", "Rund");
			intent = new Intent(this, PizzaEditRound.class);
			intent.putExtra("pizza", intentPizza);

		} else if (intentPizza.getClass() == PizzaRectangular.class) {
			Log.i("MainActivity", "Eckig");
			intent = new Intent(this, PizzaEditRectangular.class);
			intent.putExtra("pizza", intentPizza);

		} else {
			Log.e("MainActivity", "Weder Noch!!!");
			return;
		}

		intent.putExtra("id", (int) id);

		startActivityForResult(intent, PIZZA_REQUEST);

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == PIZZA_REQUEST) {
			if (resultCode == RESULT_OK) {

				int id = data.getExtras().getInt("id");

				if (id >= 0) {
					// Update
					pizzaList.set(id, ((Pizza) data.getExtras().get("pizza")));
					Log.i("PizzaEdit","Cast: " + ((Pizza) data.getExtras().get("pizza")).getPizzaName());
				} else {
					// Hinzufügen
					pizzaList.add((Pizza) data.getExtras().get("pizza"));
				}

				
				java.util.Collections.sort(pizzaList);
				
				//java.util.ArrayList<Pizza>.so >> Arrays.sort( pizzaList );
				
				pizzaAdapter.notifyDataSetChanged();
			}
		}
	}

	public void onClick(View v) {
		
		if(v.equals(addRound)){
			Intent intent;
			intent = new Intent(this, PizzaEditRound.class);
			intent.putExtra("id", -1);
			startActivityForResult(intent, PIZZA_REQUEST);
			
		} else if(v.equals(addRectangular)){
			Intent intent;
			intent = new Intent(this, PizzaEditRectangular.class);
			intent.putExtra("id", -1);
			startActivityForResult(intent, PIZZA_REQUEST);
			
		}
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	                                ContextMenuInfo menuInfo) {
	  super.onCreateContextMenu(menu, v, menuInfo);
	  MenuInflater inflater = getMenuInflater();
	  inflater.inflate(R.menu.pizza_item_kontext, menu);
	}


	@Override
	public boolean onContextItemSelected(MenuItem item) {
	  AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	  switch (item.getItemId()) {
	  case R.id.delete:
		  pizzaList.remove((int)info.id);
		  pizzaAdapter.notifyDataSetChanged();
	    return true;
	  default:
	    return super.onContextItemSelected(item);
	  }
	}

}


