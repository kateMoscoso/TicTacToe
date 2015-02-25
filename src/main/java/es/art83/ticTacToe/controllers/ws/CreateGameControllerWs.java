package es.art83.ticTacToe.controllers.ws;

import es.art83.ticTacToe.controllers.CreateGameController;
import es.art83.ticTacToe.ws.SessionUris;
import es.art83.ticTacToe.ws.SessionGameUris;

public class CreateGameControllerWs extends ControllerWs implements CreateGameController {

    CreateGameControllerWs(String sessionId) {
        super(sessionId);
    }

    @Override
    public void createGame() {
        // Se busca si existe una partida a medias
        WsManager webServicesManager = ControllerWs.buildWebServiceManager(
                SessionUris.PATH_SESSIONS, this.getSessionId(), SessionGameUris.PATH_GAME);
        webServicesManager.create();
    }

}
