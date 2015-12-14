package ludum.dare.actors.state;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.jump.Facing;
import com.bytebreakstudios.animagic.animation.Animation;
import com.bytebreakstudios.animagic.animation.AnimationListener;
import com.bytebreakstudios.animagic.animation.IFrameByFrameAnimation;
import ludum.dare.actors.projectile.Projectile;
import ludum.dare.components.LevelInteractionComponent;
import ludum.dare.control.InputAction;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class PunchState extends AbstractState implements AnimationListener {
    private Animation punchAnimation;
    private boolean done = false;

    LevelInteractionComponent levelComponent;

    public PunchState(Set<IComponent> components) {
        super(components);

        components.forEach(comp -> {
            if (comp instanceof LevelInteractionComponent) levelComponent = (LevelInteractionComponent) comp;
        });

        if (levelComponent == null || levelComponent.getObjects() == null || levelComponent.getWorld() == null) {
            throw new RuntimeException(LevelInteractionComponent.class + " with valid data expected");
        }
    }

    public Boolean shouldRun(IState currentState) {
        if (inputComponent.isJustPressed(InputAction.PUNCH)) {
            if (!(currentState instanceof PunchState)) {
                return true;
            }
        }

        return false;
    }

    public void enter() {
        super.enter();

        Vector2 direction = new Vector2();
        if (!physicsComponent.getBody().grounded) {
            if (inputComponent.isPressed(InputAction.UP)) {
                switchToAnimation("punch/jumping/up");
                direction.y = 1;
            } else if (inputComponent.isPressed(InputAction.DOWN)) {
                switchToAnimation("punch/jumping/down");
                direction.y = -1;
            } else {
                switchToAnimation("punch/jumping/front");
                direction.x = 1;
            }
        } else {
            if (inputComponent.isPressed(InputAction.UP)) {
                switchToAnimation("punch/up");
                direction.y = 1;
            } else {
                switchToAnimation("punch/front");
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

    private void switchToAnimation(String animationName) {
        animationComponent.animator.switchToAnimation(animationName);
        IFrameByFrameAnimation anim = animationComponent.animator.getAnimationByName(animationName);
        if (anim != null && anim instanceof Animation) {
            punchAnimation = (Animation) anim;
            punchAnimation.listen(this);
        }
    }

    private void addProjectile(Vector2 direction) {
        Projectile projectile = new Projectile(positionComponent, direction, levelComponent);
        levelComponent.addToLevel(projectile, projectile.getPhysics());
    }

    public void exit() {
        super.exit();
        if (punchAnimation != null) punchAnimation.stopListening(this);
    }

    @Override
    public IState update(float delta) {
        return done ? getJumpState() : null;
    }

    @Override
    public void animationNotification(Animation animation, Animation.AnimationListenerState animationListenerState) {
        if (animationListenerState == Animation.AnimationListenerState.FINISHED) done = true;
    }
}
