package tradingsystem.business.trading;

public interface IAtivo {

    //TODO acrescentar ao diagrama de classes os gets() e sets(), tanto à interface com à classe (override)

    void setId(String id);
    String getId();

    void setDesignacao(String designacao);
    String getDesignacao();

    void setValorVenda(float valorVenda);
    float getValorVenda();

    void setValorCompra(float valorCompra);
    float getValorCompra();
}