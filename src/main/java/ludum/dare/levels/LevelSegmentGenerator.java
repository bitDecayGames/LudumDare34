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

        segmentsAvailable = new File(SEGMENT_DIR).list().length;
        System.out.println("Segments available: " + segmentsAvailable);

        List<Level> generatedListOfSegments = new ArrayList<>();

        for (int i = 0; i < numSegmentToUse; i++){
            int levelIndex = (int) (Math.random() * segmentsAvailable + 1);
            System.out.println("The segment chosen was " + levelIndex);
            generatedListOfSegments.add(LevelUtilities.loadLevel(SEGMENT_DIR + "segment_" + levelIndex));
        }

        return generatedListOfSegments;
    }
}
