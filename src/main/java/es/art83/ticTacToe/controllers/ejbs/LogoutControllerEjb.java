package es.art83.ticTacToe.controllers.ejbs;

import es.art83.ticTacToe.controllers.LogoutController;
import es.art83.ticTacToe.models.entities.SessionEntity;
import es.art83.ticTacToe.models.utils.StateModel;

public class LogoutControllerEjb extends ControllerEjb implements LogoutController {

    LogoutControllerEjb(SessionEntity session) {
        super(session);
    }

    @Override
    protected void changeState() {
        assert this.getSession().getState() == StateModel.CLOSED_GAME
                || this.getSession().getState() == StateModel.OPENED_GAME;
        this.getSession().setState(StateModel.FINAL);
    }
    
    @Override
    public void logout() {
        this.getSession().setPlayer(null);
        this.getSession().setGame(null);
        this.getSession().setSavedGame(true);
        this.changeState();
    }

    @Override
    public boolean savedGame() {
        return this.getSession().isSavedGame();
    }

}
