package ludum.dare.gameobject;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.level.LevelObject;
import com.bytebreakstudios.animagic.animation.Animation;
import com.bytebreakstudios.animagic.animation.FrameRate;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.RacerGame;
import ludum.dare.actors.GameObject;
import ludum.dare.components.AnimationComponent;
import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.PositionComponent;
import ludum.dare.components.SizeComponent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 12/13/2015.
 */
public class FinishLineGameObject extends BasePlacedObject {

    @Override
    public List<BitBody> build(LevelObject levelObject) {
        size = new SizeComponent(0, 0);
        pos = new PositionComponent(0, 0);
        anim = new AnimationComponent("finish", pos, 1f, new Vector2(0, -8));
        setupAnimation();

        phys = new PhysicsComponent(levelObject.buildBody(), pos, size);
        append(size).append(pos).append(phys).append(anim);
        return Arrays.asList(phys.getBody());
    }

    private void setupAnimation() {
        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/level.atlas", AnimagicTextureAtlas.class);

        anim.animator.addAnimation(new Animation("ongoing", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("collect/finish").toArray(AnimagicTextureRegion.class)));
        anim.animator.addAnimation(new Animation("finished", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("collect/finish2").toArray(AnimagicTextureRegion.class)));
        anim.animator.switchToAnimation("ongoing");
    }
}