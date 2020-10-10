package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
	private static Socket s;
	private static BufferedReader br;
	private static PrintStream out;
	public static boolean estadoCaminhao = true;
	private static List<Parser> lista = new ArrayList<Parser>();
	private static Parser p = new Parser();
	private static int idCaminhao = 4;

	public static void main(String[] args) {

		central(estadoCaminhao);
	}

	public static void executarCliente(String porta, String ipS, int idCaminhao) throws Exception {

		Integer ip = Integer.parseInt(ipS);
		s = new Socket(porta, ip);

		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new PrintStream(s.getOutputStream());

		System.out.println("Cliente conectado!");
	}

	public static void enviarComando(String msg, int idCaminhao) throws IOException {

		out.println(msg + " " + idCaminhao);

		while (true) {
			msg = br.readLine();
			System.out.println(msg);

			if (msg.contains("COLETAR")) {
				coletar(msg, idCaminhao);
				break;
			}
		}
	}

	public static void coletar(String msg, int idCaminhao) throws IOException {

		estadoCaminhao = false;
		Pattern regex = Pattern.compile("[0-9]+");

		Matcher matcher = regex.matcher(msg);
		String idContainer = null;
		while (matcher.find()) {
			idContainer = matcher.group(0);
		}

		enviarComando("CHEGUEI_CONTAINER", idCaminhao);

		enviarComando("COLETA_FINALIZADA", idCaminhao);

	}

	public static void central(boolean estadoCaminhao) {

		if (estadoCaminhao = true) {

			try {
				listarDispositivos();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			for (Parser p : lista) {

				try {
					executarCliente(p.getIp().strip(), p.getPorta().strip(), idCaminhao);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					enviarComando("LIVRE", idCaminhao);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}else {
			try {
				enviarComando("OCUPADO", idCaminhao);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void listarDispositivos() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\55719\\Desktop\\lista_dispositivos.txt"));

		Pattern regex = Pattern.compile("(([a-zA-Z]*[0-9]*[_]\\d+)|([a-zA-Z]*[0-9]*[:])|^([a-zA-Z]*[0-9]*))\\s");
		Pattern regex2 = Pattern.compile("\\s([1-9][0-9]*[.][1-9][0-9]*[.][1-9][0-9]*[.][1-9][0-9]*)\\s");
		Pattern regex3 = Pattern.compile("\\s(\\d{5})");

		String line;

		while ((line = br.readLine()) != null) {
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
