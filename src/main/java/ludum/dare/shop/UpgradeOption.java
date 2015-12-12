package ludum.dare.shop;

import com.bytebreakstudios.animagic.animation.Animation;
import com.bytebreakstudios.animagic.animation.FrameRate;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.RacerGame;
import ludum.dare.interfaces.IComponent;

/**
 * Represents a single choice a player has for an upgrade
 * Created by Admin on 12/12/2015.
 */
public class UpgradeOption {
    IComponent component;
    Animation animation;
    String description; // Not sure if we need this, butt fucket.

    public UpgradeOption(IComponent component, String textureDir) {
        this.component = component;
        // need the atlas

        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/test.atlas", AnimagicTextureAtlas.class);
        this.animation = new Animation("item", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(.25f), new AnimagicTextureRegion[] {atlas.findRegion("doubleJump")});
    }

    public void update(float delta) {
        animation.update(delta);
        //maybe some text effects or some sheeeeeit.
    }
}
