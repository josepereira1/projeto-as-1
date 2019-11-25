package tradingsystem.business.trading;

import java.time.LocalDateTime;

public interface ICFD {
    /**
     *  Return the id of CFD.
     * @return Return the id of CFD
     */
    public String getId();


    /**
     *  Set id of CFD.
     * @param id id of CFD
     */
    public void setId(String id);

    /**
     *  Return the id of stock.
     * @return Return the id of stock
     */
    public String getIdAtivo();

    /**
     *  Set id of stock.
     * @param idAtivo id of stock
     */
    public void setIdAtivo(String idAtivo);

    /**
     *  Return type of CFD (buy (0) or sell (1))
     * @return Return type of CFD (buy (0) or sell (1))
     */
    public int getTipo();

    /**
     *  Set type of CFD (buy (0) or sell (1))
     * @param tipo type of CFD (buy (0) or sell (1)
     */
    public void setTipo(int tipo);

    /**
     *  Return username of owner of this CFD.
     * @return Return username of owner of this CFD
     */
    public String getUsername();

    /**
     *  Set username of owner of this CFD.
     * @param username username of owner
     */
    public void setUsername(String username);

    /**
     *  Return the value of stop less.
     * @return Return the value of stop less
     */
    public float getStopLess();

    /**
     *  Set value of stop less.
     * @param stopLess value of stop less
     */
    public void setStopLess(float stopLess);

    /**
     *  Return the value of take profit.
     * @return Return the value of take profit
     */
    public float getTakeProfit();

    /**
     *  Set value of take profit.
     * @param takeProfit value of take profit
     */
    public void setTakeProfit(float takeProfit);

    /**
     *  Return open date of CFD.
     * @return Return open date of CFD
     */
    public LocalDateTime getDataAbertura();

    /**
     *  Set open date of CFD.
     * @param dataAbertura open date of CFD
     */
    public void setDataAbertura(LocalDateTime dataAbertura);

    /**
     *  Return end date of CFD
     * @return Return end date of CFD
     */
    public LocalDateTime getDataEncerramento();

    /**
     *  Set end date of CFD
     * @param dataEncerramento end date of CFD
     */
    public void setDataEncerramento(LocalDateTime dataEncerramento);

    /**
     *  Return number of stock.
     * @return Return number of stock
     */
    public int getNumeroDeAtivos();

    /**
     *  Set number of stock.
     * @param numeroDeAtivos number of stock
     */
    public void setNumeroDeAtivos(int numeroDeAtivos);

    /**
     *  Return initial value of CFD.
     * @return Return initial value of CFD
     */
    public float getValorInicial();

    /**
     *  Set initial value of CFD.
     * @param valorInicial initial value
     */
    public void setValorInicial(float valorInicial);

    /**
     *  Return invested amount of CFD.
     * @return Return invest value of CFD
     */
    public float getValorInvestido();

    /**
     *  Set invested amount of CFD
     * @param valorInvestido invested amount
     */
    public void setValorInvestido(float valorInvestido);
}