package com.valdir.os.enuns;

public enum Status {
	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

	private Integer cod;
	private String descricao;

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	private Status(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public static Status toEnum(Integer cod) {
		if (cod != null) {
			for (Status x : Status.values()) {
				if (cod.equals(x.getCod())) {
					return x;
				}
			}
		}
		throw new IllegalArgumentException("Status inválido! " + cod);
	}
}
