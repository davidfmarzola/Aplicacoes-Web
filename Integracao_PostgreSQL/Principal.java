package com.ti2cc;

import java.util.*;

public class Principal {
	public static Scanner sc = new Scanner(System.in);
	
	//leitura dos dados a serem atualizados ou atualizados
	public static String[] leitura(){
		//esvazia o buffer do teclado
		sc.nextLine();
		System.out.println("Telefone do aluno: ");
		String telefone = sc.nextLine();
		System.out.println("Matrícula: ");
		String matricula = sc.nextLine();
		System.out.println("Nome: ");
		String nome = sc.nextLine();
		System.out.println("Email: ");
		String email = sc.nextLine();
		System.out.println("Curso: ");
		String curso = sc.nextLine();
		String[]vetor = {telefone, matricula, nome, email, curso};
		
		return vetor;
	}
	
	//setando os dados de entrada
	public static Aluno sets(String[] vetor) {
		Aluno aluno = new Aluno();
		int telefone = Integer.parseInt(vetor[0]);
		int matricula = Integer.parseInt(vetor[1]);
		aluno.setTelefone(telefone);
		aluno.setMatricula(matricula);
		aluno.setNome(vetor[2]);
		aluno.setEmail(vetor[3]);
		aluno.setCurso(vetor[4]);
		
		return aluno;
	}
	
	// Mostrar usuários
	public static void listar(DAO dao) {
		System.out.println("==== Mostrar alunos === ");
		Aluno[] alunos = dao.getAlunos();
		for (int i = 0; i < alunos.length; i++) {
			System.out.println(alunos[i].toString());
		}
	}
	
	// Inserir um elemento na tabela
	public static void inserir(DAO dao) {
		Aluno aluno = sets(leitura());
		if (dao.inserirAluno(aluno) == true) {
			System.out.println("Inserido com sucesso -> " + aluno.toString());
		}

	}

	public static void excluir(DAO dao) {
		// Excluir usuï¿½rio
		Aluno aluno = new Aluno();
		System.out.println("Matrícula a ser excluída: ");
		int matricula = sc.nextInt();
		aluno.setMatricula(matricula);
		dao.excluirAluno(aluno.getMatricula());
	}	
	
	// Atualizar usuário
	public static void atualizar(DAO dao) {
		
		//valores setados como parâmetro
		dao.atualizarAluno(sets(leitura()));

	}
	
	//Encerrar o programa
	public static void sair() {
		System.exit(0);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DAO dao = new DAO();

		dao.conectar();
		
		//Menu para acessar os metódos da DAO
		int opcao = 0;
		while (opcao != 5) {
			System.out.println("(1) Listar\n(2) Inserir\n(3) Excluir\n(4) Atualizar\n(5) Sair\n");
			opcao = sc.nextInt();
			switch (opcao) {
				case 1:
					listar(dao);
					break;
				case 2:
					inserir(dao);
					break;
				case 3:
					excluir(dao);
					break;
				case 4:
					atualizar(dao);
					break;
				case 5:
					sair();
					break;
				default:
					System.out.println("Valor inválido\nPor favor, entre com um número válido");
					break;
			}

		}

		dao.close();
	}

}
