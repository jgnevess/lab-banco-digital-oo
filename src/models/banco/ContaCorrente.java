package models.banco;

import models.banco.abstracts.Conta;
import models.cliente.Cliente;

public class ContaCorrente extends Conta {
	
	private final double TAXA_SAQUE = 0.05;

	public ContaCorrente(Cliente cliente) {
		super(cliente);
	}
	
	@Override
	public void sacar(double valor, int hora) {
		super.subtrairDinheiroDaConta(valor, this.TAXA_SAQUE, hora);
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Corrente ===");
		super.imprimirInfosComuns();
	}
	
	@Override
	protected void setLimiteDeConta(double limite) {
		if(limite > 0) {
			limite = limite * -1;			
		}
		this.chequeEspecial = limite;
	}
	
	public void definirLimite(double limite) {
		this.setLimiteDeConta(limite);
	}
}
