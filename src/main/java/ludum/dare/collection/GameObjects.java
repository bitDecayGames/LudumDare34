package ludum.dare.collection;

import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import ludum.dare.actors.GameObject;
import ludum.dare.interfaces.IRemoveable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameObjects {
    List<GameObject> gameObjects;

    List<GameObject> pendingAdds;
    List<GameObject> pendingRemoves;

    public GameObjects() {
        gameObjects = new ArrayList<>();
        pendingAdds = new ArrayList<>();
        pendingRemoves = new ArrayList<>();
    }

    public Iterator getIter() {
        return gameObjects.iterator();
    }

    // Will be added at start of next update loop.
    public void add(GameObject obj) {
        pendingAdds.add(obj);
    }

    public void update(float delta) {
        // Add pending objects.
        gameObjects.addAll(pendingAdds);
        pendingAdds.clear();

        gameObjects.forEach(obj -> {
            obj.update(delta);

            if (obj instanceof IRemoveable && ((IRemoveable) obj).shouldRemove()) {
                pendingRemoves.add(obj);
            }
        });

        // Remove any object that has flagged itself for removal during the loop.
        gameObjects.removeAll(pendingRemoves);
        // Make sure to let it know it was removed.
        pendingRemoves.forEach(obj -> ((IRemoveable) obj).remove());
        pendingRemoves.clear();
    }

    public void draw(AnimagicSpriteBatch batch) {
        gameObjects.forEach(obj -> obj.draw(batch));
    }

    public void clear() {
        gameObjects.clear();
    }
}
