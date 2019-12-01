package tradingsystem.business.trading;

public interface IAtivo {

    //TODO acrescentar ao diagrama de classes os gets() e sets(), tanto à interface com à classe (override)

    /**
     * Set id of stock.
     * @param id id of stock
     */
    void setId(String id);

    /**
     * Returns id of stock.
     */
    String getId();

    /**
     * Return
     * @param designacao
     */
    void setDesignacao(String designacao);

    String getDesignacao();

    void setValorVenda(float valorVenda);

    float getValorVenda();

    void setValorCompra(float valorCompra);

    float getValorCompra();
}