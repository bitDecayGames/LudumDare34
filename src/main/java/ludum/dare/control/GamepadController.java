package ludum.dare.control;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.bitdecay.jump.control.ControlMap;
import com.bitdecay.jump.control.PlayerAction;

import java.util.HashMap;
import java.util.Map;

public class GamepadController implements ControlMap {

    Controller controller;

    Map<PlayerAction, Boolean> previousPresses = new HashMap<>();

    static Map<PlayerAction, Integer> actionsToXboxButtons = new HashMap<>();
    static
    {
        actionsToXboxButtons.put(PlayerAction.JUMP, Xbox360Pad.A.val);
    }

    public GamepadController(int index) {
        try {
            controller = Controllers.getControllers().get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new Error("Xbox controller not found, are you sure it's connected?");
        }
    }

    public void update(float delta) {
        for (PlayerAction action : actionsToXboxButtons.keySet()) {
            previousPresses.put(action, controller.getButton(actionsToXboxButtons.get(action)));
        }
    }

    // Buttons
    public boolean isJustPressed(PlayerAction action) {
        if (actionsToXboxButtons.containsKey(action)) {
            int xboxButton = actionsToXboxButtons.get(action);
            return controller.getButton(xboxButton) && !previousPresses.get(action);
        }
        return false;
    }

    // Axises/Sticks
    public boolean isPressed(PlayerAction action) {
        switch (action) {
            case DOWN:
                return controller.getAxis(Xbox360Pad.LS_UP.val) > 0.2f;
            case UP:
                return controller.getAxis(Xbox360Pad.LS_DOWN.val) < -0.2f;
            case RIGHT:
                return controller.getAxis(Xbox360Pad.LS_LEFT.val) > 0.2f;
            case LEFT:
                return controller.getAxis(Xbox360Pad.LS_RIGHT.val) < -0.2f;
        }
        return false;
    }
}
