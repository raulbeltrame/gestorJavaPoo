package negocio;
import java.util.ArrayList;
import java.util.List;
import negocio.entity.Morador;
import negocio.entity.Gasto;
import negocio.entity.GastoPorMorador;
import dados.RepositorioMorador;
import dados.RepositorioGasto;
public class ControladorGasto {
    
	private RepositorioGasto repoGasto;
	
	 public ControladorGasto(){
	        this.repoGasto = new RepositorioGasto();
	    }
	
		public boolean inserir(Gasto g) {
		return repoGasto.inserirGasto(g);
	}


	public List<Gasto> listarGastos() {
		return repoGasto.listarGasto();
	}


	public boolean alterar(Gasto g, int posicao) {
		return repoGasto.alterarGasto(g, posicao);
	}


	public boolean ExcluirGasto(Gasto g) {
		return repoGasto.ExcluirGasto(g);
	}
	
	
	
}
