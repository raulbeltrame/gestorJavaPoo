package ui;
import java.util.Scanner;
import negocio.Sistema;
import negocio.entity.Gasto;
import negocio.entity.Morador;
import java.util.List;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;


public class UiGasto {

	 	Sistema sistema;
	    UIprincipal uiprincipal;
	    Scanner scn;
	    
	    public UiGasto(){
	        this.sistema = Sistema.getInstance();
	        this.scn = new Scanner(System.in);
	    }
	
	    public void menu(){
	        
	        int resposta;
	        
	        do{
	            System.out.println("---------------------------------------------------------------------------------------------");
	            System.out.println("MENU DE GASTO");
	            System.out.println();
	            System.out.println("0. Voltar à tela principal");
	            System.out.println("1. Inserir Gasto");
	            System.out.println("2. Alterar Gasto");
	            System.out.println("3. Excluir Gasto");
	            System.out.println("4. Listar Gasto");
	            System.out.println();
	            System.out.print("Opção: ");
	            resposta = scn.nextInt();

	            while(resposta<0 && resposta>4){
	                System.out.println("Opção Inválida. Digite outra:");
	                resposta = scn.nextInt();
	            }

	            switch (resposta) {
	                case 1:
	                    inserir();
	                    break;
	            case 2:
	                    alterar();
	                    break;
	            case 3:
	                   excluir();
	                    break;
	            case 4:
	                  imprimirGasto();
						
	                    break;
	           	}
	            
	        }while(resposta!=0);

	    }
	    
//	    public void teste(){
//
//	    	List<Morador> moradores = sistema.listarMoradores();
//	    	List<Gasto> g = sistema.listarGasto();
//	    	// System.out.println(g.get(0).getNome());
//	    	System.out.println(moradores.get(0).getNome());	    	
//	    }
	    
	    public void imprimirGasto() {
	    	List<Gasto> g = sistema.listarGasto();
	    	
	    	if(!g.isEmpty()){
	            for(int i = 0; i < g.size(); i++){
	                if(g.get(i)!=null)
	                	imprimirGasto(g.get(i), i);
	            }
	        }else{
	                System.out.println();
	                System.out.println();
	                System.out.println("Não há gastos para listar");
	        }
	    	
	    }
	
	    public void inserir() {
	    	String nome, descricao;
	    	int[] qtdias = {31,28,31,30,31,30,31,31,30,31,30,31};
	    	int ano, dia, mes;
	    	List<Morador> m = sistema.listarMoradores();
	    	Double valor;
	    	LocalDate c;
	    	Gasto g;
	    	System.out.println("Digite o nome do gasto");
	    	nome= scn.next();
	    	System.out.println("Digite a descri��o do gasto");
	    	descricao= scn.next();
	    	System.out.println("Digite o valor do gasto");
	    	valor= scn.nextDouble();
	    	while(valor<=0) {
	    		System.out.println("Uma conta n�o pode ser menor ou igual a 0");
	    		valor= scn.nextDouble();
	    	}
	    	System.out.print("Ano de Vencimento: ");

	        ano = scn.nextInt();

	        while (ano<2021 || ano>2025){
	            System.out.print("N�o pode adicionar conta com ano inferior ao atual ou superior a 2025");
	            ano = scn.nextInt();
	        }

	        System.out.print("Mês de vencimento: ");

	        mes = scn.nextInt();

	        while(mes>12 || mes<1){
	            System.out.print("Mês inválido. Digite outro:");
	            mes = scn.nextInt();
	        }

	        System.out.print("Dia do vencimento: ");

	        dia = scn.nextInt();

	        while(qtdias[mes-1]<dia || dia<1){
	            System.out.print("Dia inválido. Digite outro:");
	            dia = scn.nextInt();
	        }
			// c = new GregorianCalendar(ano, mes, dia);
	       
	        c = LocalDate.of(ano, mes, dia);
	    	g= Gasto.getInstance(nome, descricao, valor, c);
	    	if(!sistema.inserir(g)){
	    		System.out.println("Gasto n�o inserido");
	    	}else {
	    			System.out.println("Gasto inserido com sucesso");
	    			if(sistema.DividirGasto(g, m)){
	    			System.out.println("Gasto dividido com sucesso");
	    		}else {
	    			System.out.println("N�o h� moradores para dividir o gasto");
	    		}
	    	}
	    	
	    }
	    
	    public void imprimirGasto(Gasto g, int i){
	    	
	    	System.out.println();
	        System.out.println("---------------------------------------------------------------------------------------------");
	        System.out.println();
	        System.out.println("Posicao "+ i);
	        System.out.println("Gasto ID "+g.getCodigo());
	        System.out.println("Nome: "+g.getNome());
	        System.out.println("Data de Vencimento: "+g.getdataVenc().getDayOfMonth()+"/"+
                    g.getdataVenc().getMonthValue()+"/"+
                    g.getdataVenc().getYear());
	        System.out.println("Descricao: "+g.getDescricao());
	        System.out.println("Valor: "+g.getvalor());
	        System.out.println();
	        
	        System.out.println("---------------------------------------------------------------------------------------------");
	    }
	    
