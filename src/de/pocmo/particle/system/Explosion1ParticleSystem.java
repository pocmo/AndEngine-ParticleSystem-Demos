package de.pocmo.particle.system;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.particle.ParticleSystem;
import org.anddev.andengine.entity.particle.emitter.CircleParticleEmitter;
import org.anddev.andengine.entity.particle.emitter.IParticleEmitter;
import org.anddev.andengine.entity.particle.initializer.ScaleInitializer;
import org.anddev.andengine.entity.particle.modifier.ExpireModifier;
import org.anddev.andengine.entity.particle.modifier.AlphaModifier;
import org.anddev.andengine.entity.particle.modifier.ScaleModifier;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.content.Context;
import de.pocmo.particle.ParticleSystemFactory;
import de.pocmo.particle.modifier.RandomColorInitializer;
import de.pocmo.particle.modifier.RandomDirectionModifier;

public class Explosion1ParticleSystem implements ParticleSystemFactory
{
    private static final float RATE_MIN    = 200;
    private static final float RATE_MAX	   = 200;
    private static final int PARTICLES_MAX = 200;
    
    private BitmapTextureAtlas mBitmapTextureAtlas;
    private TextureRegion mParticleTextureRegion;

	@Override
	public void load(Context context, Engine engine)
	{
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("particle/");
    	
        mBitmapTextureAtlas = new BitmapTextureAtlas(16, 16, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mParticleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, context, "dot.png", 0, 0);

        engine.getTextureManager().loadTexture(this.mBitmapTextureAtlas);
	}

	@Override
	public ParticleSystem build(Engine engine)
	{
		IParticleEmitter emitter = new CircleParticleEmitter(
			engine.getCamera().getWidth() / 2,
			engine.getCamera().getHeight() / 2,
			5
		);
		
        final ParticleSystem particleSystem = new ParticleSystem(
    		emitter,
    		RATE_MIN,
    		RATE_MAX,
    		PARTICLES_MAX,
    		this.mParticleTextureRegion
		);
        
        particleSystem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
        
        particleSystem.addParticleInitializer(new RandomColorInitializer());
        particleSystem.addParticleInitializer(new ScaleInitializer(2f));
        
        particleSystem.addParticleModifier(new RandomDirectionModifier(10));
        particleSystem.addParticleModifier(new ExpireModifier(3f));
        particleSystem.addParticleModifier(new AlphaModifier(1f, 0.2f, 0.5f, 1.5f));
        particleSystem.addParticleModifier(new ScaleModifier(2f, 1f, 0f, 1.5f));
        
        //particleSystem.addParticleInitializer(new VelocityInitializer(-50, 50, -50, 50));

        return particleSystem;
	}

	@Override
	public String getTitle()
	{
		return "Explosion1ParticleSystem"; 
	}
	
}
