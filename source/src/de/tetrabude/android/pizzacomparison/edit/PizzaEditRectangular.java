package de.tetrabude.android.pizzacomparison.edit;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import de.tetrabude.android.pizzacomparison.Helper;
import de.tetrabude.android.pizzacomparison.R;
import de.tetrabude.android.pizzacomparison.pizza.Pizza;
import de.tetrabude.android.pizzacomparison.pizza.PizzaRectangular;

public class PizzaEditRectangular extends PizzaEdit implements OnSeekBarChangeListener, OnKeyListener {

	private EditText txtWidth;
	private EditText txtLength;
	private SeekBar sbWidth;
	private SeekBar sbLength;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.pizza_edit_rectangular);
		super.onCreate(savedInstanceState);

		txtWidth = (EditText) findViewById(R.id.txtWidth);
		txtLength = (EditText) findViewById(R.id.txtLength);

		PizzaRectangular temp = (PizzaRectangular) pizza;

		txtWidth.setText("" + temp.getWidth());
		txtLength.setText("" + temp.getLength());

		txtLength.setText(Helper.doubleToRealNumberString(temp.getLength()));
		txtLength.setOnKeyListener(this);
		txtWidth.setText(Helper.doubleToRealNumberString(temp.getWidth()));
		txtWidth.setOnKeyListener(this);

		sbWidth = (SeekBar) findViewById(R.id.seekBarWidth);
		sbWidth.setOnSeekBarChangeListener(this);

		sbLength = (SeekBar) findViewById(R.id.seekBarLength);
		sbLength.setOnSeekBarChangeListener(this);

		synchronizeTextToSeekBar(R.id.txtWidth, R.id.seekBarWidth);
		synchronizeTextToSeekBar(R.id.txtLength, R.id.seekBarLength);


	}

	@Override
	public void onClick(View v) {

		if (v.equals(btnSave) && checkValues()) {
			newPizza = new PizzaRectangular();

			((PizzaRectangular) newPizza).setWidth(Double.valueOf(txtWidth.getText().toString()));

			((PizzaRectangular) newPizza).setLength(Double.valueOf(txtLength.getText().toString()));
		}

		super.onClick(v);
	}

	@Override
	protected Pizza getNewPizzaStore() {
		return new PizzaRectangular();
	}

	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

		switch (seekBar.getId()) {

		case R.id.seekBarLength:
			if (fromUser) {
				synchronizeSeekBarToText(R.id.txtLength, R.id.seekBarLength);
			}
			break;
		case R.id.seekBarWidth:

			if (fromUser) {
				synchronizeSeekBarToText(R.id.txtWidth, R.id.seekBarWidth);
			}
			break;
		}

		super.onProgressChanged(seekBar, progress, fromUser);
	}

	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	public void onStopTrackingTouch(SeekBar seekBar) {

	}
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {

		switch (v.getId()) {

		case R.id.txtWidth:

			synchronizeTextToSeekBar(R.id.txtWidth, R.id.seekBarWidth);
			break;

		case R.id.txtLength:

			synchronizeTextToSeekBar(R.id.txtLength, R.id.seekBarLength);
			break;

		}
		
		super.onKey(v, keyCode, event);

		return false;
	}

	@Override
	protected boolean checkValues() {

		if (!Helper
				.checkIntegerIsPositive(txtWidth.getText().toString(), R.string.error_width, getApplicationContext())) {
			return false;
		}

		if (!Helper.checkIntegerIsPositive(txtLength.getText().toString(), R.string.error_length,
				getApplicationContext())) {
			return false;
		}

		if (!super.checkValues()) {
			return false;
		}

		return true;
	}

}