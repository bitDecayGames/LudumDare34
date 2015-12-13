package ludum.dare.components;

import ludum.dare.interfaces.IComponent;

/**
 * Created by jake on 12/12/2015.
 */
public class PlayerCurrencyComponent implements IComponent {
    public int currency = 0;

    public void getACoin(){
        currency ++;
    }

    public void loseACoin(){
        if(currency > 0){
            currency --;
        }
    }
}
