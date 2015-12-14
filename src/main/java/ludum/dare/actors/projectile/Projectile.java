package ludum.dare.actors.projectile;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.BodyType;
import com.bitdecay.jump.JumperBody;
import com.bitdecay.jump.collision.ContactListener;
import com.bitdecay.jump.geom.BitRectangle;
import com.bitdecay.jump.properties.JumperProperties;
import com.bytebreakstudios.animagic.animation.Animation;
import com.bytebreakstudios.animagic.animation.Animator;
import com.bytebreakstudios.animagic.animation.FrameRate;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.RacerGame;
import ludum.dare.actors.StateMachine;
import ludum.dare.components.*;
import ludum.dare.interfaces.IRemoveable;

public class Projectile extends StateMachine implements ContactListener, IRemoveable{
    private final SizeComponent size;
    private final PositionComponent pos;
    private final PhysicsComponent phys;
    private final AnimationComponent anim;
    private final AttackComponent attack;

    private Boolean shouldRemove = false;

    public Projectile(PositionComponent source) {
        super();

        size = new SizeComponent(100, 100);
        pos = new PositionComponent(source.x, source.y);
        anim = new AnimationComponent("projectiles", pos, 1f, new Vector2(8, 0));
        setupAnimation(anim.animator);

        attack = new AttackComponent(10);

        phys = createBody();
        append(size).append(pos).append(phys).append(anim);
    }

    private PhysicsComponent createBody() {
        JumperBody body = new JumperBody();
        body.jumperProps = new JumperProperties();
//        body.renderStateWatcher = new JumperRenderStateWatcher(); TODO do we need this?
        body.bodyType = BodyType.DYNAMIC;
        body.aabb.set(new BitRectangle(pos.x, pos.y, 16, 32));

        body.velocity.set(500, 0);
        body.userObject = this;
        body.props.gravitational = false;
        body.addContactListener(this);

        setupAnimation(anim.animator);
        return new PhysicsComponent(body, pos, size);
    }

    private void setupAnimation(Animator a) {
        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/level.atlas", AnimagicTextureAtlas.class);

        a.addAnimation(new Animation("fire", Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), atlas.findRegions("collect/coin").toArray(AnimagicTextureRegion.class)));

        a.switchToAnimation("fire");
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    public PhysicsComponent getPhysics() {
        return phys;
    }

    @Override
    public boolean shouldRemove() {
        return shouldRemove;
    }

    @Override
    public void remove() {
    }

    @Override
    public void contactStarted(BitBody bitBody) {
        // TODO Add more logic for damage here if we hit a player.
        shouldRemove = true;
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
