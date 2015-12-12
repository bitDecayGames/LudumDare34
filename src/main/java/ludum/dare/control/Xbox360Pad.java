package ludum.dare.control;

public enum Xbox360Pad {
    UP(0, -1),
    DOWN(1, -1),
    LEFT(2, -1),
    RIGHT(3, -1),
    START(4, -1),
    BACK(5, -1),
    LS(6, -1),
    RS(7, -1),
    LB(8, -1),
    RB(9, -1),
    XBOX(10, -1),
    A(11, -1),
    B(12, -1),
    X(13, -1),
    Y(14, -1),
    LT(0, -1),
    RT(1, -1),
    LS_RIGHT(2, -1),
    LS_UP(3, -1),
    LS_DOWN(3, -1),
    RS_LEFT(4, -1),
    RS_RIGHT(4, -1),
    RS_UP(5, -1),
    RS_DOWN(5, -1),
    LS_LEFT(2, -1),;

    public int val;

    Xbox360Pad(int macVal, int winVal) {
        // Mappings are different for different OSs.
        String osName = System.getProperty("os.name");
        if (osName.contains("Mac")) {
            val = macVal;
        } else if (osName.contains("Windows")) {
            val = winVal;
        } else {
            val = macVal;
        }
    }
}

