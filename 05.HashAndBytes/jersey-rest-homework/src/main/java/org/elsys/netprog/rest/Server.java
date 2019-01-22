package org.elsys.netprog.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.groovy.classgen.genArrayAccess;
import org.json.JSONArray;
import org.json.JSONObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


@Path("/game")
public class Server {
	private static int LENGTH = 2;
	private static byte[] b = new byte[LENGTH];
	private static String hashString;
	
	@POST
	@Path("/sendInput")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response sendInput(@FormParam("hash") String hash,@FormParam("input") String input)
			throws URISyntaxException, NoSuchAlgorithmException {
		System.out.println("Hash: " + hash + "\nInput: " + input);
		if (Base64.getUrlEncoder().encodeToString(b).equals(input) && hashString.equals(hash)) {
			genBytes();
			return Response.status(200).build();
		}
		return Response.status(406).build();
	}
	
	@GET
	@Path("/randomBytes")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response randomBytes() throws NoSuchAlgorithmException {
		genBytes();
		
		JSONObject myJsonObject = new JSONObject();
		myJsonObject.put("HASH", hashString)
			.put("LENGTH", LENGTH);
		
		return Response.status(200).entity(myJsonObject.toString()).build();
	}
	
	private void genBytes() throws NoSuchAlgorithmException {
		new Random().nextBytes(b);
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hash = md.digest(b);
		hashString = Base64.getUrlEncoder().encodeToString(hash);
	}
}
