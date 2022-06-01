package negocio.entity;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.LocalDate;
public class Gasto {
	
	
	
	protected String NomeCompra;
	protected int codigo;
	protected LocalDate dataVenc;
	protected String Descricao;
	protected double valor;
	static protected int id = 1;
	
	public int getCodigo() {
		return this.codigo;
	}
	
		
	public String getNome() {
		return this.NomeCompra;
	}
	
	public void setNome(String nome){
		this.NomeCompra=nome;
	}
	
	public LocalDate getdataVenc() {
		return this.dataVenc;
	}
	
	public void setdataVence(LocalDate dataVenc){
		this.dataVenc=dataVenc;
	}
	
	public String getDescricao() {
		return this.Descricao;
	}
	
	public void setDescricao(String Descricao){
		this.Descricao=Descricao;
	}
	
	public Double getvalor() {
		return this.valor;
	}
	
	public void setvalor(Double valor){
		this.valor=valor;
	}

	private Gasto(String nome, String descricao, Double valor, LocalDate dataVen) {
		this.NomeCompra = nome;
		this.dataVenc = dataVen;
		this.Descricao = descricao;
		this.valor = valor;
		this.codigo=id;
		id++;
	}
	
	private Gasto(String nome, String descricao, Double valor, LocalDate dataVen, int codigo) {
		this.NomeCompra = nome;
		this.dataVenc = dataVen;
		this.Descricao = descricao;
		this.valor = valor;
		this.codigo=codigo;
	}
	

	public static Gasto getInstance(String nome, String descricao, Double valor, LocalDate dataVen) {
		if (nome != null && descricao != null && valor != null && dataVen != null) {
			return new Gasto(nome, descricao, valor, dataVen);
		} else {
			return null;
		}
	}
	
	public static Gasto getInstance(String nome, String descricao, Double valor, LocalDate dataVen, int codigo) {
		if (nome != null && descricao != null && valor != null && dataVen != null && codigo != 0) {
			return new Gasto(nome, descricao, valor, dataVen, codigo);
		} else {
			return null;
		}
	}


	public void setCodigo(int codigo2) {
		this.codigo=codigo2;
		
	}
}
