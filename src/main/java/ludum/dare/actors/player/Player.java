package ludum.dare.actors.player;

import com.bitdecay.jump.JumperBody;
import ludum.dare.actors.StateMachine;
import ludum.dare.components.*;

public class Player extends StateMachine {


    public Player() {
        SizeComponent size = new SizeComponent(1, 2);
        PositionComponent pos = new PositionComponent(0, 0);
        PhysicsComponent phys = new PhysicsComponent(new JumperBody(), pos, size);
        HealthComponent health = new HealthComponent(10, 10);
        AnimationComponent anim = new AnimationComponent("player", pos, size);

        this.append(size).append(pos).append(phys).append(health).append(anim);

        this.activeState = new StandState(this.components);
    }
}
