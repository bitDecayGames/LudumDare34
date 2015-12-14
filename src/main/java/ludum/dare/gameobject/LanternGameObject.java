package ludum.dare.gameobject;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.level.LevelObject;
import com.bytebreakstudios.animagic.animation.Animation;
import com.bytebreakstudios.animagic.animation.FrameRate;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.RacerGame;
import ludum.dare.components.AnimationComponent;
import ludum.dare.components.LightComponent;
import ludum.dare.components.PositionComponent;
import ludum.dare.components.SizeComponent;
import ludum.dare.util.LightUtil;

import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 12/14/2015.
 */
public class LanternGameObject extends BasePlacedObject {

    LightComponent light;

    @Override
    public List<BitBody> build(LevelObject levelObject) {
        size = new SizeComponent(1, 1);
        pos = new PositionComponent(levelObject.rect.xy.x, levelObject.rect.xy.y);

        anim = new AnimationComponent("lantern", pos, 1f, new Vector2(0, -5));
        setupAnimation();

        light = new LightComponent(pos, new Vector2(10, 40));

        append(size).append(pos).append(anim).append(light);

        return Collections.emptyList();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        switch ( anim.animator.getFrameIndex()) {
            case 0:
                light.setAttenuation(1f);
                light.setzAxis(.1f);
                break;
            case 1:
                light.setAttenuation(1.2f);
                light.setzAxis(.13f);
                break;
            case 2:
                light.setAttenuation(1.05f);
                light.setzAxis(.11f);
                break;
            default:
                light.setAttenuation(.8f);
                light.setzAxis(.07f);
        }
    }

    private void setupAnimation() {
        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/level.atlas", AnimagicTextureAtlas.class);

        anim.animator.addAnimation(new Animation("lantern", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.05f), atlas.findRegions("lantern").toArray(AnimagicTextureRegion.class)));
        anim.animator.switchToAnimation("lantern");
    }
}
