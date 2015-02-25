package es.art83.ticTacToe.controllers.ws;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.art83.ticTacToe.controllers.LogoutController;
import es.art83.ticTacToe.controllers.ws.ControllerWs;
import es.art83.ticTacToe.controllers.ws.ControllerWsFactory;
import es.art83.ticTacToe.controllers.ws.CreateGameControllerWs;
import es.art83.ticTacToe.models.entities.PlayerEntity;
import es.art83.ticTacToe.ws.PlayerUris;
import es.art83.ticTacToe.ws.SessionUris;

public class CreateGameControllerWebServiceTest {

    private CreateGameControllerWs create;

    private PlayerEntity playerEntity;

    private LogoutController logout;

    @Before
    public void before() {
        ControllerWsFactory factory = new ControllerWsFactory();
        this.create = (CreateGameControllerWs) factory.getCreateGameControler();
        this.logout = factory.getLogoutController();
        this.playerEntity = new PlayerEntity("u", "pass");
        factory.getLoginController().register(playerEntity);
    }

    @Test
    public void testCreateGame() {
        this.create.createGame();
    }

    @After
    public void after() {
        this.logout.logout();
        ControllerWs.buildWebServiceManager(SessionUris.PATH_SESSIONS, this.create.getSessionId())
                .delete();
        ControllerWs.buildWebServiceManager(PlayerUris.PATH_PLAYERS, this.playerEntity.getUser())
                .delete();
    }
}
