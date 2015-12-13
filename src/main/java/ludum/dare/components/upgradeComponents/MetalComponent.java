package ludum.dare.components.upgradeComponents;

import ludum.dare.components.AttackComponent;
import ludum.dare.components.HealthComponent;
import ludum.dare.components.PhysicsComponent;
import ludum.dare.interfaces.IComponent;

/**
 * Created by jake on 12/12/2015.
 */
public class MetalComponent implements IComponent {
    public int cost = 0;

    public MetalComponent(PhysicsComponent phys, HealthComponent health, AttackComponent attack){
        phys.getBody().props.acceleration = (int) (phys.getBody().props.acceleration * 0.9f);
        phys.getBody().props.gravityModifier = phys.getBody().props.gravityModifier * 0.9f;
        health.max = health.max * 1.25f;
        attack.attack = attack.attack * 1.25f;
    }
}
