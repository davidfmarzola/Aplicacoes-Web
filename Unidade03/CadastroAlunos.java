//package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import DAO;
import Aluno;
import spark.Request;
import spark.Response;


public class CadastroAlunos {

	private DAO DAO;

	public CadastroAlunos() {
		//acesso fake ao banco de dados
		try {
			DAO = new DAO("aluno.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
            e.printStackTrace();
		}
	}

	public Object add(Request request, Response response) {
		int matricula = Integer.parseInt(request.queryParams("matricula"));
		String nome = request.queryParams("nome");
		String email = request.queryParams("email");
		String curso = request.queryParams("curso");
		int telefone = Integer.parseInt(request.queryParams("telefone"));

		matricula = DAO.getMaxMatricula() + 1;

		Aluno aluno = new Aluno(matricula, nome, email, curso, telefone);

		DAO.inserirAluno(aluno);

		response.status(201); // 201 Created
		return matricula;
	}

	public Object get(Request request, Response response) {
		int matricula = Integer.parseInt(request.params(":matricula"));
		
		Aluno aluno = (Aluno) DAO.get(matricula);
		
		if (aluno != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<aluno>\n" + 
            		"\t<matricula>" + aluno.getMatricula() + "</matricula>\n" +
            		"\t<nome>" + aluno.getNome() + "</nome>\n" +
            		"\t<email>" + aluno.getEmail() + "</email>\n" +
            		"\t<curso>" + aluno.getCurso() + "</curso>\n" +
            		"\t<telefone>" + aluno.getTelefone() + "</telefone>\n" +
            		"</aluno>\n";
        } else {
            response.status(404); // 404 Not found
            return "Aluno " + matricula + " nÃ£o encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        String matricula = request.queryParams("matrícula");
        
		Aluno aluno = (Aluno) DAO.get(matricula);

        if (aluno != null) {
        	aluno.setMatricula(Integer.parseInt(request.queryParams("matricula"));
        	aluno.setPreco(Float.parseFloat(request.queryParams("preco")));
        	aluno.setQuant(Integer.parseInt(request.queryParams("quantidade")));
        	aluno.setDataFabricacao(LocalDateTime.parse(request.queryParams("dataFabricacao")));
        	aluno.setDataValidade(LocalDate.parse(request.queryParams("dataValidade")));

        	DAO.atualizarAluno(aluno);
        	
            return matricula;
        } else {
            response.status(404); // 404 Not found
            return "Produto nÃ£o encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int matricula = Integer.parseInt(request.params(":matricula"));

        Aluno aluno = (Aluno) DAO.get(matricula);

        if (aluno != null) {

            DAO.excluirAluno(matricula);

            response.status(200); // success
        	return matricula;
        } else {
            response.status(404); // 404 Not found
            return "Produto nÃ£o encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<produtos type=\"array\">");
		for (Aluno aluno : DAO.getAll()) {
			returnValue.append("\n<produto>\n" + 
            		"\t<nome>" + aluno.getNome() + "</nome>\n" +
            		"\t<nome>" + aluno.getNome() + "</nome>\n" +
            		"\t<email>" + aluno.getEmail() + "</email>\n" +
            		"\t<curso>" + aluno.getCurso() + "</curso>\n" +
            		"\t<telefone>" + aluno.getTelefone() + "</telefone>\n" +
            		"</produto>\n");
		}
		returnValue.append("</alunos>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}
