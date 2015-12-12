package ludum.dare.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Array;

public class InputUtil {
    // Check for global keyboard or gamepad input. Use only for menus.
    public static Boolean checkInputs(int keyBoardKey, Xbox360Pad value) {
        return  Gdx.input.isKeyPressed(keyBoardKey) || checkAllXboxInputs(value);
    }

    private static Boolean checkAllXboxInputs(Xbox360Pad value) {
        Array<Controller> cList = Controllers.getControllers();
        for (int i = 0; i < cList.size; i++) {
            // If one is true, return;
            if (cList.get(i).getButton(value.val)) {
                return true;
            }
        }

        return false;
    }
}
