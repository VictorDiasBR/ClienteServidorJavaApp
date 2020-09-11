package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java_cup.internal_error;

public class App {
	
private static Scanner sc = new Scanner(System.in);
private static List<Parser> lista = new ArrayList<Parser>();
private static Parser p = new Parser();

	public static void main(String[] args) throws internal_error, Exception {

		System.out.println("Pront de comando:");
		analiseLexica();
		}

	public static void analiseLexica() throws Exception {

		while (true) {

			String comando = sc.nextLine();
		
			Lexer lexer = new Lexer(new StringReader(comando));
	        
			 Token token = lexer.yylex();

			switch (token) {
			
			case CONEXAO:
                
			listarDispositivos();
			
			for(Parser p:lista) {
				
				if(lexer.lexeme.contains(p.getNome().strip())) {
					
					Cliente c = new Cliente();
					c.executarCliente(p.getIp().strip(), p.getPorta().strip());
					
						c.enviarComando(lexer.lexeme);		
				}
				
			}
				
			break;
				
							
			default:
				
				System.err.println("Sintaxe incorreta");

			}

		}

	}
	
	public static void listarDispositivos() throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\55719\\Desktop\\lista_dispositivos.txt"));

		Pattern regex = Pattern.compile("(([a-zA-Z]*[0-9]*[_]\\d+)|([a-zA-Z]*[0-9]*[:])|^([a-zA-Z]*[0-9]*))\\s");
		Pattern regex2 = Pattern.compile("\\s([1-9][0-9]*[.][1-9][0-9]*[.][1-9][0-9]*[.][1-9][0-9]*)\\s");
		Pattern regex3 = Pattern.compile("\\s(\\d{5})");
		
		 String line ;
		 
		while ((line =br.readLine()) != null) {
			p = new Parser();
			Matcher matcher = regex.matcher(line);	
			
			while (matcher.find()) {
				String nome = matcher.group(0);	
				p.setNome(nome);
				
				Matcher matcher2 = regex2.matcher(line);	
				while (matcher2.find()) {
					String ip = matcher2.group(0);	
					p.setIp(ip);
					
					Matcher matcher3 = regex3.matcher(line);	
					while (matcher3.find()) {
						String porta = matcher3.group(0);	
						p.setPorta(porta);	
		}
				}	
			}	
						
		lista.add(p);
		}
		
		
		br.close();
	}
}
