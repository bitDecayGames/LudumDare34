package ludum.dare.components.PowerUpComponents;

import ludum.dare.components.TimedComponent;

/**
 * Created by jake on 12/12/2015.
 */
public class InvincibleComponent extends TimedComponent{

    public InvincibleComponent(){
        super(5);
//        need a flag to prevent any bad things from happening to player, dunno how do
    }

    @Override
    public void update(float delta) {
        
    }

    @Override
    public void remove(){
//      turn flag off
    }
}
