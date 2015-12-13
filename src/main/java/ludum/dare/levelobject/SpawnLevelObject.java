package ludum.dare.levelobject;

import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.level.LevelObject;

/**
 * Created by Admin on 12/12/2015.
 */
public class SpawnLevelObject extends LevelObject {
    @Override
    public BitBody buildBody() {
        return null;
    }

    @Override
    public String name() {
        return "Spawn Point";
    }
}
