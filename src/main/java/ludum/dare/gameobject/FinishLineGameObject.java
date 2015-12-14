package ludum.dare.gameobject;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.collision.ContactListener;
import com.bitdecay.jump.level.LevelObject;
import com.bytebreakstudios.animagic.animation.Animation;
import com.bytebreakstudios.animagic.animation.FrameRate;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.RacerGame;
import ludum.dare.actors.GameObject;
import ludum.dare.actors.player.Player;
import ludum.dare.components.*;
import ludum.dare.util.LightUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 12/13/2015.
 */
public class FinishLineGameObject extends BasePlacedObject implements ContactListener {

    public boolean raceOver = false;

    LightComponent light;

    @Override
    public List<BitBody> build(LevelObject levelObject) {
        size = new SizeComponent(0, 0);
        pos = new PositionComponent(0, 0);
        anim = new AnimationComponent("finish", pos, 1f, new Vector2(0, -8));
        light = new LightComponent(pos);
        setupAnimation();

        phys = new PhysicsComponent(levelObject.buildBody(), pos, size);
        phys.getBody().addContactListener(this);
        append(size).append(pos).append(phys).append(anim).append(light);
        return Arrays.asList(phys.getBody());
    }

    private void setupAnimation() {
        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/level.atlas", AnimagicTextureAtlas.class);

        anim.animator.addAnimation(new Animation("ongoing", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("collect/finish/1").toArray(AnimagicTextureRegion.class)));
        anim.animator.addAnimation(new Animation("finished", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("collect/finish/2").toArray(AnimagicTextureRegion.class)));
        anim.animator.switchToAnimation("ongoing");
    }

    @Override
    public void contactStarted(BitBody bitBody) {
        if (!raceOver) {
            if (bitBody.userObject instanceof Player) {
                raceOver = true;
                remove(LightComponent.class);
                anim.animator.switchToAnimation("finished");
                ((Player) bitBody.userObject).achieveMoney(10);
                ((Player) bitBody.userObject).showWinner();
            }
        }
    }

    @Override
    public void contact(BitBody bitBody) {

    }

    @Override
    public void contactEnded(BitBody bitBody) {

    }

    @Override
    public void crushed() {

    }
}
