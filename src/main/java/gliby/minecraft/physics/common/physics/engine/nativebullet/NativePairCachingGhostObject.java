package gliby.minecraft.physics.common.physics.engine.nativebullet;

import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btPairCachingGhostObject;
import com.bulletphysicsx.linearmath.Transform;
import gliby.minecraft.physics.client.render.VecUtility;
import gliby.minecraft.physics.common.physics.PhysicsWorld;
import gliby.minecraft.physics.common.physics.engine.ICollisionShape;
import gliby.minecraft.physics.common.physics.engine.IGhostObject;
import net.minecraft.entity.Entity;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 *
 */
class NativePairCachingGhostObject implements IGhostObject {

    protected SoftReference<NativePhysicsWorld> physicsWorld;
    protected SoftReference<Entity> owner;
    private btPairCachingGhostObject ghostObject;

    NativePairCachingGhostObject(PhysicsWorld physicsWorld, btPairCachingGhostObject object) {
        this.physicsWorld = new SoftReference<NativePhysicsWorld>((NativePhysicsWorld) physicsWorld);
        this.ghostObject = object;
    }

    NativePairCachingGhostObject(PhysicsWorld physicsWorld, Entity owner, btPairCachingGhostObject object) {
        this(physicsWorld, object);
        this.owner = new SoftReference<Entity>(owner);
    }

    @Override
    public Object getGhostObject() {
        return ghostObject;
    }

    @Override
    public Object getCollisionObject() {
        return ghostObject;
    }

    @Override
    public void setWorldTransform(final Transform entityTransform) {
        ghostObject.setWorldTransform(VecUtility.toMatrix4(entityTransform));

    }

    @Override
    public void setCollisionShape(final ICollisionShape collisionShape) {
        ghostObject.setCollisionShape((btCollisionShape) collisionShape.getCollisionShape());
    }

    @Override
    public void setCollisionFlags(final int characterObject) {
        ghostObject.setCollisionFlags(characterObject);
    }

    @Override
    public void setInterpolationWorldTransform(final Transform entityTransform) {
        ghostObject.setInterpolationWorldTransform(VecUtility.toMatrix4(entityTransform));
    }

    @Override
    public boolean isValid() {
        return ghostObject != null && ghostObject != null && !ghostObject.isDisposed();
    }

    @Override
    public Entity getOwner() {
        return owner.get();
    }

    @Override
    public PhysicsWorld getPhysicsWorld() {
        return physicsWorld.get();
    }
}
