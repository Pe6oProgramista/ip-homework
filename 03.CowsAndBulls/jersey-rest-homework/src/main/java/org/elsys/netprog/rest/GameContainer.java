package org.elsys.netprog.rest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GameContainer {
	private List<Game> games;
	
	public GameContainer() {
		games = new ArrayList<Game>();
	}
	
	public List<Game> getGames() {
		return games;
	}
	
	public JSONArray asJson() {
		JSONArray myJsonArray = new JSONArray();
		for(Game g : games) {
			JSONObject jsnObj = g.asJson();
			jsnObj.remove("cowsNumber");
			jsnObj.remove("bullsNumber");
			myJsonArray.put(jsnObj);
		}
		
		return myJsonArray;
	}
}
