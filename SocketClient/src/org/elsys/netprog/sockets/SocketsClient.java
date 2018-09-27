package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketsClient {

	public static void main(String[] args) throws IOException {
		Socket clientSocket = null;
		try {
			clientSocket = new Socket("localhost", 10001);
			
			ConsoleWrite(clientSocket);
			ConsoleRead(clientSocket);
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (clientSocket != null && !clientSocket.isClosed()) {
				clientSocket.close();
				System.out.println("Socket closed!");
			}
		}
	}
	
	private static void ConsoleWrite(Socket socket) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					BufferedReader in = new BufferedReader(
				            new InputStreamReader(socket.getInputStream()));
					
					String input;
					while((input = in.readLine()) != null) {
						System.out.println("server response: " + input);
					}
				} catch (Throwable t) {
					System.out.println(t.getMessage());
				}
			}
		});
	    thread.start();
	}
	
	private static void ConsoleRead(Socket socket) {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader stdIn = new BufferedReader(
		            new InputStreamReader(System.in));
		    
		    String userInput;
		    while ((userInput = stdIn.readLine()) != null) {
		        out.println(userInput);
		    }
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}

}
