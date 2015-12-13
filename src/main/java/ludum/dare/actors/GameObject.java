package ludum.dare.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.level.LevelObject;
import ludum.dare.components.upgradeComponents.IRemoveable;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IDraw;
import ludum.dare.interfaces.IUpdate;

import java.util.*;

public class GameObject implements IUpdate, IDraw {
    protected final Set<IComponent> components = new HashSet<>();
    protected final Set<IUpdate> updateableComponents = new HashSet<>();
    protected final Set<IDraw> drawableComponents = new HashSet<>();
    private Object inputComponent;
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
            } else if (clazz.isAssignableFrom(comp.getClass())) {
                returnList.add(comp);
            }
        });
        return returnList;
    }

    public boolean hasComponent(Class<? extends IComponent> clazz) {
        return getComponents(clazz).size() > 0;
    }

    protected IComponent getFirstComponent(Class<? extends IComponent> clazz) {
        List<IComponent> components = getComponents(clazz);
        return components.size() > 0 ? components.get(0) : null;
    }

    public List<BitBody> build(LevelObject levelObject) {
        return Collections.emptyList();
    }

    @Override
    public void update(float delta) {
        List<IComponent> pendingRemoves = new ArrayList<>();
        updateableComponents.forEach(c -> {
            c.update(delta);
            if (c instanceof IRemoveable) {
                if(((IRemoveable) c).shouldRemove()){
                    ((IRemoveable) c).remove();
                    pendingRemoves.add((IComponent) c);
                }
            }
        });
        components.removeAll(pendingRemoves);
        updateableComponents.removeAll(pendingRemoves);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        drawableComponents.forEach(c -> c.draw(spriteBatch));
    }
}
