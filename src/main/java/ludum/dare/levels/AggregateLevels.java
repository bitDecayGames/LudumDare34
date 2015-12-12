package ludum.dare.levels;

import com.bitdecay.jump.gdx.desktop.FileUtilities;
import com.bitdecay.jump.geom.BitPointInt;
import com.bitdecay.jump.level.FileUtils;
import com.bitdecay.jump.level.Level;
import com.bitdecay.jump.level.LevelUtilities;
import com.bitdecay.jump.level.TileObject;
import com.bitdecay.jump.level.builder.LevelBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12/12/2015.
 */
public class AggregateLevels {

    public static int TILE_SIZE = 16;
    public static boolean DEBUG = true;

    public AggregateLevels(List<Level> levelsRaw){

        List<LevelWithMeta> levels = new ArrayList<>();

        levelsRaw.forEach(l -> levels.add(new LevelWithMeta(l)));

        adjustRectsInEachLevelSegment(levels);
        buildAggregatedLevels(levels);
    }

    private void adjustRectsInEachLevelSegment(List<LevelWithMeta> levels) {

        if(DEBUG){
            System.out.println("Printing out two level sections before adjustments:");
            System.out.println("\n");

            for(LevelWithMeta levelWithMeta : levels) {

                if (levelWithMeta.leftMostTile != null) {
                    System.out.println("Coordinates of the left-most tile in the level: (" + levelWithMeta.getLeftMostTileX() + ", " + levelWithMeta.getLeftMostTileY() + ")");
                } else {
                    System.out.println("Could not find a left-most tile!");
                }

                if (levelWithMeta.rightMostTile != null) {
                    System.out.println("Coordinates of the right-most tile in the leve1: (" + levelWithMeta.getRightMostTileX() + ", " + levelWithMeta.getRightMostTileY() + ")");
                } else {
                    System.out.println("Could not find a right-most tile!");
                }
            }

            System.out.println("\n");

        }

        float tileAdjustmentX;
        float tileAdjustmentY;

        if (levels.size() <= 1) {
            System.out.println("Only one level segment found in the list! Nothing to merge.");
            return;
        }

        for(int i = 0; i < levels.size()-1; i++){

            LevelWithMeta segmentA = levels.get(i);
            LevelWithMeta segmentB = levels.get(i+1);

            tileAdjustmentX = segmentA.getRightMostTileX() - segmentB.getLeftMostTileX();
            tileAdjustmentY = segmentA.getRightMostTileY() - segmentB.getLeftMostTileY();

            for (TileObject[] ta : segmentB.level.gridObjects) {
                for (TileObject t : ta) {
                    if (t != null) {
                        t.rect.xy.x += tileAdjustmentX + TILE_SIZE;
                        t.rect.xy.y += tileAdjustmentY;
                    }
                }
            }
        }

        if(DEBUG) {

            System.out.println("Printing out two level sections after adjustments:");
            System.out.println("\n");

            for (LevelWithMeta levelWithMeta : levels) {

                if (levelWithMeta.leftMostTile != null) {
                    System.out.println("Coordinates of the left-most tile in the level: (" + levelWithMeta.getLeftMostTileX() + ", " + levelWithMeta.getLeftMostTileY() + ")");
                } else {
                    System.out.println("Could not find a left-most tile!");
                }

                if (levelWithMeta.rightMostTile != null) {
                    System.out.println("Coordinates of the right-most tile in the leve1: (" + levelWithMeta.getRightMostTileX() + ", " + levelWithMeta.getRightMostTileY() + ")");
                } else {
                    System.out.println("Could not find a right-most tile!");
                }
            }

            System.out.println("\n");
        }
    }

    private void buildAggregatedLevels(List<LevelWithMeta> levelsWithMetaData){
        LevelBuilder levelBuilder = new LevelBuilder(TILE_SIZE);

        for(LevelWithMeta level : levelsWithMetaData) {

            for (TileObject[] toa : level.level.gridObjects) {
                for (TileObject to : toa) {
                    if (to != null) {
                        levelBuilder.createLevelObject(new BitPointInt((int) to.rect.xy.x, (int) to.rect.xy.y), new BitPointInt((int) to.rect.xy.x + TILE_SIZE, (int) to.rect.xy.y + TILE_SIZE), to.oneway, to.material);
                    }
                }
            }
        }
        LevelUtilities.saveLevel(levelBuilder, false);
    }

    public static void main(String args[]){

        List<Level> levelsRaw = new ArrayList<Level>();

        String level1Path = "src\\main\\resources\\levels\\level_1";
        String level2Path = "src\\main\\resources\\levels\\level_2";
        String level3Path = "src\\main\\resources\\levels\\level_3";
        String level4Path = "src\\main\\resources\\levels\\level_4";

        levelsRaw.add(LevelUtilities.loadLevel(level1Path));
        levelsRaw.add(LevelUtilities.loadLevel(level2Path));
        levelsRaw.add(LevelUtilities.loadLevel(level3Path));
        levelsRaw.add(LevelUtilities.loadLevel(level4Path));
        levelsRaw.add(LevelUtilities.loadLevel(level1Path));
        levelsRaw.add(LevelUtilities.loadLevel(level2Path));
        levelsRaw.add(LevelUtilities.loadLevel(level3Path));
        levelsRaw.add(LevelUtilities.loadLevel(level4Path));

        new AggregateLevels(levelsRaw);
    }
}
