package ludum.dare.collection;

import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import ludum.dare.actors.GameObject;

import java.util.ArrayList;
import java.util.List;

public class GameObjects {
    List<GameObject> gameObjects;

    List<GameObject> pendingAdds;

    public GameObjects() {
        gameObjects = new ArrayList<>();
        pendingAdds = new ArrayList<>();
    }

    // Will be added at start of next update loop.
    public void add(GameObject obj) {
        pendingAdds.add(obj);
    }

    public void update(float delta) {
        gameObjects.addAll(pendingAdds);

        gameObjects.forEach(obj -> obj.update(delta));
    }

    public void draw(AnimagicSpriteBatch batch) {
        gameObjects.forEach(obj -> obj.draw(batch));
    }

    public void clear() {
        gameObjects.clear();
    }
}
