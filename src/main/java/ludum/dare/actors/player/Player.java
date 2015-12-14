package ludum.dare.actors.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.bitdecay.jump.BodyType;
import com.bitdecay.jump.JumperBody;
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
import ludum.dare.actors.items.PowerBlock;
import ludum.dare.actors.state.HurtState;
import ludum.dare.actors.state.ProjectileState;
import ludum.dare.actors.state.PunchState;
import ludum.dare.actors.state.StandState;
import ludum.dare.components.*;
import ludum.dare.components.PowerDownComponents.*;
import ludum.dare.components.PowerUpComponents.*;
import ludum.dare.components.upgradeComponents.*;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;
import ludum.dare.util.Players;

public class Player extends StateMachine {
    private final SizeComponent size;
    private final PositionComponent pos;
    private final PhysicsComponent phys;
    private final HealthComponent health;
    private final AnimationComponent anim;
    private final PlayerCurrencyComponent wallet;
    private final AttackComponent attack;
    private LevelInteractionComponent levelComponent;
    private final int playerNum;

    public Player(int playerNum) {
        this.playerNum = playerNum;
        size = new SizeComponent(100, 100);
        pos = new PositionComponent(0, 0);
        health = new HealthComponent(10, 10);
        anim = new AnimationComponent("player", pos, 1f, new Vector2(8, -5));
        wallet = new PlayerCurrencyComponent();
        setupAnimation(anim.animator);

        attack = new AttackComponent(10);

        phys = createBody();
        append(size).append(pos).append(phys).append(health).append(anim);
    }

    private PhysicsComponent createBody() {
        JumperBody body = new JumperBody();
        body.jumperProps = new JumperProperties();
        body.jumperProps.jumpCount = 1;
        body.renderStateWatcher = new JumperRenderStateWatcher();
        body.bodyType = BodyType.DYNAMIC;
        body.aabb.set(new BitRectangle(0, 0, 16, 32));
        body.userObject = this;

        setupAnimation(anim.animator);
        return new PhysicsComponent(body, pos, size);
    }

    private void setupAnimation(Animator a) {
        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/player" + getPlayerNum() + ".atlas", AnimagicTextureAtlas.class);

        a.addAnimation(new Animation("run", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("run").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("jump", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("jump").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("apex", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("apex").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("fall", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("fall").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("knockback", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("knockback").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/front", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.05f), atlas.findRegions("punch/front").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/jumping/down", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.05f), atlas.findRegions("punch/jumping/down").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/jumping/front", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.05f), atlas.findRegions("punch/jumping/front").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/jumping/up", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("punch/jumping/up").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/up", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.05f), atlas.findRegions("punch/up").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("stand", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.2f), atlas.findRegions("stand").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("wall", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("wall").toArray(AnimagicTextureRegion.class)));

        a.switchToAnimation("stand");
    }

    public int getPlayerNum() {
        return playerNum;
    }

    @Override
    public void update(float delta) {
        // Reset for now
        // TODO do this somewhere else?
//        if (pos.y < -1000) {
//            setPosition(0, 0);
//        }

        checkForStateSwitch();

        super.update(delta);
    }

    public void hit(AttackComponent attackComponent) {
        setActiveState(new HurtState(components, attackComponent));
    }

    private void checkForStateSwitch() {
        IState newState = null;
        PunchState punch = new PunchState(components);
        ProjectileState projectile = new ProjectileState(components);
        if (punch.shouldRun(activeState)) {
            newState = punch;
        } else if (projectile.shouldRun(activeState)) {
            newState = projectile;
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

    public void addToScreen(LevelInteractionComponent levelComp) {
        // Remove any existing level components.
        remove(LevelInteractionComponent.class);

        levelComponent = levelComp;
        append(levelComponent);

        levelComponent.addToLevel(this, phys);
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
            append(new MysteryBagComponent(this, phys, health, attack));
        } else if (clazz.equals(SpeedComponent.class)) {
            append(new SpeedComponent(phys));
        } else if (clazz.equals(WallJumpComponent.class)) {
            append(new WallJumpComponent(phys));
        } else if (clazz.equals(EmptyUpgradeComponent.class)) {
            append(new EmptyUpgradeComponent());
        } else if (clazz.equals(FireProjectileComponent.class)) {
            append(new FireProjectileComponent());
        } else if (clazz.equals(IceProjectileComponent.class)) {
            append(new IceProjectileComponent());
        } else if (clazz.equals(WebProjectileComponent.class)) {
            append(new WebProjectileComponent());
        } else if (clazz.equals(PoisonProjectileComponent.class)) {
            append(new PoisonProjectileComponent());
        } else {
            throw new RuntimeException("Could not instantiate " + clazz);
        }
    }

    public void achieveMoney(int amount) {
        this.wallet.getACoin(amount);
    }

    public void getPowerBlock(PowerBlock power){
        String myPower = power.randomPowerGenerator(getRank());
        if(myPower == "TEMP_SPEED"){
            append(new TempSpeedComponent(phys));
        }else if(myPower == "SLOW"){
            for(Player p: Players.list()){
                if(Players.list().indexOf(p) != Players.list().indexOf(this)){
                    p.takeAPowerDown("SLOW");
                }
            }
        }else if(myPower == "DOUBLE COINS"){
            append(new DoubleCoinsComponent());
        }else if(myPower == "LIGHTS_OFF"){
//        TODO: add some shit here that lets this happen
        }else if(myPower == "FORCE_HIGH_JUMP"){
            for(Player p: Players.list()){
                if(Players.list().indexOf(p) != Players.list().indexOf(this)){
                    p.takeAPowerDown("FORCE_HIGH_JUMP");
                }
            }
        }else if(myPower == "STEAL_COINS"){
            for(Player p: Players.list()){
                if(Players.list().indexOf(p) != Players.list().indexOf(this)){
                    p.takeAPowerDown("STEAL_COINS");
                }
            }
        }else if(myPower == "TEMP_FLY"){
            append(new TempFlyComponent(phys));
        }else if(myPower == "STUN"){
            for(Player p: Players.list()){
                if(Players.list().indexOf(p) != Players.list().indexOf(this)){
                    p.takeAPowerDown("STUN");
                }
            }
        }
    }

    public void takeAPowerDown(String powerDown){
        if(powerDown == "SLOW"){
            append(new SlowComponent(phys));
        }else if(powerDown == "FORCE_HIGH_JUMP"){
            append(new ForceHighJumpComponent(phys));
        }else if(powerDown == "STUN"){
            append(new StunComponent(phys));
        }
    }

    public int getRank(){
        return 0;
    }

    public void showWinner() {
        append(new LightComponent(pos));
    }

    public int moneyCount() {
        return this.wallet.currency;
    }
}
