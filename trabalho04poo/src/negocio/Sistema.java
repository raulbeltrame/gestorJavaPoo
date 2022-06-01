package negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import jdk.vm.ci.meta.Local;
import negocio.entity.Morador;
import negocio.entity.Gasto;
public class Sistema {
    
    private ControladorMorador morador_controller;
    private ControladorGasto gasto_controller;
    // private ControladorGasto gasto_controller;
    private static Sistema instance = null; 


    private Sistema(){
        morador_controller = new ControladorMorador();
        gasto_controller = new ControladorGasto();
        init();
    }
    
    void init(){
    	List<Morador> m1 = listarMoradores();
    	Morador m;
    	LocalDate c;
    	LocalDate c1;
    	c = LocalDate.of(1980,1,1);
    	//imagina que todos nasceram no mesmo dia tamb�m
    	String[] Nomes= {"Davi","Danilo","Daniel","Denilson","Dimitri"};
    	String[] Email= {"1@gmail.com","12@gmail.com","123@gmail.com","1234@gmail.com","12345@gmail.com"};
    	String[] Senha= {"a123456789","b123456789","c123456789","d123456789","e123456789"};
    	String[] Profissao= {"Engenheiro","Arquiteto","Secret�rio","Programador","T�cnico em eletr�nica"};
    	Double[] Renda= {5000.00,3500.00,2500.00,150.00,1500.00};
    	List <String> Telefone=new ArrayList<String>();
    	Telefone.add("38234508");//Deixei s� um telefone para n�o ter muito trabalho
    	String sexo="masculino";
    	for(int i=0;i<5;i++){
    		m=Morador.getInstance(Nomes[i], c, sexo, Profissao[i], Email[i], Senha[i],Renda[i],Telefone, false);
    		inserir(m);
    	}
    	Gasto g;
    	String[] NomesCompra= {"Conta de luz","Internet","Conta de �gua","Conta de telefone","Taxa de lixo"};
    	String[] Descricao= {"Conta t� cara, vamos diminuir o consumo (CEMIG)","Conta da giganet","Copasa","Telefone est� muito caro","Temos que pagar mesmo?"};
    	c1 = LocalDate.of(2021,1,31);
    	Double[] Valor= {250.00,225.00,150.00,120.00,80.00};
    	for(int i=0;i<3;i++){
    		g= Gasto.getInstance(NomesCompra[i],Descricao[i],Valor[i],c1);
    		inserir(g);
    		DividirGasto(g, m1);
    	}
    }
    
    public static Sistema getInstance(){
        if(instance==null){
            instance = new Sistema();
        }
        return instance;
    }

    public boolean inserir(Morador m){
        return this.morador_controller.inserir(m);
    }

    public boolean alterarGastos(Gasto g, int posicao){
        return this.gasto_controller.alterar(g, posicao);
    }
    
    public void DividirGastoEditado(Gasto g, List<Morador> m){
         this.morador_controller.RegistrarGastoEditadoMorador(m, g);
    } 
    
    public boolean DividirGasto(Gasto g, List<Morador> m){
        return this.morador_controller.RegistrarGastoMorador(m, g);
    }
    
    public boolean excluirGasto(Gasto g){
        return this.gasto_controller.ExcluirGasto(g);
    }
    
    public boolean inserir(Gasto g){
        return this.gasto_controller.inserir(g);
    }

    public List<Morador> listarMoradores(){

        List<Morador> m = this.morador_controller.listarMoradores();
//        if(m.size()==0)
//            System.out.println("VaziaSistema");

        return m;
    }

    public List<Gasto> listarGasto(){
        return this.gasto_controller.listarGastos();
    }

    
    public Morador encontrarMorador(long codigo){
        return this.morador_controller.encontrarMorador(codigo);
    }

    public boolean alterar(long codigo, Morador m){
        return morador_controller.alterar(codigo, m);
    }

    public boolean excluir(long codigo){
        return morador_controller.excluir(codigo);
    }

    public List<Morador> encontrarMoradores(){
        return morador_controller.encontrarMoradores();
    }

    public void alterarTelefone(long codigo, String telefone_velho, String telefone_novo){
        morador_controller.alterarTelefone(codigo, telefone_velho, telefone_novo);
    }

	public void excluirGastoMorador(Gasto g1, List<Morador> m) {
		morador_controller.ExcluirGastoExcluido(m, g1);
		
	}

    public boolean checarSenha(String senha, long codigo){
        return morador_controller.checarSenha(senha, codigo);
    }

    public boolean checarEmail(String email){
        return morador_controller.checarEmail(email);
    }
}
