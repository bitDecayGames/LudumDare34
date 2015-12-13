package ludum.dare.components;

import com.bitdecay.jump.control.ControlMap;
import com.bitdecay.jump.control.PlayerAction;
import com.bitdecay.jump.gdx.input.GDXControls;
import ludum.dare.control.AIControlMapAdapter;
import ludum.dare.control.ControlType;
import ludum.dare.control.GamepadControlMapAdapter;
import ludum.dare.interfaces.IComponent;

/**
 * Adapter component around inputs from users or an AI.
 */
public class InputComponent implements IComponent, ControlMap {

    ControlType type;
    ControlMap controller;

    public InputComponent(ControlMap controller) {
        this.controller = controller;

        if (this.controller instanceof GamepadControlMapAdapter) {
            this.type = ControlType.XBOX_360;
        } else if (this.controller instanceof GDXControls) {
            this.type = ControlType.KEYBOARD;
        } else if (this.controller instanceof AIControlMapAdapter) {
            this.type = ControlType.AI;
        } else {
            throw new Error("Controller type not supported, please add support in InputComponent.");
        }
    }

    public ControlType getType() {
        return this.type;
    }

    public boolean isJustPressed(PlayerAction action) {
        return controller.isJustPressed(action);
    }

    public boolean isPressed(PlayerAction action) {
        return controller.isPressed(action);
    }
}
