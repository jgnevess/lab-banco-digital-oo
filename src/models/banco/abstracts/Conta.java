package models.banco.abstracts;
import exceptions.HoraSaqueException;
import lombok.Getter;
import lombok.Setter;
import models.banco.interfaces.IConta;
import models.cliente.Cliente;

@Getter
@Setter
public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;
	private static final double VALOR_MINIMO_SAQUE = 10.0;
	private static final double LIMITE_SAQUE = 200.0;
	
	protected int agencia;
	protected int numero;
	protected double saldo;
	protected double chequeEspecial;
	protected double valorMinimoSaque;
	protected Cliente cliente;
	protected double limiteSaqueTransferencia;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
		this.chequeEspecial = 0;
		this.valorMinimoSaque = Conta.VALOR_MINIMO_SAQUE;
		this.limiteSaqueTransferencia = Conta.LIMITE_SAQUE;
	}

	@Override
	public abstract void sacar(double valor, int hora);
	
	protected abstract void setLimiteDeConta(double limite);
	
	private void subtrairDinheiroDaConta(double valor, int hora) {
		if(hora < 0 || hora > 23) {
			throw new HoraSaqueException();
		}
		if(hora > 20 && hora < 23 && valor >= this.limiteSaqueTransferencia) {
			System.out.println("Operação não realizada");
			System.out.printf("Seu limite de saque e transferencia após as 20:00 é de %.2f\n", this.limiteSaqueTransferencia);
		}
		if(saldo - valor < this.chequeEspecial) {
			System.out.println("Operação não realizada");
			System.out.printf("Seu limite de cheque especial é de %.2f\n", this.chequeEspecial);
			System.out.println("Seu saldo não pode exceder esse valor!");
		}
		else {
			saldo -= valor;			
		}
	}
	
	protected void subtrairDinheiroDaConta(double valor, double taxa, int hora) {
		if(hora < 0 || hora > 23) {
			throw new HoraSaqueException();
		}
		if(hora > 20 && hora < 23 && valor >= this.limiteSaqueTransferencia) {
			System.out.println("Operação não realizada");
			System.out.printf("Seu limite de saque e transferencia após as 20:00 é de %.2f\n", this.limiteSaqueTransferencia);
		}
		if(saldo - (valor + (valor * taxa)) < this.chequeEspecial) {
			System.out.println("Operação não realizada");
			System.out.printf("Seu limite de conta é de %.2f\n", this.chequeEspecial);
			System.out.println("Seu saldo não pode exceder esse valor!");
		}
		else {
			saldo -= (valor + (valor * taxa));			
		}
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
	}

	@Override
	public void transferir(double valor, int hora ,IConta contaDestino) {
		this.subtrairDinheiroDaConta(valor, hora);
		contaDestino.depositar(valor);
	}
	
	@Override
	public void definirLimiteSaque(double limite) {
		this.limiteSaqueTransferencia = limite;
		
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
}
