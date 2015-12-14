package ludum.dare.gameobject;

import ludum.dare.actors.GameObject;
import ludum.dare.components.AnimationComponent;
import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.PositionComponent;
import ludum.dare.components.SizeComponent;

/**
 * Created by Admin on 12/13/2015.
 */
public class BasePlacedObject extends GameObject {
    protected SizeComponent size;
    protected PositionComponent pos;
    protected PhysicsComponent phys;
    protected AnimationComponent anim;
}
