package de.pocmo.particle;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.particle.ParticleSystem;

import android.content.Context;

public interface ParticleSystemFactory
{
	public void load(Context context, Engine engine);
	
	public ParticleSystem build(Engine engine);
	
	public String getTitle();
}
