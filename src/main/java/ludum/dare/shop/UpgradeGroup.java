package ludum.dare.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.RacerGame;
import ludum.dare.actors.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A container class to hold a single player's upgrade choices
 * Created by Admin on 12/12/2015.
 */
public class UpgradeGroup {
    GameObject player;
    List<UpgradeOption> choices;
    int selectedIndex = 0;

    AnimagicTextureRegion selectionTexture;

    boolean active = true;

    //TODO: we will need to replace this argument with a player object once we have one.

    /**
     * This will build the options based on a provided player object
     * @param player
     */
    public void initialize(GameObject player) {
        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/test.atlas", AnimagicTextureAtlas.class);
        selectionTexture = atlas.findRegion("selection");
        this.player = player;
        choices = new ArrayList<>();
        choices.add(new UpgradeOption(null, "test"));
        choices.add(new UpgradeOption(null, "test"));
        choices.add(new UpgradeOption(null, "test"));
    }

    public void update(float delta) {
        if (active) {
            // update the upgrades (animation, etc)
            for (UpgradeOption option : choices) {
                option.update(delta);
            }

            // check player controls and update our selection if one was made

            // TODO: these need to be pulled from the control component once we have one
            boolean playerLeft = false, playerRight = false, playerSelect = false;
            if (playerLeft) {
                left();
            } else if (playerRight) {
                right();
            } else if (playerSelect) {
                select();
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                left();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                right();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                select();
            }
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
        System.out.println(selectedIndex + " " + choices.get(selectedIndex));
        active = false;
    }

    public void render(AnimagicSpriteBatch batch, int yTop, int yBottom) {
        int workingSpace = Math.abs(yTop - yBottom);
        int middle = (yTop + yBottom) / 2;
        int renderSize = (int) (workingSpace *.9f);

        int buffer = 20;

        int neededTotalWidth = choices.size() * (renderSize + buffer);
        int edgeSpace = (int) ((batch.getCamera().viewportWidth - neededTotalWidth) / 2);

        int renderX = edgeSpace;
        if (active) {
            batch.setColor(Color.WHITE);
        } else {
            batch.setColor(Color.GRAY);
        }
        for (int i = 0; i < choices.size(); i ++) {
            // do shit plox.
            batch.draw(choices.get(i).animation.getFrame(), renderX, middle - renderSize / 2, renderSize, renderSize);
            if (selectedIndex == i) {
                batch.draw(selectionTexture, renderX, middle - renderSize / 2, renderSize, renderSize);
            }
            renderX += renderSize + buffer;
        }
    }
}
