/**
 * Copyright (c) 2015, Mine Fortress.
 */
package gliby.minecraft.physics.common.physics.engine;

import com.bulletphysicsx.linearmath.Transform;

/**
 *
 */
public interface ICollisionShapeChildren {

	public Transform getTransform();

	public ICollisionShape getCollisionShape();
	
}
