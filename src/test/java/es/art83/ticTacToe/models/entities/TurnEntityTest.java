package es.art83.ticTacToe.models.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import es.art83.ticTacToe.models.entities.TurnEntity;
import es.art83.ticTacToe.models.utils.ColorModel;

public class TurnEntityTest {

    @Test
    public void testTurnEntityColor() {
        for (ColorModel color : ColorModel.values()) {
            this.testTurnColor(new TurnEntity(color), color);
        }
    }

    private void testTurnColor(TurnEntity turn, ColorModel color) {
        assertEquals(color, turn.getColor());
    }

    @Test
    public void testTurnEntity() {
        this.testTurnColor(new TurnEntity(), ColorModel.X);
    }

    @Test
    public void testColorChanged() {
        for (ColorModel color : ColorModel.values()) {
            assertEquals(color.next(), new TurnEntity(color).colorChanged());
        }
    }

    @Test
    public void testChange() {
        for (ColorModel color : ColorModel.values()) {
            TurnEntity turn = new TurnEntity(color);
            turn.change();
            this.testTurnColor(turn, color.next());
            turn.change();
            this.testTurnColor(turn, color);
        }
    }
    
    @Test
    public void testUpdate() {
        for (ColorModel color : ColorModel.values()) {
            TurnEntity turn = new TurnEntity(color);
            for (ColorModel color2 : ColorModel.values()){
                turn.update(new TurnEntity(color2));
                assertEquals(color2, turn.getColor());
            }
        }
    }

}
