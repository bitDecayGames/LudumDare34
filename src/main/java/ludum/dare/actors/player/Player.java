package ludum.dare.actors.player;

import com.bitdecay.jump.JumperBody;
import com.bitdecay.jump.collision.BitWorld;
import com.bitdecay.jump.gdx.input.GDXControls;
import com.bytebreakstudios.animagic.animation.Animation;
import com.bytebreakstudios.animagic.animation.Animator;
import com.bytebreakstudios.animagic.animation.FrameRate;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.RacerGame;
import ludum.dare.actors.StateMachine;
import ludum.dare.components.*;

public class Player extends StateMachine {
    private final SizeComponent size;
    private final PositionComponent pos;
    private final PhysicsComponent phys;
    private final HealthComponent health;
    private final AnimationComponent anim;

    public Player() {
        size = new SizeComponent(100, 100);
        pos = new PositionComponent(0, 0);
        phys = new PhysicsComponent(new JumperBody(), pos, size);
        health = new HealthComponent(10, 10);
        anim = new AnimationComponent("player", pos, size);
        setupAnimation(anim.animator);

        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/player.atlas", AnimagicTextureAtlas.class);
        anim.animator.addAnimation(new Animation("standing", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(.1f), atlas.findRegions("stand").toArray(AnimagicTextureRegion.class)));
        anim.animator.switchToAnimation("standing");

//        TextureRegionComponent textComp = new TextureRegionComponent(atlas.findRegion("standing/1"), pos, size);

        this.append(size).append(pos).append(phys).append(health).append(anim);

        this.activeState = new StandState(this.components);
    }

    public void setPosition(float x, float y) {
        phys.getBody().velocity.set(0, 0);
        phys.getBody().aabb.xy.set(x, y);
    }

    public void addToWorld(BitWorld world) {
        world.addBody(phys.getBody());
    }

    private void setupAnimation(Animator a) {
        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/player.atlas", AnimagicTextureAtlas.class);

        a.addAnimation(new Animation("run", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("run").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("jump", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("jump").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("knockback", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("knockback").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/front", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("punch/front").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/jumping/down", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("punch/jumping/down").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/jumping/front", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("punch/jumping/front").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/jumping/up", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("punch/jumping/up").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("punch/up", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("punch/up").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("stand", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("stand").toArray(AnimagicTextureRegion.class)));
        a.addAnimation(new Animation("wall", Animation.AnimationPlayState.ONCE, FrameRate.perFrame(0.1f), atlas.findRegions("wall").toArray(AnimagicTextureRegion.class)));

        a.switchToAnimation("run");
    }
}
