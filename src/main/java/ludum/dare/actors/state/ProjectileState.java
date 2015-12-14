package ludum.dare.actors.state;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.jump.Facing;
import ludum.dare.actors.projectile.Projectile;
import ludum.dare.components.LevelInteractionComponent;
import ludum.dare.control.InputAction;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;
import ludum.dare.util.SoundLibrary;

import java.util.Set;

public class ProjectileState extends AbstractState {

    LevelInteractionComponent levelComponent;

    public ProjectileState(Set<IComponent> components) {
        super(components);

        components.forEach(comp -> {
            if (comp instanceof LevelInteractionComponent) levelComponent = (LevelInteractionComponent) comp;
        });

        if (levelComponent == null || levelComponent.getObjects() == null || levelComponent.getWorld() == null) {
            throw new RuntimeException(LevelInteractionComponent.class + " with valid data expected");
        }
    }

    public Boolean shouldRun(IState currentState) {
        if (inputComponent.isJustPressed(InputAction.PROJECTILE)) {
            if (!(currentState instanceof ProjectileState)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void enter() {
        super.enter();
        int randomizer = (int) (Math.random() * 4) + 1;
        SoundLibrary.GetSound("Punch" + randomizer).play();
        Vector2 direction = new Vector2();
        if (!physicsComponent.getBody().grounded) {
            if (inputComponent.isPressed(InputAction.UP)) {
                direction.y = 1;
            } else if (inputComponent.isPressed(InputAction.DOWN)) {
                direction.y = -1;
            } else {
                direction.x = 1;
            }
        } else {
            if (inputComponent.isPressed(InputAction.UP)) {
                direction.y = 1;
            } else {
                direction.x = 1;
            }
        }

        Facing facing = physicsComponent.getBody().facing;
        switch (facing) {
            case LEFT:
                direction.x *= -1;
                break;
            case RIGHT:
                // Do nothing.
                break;
            default:
                throw new Error("Invalid facing set");
        }

        addProjectile(direction);
    }

    private void addProjectile(Vector2 direction) {
        Projectile projectile = new Projectile(positionComponent, direction, levelComponent, physicsComponent);
        levelComponent.addToLevel(projectile, projectile.getPhysics());
    }

    @Override
    public void exit() {
        super.exit();
    }

    @Override
    public IState update(float delta) {
        return getJumpState();
    }
}
