//package dao;

import Aluno;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
	private List<Aluno> alunos;
	private int maxMatricula = 0;

	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public int getMaxMatricula() {
		return maxMatricula;
	}

	public AlunoDAO(String filename) throws IOException {
		file = new File(filename);
		alunos = new ArrayList<Aluno>();
		if (file.exists()) {
			readFromFile();
		}

	}

	public void add(Aluno aluno) {
		try {
			alunos.add(aluno);
			this.maxMatricula = (aluno.getMatricula() > this.maxMatricula) ? aluno.getMatricula() : this.maxMatricula;
			this.saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o produto '" + aluno.getDescricao() + "' no disco!");
		}
	}

	public Aluno get(int id) {
		for (Aluno aluno : alunos) {
			if (id == aluno.getId()) {
				return aluno;
			}
		}
		return null;
	}

	public void update(Aluno p) {
		int index = alunos.indexOf(p);
		if (index != -1) {
			alunos.set(index, p);
			this.saveToFile();
		}
	}

	public void remove(Aluno p) {
		int index = alunos.indexOf(p);
		if (index != -1) {
			alunos.remove(index);
			this.saveToFile();
		}
	}

	public List<Aluno> getAll() {
		return alunos;
	}

	private List<Aluno> readFromFile() {
		alunos.clear();
		Aluno aluno = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				aluno = (Aluno) inputFile.readObject();
				alunos.add(aluno);
				maxMatricula = (aluno.getMatricula() > maxMatricula) ? aluno.getMatricula() : maxMatricula;
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar produto no disco!");
			e.printStackTrace();
		}
		return alunos;
	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Aluno aluno : alunos) {
				outputFile.writeObject(aluno);
			}
			outputFile.flush();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar produto no disco!");
			e.printStackTrace();
		}
	}

	private void close() throws IOException {
		outputFile.close();
		fos.close();
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			this.saveToFile();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao salvar a base de dados no disco!");
			e.printStackTrace();
		}
	}
}
