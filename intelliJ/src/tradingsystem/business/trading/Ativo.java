package tradingsystem.business.trading;

import java.util.Objects;

public class Ativo implements IAtivo {

	public String id;
	public String designacao;
	public float valorVenda;
	public float valorCompra;

	@Override
	public String toString() {
		return "Ativo{" +
				"id='" + id + '\'' +
				", designacao='" + designacao + '\'' +
				", valorVenda=" + valorVenda +
				", valorCompra=" + valorCompra +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ativo ativo = (Ativo) o;
		return Float.compare(ativo.valorVenda, valorVenda) == 0 &&
				Float.compare(ativo.valorCompra, valorCompra) == 0 &&
				Objects.equals(id, ativo.id) &&
				Objects.equals(designacao, ativo.designacao);
	}

	public Ativo clone() {
		// TODO - implement Ativo.clone
		throw new UnsupportedOperationException();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getDesignacao() {
		return designacao;
	}

	@Override
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	@Override
	public float getValorVenda() {
		return valorVenda;
	}

	@Override
	public void setValorVenda(float valorVenda) {
		this.valorVenda = valorVenda;
	}

	@Override
	public float getValorCompra() {
		return valorCompra;
	}

	@Override
	public void setValorCompra(float valorCompra) {
		this.valorCompra = valorCompra;
	}
}