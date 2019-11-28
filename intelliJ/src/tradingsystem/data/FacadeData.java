package tradingsystem.data;

import tradingsystem.business.AtorNotExistsException;
import tradingsystem.business.AtorTypeNotValidException;
import tradingsystem.business.CFDTypeNotValidException;
import tradingsystem.business.StockTypeNotValidException;
import tradingsystem.business.recursoshumanos.IAtor;
import tradingsystem.business.trading.IAtivo;
import tradingsystem.business.trading.ICFD;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FacadeData implements IFacadeData {

	private AtorDAO utilizadores;
	private AtivoDAO ativos;
	private CFDDAO cfds;
	private static IFacadeData data;

	private FacadeData() throws SQLException, ClassNotFoundException {
		this.utilizadores = new AtorDAO();
		this.ativos = new AtivoDAO();
		this.cfds = new CFDDAO();
	}

	//TODO copy documentation from inner classes to this class

	/**
	 * 
	 * @param username
	 */
	public IAtor getUtilizador(String username, String userType) throws SQLException, AtorTypeNotValidException {
		return this.utilizadores.get(username, userType);
	}

	public Collection<IAtivo> getAtivos() throws IOException, StockTypeNotValidException {
		return this.ativos.values();
	}

	/**
	 * 
	 * @param cfd
	 */
	public Future<ICFD> putCFD(ICFD cfd) {
		return 	this.cfds.put(cfd);
	}

	/**
	 * 
	 * @param id
	 */
	public void removeCFD(String id) {
		this.cfds.remove(id);
	}

	/**
	 * 
	 * @param utilizador
	 */
	public void putUtilizador(IAtor utilizador) throws SQLException, AtorTypeNotValidException {
		this.utilizadores.put(utilizador);
	}

	/**
	 * 
	 * @param id
	 */
	public float getValorAtualAtivo(String id, int typeOfCFD) throws IOException, CFDTypeNotValidException {
		return this.ativos.getValorAtual(id, typeOfCFD);
	}

	/**
	 * @param id
	 * @param TP
	 * @param SL
	 * @return
	 */
	public Future<Void> setCFDlimits(String id, float TP, float SL) {
		return this.cfds.setLimits(TP, SL, id);
	}

	/**
	 * 
	 * @param username
	 * @param valor
	 */
	public void addFundos(String username, float valor) throws SQLException {
		this.utilizadores.addFundos(username, valor);
	}

	/**
	 * 
	 * @param id
	 */
	public Future<ICFD> getCFD(String id) {
		return this.cfds.get(id);
	}

	/**
	 *
	 * @param username
	 * @return
	 */
	public Future<Collection<ICFD>> getCFDs(String username) {
		return this.cfds.getCFDs(username);
	}

	/**
	 *
	 * @param username
	 * @return
	 */
	public Future<Collection<String>> getCFDsIdsOpen(String username) {
		return this.cfds.getCFDsIdsOpen(username);
	}

	/**
	 * 
	 * @param idCFD
	 */
	public Future<Float> getTakeProfit(String idCFD) {
		return this.cfds.getTakeProfit(idCFD);
	}

	/**
	 * 
	 * @param idCFD
	 */
	public Future<Float> getStopLess(String idCFD) {
		return this.cfds.getStopLess(idCFD);
	}

	public static IFacadeData getInstance() throws SQLException, ClassNotFoundException {
		if (data == null) data = new FacadeData();
		return data;
	}

	/**
	 * 
	 * @param idCFD
	 */
	public Future<Float> getValorInvestidoCFD(String idCFD) {
		return this.cfds.getValorInvestidoCFD(idCFD);
	}

	public Future<Integer> getNumeroDeAtivosCFD(String idCFD) {
		return this.cfds.getNumeroDeAtivosCFD(idCFD);
	}

	/**
	 * 
	 * @param username
	 */
	public boolean containsUtilizador(String username, String userType) throws SQLException, AtorTypeNotValidException {
		return this.utilizadores.contains(username, userType);
	}

	/**
	 * 
	 * @param idCFD
	 */
	public Future<Boolean> containsCFD(String idCFD) {
		return this.cfds.contains(idCFD);
	}

	public Future<String> getNextId(){
		return cfds.getNextId();
	}

	public boolean containsAtivo(String id) throws IOException {
		return ativos.contains(id);
	}

	public Future<Void> updateEndDateCFD(String id, LocalDateTime endDate){
		return cfds.updateEndDateCFD(id,endDate);
	}

	public Future<String> getIdAtivoDoCFD(String idCFD){
		return cfds.getIdAtivoDoCFD(idCFD);
	}

	public Future<Integer> getTipoCFD(String id){
		return cfds.getTipoCFD(id);
	}

	public float getPlafond(String username) throws SQLException, AtorNotExistsException{
		return utilizadores.getPlafond(username);
	}

}