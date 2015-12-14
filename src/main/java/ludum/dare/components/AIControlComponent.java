package ludum.dare.components;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bitdecay.jump.control.PlayerAction;
import ludum.dare.actors.ai.AIRunAlongState;
import ludum.dare.actors.player.Player;
import ludum.dare.control.InputAction;
import ludum.dare.gameobject.FinishLineGameObject;
import ludum.dare.interfaces.IShapeDraw;
import ludum.dare.interfaces.IState;
import ludum.dare.levels.ai.Node;
import ludum.dare.levels.ai.Nodes;
import ludum.dare.util.Players;

import java.util.HashSet;
import java.util.Set;

// TODO Mike implement for AI
public class AIControlComponent extends InputComponent implements IShapeDraw {
    Player me;
    Nodes nodes = null;

    IState activeState;

    Set<InputAction> currentActions = new HashSet<>();
    Set<InputAction> previousActions = new HashSet<>();

    public FinishLineGameObject finishLine;

    public AIControlComponent() {
        super();
    }

    private void discoverMe() {
        for (int i = 0; i < Players.list().size(); i++) {
            if (Players.list().get(i).getInputComponent() == this) {
                me = Players.list().get(i);
            }
        }

        reset();
    }

    public void reset() {
        if (me != null) {
            Node n = nodes.closestContainingNode(me.getPosition());
            activeState = new AIRunAlongState(me, this, nodes, n, finishLine.getPosition());
            activeState.enter();
        }
    }

    @Override
    public boolean isPressed(PlayerAction action) {
        InputAction act = InputAction.forPlayerAction(action);
        return action != null && inControl && currentActions.contains(act);
    }

    public void pressed(PlayerAction action) {
        currentActions.add(InputAction.forPlayerAction(action));
        previousActions.add(InputAction.forPlayerAction(action));
    }

    @Override
    public boolean isJustPressed(PlayerAction action) {
        InputAction act = InputAction.forPlayerAction(action);
        return action != null && inControl && currentActions.contains(act) && !previousActions.contains(act);
    }

    public void justPressed(PlayerAction action) {
        currentActions.add(InputAction.forPlayerAction(action));
        previousActions.remove(InputAction.forPlayerAction(action));
    }

    @Override
    public boolean isPressed(InputAction action) {
        return action != null && inControl && currentActions.contains(action);
    }

    public void pressed(InputAction action) {
        currentActions.add(action);
        previousActions.add(action);
    }

    @Override
    public boolean isJustPressed(InputAction action) {
        return action != null && inControl && currentActions.contains(action) && !previousActions.contains(action);
    }

    public void justPressed(InputAction action) {
        currentActions.add(action);
        previousActions.remove(action);
    }

    @Override
    public void update(float delta) {
        previousActions.addAll(currentActions);
        currentActions.clear();
        if (me == null && Players.isInitialized() && nodes != null) discoverMe();
        else if (me != null && activeState != null) {
            IState newState = activeState.update(delta);
            if (newState != null) {
                activeState.exit();
                newState.enter();
                activeState = newState;
            }
        }
    }

    public void setAINodes(Nodes nodes) {
        this.nodes = nodes;
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        nodes.draw(shapeRenderer);
    }
}
