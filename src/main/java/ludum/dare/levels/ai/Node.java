package ludum.dare.levels.ai;

import com.badlogic.gdx.math.Vector2;

public class Node {
    public final float y;
    public final float left;
    public final float right;
    public final float length;
    public final boolean oneWay;
    private final float yMin;
    private final float yMax;

    public Node(float left, float right, float y, boolean oneWay) {
        this.left = left;
        this.right = right;
        this.y = y;
        this.length = right - left;
        this.yMin = y - 8;
        this.yMax = y + 8;
        this.oneWay = oneWay;
    }

    public float whereIsPointInNode(float x) {
        return (x - left) / length;
    }

    public boolean isPointContainedBetweenNode(float x) {
        float where = whereIsPointInNode(x);
        return where > 0 && where < 1;
    }

    public boolean isPointInNode(Vector2 point) {
        return isPointContainedBetweenNode(point.x) && point.y > yMin && point.y < yMax;
    }
}
