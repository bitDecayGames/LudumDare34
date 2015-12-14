package ludum.dare.actors.ai;

import com.badlogic.gdx.math.Vector2;
import ludum.dare.actors.player.Player;
import ludum.dare.components.AIControlComponent;
import ludum.dare.control.InputAction;
import ludum.dare.gameobject.AINodeGameObject;
import ludum.dare.interfaces.IState;

import java.util.ArrayList;
import java.util.List;

public class AIMoveState implements IState {

    private Player me;
    private AIControlComponent input;

    private List<AINodeGameObject> nodes;
    private List<AINodeGameObject> visited = new ArrayList<>();
    private AINodeGameObject target = null;

    float waitToGo = 0;

    public AIMoveState(Player me, AIControlComponent input, List<AINodeGameObject> nodes) {
        this.me = me;
        this.input = input;
        this.nodes = nodes;
        if (this.me == null) throw new RuntimeException("Cant have null ai player");
        if (this.input == null) throw new RuntimeException("Cant have null ai input");
        if (this.nodes == null) throw new RuntimeException("Cant have null ai nodes");
    }

    @Override
    public void enter() {
        target = nextNode();
    }

    @Override
    public void exit() {

    }

    @Override
    public IState update(float delta) {
        waitToGo -= delta;
        if (waitToGo < 0) {
            if (isAtNode(target)) target = nextNode();
            if (target == null) return null;

            Vector2 myPos = me.getPosition();
            Vector2 targetPos = target.getPosition();
            if (!isCenteredOnNode(target)) {
                if (myPos.x < targetPos.x) input.pressed(InputAction.RIGHT);
                if (myPos.x > targetPos.x) input.pressed(InputAction.LEFT);
            }

            if (myPos.y < targetPos.y) input.justPressed(InputAction.JUMP);
        }
        return null;
    }

    private boolean isAtNode(AINodeGameObject node) {
        return node != null && me.getPosition().dst(node.getPosition()) < 16;
    }

    private boolean isCenteredOnNode(AINodeGameObject node) {
        return node != null && me.getPosition().x < node.getPosition().x + 5 && me.getPosition().x > node.getPosition().x - 5;
    }

    private AINodeGameObject nextNode() {
        if (target != null) visited.add(target);
        for (int i = 0; i < nodes.size(); i++) {
            if (!visited.contains(nodes.get(i))) {
                nodes.get(i).isTargeted = true;
                waitToGo = (float) Math.random() * 0.5f;
                return nodes.get(i);
            }
        }
        return null;
    }
}
