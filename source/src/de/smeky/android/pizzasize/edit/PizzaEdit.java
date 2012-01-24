package de.smeky.android.pizzasize.edit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import de.smeky.android.pizzasize.Helper;
import de.smeky.android.pizzasize.R;
import de.smeky.android.pizzasize.pizza.Pizza;

public abstract class PizzaEdit extends Activity implements OnClickListener {

	protected EditText txtName;
	protected EditText txtSellingPrice;
	// EditText txtDiameter;
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

		if (pizza == null) {
			pizza = getNewPizzaStore();
		}

		txtName.setText(pizza.getPizzaName());
		txtSellingPrice.setText("" + pizza.getPrize());
		id = getIntent().getExtras().getInt("id", -1);

	}

	protected abstract Pizza getNewPizzaStore();

	public void onClick(View v) {
		if (v.equals(btnSave) && checkValues()) {
			Intent intent = new Intent();

			newPizza.setPizzaName(txtName.getText().toString());
			Log.i("PizzaEdit", "Pizza Name: " + txtName.getText().toString());
			newPizza.setPrize(Double.valueOf(txtSellingPrice.getText()
					.toString()));
			// piz.setDiameter(Double.valueOf(txtDiameter.getText().toString()));

			intent.putExtra("id", id);
			
			intent.putExtra("pizza", (Parcelable) newPizza);

			setResult(Activity.RESULT_OK, intent);
			finish();
		}

	}

	protected boolean checkValues() {
		if (txtName.getText().toString().length() <= 0) {
			Toast.makeText(getApplicationContext(), getString(R.string.error_name), Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if (!Helper.checkDoubleIsPositive(txtSellingPrice.getText().toString(),
				R.string.error_price, getApplicationContext())) {
			return false;
		}
		return true;
	}

	protected void synchronizeTextToSeekBar(int textId, int seekBarId) {
		EditText eText = (EditText) findViewById(textId);
		SeekBar sBar = (SeekBar) findViewById(seekBarId);

		if (!eText.getText().toString().equals("")) {

			int sekWidth = sBar.getProgress();
			int width = Integer.valueOf(eText.getText().toString());

			if (sekWidth != width) {
				sBar.setProgress(width);
			}
		}
	}

}