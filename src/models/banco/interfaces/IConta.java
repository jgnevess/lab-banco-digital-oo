package models.banco.interfaces;

public interface IConta {
	
	void sacar(double valor, int hora);
	
	void depositar(double valor);
	
	void transferir(double valor, int hora, IConta contaDestino);
	
	void imprimirExtrato();
	
	void definirLimiteSaque(double limite);
}
