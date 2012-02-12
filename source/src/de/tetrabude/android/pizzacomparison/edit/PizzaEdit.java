package de.tetrabude.android.pizzacomparison.edit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import de.tetrabude.android.pizzacomparison.Helper;
import de.tetrabude.android.pizzacomparison.R;
import de.tetrabude.android.pizzacomparison.pizza.Pizza;

public abstract class PizzaEdit extends Activity implements OnClickListener, OnKeyListener, OnSeekBarChangeListener {

	protected EditText txtName;
	//protected EditText txtSellingPrice;
	protected EditText txtSellingPriceEuro;
	protected EditText txtSellingPriceCent;
	protected SeekBar sbSellingPriceEuro;
	protected SeekBar sbSellingPriceCent;
	// EditText txtDiameter;
	protected Button btnSave;
	protected Pizza pizza;
	protected Pizza newPizza;
	protected int id;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		txtName = (EditText) findViewById(R.id.txtName);
		//txtSellingPrice = (EditText) findViewById(R.id.txtSellingPriceEuro);
		btnSave = (Button) findViewById(R.id.btnSave);
		btnSave.setOnClickListener(this);
		
		txtSellingPriceEuro = (EditText) findViewById(R.id.txtSellingPriceEuro);
		txtSellingPriceEuro.setOnKeyListener(this);
		
		txtSellingPriceCent = (EditText) findViewById(R.id.txtSellingPriceCent);
		txtSellingPriceCent.setOnKeyListener(this);

		sbSellingPriceEuro = (SeekBar) findViewById(R.id.seekBarEuro);
		sbSellingPriceEuro.setOnSeekBarChangeListener(this);

		sbSellingPriceCent = (SeekBar) findViewById(R.id.seekBarCent);
		sbSellingPriceCent.setOnSeekBarChangeListener(this);

		pizza = (Pizza) getIntent().getExtras().get("pizza");

		if (pizza == null) {
			pizza = getNewPizzaStore();
		}

		txtName.setText(pizza.getPizzaName());
		
		
		
		txtSellingPriceEuro.setText(
				Helper.getEuroString(pizza.getPrize()));
		txtSellingPriceCent.setText(Helper.getCentString(pizza.getPrize()));
		id = getIntent().getExtras().getInt("id", -1);
		
		synchronizeTextToSeekBar(R.id.txtSellingPriceEuro, R.id.seekBarEuro);
		synchronizeTextToSeekBar(R.id.txtSellingPriceCent, R.id.seekBarCent);

	}

	protected abstract Pizza getNewPizzaStore();

	public void onClick(View v) {
		if (v.equals(btnSave) && checkValues()) {
			Intent intent = new Intent();

			newPizza.setPizzaName(txtName.getText().toString());
			Log.i("PizzaEdit", "Pizza Name: " + txtName.getText().toString());
			
			double price = Double.valueOf(txtSellingPriceEuro.getText().toString());
			price += Double.valueOf(txtSellingPriceCent.getText().toString())* 0.01;
			newPizza.setPrize(price);
			// piz.setDiameter(Double.valueOf(txtDiameter.getText().toString()));

			intent.putExtra("id", id);
			
			intent.putExtra("pizza", (Parcelable) newPizza);

			setResult(Activity.RESULT_OK, intent);
			finish();
		}

	}
	
	public boolean onKey(View v, int keyCode, KeyEvent event) {

		switch (v.getId()) {

		case R.id.txtSellingPriceEuro:

			synchronizeTextToSeekBar(R.id.txtSellingPriceEuro, R.id.seekBarEuro);
			break;
		case R.id.txtSellingPriceCent:

			synchronizeTextToSeekBar(R.id.txtSellingPriceCent, R.id.seekBarCent);
			break;

		}

		return false;
	}
	
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

		switch (seekBar.getId()) {

		case R.id.seekBarEuro:
			if (fromUser) {
				synchronizeSeekBarToText(R.id.txtSellingPriceEuro, R.id.seekBarEuro);
			}
			break;
		case R.id.seekBarCent:

			if (fromUser) {
				synchronizeSeekBarToText(R.id.txtSellingPriceCent, R.id.seekBarCent);
			}
			break;
		}

	}

	protected boolean checkValues() {
		if (txtName.getText().toString().length() <= 0) {
			Toast.makeText(getApplicationContext(), getString(R.string.error_name), Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if (!Helper.checkPrice(txtSellingPriceEuro.getText().toString(),txtSellingPriceCent.getText().toString(),
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
	
	protected void synchronizeSeekBarToText(int textId, int seekBarId)
	{
		SeekBar sBar = (SeekBar) findViewById(seekBarId);
		EditText eText = (EditText) findViewById(textId);
		
		String progressStringWidth = sBar.getProgress() + "";

		eText.setText(progressStringWidth);
		
	}

}