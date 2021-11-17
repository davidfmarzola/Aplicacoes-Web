package com.ti2cc;

import java.util.*;
import model.Aluno;

public class Principal {
	public static Scanner sc = new Scanner(System.in);
	Aluno aluno = new Aluno();
	
	// Mostrar usuários
	public void listar(DAO dao) {
		System.out.println(" === Mostrar alunos === ");
		Aluno[] alunos = dao.getAlunos();
		if(alunos == null){
			System.out.println("Não há alunos cadastrados!");
			System.exit(0);
		}
		for (int i = 0; i < alunos.length; i++) {
			System.out.println(alunos[i].toString());
		}
	}
	
	// Inserir um elemento na tabela
	public void inserir(Aluno aluno, DAO dao) {
		if (dao.inserirAluno(aluno) == true) {
			System.out.println("Inserido com sucesso -> " + aluno.toString());
		}

	}

	public void excluir(Aluno aluno, DAO dao) {
		// Excluir usuï¿½rio
		dao.excluirAluno(aluno.getId());
	}	
	
	public void detalhar(Aluno aluno, DAO dao) {
		System.out.println(" === Mostrar aluno === ");
		if(aluno == null){
			System.out.println("Aluno não cadastrado!");
			System.exit(0);
		}
		System.out.println(aluno.toString());
	}
	
	// Atualizar usuário
	public void atualizar(Aluno aluno, DAO dao) {
		
		//valores setados como parâmetro
		dao.atualizarAluno(aluno);

	}
	
	//Encerrar o programa
	public static void sair() {
		System.exit(0);
	}
	
	public void chamadas(Aluno aluno, int opcao) {
		// TODO Auto-generated method stub
		DAO dao = new DAO();

		dao.conectar();
		
		//Menu para acessar os metódos da DAO
			//System.out.println("(1) Listar\n(2) Inserir\n(3) Excluir\n(4) Atualizar\n(5) Sair\n");
			switch (opcao) {
				case 1:
					listar(dao);
					break;
				case 2:
					inserir(aluno, dao);
					break;
				case 3:
					excluir(aluno, dao);
					break;
				case 4:
					atualizar(aluno, dao);
					break;
				case 5:
					detalhar(aluno, dao);
					break;
				//case 6:
					//sair();
					//break;
				default:
					//System.out.println("Valor inválido\nPor favor, entre com um número válido");
					break;
			}


		dao.close();
	}

}
