package de.smeky.android.pizzasize.edit;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import de.smeky.android.pizzasize.Helper;
import de.smeky.android.pizzasize.R;
import de.smeky.android.pizzasize.pizza.Pizza;
import de.smeky.android.pizzasize.pizza.PizzaRectangular;
import de.smeky.android.pizzasize.pizza.PizzaRound;

public class PizzaEditRectangular extends PizzaEdit  implements OnSeekBarChangeListener, OnKeyListener {
	
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
        
        txtWidth.setText(""+temp.getWidth());
        txtLength.setText(""+temp.getLength());
        
        txtLength.setText(Helper.doubleToRealNumberString(temp.getLength()));
        txtLength.setOnKeyListener(this);
        txtWidth.setText(Helper.doubleToRealNumberString(temp.getWidth()));
        txtWidth.setOnKeyListener(this);
        
        sbWidth = (SeekBar)findViewById(R.id.seekBarWidth);
        sbWidth.setOnSeekBarChangeListener(this);
        
        sbLength = (SeekBar)findViewById(R.id.seekBarLength);
        sbLength.setOnSeekBarChangeListener(this);
        
        synchronizeTextToSeekBar(R.id.txtWidth, R.id.seekBarWidth);
        synchronizeTextToSeekBar(R.id.txtLength, R.id.seekBarLength);
      
      
    }

    
    
    @Override
	public void onClick(View v) {
		
		if(v.equals(btnSave) && checkValues() ){
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

	 public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
	        
		 
		 switch(seekBar.getId()){
		 
		 case R.id.seekBarLength:
			 String progressStringHeight = progress +"";
		    	
		    	if(fromTouch) {
		        	txtLength.setText(progressStringHeight);
		        } 
		    	break;
		 case R.id.seekBarWidth:
			 
			 String progressStringWidth = progress +"";
		    	
		    	if(fromTouch) {
		        	txtWidth.setText(progressStringWidth);
		        }
		    	break;
		 }
	    	
	    }

	    public void onStartTrackingTouch(SeekBar seekBar) {
	        
	    }

	    public void onStopTrackingTouch(SeekBar seekBar) {
	      
	    }


		public boolean onKey(View v, int keyCode, KeyEvent event) {
			
			switch(v.getId()){
			
			case R.id.txtWidth:
				
				synchronizeTextToSeekBar(R.id.txtWidth, R.id.seekBarWidth);
				break;
			
			case R.id.txtLength:
				
			    synchronizeTextToSeekBar(R.id.txtLength, R.id.seekBarLength);
				break;
			
				
			}
				
			return false;
		}


	    @Override
		protected boolean checkValues() {
				
		
	    	if(!Helper.checkIntegerIsPositive(txtWidth.getText().toString(), R.string.error_width, getApplicationContext())){
	    		return false;
	    	}
			
	    	if(!Helper.checkIntegerIsPositive(txtLength.getText().toString(), R.string.error_length, getApplicationContext())){
	    		return false;
	    	}
	    	
			
	    	if(!super.checkValues()){
	    		return false;
	    	}
						
			return true;
		}

	
	
}