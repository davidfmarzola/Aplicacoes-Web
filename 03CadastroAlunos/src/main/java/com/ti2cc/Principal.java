package com.ti2cc;

import java.util.*;
import model.Aluno;

public class Principal {
	public static Scanner sc = new Scanner(System.in);
	Aluno aluno = new Aluno();
	
	// Mostrar usu�rios
	public void listar(DAO dao) {
		System.out.println(" === Mostrar aluno(s) === ");
		Aluno[] alunos = dao.getAlunos();
		if(alunos == null){
			System.out.println("N�o h� alunos cadastrados!");
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
		// Excluir usu�rio
		//dao.excluirAluno(aluno.getId());
		if (dao.excluirAluno(aluno.getId()) == true) {
			System.out.println("Excluido com sucesso -> " + aluno.toString());
		}
	}	
	
	public void consultar(Aluno aluno, DAO dao) {
		System.out.println(" === Consultar aluno === ");
		if(aluno == null){
			System.out.println("Aluno n�o cadastrado!");
			System.exit(0);
		}
		System.out.println(aluno.toString());
	}
	
	// Atualizar usu�rio
	public void atualizar(Aluno aluno, DAO dao) {
		
		//valores setados como par�metro
		//dao.atualizarAluno(aluno);
		if (dao.atualizarAluno(aluno) == true) {
			System.out.println("Atualizado com sucesso -> " + aluno.toString());
		}

	}
	
	//Encerrar o programa
	public static void sair() {
		System.exit(0);
	}
	
	public void chamadas(Aluno aluno, int opcao) {
		// TODO Auto-generated method stub
		DAO dao = new DAO();

		dao.conectar();
		
		//Menu para acessar os met�dos da DAO
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
					consultar(aluno, dao);
					break;
				//case 6:
					//sair();
					//break;
				default:
					//System.out.println("Valor inv�lido\nPor favor, entre com um n�mero v�lido");
					break;
			}


		dao.close();
	}

}
