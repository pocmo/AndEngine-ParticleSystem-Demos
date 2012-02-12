package de.pocmo.particle.system;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.particle.ParticleSystem;
import org.anddev.andengine.entity.particle.emitter.CircleParticleEmitter;
import org.anddev.andengine.entity.particle.emitter.IParticleEmitter;
import org.anddev.andengine.entity.particle.initializer.AlphaInitializer;
import org.anddev.andengine.entity.particle.initializer.ColorInitializer;
import org.anddev.andengine.entity.particle.initializer.GravityInitializer;
import org.anddev.andengine.entity.particle.initializer.VelocityInitializer;
import org.anddev.andengine.entity.particle.modifier.ExpireModifier;
import org.anddev.andengine.entity.particle.modifier.ScaleModifier;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.content.Context;
import de.pocmo.particle.ParticleSystemFactory;

public class WaterParticleSystem implements ParticleSystemFactory
{
    private static final float RATE_MIN    = 10;
    private static final float RATE_MAX	   = 20;
    private static final int PARTICLES_MAX = 100;
    
    private BitmapTextureAtlas mBitmapTextureAtlas;
    private TextureRegion mParticleTextureRegion;

	@Override
	public void load(Context context, Engine engine)
	{
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("particle/");
    	
        mBitmapTextureAtlas = new BitmapTextureAtlas(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mParticleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, context, "water.png", 0, 0);

        engine.getTextureManager().loadTexture(this.mBitmapTextureAtlas);
	}

	@Override
	public ParticleSystem build(Engine engine)
	{
		IParticleEmitter emitter = new CircleParticleEmitter(
			5,
			engine.getCamera().getHeight() / 2,
			10
		);
		
		final ParticleSystem particleSystem = new ParticleSystem(
    		emitter,
    		RATE_MIN,
    		RATE_MAX,
    		PARTICLES_MAX,
    		this.mParticleTextureRegion
		);
		
		particleSystem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
		
        particleSystem.addParticleInitializer(new VelocityInitializer(150, 150, -60, -50));
        particleSystem.addParticleInitializer(new AlphaInitializer(0.6f));
        particleSystem.addParticleInitializer(new ColorInitializer(0f, 0f, 0.8f));
        
        GravityInitializer gravity = new GravityInitializer();
        gravity.setAccelerationY(20);
        
        particleSystem.addParticleInitializer(gravity);
        
        particleSystem.addParticleModifier(new ExpireModifier(4f));
        particleSystem.addParticleModifier(new ScaleModifier(1.4f, 2f, 0.2f, 3f));
        //particleSystem.addParticleModifier(new AlphaModifier(1f, 0.9f, 0.2f, 3f));
        		
		return particleSystem;
	}

	@Override
	public String getTitle()
	{
		return "WaterParticleSystem";
	}
}
