package ludum.dare.components;

import com.bitdecay.jump.collision.BitWorld;
import ludum.dare.actors.GameObject;
import ludum.dare.collection.GameObjects;
import ludum.dare.interfaces.IComponent;

public class LevelInteractionComponent implements IComponent {
    BitWorld world;
    GameObjects objects;

    public LevelInteractionComponent(BitWorld world, GameObjects objects) {
        this.world = world;
        this.objects = objects;
    }

    public BitWorld getWorld() {
        return world;
    }

    public GameObjects getObjects() {
        return objects;
    }

    public void addToLevel(GameObject obj, PhysicsComponent phys) {
        getWorld().addBody(phys.getBody());
        getObjects().add(obj);
    }
}
