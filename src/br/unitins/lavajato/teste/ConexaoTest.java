package br.unitins.lavajato.teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoTest {

	public static void main(String[] args) {

		try {
			// Registrando driver do PostgreSQL
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		try {
			// Estabelecendo conexão com o banco
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/lavajato", "topicos1", "123456");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (conn == null) {
			System.out.println("Conexão falhou!");
			return;
		} else {
			System.out.println("Conexão realizada com sucesso!");
		}
		try {
			Statement stat = conn.createStatement();
			stat.execute("INSERT INTO carro(placa, categoria, modelo, marca)" + "VALUES ('KGB-1223', 1, 'GOL', 1)");
			conn.close();
			
			System.out.println("Inserção realicada com sucesso!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}