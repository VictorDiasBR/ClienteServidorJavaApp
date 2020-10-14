package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Servidor {
	
	    public static void main(String[] args) throws IOException {
	        
	    	ServerSocket servidor = new ServerSocket(8080); 
	    	System.out.println("Aguardando conexão!");
	    	 Socket socket = servidor.accept();
	        
	        System.out.println("Server Conectado");
	        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        PrintStream out = new PrintStream(socket.getOutputStream());
	       
	        Scanner sc = new Scanner(System.in);
	            while (true) {
	            	
	                    String msg = br.readLine();
	                    
	                    System.out.println(socket.getRemoteSocketAddress()+": "+msg);
	                    
	                    if(msg.equals("close")) {
	                    	break;
	                    }else if(msg.contains("LIVRE")) {
	                    	out.println("COLETAR 1");
	                    }
	                    
	            			out.flush();
	                 
	            		}
	                  
	            System.out.println("servidor encerrado");
	               br.close();
	               out.close();
	               socket.close();
		           servidor.close();
	    }
	}

