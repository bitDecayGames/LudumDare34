package ludum.dare.levels;

import com.bitdecay.jump.geom.BitRectangle;
import com.bitdecay.jump.level.Level;
import com.bitdecay.jump.level.TileObject;

/**
 * Created by Admin on 12/12/2015.
 */
public class LevelWithMeta {

    public Level level;
    public TileObject leftMostTile;
    public TileObject rightMostTile;

    public LevelWithMeta(Level level){
        this.level = level;

        leftMostTile = new TileObject(new BitRectangle(0, 0, 0, 0), false, 0);
        leftMostTile.rect.xy.x = 999999;
        rightMostTile = new TileObject(new BitRectangle(0, 0, 0, 0), false, 0);
        rightMostTile.rect.xy.x = 0;

    }

    public float getLeftMostTileX(){
        return leftMostTile.rect.xy.x;
    }

    public float getLeftMostTileY(){
        return leftMostTile.rect.xy.y;
    }

    public float getRightMostTileX(){
        return rightMostTile.rect.xy.x;
    }

    public float getRightMostTileY(){
        return rightMostTile.rect.xy.y;
    }

    public void setLeftMostTile(TileObject t){
        leftMostTile = t;
    }

    public void setRightMostTile(TileObject t){
        rightMostTile = t;
    }
}
