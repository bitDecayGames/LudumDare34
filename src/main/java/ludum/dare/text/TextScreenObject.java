package ludum.dare.text;

import com.badlogic.gdx.graphics.Color;
import ludum.dare.actors.GameObject;
import ludum.dare.components.PositionComponent;
import ludum.dare.components.TextComponent;

public class TextScreenObject extends GameObject {

    TextComponent textComp;

    public TextScreenObject(PositionComponent positionComponent, String text, Color color) {
        textComp = (new TextComponent(positionComponent)).setText(text).setColor(color);
        append(textComp);
    }
}
