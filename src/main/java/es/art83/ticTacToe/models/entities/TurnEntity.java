package es.art83.ticTacToe.models.entities;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import es.art83.ticTacToe.models.utils.ColorModel;

@Embeddable
public class TurnEntity {
    @Enumerated(EnumType.STRING)
    private ColorModel color;

    public TurnEntity(ColorModel color) {
        this.setColor(color);
    }

    public TurnEntity() {
        this(ColorModel.X);
    }

    public ColorModel getColor() {
        return color;
    }

    private void setColor(ColorModel color) {
        this.color = color;
    }

    public ColorModel colorChanged() {
        return this.color.next();
    }

    public void change() {
        this.color = this.colorChanged();
    }

    public void update(TurnEntity turn) {
        this.setColor(turn.color);
    }

    @Override
    public String toString() {
        return "TurnEntity[" + color + "]";
    }

    @Override
    protected TurnEntity clone() {
        return new TurnEntity(this.color);
    }

}
