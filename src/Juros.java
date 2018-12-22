/************************************************************************/
/** ACH2001 - Introducão a Ciência da Computacão I						*/
/** EACH-USP - Primeiro Semestre de 2014								*/
/** <SI Matutino> - <Fábio Nakano>										*/
/**																		*/
/** Segundo Exercício-Programa											*/
/** Arquivo: <Juros.java>												*/
/**																		*/
/** <Matheus Mendes de Sant'Ana> <8921666>								*/
/**																		*/
/** <27/07/2014>														*/
/************************************************************************/

import lista.ListaDepositos;
import depositos.Deposito;
import lista.No;
import depositos.Data;
import lista.ListaSimples;

class Juros {
	// O metodo seguinte define a funcao e encontra sua derivada
	static double funcoes(ListaDepositos ll, Deposito total, double taxa) {
		No p = ll.getInicio();
		double derivas = 0;	// Armazena o valor da derivada
		double funcao = 0;	// Armazena o valor da funcao
		while(p != null) {
			Deposito r = p.getDeposito();	// Variavel utilizada para simplificar o codigo que calcula a funcao e a derivada
			funcao += r.getValor()*Math.pow(taxa+1, r.getData().mesesEntre(total.getData()));	/*	Calcula o valor do deposito de "p"
															e multiplica-o pelo valor deduzido "taxa" + 1
															elevado à potencia de meses entre
															o deposito de "p" e o deposito "total"	*/
			derivas += (double)r.getData().mesesEntre(total.getData()) * r.getValor() * Math.pow	// Calcula a derivada da funcao definida
				(taxa+1,r.getData().mesesEntre(total.getData())-1);
			p = p.getProx();
		}
		funcao -= total.getValor();	// Operacao de subtracao da funcao pelo saldo final que tem sua referencia em total.getValor()
		return funcao / derivas;
	}
	
	/*Calcula os juros pagos, a partir de uma seqüência de depósitos e um saldo final, com a precisão indicada. Usa o método de Newton-Raphson para tal.
	@param depositos Lista de depósitos (não incluindo o saldo final)
	@param saldo Saldo final da aplicação
	@param epsilon Precisão do cálculo (0 < epsilon < 1)
	@return Valor dos juros, ou NAN em caso de erro (epsilon fora dos limites, lista de depósitos sem depósitos, saldo null)
	*/
	
	public static double juros(ListaDepositos depositos, Deposito saldo, double epsilon) {
		// Return NAN no caso dos erros seguintes
		if(depositos == null || depositos.getInicio() == null || saldo == null || epsilon >= 1 || epsilon <= 0) {
			return 0.0 / 0.0;
		}
		double k = 0.5;	// Valor double pequeno para começar o processo de aproximacao
		double auxiliar = k-funcoes(depositos, saldo, k);	/*	Comeca o processo de aproximacao pela
										chamada do metodo funcao que retorna
										(funcao/derivada) */
		while(Math.abs(k-auxiliar) >= epsilon) {	// Calcula o valor absoluto da diferenca e recalcula o valor de k se a aproximacao nao for suficiente
			k = auxiliar;
			auxiliar = k - funcoes(depositos, saldo, k);
		}
		return auxiliar; // Retorna o valor da taxa de juros quando a aproximacao é suficiente
	}
	public static void main(String[] args) {
	}
}
