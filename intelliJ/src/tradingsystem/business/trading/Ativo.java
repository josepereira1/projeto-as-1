package tradingsystem.business.trading;

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

	/**
	 * 
	 * @param o
	 */
	public boolean equals(Object o) {
		// TODO - implement Ativo.equals
		throw new UnsupportedOperationException();
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