	    public void alterar() {
	    	int ano, dia, mes;
	    	String nome="", descricao="";
	    	Double valor=0.0;
	    	Gasto g1;
	    	int[] qtdias = {31,28,31,30,31,30,31,31,30,31,30,31};
	    	LocalDate c;
	    	List<Morador> m = sistema.listarMoradores();
	    	List<Gasto> g = sistema.listarGasto();
	    	imprimirGasto();
	    	System.out.println("Digite a posi��o do gasto que deseja alterar");
	    	int numero=scn.nextInt();
	    	while(!checarnumero(numero, g)) {
	    		System.out.println("Digite um n�mero que esteja listado");
	    		numero=scn.nextInt();
	    	}
	    	
	    	System.out.println("Deseja alterar o nome ('0'-sim '1'-n�o)? "+g.get(numero).getNome());
	    	int opcao=scn.nextInt();
	    	while(!checaropcao(opcao)){
	    		System.out.println("Digite um op��o v�lida ('0'-sim '1'-n�o)");
		    	opcao=scn.nextInt();
	    	}
	    	if(opcao==0){
	    		System.out.println("Digite um novo nome: ");
	    		nome= scn.next();
	    	}else {
	    		nome=g.get(numero).getNome();
	    	}
	    	
	    	System.out.println("Deseja alterar a descri��o ('0'-sim '1'-n�o)? "+g.get(numero).getDescricao());
	    	opcao=scn.nextInt();
	    	while(!checaropcao(opcao)){
	    		System.out.println("Digite um op��o v�lida ('0'-sim '1'-n�o)");
		    	opcao=scn.nextInt();
	    	}
	    	if(opcao==0){
	    		System.out.println("Digite uma nova desci��o: ");
	    		descricao= scn.next();
	    	}else {
	    		descricao=g.get(numero).getDescricao();
	    	}
	    	
	    	System.out.println("Deseja alterar o valor ('0'-sim '1'-n�o)? "+g.get(numero).getvalor());
	    	opcao=scn.nextInt();
	    	while(!checaropcao(opcao)){
	    		System.out.println("Digite um op��o v�lida ('0'-sim '1'-n�o)");
		    	opcao=scn.nextInt();
	    	}
	    	if(opcao==0){
	    		System.out.println("Digite um novo valor: ");
	    		valor= scn.nextDouble();
	    	}else {
	    		valor=g.get(numero).getvalor();
	    	}
	    	
	    	System.out.println("Deseja alterar a data de vencimento ('0'-sim '1'-n�o)? "+g.get(numero).getdataVenc().getDayOfMonth()+"/"+
                    g.get(numero).getdataVenc().getMonthValue()+"/"+
                    g.get(numero).getdataVenc().getYear());//+g.get(numero).getdataVenc());
	    	opcao=scn.nextInt();
	    	while(!checaropcao(opcao)){
	    		System.out.println("Digite um op��o v�lida ('0'-sim '1'-n�o)");
		    	opcao=scn.nextInt();
	    	}
	    	
	    	if(opcao==0){
	    		System.out.print("Ano de Vencimento: ");

		        ano = scn.nextInt();

		        while (ano<2021){
		            System.out.print("N�o pode adicionar conta com ano inferior ao atual");
		            ano = scn.nextInt();
		        }

		        System.out.print("Mês de vencimento: ");

		        mes = scn.nextInt();

		        while(mes>12 || mes<1){
		            System.out.print("Mês inválido. Digite outro:");
		            mes = scn.nextInt();
		        }

		        System.out.print("Dia do vencimento: ");

		        dia = scn.nextInt();

		        while(qtdias[mes-1]<dia || dia<1){
		            System.out.print("Dia inválido. Digite outro:");
		            dia = scn.nextInt();
		        }

		        c = LocalDate.of(ano, mes, dia);
	    	}else {
	    		c=g.get(numero).getdataVenc();
	    	}
	    	
	    	int codigo=g.get(numero).getCodigo();
	    	
	    	g1= Gasto.getInstance(nome, descricao, valor, c, codigo);
	    	if(!sistema.alterarGastos(g1, numero)) {
	    		System.out.println("Gasto n�o alterado");
	    	}else {
	    		sistema.DividirGastoEditado(g1,m);  		
	    		System.out.println("Gasto alterado com sucesso");
	    	}
	    }
	    
	    public void excluir() {
	    	List<Morador> m = sistema.listarMoradores();
	    	List<Gasto> g = sistema.listarGasto();
	    	imprimirGasto();
	    	System.out.println("Digite o n�mero do gasto que deseja excluir");
	    	int numero=scn.nextInt();
	    	while(!checarnumero(numero, g)) {
	    		System.out.println("Digite um n�mero que esteja listado");
	    		numero=scn.nextInt();
	    	}
	    	Gasto g1= g.get(numero);
	    	if(!sistema.excluirGasto(g1)) {
	    		System.out.println("Gasto n�o exclu�do");
	    	}else {
	    		System.out.println("Gasto exclu�do");
	    		sistema.excluirGastoMorador(g1, m);
	    	}
	    	
	    }
	    
	 	    
	    public boolean checaropcao(int opcao) {
	    	if(opcao>1 || opcao<0){
	    		return false;
	    	}
	    	return true;
	    }
	    
	    public boolean checarnumero(int numero, List<Gasto> g) {
	    	if(numero>=g.size() || numero<0){
	    		return false;
	    	}
	    	return true;
	    }
	    
	    
}
