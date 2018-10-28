package org.elsys.netprog.rest;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.json.JSONObject;

import com.sun.jersey.core.impl.provider.header.NewCookieProvider;

import groovy.xml.Entity;

public class Game {
	private String id;
	private String number;
	private int cows;
	private int bulls;
	private int turnsCnt;
	private boolean success;
	
	public Game() {
		id = UUID.randomUUID().toString();
		number = generateNumber();
		cows = 0;
		bulls = 0;
		turnsCnt = 0;
		success = false;
	}
	
	public String getId() {
		return id;
	}
	
	public int getCows() {
		return cows;
	}
	
	public int getBulls() {
		return bulls;
	}
	
	public int getTurnsCnt() {
		return turnsCnt;
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	public String getNumber() {
		return number;
	}
	
	private String generateNumber() {
		Set<Integer> numberSet = new LinkedHashSet<Integer>();
		Random rand = new Random();
		numberSet.add(rand.nextInt(9) + 1);
		while(numberSet.size() < 4) {
			numberSet.add(rand.nextInt(9));
		}
		String number = "";
		for(Integer i : numberSet) {
			number += i;
		}
		return number;
	}
	
	public void checkNumber(String number) {
		turnsCnt++;
		if(this.number.equals(number)) {
			bulls += 4;
			success = true;
		} else {
			for(int i = 0; i < 4; i++) {
				char c = number.toCharArray()[i];
				if(this.number.contains("" + c)) {
					if(this.number.indexOf(c) == i) {
						bulls++;
					} else {
						cows++;
					}
				}
			}
		}
	}
	
	public JSONObject asJson() {
		JSONObject myJsonObject = new JSONObject();
		myJsonObject.put("gameId", id)
			.put("cowsNumber", cows)
			.put("bullsNumber", bulls)
			.put("turnsCount", turnsCnt)
			.put("secret", (success)? number : "****")
			.put("success", success);
		
		return myJsonObject;
	}
}
