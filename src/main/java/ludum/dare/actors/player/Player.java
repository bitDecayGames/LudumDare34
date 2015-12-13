package ludum.dare.actors.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.bitdecay.jump.BodyType;
import com.bitdecay.jump.JumperBody;
import com.bitdecay.jump.collision.BitWorld;
import com.bitdecay.jump.control.ControlMap;
import com.bitdecay.jump.control.PlayerInputController;
import com.bitdecay.jump.geom.BitRectangle;
import com.bitdecay.jump.properties.JumperProperties;
import com.bitdecay.jump.render.JumperRenderStateWatcher;
import com.bytebreakstudios.animagic.animation.Animation;
import com.bytebreakstudios.animagic.animation.Animator;
import com.bytebreakstudios.animagic.animation.FrameRate;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.RacerGame;
import ludum.dare.actors.StateMachine;
import ludum.dare.actors.state.PunchState;
import ludum.dare.actors.state.StandState;
import ludum.dare.components.*;
import ludum.dare.components.upgradeComponents.*;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

public class Player extends StateMachine {
    private final SizeComponent size;
    private final PositionComponent pos;
    private final PhysicsComponent phys;
    private final HealthComponent health;
    private final AnimationComponent anim;
    private final PlayerCurrencyComponent wallet;
    private final AttackComponent attack;

    public Player() {
        size = new SizeComponent(100, 100);
        pos = new PositionComponent(0, 0);
        health = new HealthComponent(10, 10);
        anim = new AnimationComponent("player", pos, 1f, new Vector2(8, 0));
        wallet = new PlayerCurrencyComponent();
        setupAnimation(anim.animator);

        attack = new AttackComponent(10);

        phys = createBody();
        append(size).append(pos).append(phys).append(health).append(anim);
    }

    private PhysicsComponent createBody() {
        JumperBody body = new JumperBody();
        body.jumperProps = new JumperProperties();
        body.renderStateWatcher = new JumperRenderStateWatcher();
        body.bodyType = BodyType.DYNAMIC;
        body.aabb.set(new BitRectangle(0, 0, 16, 32));

        setupAnimation(anim.animator);
        return new PhysicsComponent(body, pos, size);
    }

    private void setupAnimation(Animator a) {
        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/player.atlas", AnimagicTextureAtlas.class);

        a.addAnimation(new Animation("run", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("run").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("jump", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("jump").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("apex", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("apex").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("fall", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("fall").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("knockback", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("knockback").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/front", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.05f), atlas.findRegions("punch/front").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/jumping/down", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.05f), atlas.findRegions("punch/jumping/down").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/jumping/front", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.05f), atlas.findRegions("punch/jumping/front").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/jumping/up", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("punch/jumping/up").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/up", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.05f), atlas.findRegions("punch/up").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("stand", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.2f), atlas.findRegions("stand").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("wall", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("wall").toArray(AnimagicTextureRegion.class)));

        a.switchToAnimation("stand");
    }

    @Override
    public void update(float delta) {
        // Reset for now
        // TODO do this somewhere else?
        if (pos.y < -1000) {
            setPosition(0, 0);
        }

        checkForStateSwitch();

        super.update(delta);
    }

    private void checkForStateSwitch() {
        IState newState = null;
        PunchState punch = new PunchState(components);
        if (punch.shouldRun(activeState)) {
            newState = punch;
        }
        if (newState != null) {
            setActiveState(newState);
        }
    }

    public void setPosition(float x, float y) {
        phys.getBody().velocity.set(0, 0);
        phys.getBody().aabb.xy.set(x, y);
    }

    public InputComponent getInputComponent() {
        IComponent input = getFirstComponent(InputComponent.class);
        if (input != null) {
            return (InputComponent) input;
        } else {
            return null;
        }
    }

    public Vector3 getPosition() {
        return new Vector3(pos.x, pos.y, 0);
    }

    public void addToWorld(BitWorld world) {
        world.addBody(phys.getBody());
    }

    public void activateControls() {
        try {
            ControlMap controls = (ControlMap) getFirstComponent(InputComponent.class);
            phys.getBody().controller = new PlayerInputController(controls);
            setActiveState(new StandState(components));
        } catch (Error e) {
            throw new Error("Could not activate player controls");
        }

    }

    public void addUpgrade(Class clazz) {
        if (clazz.equals(DoubleJumpComponent.class)) {
            append(new DoubleJumpComponent(phys));
        } else if (clazz.equals(FloatUpgradeComponent.class)) {
            append(new FloatUpgradeComponent(phys));
        } else if (clazz.equals(MetalComponent.class)) {
            append(new MetalComponent(phys, health, attack));
        } else if (clazz.equals(MysteryBagComponent.class)) {
            append(new MysteryBagComponent());
        } else if (clazz.equals(SpeedComponent.class)) {
            append(new SpeedComponent(phys));
        } else if (clazz.equals(WallJumpComponent.class)) {
            append(new WallJumpComponent(phys));
        } else {
            throw new RuntimeException("Could not instantiate " + clazz);
        }
    }
}
