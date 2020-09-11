package br.ucsal.staticChecker;

import static test.Token.*;
%%
%class Lexer
%type Token

letra = [a-z,A-Z]
digito = [0-9]
Nome = (({letra}*)"_"({digito}*) | ({letra}*))
Ip = [1-9][0-9]* "." [1-9][0-9]* "." [1-9][0-9]* "." [1-9][0-9]* 
Porta = ( \d{4} | \d{5})
Conexao = {Nome}" " "CONECTAR" " "{Nome} 
Registro = {Nome}" "{Ip}" "{Porta} | {Nome}":"" "{Ip}" "{Porta} 


WHITE=[\t\r]
%{
public String lexeme;
%}
%%
{WHITE}
/* O PRIMEIRO LEXEME DESTA LISTA NÃO É VALIDO, POR ISSO CRIAMOS O MBUG, SOMENTE PARA FAZER O BEGIN SER O PRIMEIRO LEXEME*/
("mbug") {lexeme = yytext(); return FIXBUG;}

/* Comandos */	
("CONECTAR" ) {lexeme = yytext(); return CONECTAR;}
( "ATIVADO","DESATIVADO" ) {lexeme = yytext(); return ESTADO;}

{letra} {lexeme = yytext(); return LETRA;}

{digito} {lexeme = yytext(); return DIGITO;}

{Ip} {lexeme = yytext(); return IP;}

{Porta} {lexeme = yytext(); return PORTA;}

{Conexao} {lexeme = yytext(); return CONEXAO;}

{Nome} {lexeme = yytext(); return NOME;}

{Registro} {lexeme = yytext(); return REGISTRO;}

. {return ERROR;}