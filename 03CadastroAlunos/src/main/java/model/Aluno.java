package model;

import java.io.Serializable;

public class Aluno implements Serializable{
	private static final long serialVersionUID = 440334707429183674L;
	// o que identifica o aluno no back-end é o id
	private int id;
	private int matricula;
	private String nome;
	private String email;
	private String curso;
	private int telefone;	

	public Aluno() {
		this.id = -1;
		this.matricula = 0;
		this.nome = "";
		this.email = "";
		this.curso = "";
		this.telefone = 0;
	}

	public Aluno(int id, int matricula, String nome, String email, String curso, int telefone) {
		this.id = id;
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.curso = curso;
		this.telefone = telefone;

	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "Aluno [id="+ id +", matricula=" + matricula + ", nome=" + nome + ", email=" + email + ", curso=" + curso
				+ ", telefone=" + telefone + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Aluno) obj).getId());
	}	
}
