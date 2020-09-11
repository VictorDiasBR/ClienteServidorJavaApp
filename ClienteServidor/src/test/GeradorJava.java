package test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java_cup.internal_error;

public class GeradorJava {

	public static void main(String[] args) throws internal_error, IOException, Exception {
		// TODO Auto-generated method stub

		String path1 = "C:\\Users\\55719\\eclipse-workspace\\ClienteServidor\\src\\test\\Lexer.flex";

       
        gerar(path1);
	}

	public static void gerar (String path1) throws internal_error, IOException, Exception {
    	
  	  File archivo;
        archivo = new File(path1);
        jflex.Main.generate(archivo);
          }
}
