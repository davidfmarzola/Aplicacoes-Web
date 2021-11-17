package service;
//acessando metódos de acesso ao banco de dados
import com.ti2cc.*;

import java.io.IOException;

import dao.AlunoDAO;
import model.Aluno;
import spark.Request;
import spark.Response;
public class AlunoService {

	private AlunoDAO AlunoDAO;
	// Objeto principal inserido
	private Principal principal;
	
	public AlunoService() {
		//acesso fake ao banco de dados
		try {
			AlunoDAO = new AlunoDAO("aluno.dat");
			principal = new Principal();
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

			int id = AlunoDAO.getMaxId() + 1;

			Aluno aluno = new Aluno(id, matricula, nome, email, curso, telefone);

			AlunoDAO.add(aluno);
			//inserindo aluno no banco de dados
			principal.chamadas(aluno, 2);

			response.status(201); // 201 Created
			return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Aluno aluno = (Aluno) AlunoDAO.get(id);
		principal.chamadas(aluno, 5);
		
		if (aluno != null) {
			
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<aluno>\n" + 
                    "\t<id>" + aluno.getId() + "</id>\n" +
            		"\t<matricula>" + aluno.getMatricula() + "</matricula>\n" +
            		"\t<nome>" + aluno.getNome() + "</nome>\n" +
            		"\t<email>" + aluno.getEmail() + "</email>\n" +
            		"\t<curso>" + aluno.getCurso() + "</curso>\n" +
            		"\t<telefone>" + aluno.getTelefone() + "</telefone>\n" +
            		"</aluno>\n";
            
            
        } else {
            response.status(404); // 404 Not found
            return "Aluno " + id + " nÃ£o encontrado.";
        }

	}

	public Object update(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
        
		Aluno aluno = (Aluno) AlunoDAO.get(id);
		
        if (aluno != null) {
        	aluno.setMatricula(Integer.parseInt(request.queryParams("matricula")));
        	aluno.setNome(request.queryParams("nome"));
        	aluno.setEmail(request.queryParams("email"));
        	aluno.setCurso(request.queryParams("curso"));
        	aluno.setTelefone(Integer.parseInt(request.queryParams("telefone")));

        	AlunoDAO.update(aluno);
        	//atualiza no banco de dados
        	principal.chamadas(aluno, 4);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Aluno nÃ£o encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));

        Aluno aluno = (Aluno) AlunoDAO.get(id);

        if (aluno != null) {
        	
            AlunoDAO.remove(aluno);
            
            // removendo aluno do banco de dados 
            principal.chamadas(aluno, 3);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Aluno nÃ£o encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		// listando todos os alunos do banco de dados
		Aluno _aluno = new Aluno();
		principal.chamadas(_aluno, 1);
		
		StringBuffer returnValue = new StringBuffer("<alunos type=\"array\">");
		for (Aluno aluno : AlunoDAO.getAll()) {
			returnValue.append("\n<aluno>\n" + 
					"\t<id>" + aluno.getId() + "</id>\n" +
            		"\t<matricula>" + aluno.getMatricula() + "</matricula>\n" +
            		"\t<nome>" + aluno.getNome() + "</nome>\n" +
            		"\t<email>" + aluno.getEmail() + "</email>\n" +
            		"\t<curso>" + aluno.getCurso() + "</curso>\n" +
            		"\t<telefone>" + aluno.getTelefone() + "</telefone>\n" +
            		"</aluno>\n");
		}
		returnValue.append("</alunos>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
		
	}
	
}
