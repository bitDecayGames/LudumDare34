package ludum.dare.actors.player;

import com.bytebreakstudios.animagic.animation.Animation;
import com.bytebreakstudios.animagic.animation.AnimationListener;
import com.bytebreakstudios.animagic.animation.IFrameByFrameAnimation;
import ludum.dare.control.InputAction;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class PunchState extends IState implements AnimationListener {

    private Animation punchAnimation;
    private boolean done = false;

    public PunchState(Set<IComponent> components, IState returnState) {
        super(components, returnState);
    }

    public void enter() {
        if (returnState instanceof JumpState) {
            if (inputComponent.isPressed(InputAction.UP)) switchToAnimation("punch/jumping/up");
            if (inputComponent.isPressed(InputAction.DOWN)) switchToAnimation("punch/jumping/down");
            else switchToAnimation("punch/jumping/front");
        } else {
            if (inputComponent.isPressed(InputAction.UP)) switchToAnimation("punch/up");
            else switchToAnimation("punch/front");
        }
    }

    private void switchToAnimation(String animationName) {
        animationComponent.animator.switchToAnimation(animationName);
        IFrameByFrameAnimation anim = animationComponent.animator.getAnimationByName(animationName);
        if (anim != null && anim instanceof Animation) {
            punchAnimation = (Animation) anim;
            punchAnimation.listen(this);
        }
    }

    public void exit() {
        if (punchAnimation != null) punchAnimation.stopListening(this);
    }

    public IState update(float delta) {
        if (done) return returnState;
        return null;
    }

    @Override
    public void animationNotification(Animation animation, Animation.AnimationListenerState animationListenerState) {
        if (animationListenerState == Animation.AnimationListenerState.FINISHED) done = true;
    }
}
