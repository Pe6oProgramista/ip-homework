package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class EchoServer {
	
	private static ArrayList<Socket> clients = new ArrayList<Socket>();
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		try {
			serverSocket = new ServerSocket(10001);
					
		    while((clientSocket = serverSocket.accept()) != null) {
		    	System.out.println("client connected from " + clientSocket.getInetAddress());
		    	clients.add(clientSocket);
		    	OnConnection(clientSocket);
		    }
		    
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			
			System.out.println("Server closed!");
		}
	}
	
	private static void OnConnection(Socket socket) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					BufferedReader in = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));

					String clientInput;
					while ((clientInput = in.readLine()) != null) {
						for (Socket client : clients) {
							if(!client.equals(socket)) {
								System.out.println(clientInput);
								PrintWriter out = new PrintWriter(client.getOutputStream(), true);
								out.println(clientInput);
							}
						}
					}
				} catch (Throwable t) {
					System.out.println(t.getMessage());
				}
			}
		});
		thread.start();
	}
}
