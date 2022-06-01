package negocio.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import dados.RepositorioGasto;
import dados.RepositorioMorador;
public class Morador {
    
    protected String nome;
    protected LocalDate dataNasc;
    protected String sexo;
    protected String profissao;
    protected String email;
    protected String senha;
    protected double renda;
    protected List<String> telefones = new ArrayList<String>();
    protected List<GastoPorMorador> Gastos = new ArrayList<GastoPorMorador>();
    protected long codigo;
    static protected long id = 1;
    
    
    
    public List<GastoPorMorador> getGastos(){
        return this.Gastos;
    }
    
    public long getCodigo(){
        return this.codigo;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setDataNasc(LocalDate dataNasc){
        this.dataNasc = dataNasc;
    }

    public LocalDate getDataNasc(){
        return this.dataNasc;
    }

    public String getSexo(){
        return this.sexo;
    }

    public void setSexo(String sexo){
        this.sexo = sexo;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return this.senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public double getRenda(){
        return this.renda;
    }

    public void setRenda(double renda){
        this.renda = renda;
    }

    public String getProfissao(){
        return this.profissao;
    }

    public void setProfissao(String profissao){
        this.profissao = profissao;
    }

    public List<String> getTelefones(){
        return this.telefones;
    }

    private Morador(String nome, LocalDate dataNasc, String sexo, String profissao, String email, String senha, double renda, List<String> telefone, boolean provisorio){
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.profissao = profissao;
        this.email = email;
        this.nome = nome;
        this.renda = renda;
        this.telefones = telefone;
        
        if(!provisorio)
            this.codigo = id;
            id++; 
    }

    private Morador(String nome, LocalDate dataNasc, String sexo, String email, String senha, List<String> telefone, boolean provisorio){
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.email = email;
        this.nome = nome;
        this.telefones = telefone;
        
        if(!provisorio)
            this.codigo = id;
            id++; 
    }

    static public Morador getInstance(String nome, LocalDate dataNasc, String sexo, String profissao, String email, String senha, double renda, List<String> telefone, boolean provisorio){
        LocalDate c = LocalDate.now();
        
        if(c.getYear() - dataNasc.getYear() >= 18 && renda>0.0){
            return new Morador(nome, dataNasc, sexo, profissao, email, senha, renda, telefone, provisorio);
        }

        return null;

    } 

    static public Morador getInstance(String nome, LocalDate dataNasc, String sexo, String email, String senha, List<String> telefone, boolean provisorio){
        LocalDate c = LocalDate.now();
        
        if(c.getYear() - dataNasc.getYear() >= 18){
        }
        
        return new Morador(nome, dataNasc, sexo, email, senha, telefone, provisorio);
        // return null;
    }

    public void inserirTelefones(String telefone){
        this.telefones.add(telefone);
    }
    
    public boolean equals(Morador m){
        
        if(m.getCodigo()==this.getCodigo()){
            return true;
        }
        
        return false;
    }

    static public void aumentarID(){
        id++;
    }

    static public void diminuirID(){
        id--;
    }

//	public void inserirGasto(GastoPorMorador gasto, int i) {
////		repoMorador.inserirGasto(gasto, i);		
////	}
////
////	public void excluirGasto(int i, int x, GastoPorMorador g) {
////		repoMorador.excluirGasto(i,x,g);			
////	}
	
	

}
