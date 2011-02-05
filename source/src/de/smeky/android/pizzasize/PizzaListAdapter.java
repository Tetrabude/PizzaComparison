package de.smeky.android.pizzasize;

import java.util.List;

import de.smeky.android.pizzasize.pizza.Pizza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PizzaListAdapter extends BaseAdapter {
	
	private List<Pizza> pizzaData;
	private LayoutInflater mInflator;
	
	static class ViewHolder {
		TextView text;
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
		
		if(convertView==null){
			convertView = mInflator.inflate(R.layout.pizza_item, parent,false);
			holder = new ViewHolder();
			holder.text = (TextView) convertView.findViewById(R.id.textView1);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.text.setText(pizzaData.get(position).getPizzaName());
		
		return null;
	}

}
