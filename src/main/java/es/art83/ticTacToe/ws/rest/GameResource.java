package es.art83.ticTacToe.ws.rest;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;

import es.art83.ticTacToe.models.daos.DaoFactory;
import es.art83.ticTacToe.models.entities.GameEntity;
import es.art83.ticTacToe.models.entities.PlayerEntity;
import es.art83.ticTacToe.models.entities.SessionEntity;
import es.art83.ticTacToe.ws.GameUris;

@Path(GameUris.PATH_GAMES)
public class GameResource {

    private void debug(String msg) {
        LogManager.getLogger(this.getClass()).debug(GameUris.PATH_GAMES + msg);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response createGame(@QueryParam("sessionId") Integer sessionId) {
        Response result;
        SessionEntity session = DaoFactory.getFactory().getSessionDao().read(sessionId);
        GameEntity game = DaoFactory.getFactory().getGameDao().read(session.getGame().getId());
        GameEntity gameClone = game.clone();
        DaoFactory.getFactory().getGameDao().create(gameClone);
        result = Response.created(URI.create(GameUris.PATH_GAMES + "/" + gameClone.getId()))
                .entity(String.valueOf(gameClone.getId())).build();
        this.debug("?sessionId=" + sessionId + " /POST: " + gameClone);

        session.setSavedGame(true);
        DaoFactory.getFactory().getSessionDao().update(session);

        return result;
    }

    @Path(GameUris.PATH_SEARCH)
    @GET
    @Consumes(MediaType.APPLICATION_XML)
    public String findGame(@QueryParam("sessionId") Integer sessionId,
            @QueryParam("name") String name) {
        PlayerEntity player = DaoFactory.getFactory().getSessionDao().read(sessionId).getPlayer();
        GameEntity game = DaoFactory.getFactory().getGameDao().findPlayerGame(player, name);
        this.debug(GameUris.PATH_SEARCH + "?sessionId=" + sessionId + "&name=" + name + " /GET: " + game);
        if (game == null) {
            throw new NotFoundException();
        } else {
            return String.valueOf(game.getId());
        }

    }

    @Path(GameUris.PATH_ID_PARAM)
    @DELETE
    @Consumes(MediaType.APPLICATION_XML)
    public void deleteGame(@PathParam("id") Integer id) {
        DaoFactory.getFactory().getGameDao().deleteById(id);
        this.debug("/" + id + " /DELETE");
    }

}
