package br.unitins.lavajato.controller;

import java.io.Serializable;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.lavajato.dao.CarroDAO;
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
		}
		return listaCarro;
	}

	public void editar(Carro carro) {
		// setCarro((Usuario)usuario.getClone());
	}

	// Método para incluir um carro.
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