package ludum.dare.components;

import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IUpdate;

public class PhysicsComponent implements IComponent, IUpdate {

    private final PositionComponent position;
    private final SizeComponent size;
    // TODO: need a physics body

    public PhysicsComponent(PositionComponent position, SizeComponent size){
        this.position = position;
        this.size = size;
    }

    @Override
    public void update(float delta) {

    }
}
