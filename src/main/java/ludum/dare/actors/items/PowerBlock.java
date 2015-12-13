package ludum.dare.actors.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.level.LevelObject;
import com.bitdecay.jump.leveleditor.example.game.GameObject;
import ludum.dare.components.AnimationComponent;
import ludum.dare.components.CollectableComponent;
import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.PowerDownComponents.*;
import ludum.dare.components.PowerUpComponents.*;
import ludum.dare.components.upgradeComponents.SpeedComponent;
import ludum.dare.interfaces.IComponent;

import java.util.List;

/**
 * Created by jake on 12/11/2015.
 */
public class PowerBlock extends GameObject {

    private PhysicsComponent phys;
    private AnimationComponent anim;
    private CollectableComponent collect;

    public IComponent randomPowerGenerator(int playerRank){
        if(playerRank == 0){
            return playerZeroPicker();
        }else if (playerRank == 1){
            return playerOnePicker();
        }else if (playerRank == 2){
            return playerTwoPicker();
        } else if(playerRank == 3){
            return playerThreePicker();
        }
        return new StealCoinsComponent();
    }

    public IComponent playerZeroPicker(){
        double rand = Math.random();
        double pickRand = Math.random();
        if(rand <= .20){
            if(pickRand <= .14285714){
                return new TempSpeedComponent();
            }else if (pickRand > .14285714 && pickRand <= .28571428){
                return new OpenAttackComponent();
            }else if (pickRand > .28571428 && pickRand <= .42857142){
                return new SlowComponent();
            }else if (pickRand > .42857142 && pickRand <= .57142856){
                return new DoubleCoinsComponent();
            }else if (pickRand > .57142856 && pickRand <= .71428570){
                return new BigEnemySpawnComponent();
            }else if (pickRand > .71428570 && pickRand <= .85714284){
                return new LightsOffComponent();
            }else if (pickRand > .85714284){
                return new ForceHighJumpComponent();
            }
        }else if(rand > .20){
            if(pickRand <= .33){
                return new ManyEnemySpawnComponent();
            }else if (pickRand >.33 && pickRand <= .66){
                return new SpawnWallsComponent();
            }else if (pickRand >.66){
                return new StealCoinsComponent();
            }
        }
        return new StealCoinsComponent();
    }

    public IComponent playerOnePicker(){
        double rand = Math.random();
        double pickRand = Math.random();
        if(rand <= .10){
            if(pickRand <= .25){
                return new BlueHandComponent();
            }else if (pickRand > .25 && pickRand <= .5){
                return new TempFlyComponent();
            }else if (pickRand > .5 && pickRand <= .75){
                return new InvincibleComponent();
            }else if (pickRand > .75){
                return new StunComponent();
            }
        }else if (rand > .10 && rand <= .55){
            if(pickRand <= .14285714){
                return new TempSpeedComponent();
            }else if (pickRand > .14285714 && pickRand <= .28571428){
                return new OpenAttackComponent();
            }else if (pickRand > .28571428 && pickRand <= .42857142){
                return new SlowComponent();
            }else if (pickRand > .42857142 && pickRand <= .57142856){
                return new DoubleCoinsComponent();
            }else if (pickRand > .57142856 && pickRand <= .71428570){
                return new BigEnemySpawnComponent();
            }else if (pickRand > .71428570 && pickRand <= .85714284){
                return new LightsOffComponent();
            }else if (pickRand > .85714284){
                return new ForceHighJumpComponent();
            }
        }else if (rand > .55){
            if(pickRand <= .33){
                return new ManyEnemySpawnComponent();
            }else if (pickRand >.33 && pickRand <= .66){
                return new SpawnWallsComponent();
            }else if (pickRand >.66){
                return new StealCoinsComponent();
            }
        }
        return new StealCoinsComponent();
    }

    public IComponent playerTwoPicker(){
        double rand = Math.random();
        double pickRand = Math.random();
        if(rand <= .20){
            if(pickRand <= .25){
                return new BlueHandComponent();
            }else if (pickRand > .25 && pickRand <= .5){
                return new TempFlyComponent();
            }else if (pickRand > .5 && pickRand <= .75){
                return new InvincibleComponent();
            }else if (pickRand > .75){
                return new StunComponent();
            }
        }else if (rand > .20 && rand <= .70){
            if(pickRand <= .14285714){
                return new TempSpeedComponent();
            }else if (pickRand > .14285714 && pickRand <= .28571428){
                return new OpenAttackComponent();
            }else if (pickRand > .28571428 && pickRand <= .42857142){
                return new SlowComponent();
            }else if (pickRand > .42857142 && pickRand <= .57142856){
                return new DoubleCoinsComponent();
            }else if (pickRand > .57142856 && pickRand <= .71428570){
                return new BigEnemySpawnComponent();
            }else if (pickRand > .71428570 && pickRand <= .85714284){
                return new LightsOffComponent();
            }else if (pickRand > .85714284){
                return new ForceHighJumpComponent();
            }
        }else if (rand > .70){
            if(pickRand <= .33){
                return new ManyEnemySpawnComponent();
            }else if (pickRand >.33 && pickRand <= .66){
                return new SpawnWallsComponent();
            }else if (pickRand >.66){
                return new StealCoinsComponent();
            }
        }
        return new StealCoinsComponent();
    }

    public IComponent playerThreePicker(){
        double rand = Math.random();
        double pickRand = Math.random();
        if(rand <= .30){
            if(pickRand <= .25){
                return new BlueHandComponent();
            }else if (pickRand > .25 && pickRand <= .5){
                return new TempFlyComponent();
            }else if (pickRand > .5 && pickRand <= .75){
                return new InvincibleComponent();
            }else if (pickRand > .75){
                return new StunComponent();
            }
        }else if (rand > .30 && rand <= .90){
            if(pickRand <= .14285714){
                return new TempSpeedComponent();
            }else if (pickRand > .14285714 && pickRand <= .28571428){
                return new OpenAttackComponent();
            }else if (pickRand > .28571428 && pickRand <= .42857142){
                return new SlowComponent();
            }else if (pickRand > .42857142 && pickRand <= .57142856){
                return new DoubleCoinsComponent();
            }else if (pickRand > .57142856 && pickRand <= .71428570){
                return new BigEnemySpawnComponent();
            }else if (pickRand > .71428570 && pickRand <= .85714284){
                return new LightsOffComponent();
            }else if (pickRand > .85714284){
                return new ForceHighJumpComponent();
            }
        }else if (rand > .90){
            if(pickRand <= .33){
                return new ManyEnemySpawnComponent();
            }else if (pickRand >.33 && pickRand <= .66){
                return new SpawnWallsComponent();
            }else if (pickRand >.66){
                return new StealCoinsComponent();
            }
        }
        return new StealCoinsComponent();
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
