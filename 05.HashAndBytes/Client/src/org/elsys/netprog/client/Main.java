package org.elsys.netprog.client;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import org.json.*;
import org.omg.PortableServer.ServantActivator;

public class Main {
	private static String Url = "http://localhost:8081/jersey-rest-homework/game";
	
	public static void main(String[] args) throws Exception {
//		System.out.println(hashPerSec());
		while(true) {
			JSONObject myJson = sendGet();
			
			int length = myJson.getInt("LENGTH");
			String hash = myJson.getString("HASH");
			String hashString = null;
			
			System.out.println("LENGTH: " + length + "\nHASH: " + hash);
			
			while(true) {
				long startTime = System.nanoTime();
				byte[] b = new byte[length];
				new Random().nextBytes(b);
				
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] hashB = md.digest(b);
				
				hashString = Base64.getUrlEncoder().encodeToString(hashB);
				if(hash.equals(hashString)) {
					long endTime = System.nanoTime();
					System.out.println("Took "+(endTime - startTime) + " ns");
					sendPost(hash, Base64.getUrlEncoder().encodeToString(b));
					break;
				}
			}
		}
	}

	private static JSONObject sendGet() throws Exception {	
		URL url = new URL(Url + "/randomBytes");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		JSONObject myJson = new JSONObject(response.toString());
		return myJson;
	}
	
	private static void sendPost(String hash, String input) throws Exception {
		URL url = new URL(Url + "/sendInput");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		con.setRequestMethod("POST");

		String urlParameters = "hash=" + hash + "&input=" + input;
		
		con.setDoInput(true);
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("Code: " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
	}

	private static int hashPerSec() throws NoSuchAlgorithmException {
		byte[] b = new byte[10];
		new Random().nextBytes(b);
		long startTime = System.nanoTime();
		int i;
		for(i = 0; (System.nanoTime() - startTime) / 1000000000 < 1; i++) {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hashB = md.digest(b);
		}
		return i;
	}
}
