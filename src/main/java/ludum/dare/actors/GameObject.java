package ludum.dare.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IDraw;
import ludum.dare.interfaces.IUpdate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameObject implements IUpdate, IDraw {
    protected final Set<IComponent> components = new HashSet<>();
    protected final Set<IUpdate> updateableComponents = new HashSet<>();
    protected final Set<IDraw> drawableComponents = new HashSet<>();
    // If you add another set list, make sure to update the append/remove methods or JAKE WILL BEAT YOU!

    public GameObject(){}
    public GameObject(IComponent... componenets){
        for(IComponent c : componenets){
            this.append(c);
        }
    }

    public GameObject append(IComponent component) {
        if (component == null) throw new RuntimeException("Cannot add a null component to a GameObject");
        components.add(component);
        if (component instanceof IUpdate) updateableComponents.add((IUpdate) component);
        if (component instanceof IDraw) drawableComponents.add((IDraw) component);
        return this;
    }

    public GameObject remove(Class<? extends IComponent> clazz) {
        if (clazz == null) throw new RuntimeException("Cannot remove a null component type");

        List<IComponent> removeList = getComponents(clazz);

        components.removeAll(removeList);
        updateableComponents.removeAll(removeList);
        drawableComponents.removeAll(removeList);

        return this;
    }

    protected List<IComponent> getComponents(Class<? extends IComponent> clazz) {
        List<IComponent> returnList = new ArrayList<>();
        components.forEach(comp -> {
            if (comp.getClass().equals(clazz)) {
                returnList.add(comp);
            } else if (clazz.isInterface() && comp.getClass().isAssignableFrom(clazz)) {
                returnList.add(comp);
            }
        });
        return returnList;
    }

    @Override
    public void update(float delta) {
        updateableComponents.forEach(c -> c.update(delta));
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        drawableComponents.forEach(c -> c.draw(spriteBatch));
    }

}
