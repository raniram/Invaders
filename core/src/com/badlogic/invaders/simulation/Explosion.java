
package com.badlogic.invaders.simulation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class Explosion extends ModelInstance {
	public static final float EXPLOSION_LIVE_TIME = 1;
	public float aliveTime = 0;

	public Explosion (Model model, Vector3 position) {
		super(model, position);
	}

	public void update (float delta) {
		aliveTime += delta;
	}

	@Override
	public void getRenderables (Array<Renderable> renderables, Pool<Renderable> pool) {
		super.getRenderables(renderables, pool);
		Renderable r = renderables.get(renderables.size - 1);
		r.meshPart.offset = 6 * (int)(15 * aliveTime / EXPLOSION_LIVE_TIME);
		r.meshPart.size = 6;
	}
}
