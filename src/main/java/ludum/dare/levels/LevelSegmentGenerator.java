package ludum.dare.levels;

import com.bitdecay.jump.level.Level;
import com.bitdecay.jump.level.LevelUtilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12/12/2015.
 */
public class LevelSegmentGenerator {
    public static final String SEGMENT_DIR = "levelSegments/";

    int numSegmentToUse;

    public LevelSegmentGenerator(int numSegmentToUse) {
        this.numSegmentToUse = numSegmentToUse;
    }

    public List<Level> generateLevelSegments(){

        int segmentsAvailable;

        // Minus 1 is to account for the starting segment that we don't want chosen
        segmentsAvailable = new File(SEGMENT_DIR).list().length - 2;
        System.out.println("Segments available: " + segmentsAvailable);

        List<Level> generatedListOfSegments = new ArrayList<>();

        generatedListOfSegments.add(LevelUtilities.loadLevel(SEGMENT_DIR + "segment_start"));

        for (int i = 1; i < numSegmentToUse-1; i++){
            int levelIndex = (int) (Math.random() * segmentsAvailable + 1);
            System.out.println("The segment chosen was " + levelIndex);
            generatedListOfSegments.add(LevelUtilities.loadLevel(SEGMENT_DIR + "segment_" + levelIndex));
        }

        generatedListOfSegments.add(LevelUtilities.loadLevel(SEGMENT_DIR + "segment_end"));

        return generatedListOfSegments;
    }
}
