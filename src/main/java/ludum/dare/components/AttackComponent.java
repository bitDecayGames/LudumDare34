package ludum.dare.components;

import ludum.dare.interfaces.IComponent;

/**
 * Created by jake on 12/12/2015.
 */
public class AttackComponent implements IComponent {
    public float attack;

    public AttackComponent(int attack){
        this.attack = attack;
    }
}
