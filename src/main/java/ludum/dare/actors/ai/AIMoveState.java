package ludum.dare.actors.ai;

import com.badlogic.gdx.math.Vector2;
import ludum.dare.actors.player.Player;
import ludum.dare.components.AIControlComponent;
import ludum.dare.components.PositionComponent;
import ludum.dare.control.InputAction;
import ludum.dare.interfaces.IState;

public class AIMoveState implements IState {

    private Player me;
    private AIControlComponent input;


    private float timeBeforeAttack = 2;
    private Vector2 target;

    public AIMoveState(Player me, AIControlComponent input, PositionComponent pos) {
        this.me = me;
        this.input = input;
    }

    @Override
    public void enter() {
        getNewTargetPosition(me.getPosition());
    }

    @Override
    public void exit() {

    }

    @Override
    public IState update(float delta) {
        timeBeforeAttack -= delta;
        if (timeBeforeAttack < 0) {
            // TODO: attack is available
        }

        Vector2 pos = me.getPosition();

        if (target.x > pos.x) {
            input.pressed(InputAction.RIGHT);
        } else if (target.x < pos.x) {
            input.pressed(InputAction.LEFT);
        }

        if (target.y > pos.y && !input.isJustPressed(InputAction.JUMP)) {
            input.justPressed(InputAction.JUMP);
        }

        if (target.dst(pos.x, pos.y) < 16) {
            getNewTargetPosition(pos);
        }


        return null;
    }

    public void getNewTargetPosition(Vector2 myPos) {

        target = new Vector2(myPos.x, myPos.y).add(20, 20);
    }
}
