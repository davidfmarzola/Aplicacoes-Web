//package app;

import static spark.Spark.*;

import Aluno;

public class Aplicacao {
	
	private static CadastroAlunos cadastroAlunos = new CadastroAlunos();
	
    public static void main(String[] args) {
        port(6789);

        post("/aluno", (request, response) -> cadastroAlunos.add(request, response));

        get("/aluno/:matricula", (request, response) -> cadastroAlunos.get(request, response));

        get("/aluno/update/:matricula", (request, response) -> cadastroAlunos.update(request, response));

        get("/aluno/delete/:matricula", (request, response) -> cadastroAlunos.remove(request, response));

        get("/aluno", (request, response) -> cadastroAlunos.getAll(request, response));
               
    }
}
