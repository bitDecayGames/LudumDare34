package ludum.dare.actors.state;

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

        if (!physicsComponent.getBody().grounded) {
            if (inputComponent.isPressed(InputAction.UP)) switchToAnimation("punch/jumping/up");
            else if (inputComponent.isPressed(InputAction.DOWN)) switchToAnimation("punch/jumping/down");
            else switchToAnimation("punch/jumping/front");
        } else {
            if (inputComponent.isPressed(InputAction.UP)) switchToAnimation("punch/up");
            else switchToAnimation("punch/front");
        }

        addProjectile();
    }

    private void switchToAnimation(String animationName) {
        animationComponent.animator.switchToAnimation(animationName);
        IFrameByFrameAnimation anim = animationComponent.animator.getAnimationByName(animationName);
        if (anim != null && anim instanceof Animation) {
            punchAnimation = (Animation) anim;
            punchAnimation.listen(this);
        }
    }

    private void addProjectile() {
        Projectile projectile = new Projectile(positionComponent, levelComponent);
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
