package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {
	private Socket s ;
	private BufferedReader br;
	 private PrintStream out;
	 
	public void executarCliente(String porta, String ipS) throws Exception {
  
        Integer ip = Integer.parseInt(ipS);
		 s = new Socket(porta,ip);

	    br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out = new PrintStream(s.getOutputStream());
       
        System.out.println("Cliente conectado!");
	} 
	 
	 
        public void enviarComando(String msg) throws IOException {
        	 
        		out.println(msg);
        	
             while(true) {
             msg= br.readLine();
             System.out.println(msg);
            	 
             }
        }
       
        	
   
    
}
