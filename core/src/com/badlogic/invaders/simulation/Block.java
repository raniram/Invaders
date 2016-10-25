
package com.badlogic.invaders.simulation;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Block extends ModelInstance {
	public final static float BLOCK_RADIUS = 0.5f;

	public Block (Model model, float x, float y, float z) {
		super(model, x, y, z);
	}
}
