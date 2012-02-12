package de.pocmo.particle.system;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.particle.ParticleSystem;
import org.anddev.andengine.entity.particle.emitter.IParticleEmitter;
import org.anddev.andengine.entity.particle.emitter.PointParticleEmitter;
import org.anddev.andengine.entity.particle.initializer.AlphaInitializer;
import org.anddev.andengine.entity.particle.initializer.ColorInitializer;
import org.anddev.andengine.entity.particle.initializer.GravityInitializer;
import org.anddev.andengine.entity.particle.initializer.RotationInitializer;
import org.anddev.andengine.entity.particle.initializer.ScaleInitializer;
import org.anddev.andengine.entity.particle.initializer.VelocityInitializer;
import org.anddev.andengine.entity.particle.modifier.AlphaModifier;
import org.anddev.andengine.entity.particle.modifier.ExpireModifier;
import org.anddev.andengine.entity.particle.modifier.ScaleModifier;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.content.Context;
import de.pocmo.particle.ParticleSystemFactory;

public class FountainParticleSystem implements ParticleSystemFactory
{
    private static final float RATE_MIN    = 10;
    private static final float RATE_MAX	   = 15;
    private static final int PARTICLES_MAX = 100;
    
    private BitmapTextureAtlas mBitmapTextureAtlas;
    private TextureRegion mParticleTextureRegion;

	@Override
	public void load(Context context, Engine engine)
	{
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("particle/");
    	
        mBitmapTextureAtlas = new BitmapTextureAtlas(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mParticleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, context, "water2.png", 0, 0);

        engine.getTextureManager().loadTexture(this.mBitmapTextureAtlas);
	}

	@Override
	public ParticleSystem build(Engine engine)
	{
		IParticleEmitter emitter = new PointParticleEmitter(
			engine.getCamera().getWidth() / 2,
			engine.getCamera().getHeight() - 10
		);
		
		final ParticleSystem particleSystem = new ParticleSystem(
    		emitter,
    		RATE_MIN,
    		RATE_MAX,
    		PARTICLES_MAX,
    		this.mParticleTextureRegion
		);
		
		particleSystem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
		
        particleSystem.addParticleInitializer(new VelocityInitializer(-5, 5, -150, -150));
        particleSystem.addParticleInitializer(new AlphaInitializer(0.8f));
        particleSystem.addParticleInitializer(new ScaleInitializer(2f));
        particleSystem.addParticleInitializer(new RotationInitializer(-20, 20));
        particleSystem.addParticleInitializer(new ColorInitializer(0f, 0.6f, 0.8f));
        
        GravityInitializer gravity = new GravityInitializer();
        gravity.setAccelerationY(60);
        gravity.setAccelerationX(-15, 15);
        particleSystem.addParticleInitializer(gravity);
        
        particleSystem.addParticleModifier(new ExpireModifier(7f));
        particleSystem.addParticleModifier(new ScaleModifier(2f, 1f, 0f, 6f));
        particleSystem.addParticleModifier(new AlphaModifier(0.8f, 1f, 6f, 7f));
        
		return particleSystem;
	}

	@Override
	public String getTitle()
	{
		return "FountainParticleSystem";
	}
}
