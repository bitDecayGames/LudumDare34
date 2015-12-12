package ludum.dare.actors.player;

import com.bitdecay.jump.JumperBody;
import ludum.dare.actors.StateMachine;
import ludum.dare.components.*;

public class Player extends StateMachine {


    public Player() {
        SizeComponent size = new SizeComponent();
        PositionComponent pos = new PositionComponent();
        PhysicsComponent phys = new PhysicsComponent(new JumperBody(), pos, size);
        HealthComponent health = new HealthComponent(10, 10);
        AnimationComponent anim = new AnimationComponent("player", pos, size);
        InputComponent input = new InputComponent(null);

        this.append(phys).append(size).append(pos).append(health).append(anim).append(input);

        this.activeState = new StandState(this.components);
    }
}
