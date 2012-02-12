package de.pocmo.particle.system;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.particle.ParticleSystem;
import org.anddev.andengine.entity.particle.emitter.CircleParticleEmitter;
import org.anddev.andengine.entity.particle.emitter.IParticleEmitter;
import org.anddev.andengine.entity.particle.initializer.AlphaInitializer;
import org.anddev.andengine.entity.particle.initializer.ColorInitializer;
import org.anddev.andengine.entity.particle.initializer.VelocityInitializer;
import org.anddev.andengine.entity.particle.modifier.AlphaModifier;
import org.anddev.andengine.entity.particle.modifier.ColorModifier;
import org.anddev.andengine.entity.particle.modifier.ExpireModifier;
import org.anddev.andengine.entity.particle.modifier.ScaleModifier;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.content.Context;
import de.pocmo.particle.ParticleSystemFactory;

public class FireParticleSystem implements ParticleSystemFactory
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
        mParticleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, context, "f4.png", 0, 0);

        engine.getTextureManager().loadTexture(this.mBitmapTextureAtlas);
	}
    
	@Override
	public ParticleSystem build(Engine engine)
	{
		IParticleEmitter emitter = new CircleParticleEmitter(engine.getCamera().getWidth() / 2, engine.getCamera().getHeight() - 60, 10);
		
        final ParticleSystem particleSystem = new ParticleSystem(
    		emitter,
    		RATE_MIN,
    		RATE_MAX,
    		PARTICLES_MAX,
    		this.mParticleTextureRegion
		);
        
        particleSystem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE);

        particleSystem.addParticleInitializer(new VelocityInitializer(-75, 75, -80, -60));
        particleSystem.addParticleInitializer(new ColorInitializer(1f, 1f, 0.8f));
        particleSystem.addParticleInitializer(new AlphaInitializer(0.7f));
        
        particleSystem.addParticleModifier(new ScaleModifier(2f, 1.7f, 0f, 2f));
        particleSystem.addParticleModifier(new AlphaModifier(0.7f, 0.2f, 1f, 2f));
        particleSystem.addParticleModifier(new ExpireModifier(2f));
        particleSystem.addParticleModifier(new ColorModifier(1f, 0.98f, 1f, 0.5f, 0.8f, 0.2f, 0f, 0.5f));
        
        return particleSystem;
	}

	@Override
	public String getTitle() {
		return "FireParticleSystem";
	}
    
    
}
