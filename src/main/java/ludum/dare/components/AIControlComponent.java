package ludum.dare.components;

import com.bitdecay.jump.control.PlayerAction;
import ludum.dare.control.InputAction;

// TODO Mike implement for AI
public class AIControlComponent extends InputComponent {

    @Override
    public boolean isPressed(PlayerAction action) {
        return false;
    }

    @Override
    public boolean isJustPressed(PlayerAction action) {
        return false;
    }


    @Override
    public boolean isPressed(InputAction action) {
        return false;
    }

    @Override
    public boolean isJustPressed(InputAction action) {
        return false;
    }
}
