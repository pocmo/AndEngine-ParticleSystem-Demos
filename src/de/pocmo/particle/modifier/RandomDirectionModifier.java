package de.pocmo.particle.modifier;

import java.util.HashMap;
import java.util.Random;

import org.anddev.andengine.entity.particle.Particle;
import org.anddev.andengine.entity.particle.modifier.IParticleModifier;

import android.util.Log;

public class RandomDirectionModifier implements IParticleModifier
{
	private Random random;
	private HashMap<Particle, Double> directions;
	private float acceleration;
	
	public RandomDirectionModifier(float acceleration)
	{
		this.acceleration = acceleration;
		this.directions   = new HashMap<Particle, Double>();
		this.random       = new Random();
	}
	

	@Override
	public void onInitializeParticle(Particle particle)
	{
		float direction = random.nextFloat() * 360;
		
		Log.d("Foo", "Direction: " + direction);
		
		directions.put(particle, (double) direction);
	}

	@Override
	public void onUpdateParticle(Particle particle)
	{
		float x = particle.getX();
		float y = particle.getY();
		
		double direction = directions.get(particle);
		
		float ax = acceleration * (float) Math.cos(direction);
		float ay = acceleration * (float) Math.sin(direction);
		
		particle.setPosition(x + ax, y + ay);
	}
}
