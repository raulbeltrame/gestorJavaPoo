package negocio.entity;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import dados.RepositorioMorador;
public class GastoPorMorador {
	
	protected String Nome;
	protected Double Valor;
	protected LocalDate dataVenc;
	protected int Codigo;
	
	
	public void setNome(String nome) {
		this.Nome=nome;
	}
	
	public LocalDate getdataVenc() {
		return this.dataVenc;
	}
	
	public void setdataVence(LocalDate dataVenc){
		this.dataVenc=dataVenc;
	}
	
	public int getCodigo() {
		return this.Codigo;
	}
	
	public String getNome() {
		return this.Nome;
	}
	
	public void setValor(Double valor) {
		this.Valor=valor;
	}
	
	public Double getValor() {
		return this.Valor;
	}
	
	public void setCodigo(int codigo) {
		this.Codigo=codigo;
	}
	
	
	
	private GastoPorMorador(String nome, Double valor, LocalDate dataVen, int codigo) {
		this.Nome = nome;
		this.dataVenc = dataVen;
		this.Valor = valor;
		this.Codigo=codigo;
	}

	

	public static GastoPorMorador getInstance(String nome, Double valor, LocalDate dataVen, int codigo) {
		if (nome != null && valor != null && dataVen != null && codigo != 0) {
			return new GastoPorMorador(nome, valor, dataVen, codigo);
		} else {
			return null;
		}
	}
	
	
}
