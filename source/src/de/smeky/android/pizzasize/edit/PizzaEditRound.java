package de.smeky.android.pizzasize.edit;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import de.smeky.android.pizzasize.Helper;
import de.smeky.android.pizzasize.R;
import de.smeky.android.pizzasize.pizza.Pizza;
import de.smeky.android.pizzasize.pizza.PizzaRound;

public class PizzaEditRound extends PizzaEdit implements
		OnSeekBarChangeListener, OnKeyListener {

	private EditText txtDiameter;
	private SeekBar diameterSeekBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.pizza_edit_round);
		super.onCreate(savedInstanceState);

		txtDiameter = (EditText) findViewById(R.id.txtDiameter);

		PizzaRound temp = (PizzaRound) pizza;

		txtDiameter
				.setText(Helper.doubleToRealNumberString(temp.getDiameter()));
		txtDiameter.setOnKeyListener(this);

		diameterSeekBar = (SeekBar) findViewById(R.id.seekBarDiameter);
		diameterSeekBar.setOnSeekBarChangeListener(this);

		synchronizeTextToSeekBar(R.id.txtDiameter, R.id.seekBarDiameter);

	}

	@Override
	public void onClick(View v) {

		if (v.equals(btnSave) && checkValues()) {
			newPizza = new PizzaRound();
			((PizzaRound) newPizza).setDiameter(Double.valueOf(txtDiameter
					.getText().toString()));
		}

		super.onClick(v);
	}

	@Override
	protected boolean checkValues() {



		if (!Helper.checkIntegerIsPositive(txtDiameter.getText().toString(),
				R.string.error_diameter, getApplicationContext())) {
			return false;
		}
		
		
		if (!super.checkValues()) {
		return false;
		}

		return true;
	}

	@Override
	protected Pizza getNewPizzaStore() {
		return new PizzaRound();
	}

	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromTouch) {

		String progressString = progress + "";

		if (fromTouch) {
			txtDiameter.setText(progressString);
		}
	}

	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	public void onStopTrackingTouch(SeekBar seekBar) {

	}

	public boolean onKey(View v, int keyCode, KeyEvent event) {

		switch (v.getId()) {

		case R.id.txtDiameter:

			synchronizeTextToSeekBar(R.id.txtDiameter, R.id.seekBarDiameter);
			break;

		}

		return false;
	}

}