package ludum.dare.components.PowerUpComponents;

import ludum.dare.components.TimedComponent;

/**
 * Created by jake on 12/12/2015.
 */
public class DoubleCoinsComponent extends TimedComponent{

    public DoubleCoinsComponent(){
        super(5);
//      turn on flag to make all coins collect double, essentially call getACoin in PlayerCurrencyComponent twice per coin collected.
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void remove (){
//  turn flag off
    }
}
