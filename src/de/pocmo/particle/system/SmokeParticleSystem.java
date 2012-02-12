package de.pocmo.particle.system;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.particle.ParticleSystem;
import org.anddev.andengine.entity.particle.emitter.PointParticleEmitter;
import org.anddev.andengine.entity.particle.initializer.AccelerationInitializer;
import org.anddev.andengine.entity.particle.initializer.VelocityInitializer;
import org.anddev.andengine.entity.particle.modifier.AlphaModifier;
import org.anddev.andengine.entity.particle.modifier.ExpireModifier;
import org.anddev.andengine.entity.particle.modifier.ScaleModifier;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import de.pocmo.particle.ParticleSystemFactory;

import android.content.Context;

public class SmokeParticleSystem implements ParticleSystemFactory
{
    private static final float RATE_MIN    = 8;
    private static final float RATE_MAX	   = 12;
    private static final int PARTICLES_MAX = 70;
    
    private BitmapTextureAtlas mBitmapTextureAtlas;
    private TextureRegion mParticleTextureRegion;

    public String getTitle()
    {
    	return "SmokeParticleSystem";
    }
    
    public void load(Context context, Engine engine) 
    {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("particle/");
    	
        mBitmapTextureAtlas = new BitmapTextureAtlas(32, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mParticleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, context, "particle_fire.png", 0, 0);

        engine.getTextureManager().loadTexture(this.mBitmapTextureAtlas);
    }
    
    public ParticleSystem build(Engine engine)
    {
        final ParticleSystem particleSystem = new ParticleSystem(
    		new PointParticleEmitter(50, engine.getCamera().getHeight() / 2),
    		RATE_MIN,
    		RATE_MAX,
    		PARTICLES_MAX,
    		this.mParticleTextureRegion
		);
        
        particleSystem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
        
        particleSystem.addParticleInitializer(new VelocityInitializer(15, 40, -5, 5));
        particleSystem.addParticleInitializer(new AccelerationInitializer(10, 15, -1, 1));
        
        particleSystem.addParticleModifier(new ExpireModifier(5.5f));
        particleSystem.addParticleModifier(new ScaleModifier(1.0f, 3.0f, 0f, 5f));
        particleSystem.addParticleModifier(new AlphaModifier(1.0f, 0f, 0f, 5f));
        
        return particleSystem;
	}
}
