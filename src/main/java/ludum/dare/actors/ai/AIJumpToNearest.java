package ludum.dare.actors.ai;

import com.badlogic.gdx.math.Vector2;
import ludum.dare.actors.player.Player;
import ludum.dare.components.AIControlComponent;
import ludum.dare.control.InputAction;
import ludum.dare.interfaces.IState;
import ludum.dare.levels.ai.Node;
import ludum.dare.levels.ai.Nodes;

import java.util.ArrayList;
import java.util.List;

public class AIJumpToNearest implements IState {

    private Player me;
    private AIControlComponent input;


    private float timeBeforeAttack = 2;
    private final float timeBeforeNewTarget = (float) Math.random();
    private float newTargetTimer = 0;
    private Vector2 target;

    private List<Vector2> last3Targets = new ArrayList<>();

    private Node node;
    private Nodes nodes;

    public AIJumpToNearest(Player me, AIControlComponent input, Nodes nodes, Node node, Vector2 target) {
        this.me = me;
        this.input = input;
        this.node = node;
        this.target = target;
        this.nodes = nodes;
    }

    @Override
    public void enter() {
    }

    @Override
    public void exit() {

    }

    @Override
    public IState update(float delta) {
        Vector2 pos = me.getPosition();
        if (pos.x > target.x - 3 && pos.x < target.x + 3) {
            return new AIStopMovingState(me, input, nodes, target, 3);
        } else if (pos.x < target.x) {
            input.isJustPressed(InputAction.JUMP);
            input.pressed(InputAction.RIGHT);
        } else if (pos.x > target.x) {
            input.isJustPressed(InputAction.JUMP);
            input.pressed(InputAction.LEFT);
        }
        Node closestNode = nodes.closestContainingNode(pos);
        if (closestNode != null && closestNode != node) {
            return new AIRunAlongState(me, input, nodes, closestNode, target);
        }
        return null;
    }
}
