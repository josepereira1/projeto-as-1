package tradingsystem.business.trading;

import java.time.LocalDateTime;

public interface ICFD {
    /**
     *
     * @return
     */
    public String getId();


    /**
     *
     * @param id
     */
    public void setId(String id);

    /**
     *
     * @return
     */
    public String getIdAtivo();

    /**
     *
     * @param idAtivo
     */
    public void setIdAtivo(String idAtivo);

    /**
     *
     * @return
     */
    public int getTipo();

    /**
     *
     * @param tipo
     */
    public void setTipo(int tipo);

    /**
     *
     * @return
     */
    public String getUsername();

    /**
     *
     * @param username
     */
    public void setUsername(String username);

    /**
     *
     * @return
     */
    public float getStopLess();

    /**
     *
     * @param stopLess
     */
    public void setStopLess(float stopLess);

    /**
     *
     * @return
     */
    public float getTakeProfit();

    /**
     *
     * @param takeProfit
     */
    public void setTakeProfit(float takeProfit);

    /**
     *
     * @return
     */
    public LocalDateTime getDataExpiracao();

    /**
     *
     * @param dataExpiracao
     */
    public void setDataExpiracao(LocalDateTime dataExpiracao);

    /**
     *
     * @return
     */
    public LocalDateTime getDataAbertura();

    /**
     *
     * @param dataAbertura
     */
    public void setDataAbertura(LocalDateTime dataAbertura);

    /**
     *
     * @return
     */
    public LocalDateTime getDataEncerramento();

    /**
     *
     * @param dataEncerramento
     */
    public void setDataEncerramento(LocalDateTime dataEncerramento);

    /**
     *
     * @return
     */
    public int getNumeroDeAtivos();

    /**
     *
     * @param numeroDeAtivos
     */
    public void setNumeroDeAtivos(int numeroDeAtivos);

    /**
     *
     * @return
     */
    public float getValorInicial();

    /**
     *
     * @param valorInicial
     */
    public void setValorInicial(float valorInicial);

    /**
     *
     * @return
     */
    public float getValorInvestido();

    /**
     *
     * @param valorInvestido
     */
    public void setValorInvestido(float valorInvestido);
}