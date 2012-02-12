package de.pocmo.particle;

import de.pocmo.particle.system.Explosion1ParticleSystem;
import de.pocmo.particle.system.FireParticleSystem;
import de.pocmo.particle.system.FountainParticleSystem;
import de.pocmo.particle.system.SmokeParticleSystem;
import de.pocmo.particle.system.WaterParticleSystem;

/*
    public static final int PUNCH      = 1;
    public static final int RAM        = 2;
    public static final int NUDGE      = 3;
    public static final int BASH       = 4;
    public static final int FIRE_BOLT  = 5;
    public static final int WATER_BOLT = 6;
    public static final int FIRE_BURST = 7;
    public static final int WAVE       = 8;
 */

/*
    public static final int DODGE        = 1;
    public static final int DUCK         = 2;
    public static final int SIDESTEP     = 3;
    public static final int SHIELD       = 4;
    public static final int FIRE_SHIELD  = 5;
    public static final int WATER_SHIELD = 6;
 */

public class FightParticle
{
	public static final ParticleSystemFactory[] PARTICLE_SYSTEMS = {
		new SmokeParticleSystem(),
		new FireParticleSystem(),
		new WaterParticleSystem(),
		new FountainParticleSystem(),
		new Explosion1ParticleSystem()
	};
}
