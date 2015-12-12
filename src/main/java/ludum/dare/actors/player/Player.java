package ludum.dare.actors.player;

import com.bitdecay.jump.JumperBody;
import ludum.dare.actors.StateMachine;
import ludum.dare.components.*;

public class Player extends StateMachine {


    public Player() {
        PhysicsComponent phys = new PhysicsComponent(new JumperBody());
        HealthComponent health = new HealthComponent(10, 10);
        AnimationComponent anim = new AnimationComponent("player", phys);
        InputComponent input = new InputComponent();

        this.append(phys).append(health).append(anim).append(input);

        this.activeState = new StandState(this.components);
    }
}
