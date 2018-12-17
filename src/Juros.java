/*********************************************************************/
/** ACH2001 - Introducão a Ciência da Computacão I 					**/
/** EACH-USP - Primeiro Semestre de 2014 							**/
/** <SI Matutino> - <Fábio Nakano> 									**/
/** 																**/
/** Segundo Exercício-Programa 										**/
/** Arquivo: <Juros.java> 											**/
/** 																**/
/** <Matheus Mendes de Sant'Ana> <8921666> 							**/
/** 																**/
/** <27/07/2014> 													**/
/*********************************************************************/
import lista.ListaDepositos;
import depositos.Deposito;
import lista.No;
import depositos.Data;
import lista.ListaSimples;

class Juros {
	//o metodo seguinte define a funcao e encontra sua derivada
	static double funcoes(ListaDepositos ll,Deposito total,double taxa) {
		No p=ll.getInicio();
		double derivas=0;	//armazena o valor da derivada
		double funcao=0;	//armazena o valor da funcao
		while(p!=null) {
			Deposito r=p.getDeposito();	// variavel utilizada para simplificar o codigo*
										//*que calcula a funcao e a derivada
			//a variavel funcao define como sera a funcao 
			funcao+=r.getValor()*Math.pow(taxa+1,r.getData().mesesEntre(total.getData()));//calcula*
																		//*o valor do deposito de p*
																		//*e multiplica-o pelo valor* 
																		//*deduzido "taxa" + 1 elevado*
																		//*à potencia de meses entre*
																		//*o deposito de "p" e o* 
																		//*deposito de "total"
																		
			//a variavel derivas calcula a derivada da funcao definida
			derivas+=(double)r.getData().mesesEntre(total.getData())*r.getValor()*Math.pow
			(taxa+1,r.getData().mesesEntre(total.getData())-1);	//calcula os meses entre o deposito*
																//*de "p" e o deposito "total"* 
																//*e multiplica esse valor pelo*
																//*valor deduzido "taxa" elevado à*
																//*potencia de meses entre o deposito*
																//*de"p" e* o deposito "total"-1
															
			p=p.getProx();
		}
		funcao-=total.getValor();	//operacao de subtracao da funcao pelo saldo final que tem sua*
									//*referencia em total.getValor()	
		return funcao/derivas;	
	}
		/*Calcula os juros pagos, a partir de uma seqüência de depósitos e um saldo final, com a precisão indicada. Usa o método de Newton-Raphson para tal.									
		@param depositos Lista de depósitos (não incluindo o saldo final)
		@param saldo Saldo final da aplicação
		@param epsilon Precisão do cálculo (0 < epsilon < 1)
		
		@return Valor dos juros, ou NAN em caso de erro (epsilon fora dos limites, lista de depósitos sem depósitos, saldo null)
	*/
	public static double juros(ListaDepositos depositos, Deposito saldo, double epsilon) {
		//return NAN no caso dos erros seguintes
		if(depositos==null || depositos.getInicio()==null || saldo==null || epsilon>=1 || epsilon<=0) {
			return 0.0/0.0;
		}
		double k=0.5;	//valor double pequeno para começar o processo de aproximacao
		double auxiliar= k-funcoes(depositos,saldo,k);	//comeca o processo de aproximacao pela*
														//*chamada do metodo funcao que retorna*
														//*(funcao/derivada)
		while(Math.abs(k-auxiliar)>=epsilon) {	//calcula o valor absoluto da diferenca e recalcula 
												//o valor de k se a aproximacao nao for suficiente
			k=auxiliar;
			auxiliar=k-funcoes(depositos,saldo,k);
		}
		return auxiliar;	//retorna o valor da taxa de juros quando a aproximacao é suficiente
	}
	public static void main(String[] args) {
	}
}