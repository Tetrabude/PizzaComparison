package de.tetrabude.android.pizzacomparison;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.tetrabude.android.pizzacomparison.pizza.Pizza;
import de.tetrabude.android.pizzacomparison.pizza.PizzaRectangular;
import de.tetrabude.android.pizzacomparison.pizza.PizzaRound;

public class PizzaListAdapter extends BaseAdapter {
	
	private List<Pizza> pizzaData;
	private LayoutInflater mInflator;
	
	static class ViewHolder {
		TextView pizzaName;
		TextView pizzaSellingPrice;
		TextView pizzaSquareSize;
		TextView pizzaSquarePrice;
		TextView pizzaDimension;
		ImageView pizzaImage;
		
	}
	
	public PizzaListAdapter(Context c, List<Pizza> pizzaData ){
		this.pizzaData = pizzaData;
		mInflator = LayoutInflater.from(c);
	}

	public int getCount() {
		return pizzaData.size();
	}

	public Object getItem(int position) {
		return pizzaData.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
//		
//		if(pizzaData.size() == 0) {
//			
//			convertView = mInflator.inflate(R.layout.start_view, parent,false);
//			return convertView;
//			
//		}


		if(convertView==null){
				
			convertView = mInflator.inflate(R.layout.pizza_item, parent,false);
			holder = new ViewHolder();
			holder.pizzaName = (TextView) convertView.findViewById(R.id.textViewName);
			holder.pizzaSellingPrice = (TextView) convertView.findViewById(R.id.textViewSellingPrice);
			holder.pizzaSquarePrice = (TextView) convertView.findViewById(R.id.textViewSquarePrice);
			holder.pizzaSquareSize = (TextView) convertView.findViewById(R.id.textViewSquareSize);
			holder.pizzaDimension = (TextView) convertView.findViewById(R.id.textViewDimension);
			holder.pizzaImage = (ImageView) convertView.findViewById(R.id.imageView1);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		Pizza tmpPiz = pizzaData.get(position);
		
		double factor_currency = parent.getResources().getInteger(R.integer.factor_to_square_currency);
		double factor_unit = parent.getResources().getInteger(R.integer.factor_to_square_unit);
		
		
		holder.pizzaName.setText(tmpPiz.getPizzaName());
		holder.pizzaSellingPrice.setText("" + Helper.doubleToCurrencyString(tmpPiz.getPrize()));
		holder.pizzaSquarePrice.setText("" + Helper.doubleToCurrencyString(tmpPiz.getSquarePrice(factor_currency, factor_unit)));
		holder.pizzaSquareSize.setText("" + Helper.doubleToRealNumberString(tmpPiz.getSquareSize()));
		holder.pizzaDimension.setText(tmpPiz.getDimension());
		
		if(tmpPiz.getClass() == PizzaRound.class) {
			holder.pizzaImage.setImageResource(R.drawable.pizza_rund);
		} else if(tmpPiz.getClass() == PizzaRectangular.class){
			holder.pizzaImage.setImageResource(R.drawable.pizza_eckig);
		}
		
		return convertView;
	}


}
