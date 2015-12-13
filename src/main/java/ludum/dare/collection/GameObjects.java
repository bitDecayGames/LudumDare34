package ludum.dare.collection;

import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import ludum.dare.actors.GameObject;

import java.util.ArrayList;
import java.util.List;

public class GameObjects {
    List<GameObject> gameObjects;

    public GameObjects() {
        gameObjects = new ArrayList<>();
    }

    public void add(GameObject obj) {
        gameObjects.add(obj);
    }

    public void update(float delta) {
        gameObjects.forEach(obj -> obj.update(delta));
    }

    public void draw(AnimagicSpriteBatch batch) {
        gameObjects.forEach(obj -> obj.draw(batch));
    }

    public void clear() {
        gameObjects.clear();
    }
}
