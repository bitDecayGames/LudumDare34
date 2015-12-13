package ludum.dare.components.PowerUpComponents;

import ludum.dare.components.TimedComponent;

/**
 * Created by jake on 12/12/2015.
 */
public class OpenAttackComponent extends TimedComponent{

    public OpenAttackComponent(){
        super(5);
//      turn on flag to allow open combat during race, dunno how do
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void remove(){
//      turn off flag to allow open combat during race, dunno how do
    }
}
