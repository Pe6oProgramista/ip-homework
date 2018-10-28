package org.elsys.netprog.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;


@Path("/game")
public class GameController {
	private static GameContainer gameCont = new GameContainer();
	
	@POST
	@Path("/startGame")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response startGame() throws URISyntaxException{
		Game myGame = new Game();
		gameCont.getGames().add(myGame);
		System.out.println(myGame.getNumber());
		return Response.created(new URI("/games")).entity(myGame.getId()).build();
	}
	
	@PUT
	@Path("/guess/{id}/{guess}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response guess(@PathParam("id") String gameId, @PathParam("guess") String guess) throws Exception{
		List<Game> games = gameCont.getGames();
		for(Game g : games) {
			if(g.getId().equals(gameId)) {
				if(isValid(guess)) {
					g.checkNumber(guess);
					JSONObject myObj = g.asJson();
					myObj.remove("secret");
					return Response.status(200).entity(myObj.toString()).build();
				}
				return Response.status(400).build();
			}
		}
		return Response.status(404).build();
	}
	
	@GET
	@Path("/games")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getGames() {
		JSONArray myJsonArray = gameCont.asJson();
		return Response.status(200).entity(myJsonArray.toString()).build();
	}
	
	private boolean isValid(String number) {
		try{
			Set<Integer> num = new LinkedHashSet<Integer>();
			
			Integer n = new Integer(number);
			System.out.println(n);
			while(n != 0) {
				num.add(n % 10);
				n /= 10;
			}
			if(num.size() != 4) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
