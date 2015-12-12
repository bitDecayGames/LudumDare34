package ludum.dare.actors.player;

import ludum.dare.actors.StateMachine;
import ludum.dare.components.*;

public class Player extends StateMachine {
    public Player() {
        PositionComponent pos = new PositionComponent(0, 0);
        SizeComponent size = new SizeComponent(1, 2);
        PhysicsComponent phys = new PhysicsComponent(pos, size);
        HealthComponent health = new HealthComponent(10, 10);
        AnimationComponent anim = new AnimationComponent("player", pos, size);
        InputComponent input = new InputComponent();

        this.append(pos).append(size).append(phys).append(health).append(anim).append(input);

        this.activeState = new StandState(this.components);
    }
}
