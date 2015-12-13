package ludum.dare.components;

import com.bitdecay.jump.control.PlayerAction;
import com.bitdecay.jump.gdx.input.GDXControls;
import ludum.dare.control.InputAction;

import java.util.HashSet;
import java.util.Set;

/**
 * Adapter for interfacing keyboard with JUMP
 */
public class KeyboardControlComponent extends InputComponent {

    GDXControls keyboard;

    Set<InputAction> previousPresses = new HashSet<>();

    public KeyboardControlComponent() {
        keyboard = new GDXControls();
    }

    @Override
    public void update(float delta) {
        previousPresses.clear();
        for (InputAction action : InputAction.values()) {
            if (isPressed(action)) previousPresses.add(action);
        }
    }

    @Override
    public boolean isJustPressed(InputAction action) {
        return isPressed(action) && !previousPresses.contains(action);
    }

    @Override
    public boolean isPressed(InputAction action) {
        return keyboard.isPressed(action.playerAction);
    }

    @Override
    public boolean isJustPressed(PlayerAction playerAction) {
        return keyboard.isJustPressed(playerAction);
    }

    @Override
    public boolean isPressed(PlayerAction playerAction) {
        return keyboard.isPressed(playerAction);
    }
}
