package org.elsys.netprog.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/game")
public class GameController {
	private static List<Game> games = new ArrayList<>();

	@POST
	@Path("/startGame")
	@Produces(value={MediaType.TEXT_PLAIN})
	public Response startGame() throws URISyntaxException{
		int number = ThreadLocalRandom.current().nextInt(1023, 9876 + 1);

		while (!Game.isValidNumber(number)) {
			number = ThreadLocalRandom.current().nextInt(1023, 9876 + 1);
		}

		Game game = new Game(number);
		games.add(game);
		return Response.created(new URI("/games")).entity(game.getGameId()).build();
	}
	
	@PUT
	@Path("/guess/{id}/{guess}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response guess(@PathParam("id") String gameId, @PathParam("guess") String guess) throws Exception{
		Integer number;
		try {
			number = Integer.valueOf(guess);
		} catch (NumberFormatException ex) {
			return Response.status(400).build();
		}
		for (Game game : games) {
			if (game.getGameId().equals(gameId)) {
				if (!game.isValidNumber(number)) {
					return Response.status(400).build();
				}
				Guess guessObj = new Guess(number, game);
				return Response.ok().entity(guessObj).build();
			}
		}
		return Response.status(404).build();
	}

	@GET
	@Path("/games")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getGames() {
		return Response.status(200).entity(games).build();
	}
}
