package de.pocmo.particle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ParticleListAdapter extends BaseAdapter
{
	@Override
	public int getCount() {
		return FightParticle.PARTICLE_SYSTEMS.length;
	}

	@Override
	public ParticleSystemFactory getItem(int position) {
		return FightParticle.PARTICLE_SYSTEMS[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(android.R.layout.activity_list_item, null);
	      ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position).getTitle());
	      return convertView;
	}
	
}
