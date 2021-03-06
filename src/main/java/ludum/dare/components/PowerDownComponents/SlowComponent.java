package ludum.dare.components.PowerDownComponents;

import com.badlogic.gdx.math.Vector2;
import com.bytebreakstudios.animagic.animation.Animation;
import com.bytebreakstudios.animagic.animation.FrameRate;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.RacerGame;
import ludum.dare.components.AnimationComponent;
import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.PositionComponent;
import ludum.dare.components.TimedComponent;
import ludum.dare.interfaces.IDraw;

/**
 * Created by jake on 12/12/2015.
 */
public class SlowComponent extends TimedComponent implements IDraw {
    PhysicsComponent phys;

    AnimationComponent anim;

    int slowAmount = 200;

    public SlowComponent(PhysicsComponent phys, PositionComponent sourcePos){
        super(5);
        this.phys = phys;
        this.phys.getBody().props.maxVoluntarySpeed -= slowAmount;
        anim = new AnimationComponent("icon", sourcePos, 1, new Vector2(-20, 32));

        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/power.atlas", AnimagicTextureAtlas.class);

        anim.animator.addAnimation(new Animation("icon", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(.1f), atlas.findRegions("slow").toArray(AnimagicTextureRegion.class)));
        anim.animator.switchToAnimation("icon");
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        anim.update(delta);
    }

    @Override
    public void draw(AnimagicSpriteBatch spriteBatch) {
        anim.draw(spriteBatch);
    }

    @Override
    public void remove(){
        this.phys.getBody().props.maxVoluntarySpeed += slowAmount;
    }
}
