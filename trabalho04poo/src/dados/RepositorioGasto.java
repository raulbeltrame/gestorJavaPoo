package dados;
import java.util.List;
import java.util.ArrayList;
import negocio.entity.Gasto;
import negocio.entity.Morador;

public class RepositorioGasto {
	
	List <Gasto> gasto;
	
	public RepositorioGasto(){
        this.gasto = new ArrayList<Gasto>();
    }
	
	public List<Gasto> listarGasto(){
		return this.gasto;
	}
	
	public boolean inserirGasto(Gasto g) {
		gasto.add(g);
		return true;
	}

	public boolean alterarGasto(Gasto g, int posicao) {
		gasto.get(posicao).setNome(g.getNome());
		gasto.get(posicao).setDescricao(g.getDescricao());
		gasto.get(posicao).setvalor(g.getvalor());
		gasto.get(posicao).setdataVence(g.getdataVenc());
		gasto.get(posicao).setCodigo(g.getCodigo());
		return true;
	}

	public boolean ExcluirGasto(Gasto g) {
		gasto.remove(g);
		return true;
	}
	
	
	
}
