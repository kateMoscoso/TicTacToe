package es.art83.ticTacToe.controllers.ws;


abstract class ControllerWs {
    private String sessionId = null;

    private static final String PROTOCOL = "http";

    private static final String HOST = "localhost";

    private static final int PORT = 8080;

    private static final String WEB = "/TicTacToe/rest";

    private static final String URI = PROTOCOL + "://" + HOST + ":" + PORT + WEB;

    protected ControllerWs(String sessionId) {
        assert sessionId != null;
        this.sessionId = sessionId;
    }

    protected String getSessionId() {
        return sessionId;
    }
    
    protected static WsManager buildWebServiceManager(String... paths){
        return new WsManager(URI, paths);
    }

}
