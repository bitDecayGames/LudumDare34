package ludum.dare.levels;

import com.bitdecay.jump.geom.BitPointInt;
import com.bitdecay.jump.level.Level;
import com.bitdecay.jump.level.LevelUtilities;
import com.bitdecay.jump.level.TileObject;
import com.bitdecay.jump.level.builder.LevelBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12/12/2015.
 */
public class LevelSegmentAggregator {

    public static int TILE_SIZE = 16;
    public static boolean DEBUG = false;


    public static Level assembleSegments(List<Level> levelsRaw) {
        List<LevelWithAggData> levels = new ArrayList<>();
        levelsRaw.forEach(l -> levels.add(new LevelWithAggData(l)));

        adjustRectsInEachLevelSegment(levels);
        return buildAggregatedLevels(levels);
    }

    private static void adjustRectsInEachLevelSegment(List<LevelWithAggData> levels) {

        if(DEBUG){
            System.out.println("Printing out two level sections before adjustments:");
            System.out.println("\n");

            for(LevelWithAggData levelWithAggData : levels) {

                if (levelWithAggData.leftMostTile != null) {
                    System.out.println("Coordinates of the left-most tile in the level: (" + levelWithAggData.getLeftMostTileX() + ", " + levelWithAggData.getLeftMostTileY() + ")");
                } else {
                    System.out.println("Could not find a left-most tile!");
                }

                if (levelWithAggData.rightMostTile != null) {
                    System.out.println("Coordinates of the right-most tile in the leve1: (" + levelWithAggData.getRightMostTileX() + ", " + levelWithAggData.getRightMostTileY() + ")");
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

            LevelWithAggData segmentA = levels.get(i);
            LevelWithAggData segmentB = levels.get(i+1);

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

            for (LevelWithAggData levelWithAggData : levels) {

                if (levelWithAggData.leftMostTile != null) {
                    System.out.println("Coordinates of the left-most tile in the level: (" + levelWithAggData.getLeftMostTileX() + ", " + levelWithAggData.getLeftMostTileY() + ")");
                } else {
                    System.out.println("Could not find a left-most tile!");
                }

                if (levelWithAggData.rightMostTile != null) {
                    System.out.println("Coordinates of the right-most tile in the leve1: (" + levelWithAggData.getRightMostTileX() + ", " + levelWithAggData.getRightMostTileY() + ")");
                } else {
                    System.out.println("Could not find a right-most tile!");
                }
            }

            System.out.println("\n");
        }
    }

    private static Level buildAggregatedLevels(List<LevelWithAggData> levelsWithMetaData){
        LevelBuilder levelBuilder = new LevelBuilder(TILE_SIZE);

        for(LevelWithAggData level : levelsWithMetaData) {

            for (TileObject[] toa : level.level.gridObjects) {
                for (TileObject to : toa) {
                    if (to != null) {
                        levelBuilder.createLevelObject(new BitPointInt((int) to.rect.xy.x, (int) to.rect.xy.y), new BitPointInt((int) to.rect.xy.x + TILE_SIZE, (int) to.rect.xy.y + TILE_SIZE), to.oneway, to.material);
                    }
                }
            }
        }
        LevelUtilities.saveLevel(levelBuilder, false);
        return levelBuilder.optimizeLevel();
    }

    public static void main(String args[]){

        LevelSegmentGenerator levelSegmentGenerator = new LevelSegmentGenerator(15);

        LevelSegmentAggregator.assembleSegments(levelSegmentGenerator.generateLevelSegments());
    }
}
