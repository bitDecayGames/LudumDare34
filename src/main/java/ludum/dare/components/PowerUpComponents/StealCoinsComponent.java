package ludum.dare.components.PowerUpComponents;

import ludum.dare.components.PlayerCurrencyComponent;
import ludum.dare.components.TimedComponent;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IUpdate;

/**
 * Created by jake on 12/12/2015.
 */
public class StealCoinsComponent implements IComponent{

    public StealCoinsComponent(PlayerCurrencyComponent myWallet, PlayerCurrencyComponent aWallet, PlayerCurrencyComponent bWallet, PlayerCurrencyComponent cWallet) {
        myWallet.getACoin(3);
        aWallet.loseACoin();
        bWallet.loseACoin();
        cWallet.loseACoin();
    }
}
