package ludum.dare.components.PowerUpComponents;

import ludum.dare.components.PlayerCurrencyComponent;
import ludum.dare.components.TimedComponent;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IUpdate;

/**
 * Created by jake on 12/12/2015.
 */
public class StealCoinsComponent implements IComponent, IUpdate{

    public StealCoinsComponent(PlayerCurrencyComponent myWallet) {
        myWallet.getACoin();
        myWallet.getACoin();
        myWallet.getACoin();
//        make other players lose 1 coin each here, dunno how do
    }

    @Override
    public void update(float delta) {

    }
}
