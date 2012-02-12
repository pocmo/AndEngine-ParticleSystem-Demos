package de.pocmo.particle;

import de.pocmo.particle.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ParticleListActivity extends ListActivity
{
	private ParticleListAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.particle_list);
        
        adapter = new ParticleListAdapter();/*new ArrayAdapter<String>(
    		this,
    		android.R.layout.simple_list_item_1,
    		FightParticle.PARTICLE_SYSTEMS
		);*/
        
        setListAdapter(adapter);
    }
    
    @Override
    public void onListItemClick (ListView listView, View view, int position, long id)
    {
    	Intent intent = new Intent(ParticleListActivity.this, ParticleViewActivity.class);
    	intent.putExtra("index", position);
    	startActivity(intent);
    }
}