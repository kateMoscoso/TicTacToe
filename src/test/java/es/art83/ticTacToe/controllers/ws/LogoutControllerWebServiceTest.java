package es.art83.ticTacToe.controllers.ws;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.art83.ticTacToe.controllers.ws.ControllerWsFactory;
import es.art83.ticTacToe.controllers.ws.LoginControllerWs;
import es.art83.ticTacToe.controllers.ws.LogoutControllerWs;
import es.art83.ticTacToe.models.entities.PlayerEntity;
import es.art83.ticTacToe.ws.PlayerUris;
import es.art83.ticTacToe.ws.SessionUris;

public class LogoutControllerWebServiceTest {

    private LoginControllerWs login;

    private LogoutControllerWs logout;

    private PlayerEntity playerEntity;

    @Before
    public void before() {
        ControllerWsFactory factory = new ControllerWsFactory();
        this.login = (LoginControllerWs) factory.getLoginController();
        this.logout = (LogoutControllerWs) factory.getLogoutController();
        this.playerEntity = new PlayerEntity("u", "pass");
        this.login.register(playerEntity);
    }

    @Test
    public void testIsSavedGame() {
        assertTrue(this.logout.savedGame());
    }

    @Test
    public void testLogout() {
        this.logout.logout();
        assertFalse(this.login.loggedIn());
    }

    @Test
    public void testIsByeTrue() {
        this.logout.logout();
        assertTrue(this.login.loggedOut());
    }

    @Test
    public void testIsByeFalse() {
        assertFalse(this.login.loggedOut());
    }

    @After
    public void after() {
        ControllerWs.buildWebServiceManager(SessionUris.PATH_SESSIONS, this.logout.getSessionId()).delete();
        ControllerWs.buildWebServiceManager(PlayerUris.PATH_PLAYERS, this.playerEntity.getUser()).delete();
    }
}
