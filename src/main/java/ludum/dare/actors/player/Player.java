package ludum.dare.actors.player;

import com.bitdecay.jump.JumperBody;
import com.bitdecay.jump.collision.BitWorld;
import com.bytebreakstudios.animagic.animation.Animation;
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
        size = new SizeComponent(120, 120);
        pos = new PositionComponent(0, 0);
        phys = new PhysicsComponent(new JumperBody(), pos, size);
        health = new HealthComponent(10, 10);
        anim = new AnimationComponent("player", pos, size);

        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/player.atlas", AnimagicTextureAtlas.class);
        anim.animator.addAnimation(new Animation("standing", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(.1f), atlas.findRegions("stand").toArray(AnimagicTextureRegion.class)));
        anim.animator.switchToAnimation("standing");

        TextureRegionComponent textComp = new TextureRegionComponent(atlas.findRegion("standing/1"), pos, size);

        this.append(size).append(pos).append(phys).append(health).append(anim).append(textComp);

        this.activeState = new StandState(this.components);
    }

    public void setPosition(float x, float y) {
        phys.getBody().velocity.set(0, 0);
        phys.getBody().aabb.xy.set(x, y);
    }

    public void addToWorld(BitWorld world) {
        world.addBody(phys.getBody());
    }
}
