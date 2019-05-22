package br.unitins.lavajato.model;

public enum Categoria {

	CARRO(1, "Carro"), SUV(2, "Suv"), CAMINHONETE(3, "Caminhonete"), MOTO(4, "Moto"), CAMINHAO(5, "Caminhão");

	private int value;
	private String label;

	private Categoria(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
}