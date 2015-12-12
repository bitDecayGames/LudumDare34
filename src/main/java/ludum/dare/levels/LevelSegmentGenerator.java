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

    int numSegmentToUse;

    public LevelSegmentGenerator(int numSegmentToUse) {
        this.numSegmentToUse = numSegmentToUse;
    }

    public List<Level> generateLevelSegments(){

        int segmentsAvailable;

        segmentsAvailable = new File("src\\main\\resources\\levelSegments").list().length;
        System.out.println("Segments available: " + segmentsAvailable);

        List<Level> generatedListOfSegments = new ArrayList<>();

        for (int i = 0; i < numSegmentToUse; i++){
            int levelIndex = (int) (Math.random() * segmentsAvailable + 1);
            System.out.println("The segment chosen was " + levelIndex);
            generatedListOfSegments.add(LevelUtilities.loadLevel("src\\main\\resources\\levelSegments\\segment_" + levelIndex));
        }

        return generatedListOfSegments;
    }
}
