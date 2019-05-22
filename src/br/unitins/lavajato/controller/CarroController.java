package br.unitins.lavajato.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.lavajato.application.Util;
import br.unitins.lavajato.factory.ConnectionFactory;
import br.unitins.lavajato.model.Carro;
import br.unitins.lavajato.model.Categoria;
import br.unitins.lavajato.model.Marca;

@Named
@ViewScoped

public class CarroController implements Serializable {

	private static final long serialVersionUID = -1950413541038990627L;

	private Carro carro;

	// Método para incluir um carro.
	public void incluir() {
		Connection conn = ConnectionFactory.getInstance();

		if (conn == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return;
		}
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(
					"INSERT INTO carro (placa,  categoria, modelo, marca) " + "VALUES ( ?, ?, ?, ? )");

			stat.setString(1, getCarro().getPlaca());
			stat.setInt(2, getCarro().getCategoria().getValue());
			stat.setString(3, getCarro().getModelo());
			stat.setInt(4, getCarro().getMarca().getValue());

			stat.execute();

			limpar();

			Util.addMessageError("Cadastro realizado com sucesso!");

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

	public Marca[] getListaMarca() {
		return Marca.values();
	}

	public Categoria[] getListaCategoria() {
		return Categoria.values();
	}

	// Método para limpar.
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