package ludum.dare.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.RacerGame;
import ludum.dare.actors.GameObject;
import ludum.dare.actors.player.Player;
import ludum.dare.components.AIControlComponent;
import ludum.dare.components.InputComponent;
import ludum.dare.components.upgradeComponents.*;
import ludum.dare.control.InputAction;
import ludum.dare.interfaces.IComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * A container class to hold a single player's upgrade choices
 * Created by Admin on 12/12/2015.
 */
public class UpgradeGroup {
    Player player;
    List<UpgradeOption> choices = new ArrayList<>();
    int selectedIndex = 0;

    AnimagicTextureRegion selectionTexture;

    boolean active = true;
    private boolean ready;

    private BitmapFont font = new BitmapFont();

    //TODO: we will need to replace this argument with a player object once we have one.

    /**
     * This will build the options based on a provided player object
     * @param player
     */
    public void initialize(Player player) {
        font.getData().setScale(5);
        font.setColor(Color.BLACK);

        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/upgrades.atlas", AnimagicTextureAtlas.class);
        selectionTexture = atlas.findRegion("selection");
        this.player = player;
    }

    public void addChoice(UpgradeOption option) {
        choices.add(option);
    }

    public void update(float delta) {
        if (active) {
            if (choices.size() <= 0) {
                active = false;
            }

            // update the upgrades (animation, etc)
            for (UpgradeOption option : choices) {
                option.update(delta);
            }

            InputComponent inputComponent = player.getInputComponent();
            if (inputComponent instanceof AIControlComponent) {
                if (Math.random() < .3f) {
                    left();
                } else if (Math.random() > .7f) {
                    right();
                } else {
                    select();
                }
            } else if (inputComponent != null) {
                if (inputComponent.isJustPressed(InputAction.LEFT)) {
                    left();
                }
                if (inputComponent.isJustPressed(InputAction.RIGHT)) {
                    right();
                }
                if (inputComponent.isJustPressed(InputAction.JUMP)) {
                    select();
                }
            } else {
                throw new RuntimeException("Player had no input component");
            }
            inputComponent.update(delta);
        }
    }

    private void left() {
        selectedIndex--;
        if (selectedIndex < 0) {
            selectedIndex = choices.size()-1;
        }
    }

    private void right() {
        selectedIndex++;
        if (selectedIndex >= choices.size()) {
            selectedIndex = 0;
        }
    }

    private void select() {
        active = false;
        if (selectedIndex > choices.size()) {
            Class clazz = choices.get(selectedIndex).clazz;
            if (player.moneyCount() >= 10) {
                player.addUpgrade(clazz);
                player.achieveMoney(-10);
            }
        }
    }

    public void render(SpriteBatch batch, int yTop, int yBottom) {
        int workingSpace = Math.abs(yTop - yBottom);
        int middle = (yTop + yBottom) / 2;
        int renderSize = (int) (workingSpace *.9f);

        int buffer = 20;

        int neededTotalWidth = choices.size() * (renderSize + buffer);
        int edgeSpace = (Gdx.graphics.getWidth() - neededTotalWidth) / 2;

        int renderX = edgeSpace;
        if (active) {
            batch.setColor(Color.YELLOW);
        } else {
            batch.setColor(Color.DARK_GRAY);
        }
        for (int i = 0; i < choices.size(); i ++) {
            batch.setColor(Color.WHITE);
            batch.draw(choices.get(i).animation.getFrame(), renderX, middle - renderSize / 2, renderSize, renderSize);
            if (selectedIndex == i) {
                batch.draw(selectionTexture, renderX, middle - renderSize / 2, renderSize, renderSize);
            }
            batch.setColor(Color.BLACK);
            font.draw(batch, "$" + player.moneyCount(), 30, 800);
            font.draw(batch, "$10", renderX + 50, 150);
            renderX += renderSize + buffer;
        }
    }

    public List<UpgradeOption> getChoices() {
        return choices;
    }

    public boolean isReady() {
        return !active;
    }
}
