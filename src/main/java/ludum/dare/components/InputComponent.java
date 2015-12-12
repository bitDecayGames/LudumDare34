package ludum.dare.components;

import com.bitdecay.jump.control.ControlMap;
import com.bitdecay.jump.control.PlayerAction;
import com.bitdecay.jump.gdx.input.GDXControls;
import ludum.dare.control.ControlType;
import ludum.dare.control.GamepadControlMapAdapter;
import ludum.dare.interfaces.IComponent;

/**
 * Adapter component around
 */
public abstract class InputComponent implements IComponent, ControlMap {

    ControlType type;
    ControlMap controller;

    public InputComponent(ControlType type, ControlMap controller) {
        this.controller = controller;

        if (this.controller instanceof GamepadControlMapAdapter) {
            this.type = ControlType.XBOX_360;
        } else if (this.controller instanceof GDXControls) {
            this.type = ControlType.KEYBOARD;
        } else if (this.controller instanceof Object) {
            // TODO Mike put your AI controller here.
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
