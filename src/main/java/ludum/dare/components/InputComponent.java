package ludum.dare.components;

import com.bitdecay.jump.control.ControlMap;
import ludum.dare.control.InputAction;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IUpdate;

/**
 * Adapter component around inputs from users or an AI.
 */
public abstract class InputComponent implements IComponent, IUpdate, ControlMap {
    public abstract boolean isPressed(InputAction action);

    public abstract boolean isJustPressed(InputAction action);
}

