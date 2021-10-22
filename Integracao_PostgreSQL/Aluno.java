package com.ti2cc;

public class Aluno {
	private int matricula;
	private String nome;
	private String email;
	private String curso;
	private int telefone;

	public Aluno() {
		this.matricula = 0;
		this.nome = "";
		this.email = "";
		this.curso = "";
		this.telefone = 0;
	}

	public Aluno(int matricula, String nome, String email, String curso, int telefone) {
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.curso = curso;
		this.telefone = telefone;

	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", email=" + email + ", curso=" + curso
				+ ", telefone=" + telefone + "]";
	}

}
