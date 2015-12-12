package ludum.dare.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IDraw;
import ludum.dare.interfaces.IUpdate;

import java.util.HashSet;
import java.util.Set;

public class GameObject implements IUpdate, IDraw {
    protected final Set<IComponent> components = new HashSet<>();
    protected final Set<IUpdate> updateableComponents = new HashSet<>();
    protected final Set<IDraw> drawableComponents = new HashSet<>();

    public GameObject(){}
    public GameObject(IComponent... componenets){
        for(IComponent c : componenets){
            this.components.add(c);
        }
    }

    public GameObject append(IComponent component) {
        if (component == null) throw new RuntimeException("Cannot add a null component to a GameObject");
        components.add(component);
        if (component instanceof IUpdate) updateableComponents.add((IUpdate) component);
        if (component instanceof IDraw) drawableComponents.add((IDraw) component);
        return this;
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
