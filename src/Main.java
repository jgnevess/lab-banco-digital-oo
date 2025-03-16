import models.banco.ContaCorrente;
import models.banco.ContaPoupanca;
import models.banco.abstracts.Conta;
import models.cliente.Cliente;

public class Main {

	public static void main(String[] args) {
		Cliente venilton = new Cliente();
		venilton.setNome("Venilton");
		
		Conta cc = new ContaCorrente(venilton);
		Conta poupanca = new ContaPoupanca(venilton);

		cc.depositar(1000);
		cc.transferir(100, 22, poupanca);
		
		
		
//		cc.imprimirExtrato();
//		poupanca.imprimirExtrato();
		
		Cliente joao = new Cliente();
		joao.setNome("Joao");
		
		ContaCorrente cc2 = new ContaCorrente(joao);
		Conta poupanca2 = new ContaPoupanca(joao);

		cc2.imprimirExtrato();
		cc2.depositar(1000);
		cc2.definirLimite(-100);
//		cc2.transferir(900, 22, poupanca2);
		
		
//		cc2.sacar(300, 22);
		
//		cc.transferir(10, 2, cc2);
		
//		poupanca2.sacar(1500, 18);
		cc2.imprimirExtrato();
		poupanca2.imprimirExtrato();
	}

}
