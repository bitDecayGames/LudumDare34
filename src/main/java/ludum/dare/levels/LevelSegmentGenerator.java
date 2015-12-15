package ludum.dare.levels;

import com.bitdecay.jump.level.Level;
import com.bitdecay.jump.level.LevelObject;
import com.bitdecay.jump.level.LevelUtilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LevelSegmentGenerator {
    public static final boolean DEBUG = false;
    public static final String SEGMENT_DIR = "./levelSegments/";

    int numSegmentToUse;

    public LevelSegmentGenerator(int numSegmentToUse) {
        this.numSegmentToUse = numSegmentToUse;
    }

    public List<Level> generateLevelSegments(){

        int segmentsAvailable;

        // Minus 1 is to account for the starting segment that we don't want chosen
        File f = new File(SEGMENT_DIR);
        String[] list = f.list();
        segmentsAvailable = list.length - 2;
        System.out.println("Segments available: " + segmentsAvailable);

        List<Level> generatedListOfSegments = new ArrayList<>();

        generatedListOfSegments.add(LevelUtilities.loadLevel(SEGMENT_DIR + "segment_start"));

        for (int i = 1; i < numSegmentToUse-1; i++){
            int levelIndex = (int) (Math.random() * segmentsAvailable + 1);
            if(DEBUG) {
                System.out.println("The segment chosen was " + levelIndex);
            }
            Level loadedLevel = LevelUtilities.loadLevel(SEGMENT_DIR + "segment_" + levelIndex);
            for (LevelObject obj : loadedLevel.otherObjects) {
                obj.uuid = UUID.randomUUID().toString();
            }
            generatedListOfSegments.add(loadedLevel);
        }

        generatedListOfSegments.add(LevelUtilities.loadLevel(SEGMENT_DIR + "segment_end"));

        return generatedListOfSegments;
    }
}
