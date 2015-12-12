package ludum.dare.control;

import ludum.dare.actors.GameObject;
import ludum.dare.components.AnimationComponent;
import ludum.dare.components.PositionComponent;
import ludum.dare.components.SizeComponent;

public class ControllerScreenObject extends GameObject {
    int keyboardSelect;
    int keyboardDeselect;
    int xbox360ControllerSlot;
    Xbox360Pad xbox360Select = Xbox360Pad.A;
    Xbox360Pad xbox360Deselect = Xbox360Pad.B;

    public ControllerScreenObject(int keyboardSelect, int keyboardDeselect, int xbox360ControllerSlot, PositionComponent position) {
        this.keyboardSelect = keyboardSelect;
        this.keyboardDeselect = keyboardDeselect;
        this.xbox360ControllerSlot = xbox360ControllerSlot;

        SizeComponent size = new SizeComponent(100, 100);
        AnimationComponent animation = new AnimationComponent("badlogic.jpg", position, size);

        append(position);
        append(size);
    }

    @Override
    public void update(float delta) {
        super.update(delta);


    }
}
