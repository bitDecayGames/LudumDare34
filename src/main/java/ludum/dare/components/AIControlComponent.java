package ludum.dare.components;

import com.bitdecay.jump.control.PlayerAction;
import ludum.dare.control.InputAction;

// TODO Mike implement for AI
public class AIControlComponent extends InputComponent {

    private final static float JUMP_CYCLE_TIME = 1;
    Boolean jump = false;

    TimedComponent jumpCycleTimer;

    public AIControlComponent() {
        super();

        jumpCycleTimer = new TimedComponent(JUMP_CYCLE_TIME);
    }

    @Override
    public boolean isPressed(PlayerAction action) {
        if (action == null) {
            return false;
        }

        if (action.equals(PlayerAction.RIGHT)) {
            return true;
        }

        if (action.equals(PlayerAction.JUMP)) {
            return jump;
        }

        return false;
    }

    @Override
    public boolean isJustPressed(PlayerAction action) {
        return isPressed(action);
    }

    @Override
    public boolean isPressed(InputAction action) {
        return isPressed(action.playerAction);
    }

    @Override
    public boolean isJustPressed(InputAction action) {
        return isJustPressed(action.playerAction);
    }

    @Override
    public void update(float delta) {
        jumpCycleTimer.update(delta);

        if (jumpCycleTimer.shouldRemove()) {
            jumpCycleTimer.reset();
            jump = !jump;
        }
    }
}
