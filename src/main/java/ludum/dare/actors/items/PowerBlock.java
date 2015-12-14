package ludum.dare.actors.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.level.LevelObject;
import com.bitdecay.jump.leveleditor.example.game.GameObject;
import ludum.dare.actors.player.Player;
import ludum.dare.components.AnimationComponent;
import ludum.dare.components.CollectableComponent;
import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.PowerDownComponents.*;
import ludum.dare.components.PowerUpComponents.*;
import ludum.dare.interfaces.IComponent;

import java.util.List;

/**
 * Created by jake on 12/11/2015.
 */
public class PowerBlock extends GameObject {

    private PhysicsComponent phys;
    private CollectableComponent collect;

    public String randomPowerGenerator(int playerRank){
        if(playerRank == 0){
            return playerZeroPicker();
        }else if (playerRank == 1){
            return playerOnePicker();
        }else if (playerRank == 2){
            return playerTwoPicker();
        } else if(playerRank == 3){
            return playerThreePicker();
        }
        return "";
    }

    public String playerZeroPicker(){
        double rand = Math.random();
        double pickRand = Math.random();
        if(rand <= .20){
            if(pickRand <= .20){
                return "TEMP_SPEED";
            }else if (pickRand > .20 && pickRand <= .40){
                return "SLOW";
            }else if (pickRand > .40 && pickRand <= .60){
                return "DOUBLE COINS";
            }else if (pickRand > .60 && pickRand <= .80){
                return "LIGHTS_OFF";
            }else if (pickRand > .80){
                return "FORCE_HIGH_JUMP";
            }
        }else if(rand > .20){
                return "STEAL_COINS";
        }
        return "STEAL_COINS";
    }

    public String playerOnePicker(){
        double rand = Math.random();
        double pickRand = Math.random();
        if(rand <= .10){
            if(pickRand <= .50){
                return "TEMP_FLY";
            }else if (pickRand > .50){
                return "STUN";
            }
        }else if (rand > .10 && rand <= .55){
            if(pickRand <= .20){
                return "TEMP_SPEED";
            }else if (pickRand > .20 && pickRand <= .40){
                return "SLOW";
            }else if (pickRand > .40 && pickRand <= .60){
                return "DOUBLE COINS";
            }else if (pickRand > .60 && pickRand <= .80){
                return "LIGHTS_OFF";
            }else if (pickRand > .80){
                return "FORCE_HIGH_JUMP";
            }
        }else if (rand > .55){
            return "STEAL_COINS";
        }
        return "STEAL_COINS";
    }

    public String playerTwoPicker(){
        double rand = Math.random();
        double pickRand = Math.random();
        if(rand <= .20){
            if(pickRand <= .50){
                return "TEMP_FLY";
            }else if (pickRand > .50){
                return "STUN";
            }
        }else if (rand > .20 && rand <= .70){
            if(pickRand <= .20){
                return "TEMP_SPEED";
            }else if (pickRand > .20 && pickRand <= .40){
                return "SLOW";
            }else if (pickRand > .40 && pickRand <= .60){
                return "DOUBLE COINS";
            }else if (pickRand > .60 && pickRand <= .80){
                return "LIGHTS_OFF";
            }else if (pickRand > .80){
                return "FORCE_HIGH_JUMP";
            }
        }else if (rand > .70){
            if(pickRand <= .33){
                return "STEAL_COINS";
            }
        }
        return"STEAL_COINS";
    }

    public String playerThreePicker(){
        double rand = Math.random();
        double pickRand = Math.random();
        if(rand <= .30){
            if(pickRand <= .50){
                return "TEMP_FLY";
            }else if (pickRand > .50){
                return "STUN";
            }
        }else if (rand > .30 && rand <= .90){
            if(pickRand <= .20){
                return "TEMP_SPEED";
            }else if (pickRand > .20 && pickRand <= .40){
                return "SLOW";
            }else if (pickRand > .40 && pickRand <= .60){
                return "DOUBLE COINS";
            }else if (pickRand > .60 && pickRand <= .80){
                return "LIGHTS_OFF";
            }else if (pickRand > .80){
                return "FORCE_HIGH_JUMP";
            }
        }else if (rand > .90){
            if(pickRand <= .33){
                return "STEAL_COINS";
            }
        }
        return "STEAL_COINS";
    }

    @Override
    public List<BitBody> build(LevelObject template) {
        return null;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
