package ludum.dare.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;

public class LightUtil {

    public static void addLocatedLight(AnimagicSpriteBatch batch, Vector2 location) {
        Vector3 lightPos = new Vector3(location, .1f);
        batch.setNextLight(lightPos.x, lightPos.y, 0.8f, 5f, Color.WHITE);
    }

    public static void addBasicLight(AnimagicSpriteBatch batch) {
        addLocatedLight(batch, new Vector2(0, 0));
    }
}
