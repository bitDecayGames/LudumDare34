package ludum.dare.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.bitdecay.jump.gdx.input.GDXControls;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import com.bytebreakstudios.animagic.texture.data.AnimagicTextureData;
import ludum.dare.actors.GameObject;
import ludum.dare.components.InputComponent;
import ludum.dare.components.PositionComponent;
import ludum.dare.components.SizeComponent;
import ludum.dare.components.TextureRegionComponent;
import ludum.dare.interfaces.IComponent;

import java.util.List;

public class ControllerScreenObject extends GameObject {
    // Contoller inputs to select controller.
    int keyboardSelect;
    int keyboardDeselect;
    int xbox360ControllerIndex;
    Xbox360Pad xbox360Select = Xbox360Pad.A;
    Xbox360Pad xbox360Deselect = Xbox360Pad.B;

    // What to render when a particular controller is selected.
    TextureRegionComponent aiTexture;
    TextureRegionComponent keyboardTexture;
    TextureRegionComponent xbox360Texture;

    public ControllerScreenObject(int keyboardSelect, int keyboardDeselect, int xbox360ControllerIndex, PositionComponent position, SizeComponent size) {
        this.keyboardSelect = keyboardSelect;
        this.keyboardDeselect = keyboardDeselect;
        this.xbox360ControllerIndex = xbox360ControllerIndex;

        aiTexture = getTextureCompnent("bum.png", "bum_n.png", position, size);
        keyboardTexture = getTextureCompnent("bum_n.png", null, position, size);
        xbox360Texture = getTextureCompnent("title.png", null, position, size);

        append(position);
        append(size);

        // Default to AI selection.
        swapInput(new InputComponent(new AIControlMapAdapter()));
    }

    private TextureRegionComponent getTextureCompnent(String name, String normalName, PositionComponent p, SizeComponent s) {
        return new TextureRegionComponent(new AnimagicTextureRegion(new Texture(name), normalName != null ? new Texture(normalName) : null, new AnimagicTextureData((int) s.w, (int) s.h)), p, s);
    }

    private void updateControllerSelection() {
        InputComponent newInput = null;

        Controller xboxController = safeGetXboxController(xbox360ControllerIndex);
        // Reset to AI
        if (Gdx.input.isKeyPressed(keyboardDeselect) || safeGetXboxButton(xboxController, xbox360Deselect)) {
            newInput = new InputComponent(new AIControlMapAdapter());
        // Select Keyboard
        } else if (Gdx.input.isKeyPressed(keyboardSelect)) {
            newInput = new InputComponent(new GDXControls());
        // Select Xbox
        } else if (safeGetXboxButton(xboxController, xbox360Select)) {
            newInput = new InputComponent(new GamepadControlMapAdapter(xbox360ControllerIndex));
        }

        swapInput(newInput);
    }

    private Boolean safeGetXboxButton(Controller xboxController, Xbox360Pad button) {
        return xboxController != null ? xboxController.getButton(button.val) : false;
    }

    private Controller safeGetXboxController(int index) {
        Array<Controller> controllerList =  Controllers.getControllers();
        if (index < controllerList.size) {
            return controllerList.get(index);
        }

        return null;
    }

    private void swapInput(InputComponent input) {
        if (input == null) {
            return;
        }

        remove(InputComponent.class);
        remove(TextureRegionComponent.class);

        TextureRegionComponent texture;

        switch (input.getType()) {
            case AI:
                texture = aiTexture;
                break;
            case KEYBOARD:
                texture = keyboardTexture;
                break;
            case XBOX_360:
                texture = xbox360Texture;
                break;
            default:
                throw new Error("Input type not supported");
        }

        append(texture);
        append(input);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        updateControllerSelection();
    }

    public IComponent getInputComponent() {
        List<IComponent> inputComponents = getComponents(InputComponent.class);
        if (inputComponents.size() != 1) {
            throw new Error("Only adding one InputComponent is allowed");
        }

        return inputComponents.get(0);
    }
}
