package ludum.dare.actors.player;

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
    public Player() {
        SizeComponent size = new SizeComponent(100, 100);
        PositionComponent pos = new PositionComponent(0, 0);
        //PhysicsComponent phys = new PhysicsComponent(new JumperBody(), pos, size);
        HealthComponent health = new HealthComponent(10, 10);
        AnimationComponent anim = new AnimationComponent("player", pos, size);
        setupAnimation(anim.animator);
//        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/player.atlas", AnimagicTextureAtlas.class);
//        TextureRegionComponent img = new TextureRegionComponent(atlas.findRegion("run/1"), pos, size);
        InputComponent input = new InputComponent(new GDXControls());

        this.append(size)
                .append(pos)
                        //.append(phys)
                .append(health)
                .append(anim)
//                .append(img)
                .append(input);

        this.activeState = new StandState(this.components);
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
