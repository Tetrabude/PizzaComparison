package de.tetrabude.android.pizzacomparison;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import de.tetrabude.android.pizzacomparison.edit.PizzaEditRectangular;
import de.tetrabude.android.pizzacomparison.edit.PizzaEditRound;
import de.tetrabude.android.pizzacomparison.pizza.Pizza;
import de.tetrabude.android.pizzacomparison.pizza.PizzaRectangular;
import de.tetrabude.android.pizzacomparison.pizza.PizzaRound;

public class MainActivity extends ListActivity implements OnItemClickListener, OnClickListener, OnCreateContextMenuListener {
	/** Called when the activity is first created. */

	private static final String FILENAME = "pizzalist";
	
	private ArrayList<Pizza> pizzaList;
	private ArrayList<Pizza> pizzaDemoList;
	private PizzaListAdapter pizzaAdapter;
	private static final int PIZZA_REQUEST = 1;
	
	private ImageView addRound;
	private ImageView addRectangular;
	private TextView infoTextTop;
	private TextView infoTextBottom;

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
		
		infoTextTop = (TextView) findViewById(R.id.info_text_top);
		infoTextBottom = (TextView) findViewById(R.id.info_text_bottom);
		
		getListView().setOnItemClickListener(this);

		
		if(savedInstanceState != null){
			pizzaList = savedInstanceState.getParcelableArrayList("pizzalist");
		} else {
			pizzaList = loadPizzaListFromFile();
		}
		
		
		
		pizzaAdapter = new PizzaListAdapter(this, pizzaList);

		setListAdapter(pizzaAdapter);

		listChanged();

	}


	private void listChanged() {
		java.util.Collections.sort(pizzaList);
		pizzaAdapter.notifyDataSetChanged();
		
		if(pizzaList.size() == 0) {
			infoTextBottom.setVisibility(TextView.VISIBLE);
			infoTextTop.setVisibility(TextView.VISIBLE);
		} else {

			infoTextBottom.setVisibility(TextView.INVISIBLE);
			infoTextTop.setVisibility(TextView.INVISIBLE);
		}
	}
	

	private ArrayList<Pizza> loadPizzaListFromFile(){
		
		try {

			FileInputStream fis;
			fis = openFileInput(FILENAME);

			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Pizza> pList = (ArrayList<Pizza>) ois.readObject();
			ois.close();
			fis.close();
			
			return pList;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ArrayList<Pizza>(10);
		
	}
	
	
	private ArrayList<Pizza> createTestPizzaList() {
		
	
		
		ArrayList<Pizza> list = new ArrayList<Pizza>();
		
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
			intent.putExtra("pizza", (Parcelable) intentPizza);

		} else if (intentPizza.getClass() == PizzaRectangular.class) {
			Log.i("MainActivity", "Eckig");
			intent = new Intent(this, PizzaEditRectangular.class);
			intent.putExtra("pizza", (Parcelable) intentPizza);

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
				
				listChanged();
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
		  listChanged();
	    return true;
	  default:
	    return super.onContextItemSelected(item);
	  }
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelableArrayList("pizzalist", pizzaList);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
		
		try {
			FileOutputStream fos;
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
		
			oos.writeObject(pizzaList);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


