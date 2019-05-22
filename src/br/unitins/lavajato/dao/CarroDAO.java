package br.unitins.lavajato.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.lavajato.application.Util;
import br.unitins.lavajato.factory.ConnectionFactory;
import br.unitins.lavajato.model.Carro;
import br.unitins.lavajato.model.Categoria;
import br.unitins.lavajato.model.Marca;

public class CarroDAO implements DAO<Carro> {

	private Connection conn = null;

	private Connection getConnection() {
		if (conn == null) {
			conn = ConnectionFactory.getInstance();
		}
		return conn;
	}

	public void closeConnection() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean create(Carro obj) {
		boolean resultado = false;

		// Verificando se tem uma conexão válida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement(
					"INSERT INTO carro (placa,  categoria, modelo, marca) " + "VALUES ( ?, ?, ?, ? )");

			stat.setString(1, obj.getPlaca());
			stat.setInt(2, obj.getCategoria().getValue());
			stat.setString(3, obj.getModelo());
			stat.setInt(4, obj.getMarca().getValue());

			stat.execute();
			Util.addMessageError("Cadastro realizado com sucesso!");
			resultado = true;

		} catch (SQLException e) {
			Util.addMessageError("Falha ao incuir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public boolean update(Carro obj) {
		return false;
	}

	@Override
	public boolean delete(Carro obj) {
		return false;
	}

	@Override
	public Carro findById(int obj) {
		return null;
	}

	@Override
	public List<Carro> findAll(Carro obj) {
		// Verificando se tem uma conexão válida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}

		List<Carro> listaCarro = new ArrayList<Carro>();

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement("SELECT * FROM Carro ORDER BY modelo ASC");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Carro c = new Carro();
				c.setId(rs.getInt("id"));
				c.setPlaca(rs.getString("placa"));
				c.setModelo(rs.getString("modelo"));
				c.setCategoria(Categoria.valueOf(rs.getInt("categoria")));
				c.setMarca(Marca.valueOf(rs.getInt("marca")));

				listaCarro.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaCarro = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return listaCarro;
	}
}