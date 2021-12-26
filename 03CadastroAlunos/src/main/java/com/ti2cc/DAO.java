package com.ti2cc;

import java.sql.*;
import model.Aluno;

public class DAO {
	private Connection conexao;

	public DAO() {
		conexao = null;
	}

	public boolean conectar() {
		//tabela aluno da base de dados postgres
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "postgres";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conex�o efetuada com o postgres!");
		} catch (ClassNotFoundException e) {
			System.err.println("Conex�o N�O efetuada com o postgres -- Driver n�o encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conex�o N�O efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}

	public boolean close() {
		boolean status = false;

		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	public boolean inserirAluno(Aluno aluno) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO aluno(id, matricula, nome, email, curso, telefone)" + "VALUES("
					+ aluno.getId() + ", '" + aluno.getMatricula() + "', '" + aluno.getNome() + "', '" + aluno.getEmail() + "', '"
					+ aluno.getCurso() + "', " + aluno.getTelefone() + " );");
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarAluno(Aluno aluno) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE aluno SET matricula = " + aluno.getMatricula() + ", nome = '" + aluno.getNome()
					+ "', email = '" + aluno.getEmail() + "'" + ", curso = '" + aluno.getCurso() + "' WHERE id = "
					+ aluno.getId() + ";";
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirAluno(int id) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM aluno WHERE id = " +id+";");
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public Aluno[] getAlunos() {
		Aluno[] alunos = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM aluno;");
			if (rs.next()) {
				rs.last();
				alunos = new Aluno[rs.getRow()];
				rs.beforeFirst();

				for (int i = 0; rs.next(); i++) {
					alunos[i] = new Aluno(rs.getInt("id"), rs.getInt("matricula"), rs.getString("nome"), rs.getString("email"),
							rs.getString("curso"), rs.getInt("telefone"));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return alunos;
	}

	public Aluno[] getAlunosCurso() {
		Aluno[] aluno = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM aluno WHERE aluno.curso LIKE 'ciencia da computacao'; ");
			if (rs.next()) {
				rs.last();
				aluno = new Aluno[rs.getRow()];
				rs.beforeFirst();

				for (int i = 0; rs.next(); i++) {
					aluno[i] = new Aluno(rs.getInt("id"), rs.getInt("matricula"), rs.getString("nome"), rs.getString("email"),
							rs.getString("curso"), rs.getInt("telefone"));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return aluno;
	}
}
