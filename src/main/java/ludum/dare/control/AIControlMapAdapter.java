package ludum.dare.control;

import com.bitdecay.jump.control.ControlMap;
import com.bitdecay.jump.control.PlayerAction;

// TODO Mike implement for AI
public class AIControlMapAdapter implements ControlMap {

    @Override
    public boolean isJustPressed(PlayerAction action) {
        return false;
    }

    @Override
    public boolean isPressed(PlayerAction action) {
        return false;
    }
}
