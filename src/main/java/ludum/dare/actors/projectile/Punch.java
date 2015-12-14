package ludum.dare.actors.projectile;

import com.badlogic.gdx.math.Vector2;
import com.bytebreakstudios.animagic.animation.Animation;
import com.bytebreakstudios.animagic.animation.Animator;
import com.bytebreakstudios.animagic.animation.FrameRate;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.RacerGame;
import ludum.dare.components.LevelInteractionComponent;
import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.PositionComponent;

public class Punch extends Projectile {
    public Punch(PositionComponent source, Vector2 direction, LevelInteractionComponent levelComp, PhysicsComponent sourcePhysicsComponent) {
        super(source, direction, levelComp, sourcePhysicsComponent);
    }

    @Override
    protected PhysicsComponent createBody(Vector2 direction) {
        PROJECTILE_SPEED = 0;

        return super.createBody(direction);
    }

    // TODO Mike Logan you may need two animation components on PunchState to handle this?
    @Override
    protected void setupAnimation(Animator a) {
        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/player0.atlas", AnimagicTextureAtlas.class);

        a.addAnimation(new Animation("forward", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("waves/forward").toArray(AnimagicTextureRegion.class)));

        a.switchToAnimation("forward");
    }
}
