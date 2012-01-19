package de.smeky.android.pizzasize.edit;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import de.smeky.android.pizzasize.Helper;
import de.smeky.android.pizzasize.R;
import de.smeky.android.pizzasize.pizza.Pizza;
import de.smeky.android.pizzasize.pizza.PizzaRectangular;
import de.smeky.android.pizzasize.pizza.PizzaRound;

public class PizzaEditRectangular extends PizzaEdit  implements OnSeekBarChangeListener, OnKeyListener {
	
	private EditText txtWidth;
	private EditText txtHeight;
	private SeekBar widthSeekBar;
	private SeekBar heightSeekBar;
 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setContentView(R.layout.pizza_edit_rectangular);
    	super.onCreate(savedInstanceState);
        
        txtWidth = (EditText) findViewById(R.id.txtWidth);
        txtHeight = (EditText) findViewById(R.id.txtHeight);

        PizzaRectangular temp = (PizzaRectangular) pizza;
        
        txtWidth.setText(""+temp.getWidth());
        txtHeight.setText(""+temp.getLength());
        
        txtHeight.setText(Helper.doubleToRealNumberString(temp.getLength()));
        txtHeight.setOnKeyListener(this);
        txtWidth.setText(Helper.doubleToRealNumberString(temp.getWidth()));
        txtWidth.setOnKeyListener(this);
        
        widthSeekBar = (SeekBar)findViewById(R.id.seekBarWidth);
        widthSeekBar.setOnSeekBarChangeListener(this);
        
        heightSeekBar = (SeekBar)findViewById(R.id.seekBarHeight);
        heightSeekBar.setOnSeekBarChangeListener(this);
      
      
    }

    
    
    @Override
	public void onClick(View v) {
		
		if(v.equals(btnSave)){
			newPizza = new PizzaRectangular();
			((PizzaRectangular) newPizza).setWidth(Double.valueOf(txtWidth.getText().toString()));
			
			((PizzaRectangular) newPizza).setLength(Double.valueOf(txtHeight.getText().toString()));
		}

		super.onClick(v);
	}



	@Override
	protected Pizza getNewPizzaStore() {
		return new PizzaRectangular();
	}

	 public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
	        
		 
		 switch(seekBar.getId()){
		 
		 case R.id.seekBarHeight:
			 String progressStringHeight = progress +"";
		    	
		    	if(fromTouch) {
		        	txtHeight.setText(progressStringHeight);
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
				
				if(!txtWidth.getText().toString().equals(""))
				{
			
				
				int sekWidth = widthSeekBar.getProgress();
				int width = Integer.valueOf(txtWidth.getText().toString());
							
				
				if(sekWidth != width){
					widthSeekBar.setProgress(width);
				}
				}
				break;
			
			case R.id.txtHeight:
				
				if(!txtHeight.getText().toString().equals(""))
				{
			
				
				int sekWidth = heightSeekBar.getProgress();
				int width = Integer.valueOf(txtHeight.getText().toString());
							
				
				if(sekWidth != width){
					heightSeekBar.setProgress(width);
				}
				}
				break;
			
				
			}
				
			return false;
		}

	
	
}