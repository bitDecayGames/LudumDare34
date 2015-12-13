package ludum.dare.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;

public class LightUtil {
    public static void addBasicLight(AnimagicSpriteBatch batch) {
        Vector3 lightPos = new Vector3(0, 0, 0);
        batch.setAmbientColor(Color.WHITE);
        batch.setAmbientIntensity(0.01f);
        batch.setNextLight(lightPos.x, lightPos.y, 0.1f, 0.9f, Color.WHITE);
    }
}
