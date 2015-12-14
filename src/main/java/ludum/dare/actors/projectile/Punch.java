package ludum.dare.actors.projectile;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.Facing;
import com.bitdecay.jump.collision.ContactListener;
import com.bytebreakstudios.animagic.animation.Animation;
import com.bytebreakstudios.animagic.animation.Animator;
import com.bytebreakstudios.animagic.animation.FrameRate;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.RacerGame;
import ludum.dare.actors.player.Player;
import ludum.dare.components.*;
import ludum.dare.interfaces.IUpdate;

public class Punch extends PunchProjectile implements IUpdate{
    private PositionComponent source;
    private FacePunchingComponent facePunch;

    public Punch(PositionComponent source, Vector2 direction, LevelInteractionComponent levelComp, PhysicsComponent sourcePhysicsComponent, FacePunchingComponent facePunch) {
        super(source, direction, levelComp, sourcePhysicsComponent, .12f, facePunch);
        this.source = source;
        this.facePunch = facePunch;
    }

    @Override
    protected PhysicsComponent createBody(Vector2 direction) {
        PROJECTILE_SPEED = 0;

        return super.createBody(direction);
    }

    // TODO Mike Logan you may need two animation components on PunchState to handle this?
    @Override
    protected void setupAnimation(Animator a, FacePunchingComponent facePunch) {
        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/player0.atlas", AnimagicTextureAtlas.class);
            if(facePunch.FacePunchingUp){
                a.addAnimation(new Animation("up", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("waves/up").toArray(AnimagicTextureRegion.class)));
                a.switchToAnimation("up");
            }else if (facePunch.FacePunchingDown){
                a.addAnimation(new Animation("jumpingDown", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("waves/jumpingDown").toArray(AnimagicTextureRegion.class)));
                a.switchToAnimation("jumpingDown");
            }else if (facePunch.FacePunchingJupingUp){
                a.addAnimation(new Animation("jumpingUp", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("waves/jumpingUp").toArray(AnimagicTextureRegion.class)));
                a.switchToAnimation("jumpingUp");
            }else {
            a.addAnimation(new Animation("forward", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("waves/forward").toArray(AnimagicTextureRegion.class)));
            a.switchToAnimation("forward");
            if (sourcePhysicsComponent.getBody().facing.equals(Facing.LEFT)) {
                anim.setFlipVerticalAxis(true);
            }
        }
    }


    @Override
    public void update(float delta) {
        super.update(delta);
        if (facePunch.FacePunchingJupingUp) {
            pos.x = source.x - 11;
            pos.y = source.y + 28;
        } else if (facePunch.FacePunchingDown){
            pos.x = source.x - 7;
            pos.y = source.y - 15;
        } else {
            if(sourcePhysicsComponent.getBody().facing.equals(Facing.RIGHT)){
                if (facePunch.FacePunchingUp) {
                    pos.x = source.x + 6;
                    pos.y = source.y + 20;
                } else {
                    pos.x = source.x + 15;
                    pos.y = source.y;
                }
            } else {
                if (facePunch.FacePunchingUp) {
                    pos.x = source.x - 22;
                    pos.y = source.y + 20;
                } else {
                    pos.x = source.x - 15;
                    pos.y = source.y;
                }
            }
        }
    }
}
