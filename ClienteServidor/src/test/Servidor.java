package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;
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
	       
	            while (true) {
	            	
	                    String msg = br.readLine();
	                    
	                    System.out.println(socket.getPort()+": "+msg);
	                    
	                    if(msg.equals("close")) {
	                    	break;
	                    }
	                    Pattern regex = Pattern.compile("(([a-zA-Z]*[0-9]*[_]+)|([a-zA-Z]*[0-9]*[:])|([a-zA-Z]*[0-9]*))$");
	                    
	            			Matcher matcher = regex.matcher(msg);
	            			String resposta = null ;
	            			while (matcher.find()) {
	            				resposta= matcher.group(0);	
	            				break;
	            			}
	            			
	            			out.println(resposta+" ATIVADO 30");
	            			
            				Timer timer = new Timer();
    	            		
	            			TimerTask tt = new TimerTask() {

								@Override
								public void run() {
									int x = (int) (Math.random() * 1000);
									System.out.println(x);
									out.println(x+" cº");
								}
	            				
	            			};
	            			timer.scheduleAtFixedRate(tt, 5,22000); 
	                 
	            		}
	                  
	            System.out.println("servidor encerrado");
	               br.close();
	               out.close();
	               socket.close();
		           servidor.close();
	    }
	}

