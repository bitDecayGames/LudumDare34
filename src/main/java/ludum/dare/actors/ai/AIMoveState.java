package ludum.dare.actors.ai;

import com.badlogic.gdx.math.Vector2;
import ludum.dare.actors.player.Player;
import ludum.dare.components.AIControlComponent;
import ludum.dare.components.PositionComponent;
import ludum.dare.control.InputAction;
import ludum.dare.interfaces.IState;

import java.util.ArrayList;
import java.util.List;

public class AIMoveState implements IState {

    private Player me;
    private AIControlComponent input;


    private float timeBeforeAttack = 2;
    private float timeBeforeNewTarget = (float) Math.random();
    private float newTargetTimer = 0;
    private Vector2 target;

    private List<Vector2> last3Targets = new ArrayList<>();

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
        newTargetTimer += delta;
        if (newTargetTimer > timeBeforeNewTarget) {
            getNewTargetPosition(me.getPosition());
        }

        Vector2 pos = me.getPosition();

        if (Math.abs(target.x - pos.x) < 5) {
            // do nothing
        } else if (target.x > pos.x) {
            input.pressed(InputAction.RIGHT);
        } else if (target.x < pos.x) {
            input.pressed(InputAction.LEFT);
        }

        if (target.y > pos.y && !input.isJustPressed(InputAction.JUMP)) {
            input.justPressed(InputAction.JUMP);
        }

        if (target.dst(pos.x, pos.y) < 16) {
            getNewTargetPosition(pos);
        } else {
            System.out.println(target + " " + pos + " " + target.dst(pos.x, pos.y));
        }

        if (last3Targets.size() == 3) {
            Vector2 t1 = last3Targets.get(0);
            Vector2 t2 = last3Targets.get(1);
            Vector2 t3 = last3Targets.get(2);
            if (t1.dst(t2) < 5 && t2.dst(t3) < 5) {
                me.setPosition(target.x, pos.y + 100);
            }
        }


        return null;
    }

    public void getNewTargetPosition(Vector2 myPos) {
        newTargetTimer = 0;
        target = new Vector2(myPos.x, myPos.y).add(100, 0);
        last3Targets.add(target);
        if (last3Targets.size() > 3) last3Targets.remove(last3Targets.size() - 1);
    }
}
