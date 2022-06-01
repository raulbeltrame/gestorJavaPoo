package negocio;
import negocio.entity.Gasto;
import negocio.entity.GastoPorMorador;
import negocio.entity.Morador;
import dados.RepositorioMorador;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ControladorMorador {
    
    private RepositorioMorador repoMorador;

    public ControladorMorador(){
        this.repoMorador = new RepositorioMorador();
    }

    public boolean inserir(Morador m){
        return this.repoMorador.inserir(m);
    }

    public List<Morador> listarMoradores(){
        List<Morador> m = this.repoMorador.listarMoradores();
//        if(m.size()==0)
//            System.out.println("VaziaControladorMorador");

        return m;
    }

    public Morador encontrarMorador(long codigo){
        return this.repoMorador.encontrarMorador(codigo);
    }

    public boolean alterar(long codigo, Morador m){
        return repoMorador.alterar(codigo,m);
    }

    public boolean excluir(long codigo){
        return repoMorador.excluir(codigo);
    }

    public List<Morador> encontrarMoradores(){
        return repoMorador.encontrarMoradores();
    }

    public void alterarTelefone(long codigo, String telefone_velho, String telefone_novo){
        repoMorador.alterarTelefone(codigo, telefone_velho, telefone_novo);
    }
    
    public boolean RegistrarGastoMorador(List<Morador> m, Gasto g) {
		int posicao = m.size();
		if(posicao!=0) {
			int codigo = g.getCodigo();
			LocalDate c;
			c= g.getdataVenc();
			double valor= g.getvalor();
			double valorPorPessoa= valor/posicao;
			String nome= g.getNome();
			GastoPorMorador Gasto = GastoPorMorador.getInstance(nome, valorPorPessoa,c ,codigo);
			for(int i=0; i<posicao;i++){
				repoMorador.inserirGasto(Gasto, i);
			}
			return true;
		}else {
			return false;
		}
	}
	
	
	public void RegistrarGastoEditadoMorador(List<Morador> m, Gasto g) {
		int posicao = m.size();
		int codigo = g.getCodigo();
		double valor= g.getvalor();
		double valorPorPessoa= valor/posicao;
		String nome= g.getNome();
		LocalDate c;
		c= g.getdataVenc();
		GastoPorMorador Gasto = GastoPorMorador.getInstance(nome, valorPorPessoa,c ,codigo);
		for(int i=0; i<posicao;i++) {
			for(int x=0; x<m.get(i).getGastos().size();x++) {
				if(m.get(i).getGastos().get(x).getCodigo()==codigo){
					repoMorador.editarGasto(Gasto, x, i);//m.get(i).getGastos().get(x).editarGasto(Gasto, x, i); //se der errado, passar o i
				}
			}
		}
	}
	
	public void ExcluirGastoExcluido(List<Morador> m, Gasto g) {
		int codigo = g.getCodigo();
		for(int i=0; i< m.size();i++) {		
			for(int x=0; x<m.get(i).getGastos().size();x++) {
				if(m.get(i).getGastos().get(x).getCodigo()==codigo){
					repoMorador.excluirGasto(i, x, m.get(i).getGastos().get(x));//m.get(i).excluirGasto(i, x, m.get(i).getGastos().get(x));
				}
			}
		}
	}

	public boolean checarSenha(String senha, long codigo){
		return repoMorador.checarSenha(senha, codigo);
	}

	public boolean checarEmail(String email){
		return repoMorador.checarEmail(email);
	}

}
