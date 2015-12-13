package ludum.dare.components;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.bitdecay.jump.control.PlayerAction;
import ludum.dare.control.InputAction;
import ludum.dare.control.Xbox360Pad;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Adapter for interfacing Xbox 360 controllers with Jump.
 */
public class GamepadControlComponent extends InputComponent {

    final static float STICK_TOLERANCE = 0.5f;

    Controller controller;

    Set<InputAction> previousPresses = new HashSet<>();

    static Map<InputAction, Integer> actionsToXboxButtons = new HashMap<>();

    static {
        actionsToXboxButtons.put(InputAction.JUMP, Xbox360Pad.A.val);
        actionsToXboxButtons.put(InputAction.LEFT, Xbox360Pad.LS_LEFT.val);
        actionsToXboxButtons.put(InputAction.RIGHT, Xbox360Pad.LS_RIGHT.val);
        actionsToXboxButtons.put(InputAction.UP, Xbox360Pad.LS_UP.val);
        actionsToXboxButtons.put(InputAction.DOWN, Xbox360Pad.LS_DOWN.val);
        actionsToXboxButtons.put(InputAction.PUNCH, Xbox360Pad.X.val);
        actionsToXboxButtons.put(InputAction.PROJECTILE, Xbox360Pad.Y.val);
    }

    public GamepadControlComponent(int index) {
        try {
            controller = Controllers.getControllers().get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new Error("Xbox controller at " + index + " not found, are you sure it's connected?");
        }
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
        int button;
        if (actionsToXboxButtons.containsKey(action)) button = actionsToXboxButtons.get(action);
        else return false;

        switch (action) {
            case DOWN:
                return controller.getAxis(button) > STICK_TOLERANCE;
            case UP:
                return controller.getAxis(button) < -STICK_TOLERANCE;
            case RIGHT:
                return controller.getAxis(button) > STICK_TOLERANCE;
            case LEFT:
                return controller.getAxis(button) < -STICK_TOLERANCE;
            default:
                return controller.getButton(button);
        }
    }

    @Override
    public boolean isJustPressed(PlayerAction playerAction) {
        InputAction action = InputAction.forPlayerAction(playerAction);
        return action != null && isJustPressed(action);
    }

    @Override
    public boolean isPressed(PlayerAction playerAction) {
        InputAction action = InputAction.forPlayerAction(playerAction);
        return action != null && isPressed(action);
    }
}
