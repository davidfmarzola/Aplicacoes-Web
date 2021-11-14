package app;

import service.AlunoService;

import static spark.Spark.*;

public class Aplicacao {
	
	private static AlunoService alunoService = new AlunoService();
	
    public static void main(String[] args) {
        port(6789);

        post("/aluno", (request, response) -> alunoService.add(request, response));

        get("/aluno/:id", (request, response) -> alunoService.get(request, response));

        get("/aluno/update/:id", (request, response) -> alunoService.update(request, response));

        get("/aluno/delete/:id", (request, response) -> alunoService.remove(request, response));

        get("/aluno", (request, response) -> alunoService.getAll(request, response));
               
    }
}
