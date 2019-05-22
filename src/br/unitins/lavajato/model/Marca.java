package br.unitins.lavajato.model;

public enum Marca {

	VOLKSWAGEN(1, "Volkswagen"), CHEVROLET(2, "Chevrolet"), FIAT(3, "Fiat"), FORD(4, "Ford"), HONDA(5, "Honda");

	private int value;
	private String label;

	private Marca(int value, String label) {
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