package models.banco;

import models.banco.abstracts.Conta;
import models.cliente.Cliente;

public class ContaPoupanca extends Conta {

	private final double TAXA_SAQUE = 0.03;
	
	public ContaPoupanca(Cliente cliente) {
		super(cliente);
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Poupança ===");
		super.imprimirInfosComuns();
	}

	@Override
	public void sacar(double valor, int hora) {
		if(saldo - (valor + (valor * this.TAXA_SAQUE)) < 0) {
			System.out.println("== Operação não realizada ==\n");
			System.out.println("Operação não realizada");
			System.out.println("A conta poupança não pode ter um saldo negativo\n");
			this.imprimirExtrato();
			return;
		}
		saldo -= (valor + (valor * this.TAXA_SAQUE));
	}

	protected void setLimiteDeConta(double limite) {
		this.chequeEspecial = 0;
	}
}
