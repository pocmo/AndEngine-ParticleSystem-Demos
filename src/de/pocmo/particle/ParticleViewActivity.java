package de.pocmo.particle;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.os.Bundle;

public class ParticleViewActivity extends BaseGameActivity
{
	private static final int CAMERA_WIDTH  = 480;
    private static final int CAMERA_HEIGHT = 320;
    
	private Camera mCamera;
	private ParticleSystemFactory factory;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		factory = getFactoryByIndex(getIntent().getExtras().getInt("index"));
	}

	@Override
	public Engine onLoadEngine()
	{
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		
		return new Engine(
			new EngineOptions(
				true,
				ScreenOrientation.LANDSCAPE,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				mCamera
			)
		);
	}

	@Override
	public void onLoadResources()
	{
		factory.load(this, mEngine);
	}

	@Override
	public Scene onLoadScene()
	{
		this.mEngine.registerUpdateHandler(new FPSLogger());
		
		final Scene scene = new Scene();
        scene.setBackground(new ColorBackground(0f, 0f, 0f));
        
        scene.attachChild(factory.build(mEngine));

        return scene;
	}
	
	public ParticleSystemFactory getFactoryByIndex(int index)
	{
		return FightParticle.PARTICLE_SYSTEMS[index];
	}
	
	@Override
	public void onLoadComplete()
	{
	}
}
