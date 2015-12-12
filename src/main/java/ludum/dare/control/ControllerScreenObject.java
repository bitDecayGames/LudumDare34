package ludum.dare.control;

import com.badlogic.gdx.graphics.Texture;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import com.bytebreakstudios.animagic.texture.data.AnimagicTextureData;
import ludum.dare.actors.GameObject;
import ludum.dare.components.InputComponent;
import ludum.dare.components.PositionComponent;
import ludum.dare.components.SizeComponent;
import ludum.dare.components.TextureRegionComponent;

public class ControllerScreenObject extends GameObject {
    // Contoller inputs to select controller.
    int keyboardSelect;
    int keyboardDeselect;
    int xbox360ControllerSlot;
    Xbox360Pad xbox360Select = Xbox360Pad.A;
    Xbox360Pad xbox360Deselect = Xbox360Pad.B;

    // What to render when a particular controller is selected.
    TextureRegionComponent aiTexture;
    TextureRegionComponent keyboardTexture;
    TextureRegionComponent xbox360Texture;

    InputComponent input;

    public ControllerScreenObject(int keyboardSelect, int keyboardDeselect, int xbox360ControllerSlot, PositionComponent position) {
        this.keyboardSelect = keyboardSelect;
        this.keyboardDeselect = keyboardDeselect;
        this.xbox360ControllerSlot = xbox360ControllerSlot;

        SizeComponent size = new SizeComponent(100, 100);
        aiTexture = getTextureCompnent("bum.png", "bum_n.png", position, size);
        keyboardTexture = getTextureCompnent("bum_n.png", null, position, size);
        xbox360Texture = getTextureCompnent("title.png", null, position, size);

        input = new InputComponent(new AIControlMapAdapter());

        // Default to AI selection.
        append(aiTexture);
        append(position);
        append(size);
    }

    private TextureRegionComponent getTextureCompnent(String name, String normalName, PositionComponent p, SizeComponent s) {
        return new TextureRegionComponent(new AnimagicTextureRegion(new Texture(name), normalName != null ? new Texture(normalName) : null, new AnimagicTextureData((int) s.w, (int) s.h)), p, s);
    }

    private void updateControllerSelection() {
        
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        updateControllerSelection();
    }
}
