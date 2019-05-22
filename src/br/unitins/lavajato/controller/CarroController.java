package br.unitins.lavajato.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.lavajato.application.Util;
import br.unitins.lavajato.dao.CarroDAO;
import br.unitins.lavajato.factory.ConnectionFactory;
import br.unitins.lavajato.model.Carro;
import br.unitins.lavajato.model.Categoria;
import br.unitins.lavajato.model.Marca;

@Named
@ViewScoped

public class CarroController implements Serializable {

	private static final long serialVersionUID = -1950413541038990627L;

	private Carro carro;

	private List<Carro> listaCarro = null;

	public List<Carro> getListaCarro() {
		if (listaCarro == null) {
			listaCarro = new ArrayList<Carro>();
			Connection conn = ConnectionFactory.getInstance();
			PreparedStatement stat = null;
			if (conn == null) {
				Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			} else {
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
				} finally {
					try {
						stat.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return listaCarro;
	}

	public void editar(Carro carro) {
		// setCarro((Usuario)usuario.getClone());
	}

	// M�todo para incluir um carro.
	public void incluir() {
		CarroDAO dao = new CarroDAO();
		if (dao.create(getCarro())) {
			limpar();
			// Para atualizar o dataTable
			listaCarro = null;
		}
		dao.closeConnection();
	}

	public Marca[] getListaMarca() {
		return Marca.values();
	}

	public Categoria[] getListaCategoria() {
		return Categoria.values();
	}

	// M�todo para limpar.
	public void limpar() {
		carro = null;
	}

	public Carro getCarro() {
		if (carro == null) {
			carro = new Carro();
		}
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}
}