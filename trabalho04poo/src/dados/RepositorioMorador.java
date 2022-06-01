package dados;
import java.util.List;
import java.util.ArrayList;
import negocio.entity.GastoPorMorador;
import negocio.entity.Morador;

public class RepositorioMorador {
    
    private List<Morador> morador;

    public RepositorioMorador(){
        this.morador = new ArrayList<Morador>();
    }

    public void inserirGasto(GastoPorMorador g, int i){
    	this.morador.get(i).getGastos().add(g);
    }
    
    public void editarGasto(GastoPorMorador g, int x, int i){
       //this.morador.get(i).getGastos().set(x, g);
    	this.morador.get(i).getGastos().get(x).setNome(g.getNome());
        this.morador.get(i).getGastos().get(x).setValor(g.getValor());
        this.morador.get(i).getGastos().get(x).setdataVence(g.getdataVenc());
    }
    
    public void excluirGasto(int i, int x, GastoPorMorador g){
    	this.morador.get(i).getGastos().remove(g);
     }
    
    public boolean inserir(Morador m){
        if(checarEmail(m) && checarSenha(m)){
            this.morador.add(m);
            return true;
        }

        return false;
    }

    public List<Morador> listarMoradores(){
//        if(this.morador.size()==0)
//            System.out.println("VaziaRepo");
        return this.morador;
    }

    public Morador encontrarMorador(long codigo){
        
        for(int i = 0; i < morador.size(); i++){
            if(morador.get(i).getCodigo()==codigo){
                return morador.get(i);
            }
        }

        return null;

    }

    public boolean alterar(long codigo, Morador m){

        for(int i = 0; i < morador.size(); i++){
            if(morador.get(i).getCodigo()==codigo){
                
                if(!m.getNome().equals("0")){
                    morador.get(i).setNome(m.getNome());
                }

                if(!m.getSexo().equals("0")){
                    morador.get(i).setSexo(m.getSexo());
                }

                if(m.getDataNasc() != null){
                    morador.get(i).setDataNasc(m.getDataNasc());
                }

                m.setSenha(m.getSenha());

                if(m.getProfissao()!=null){
                    if(!m.getProfissao().equals("0")){
                        morador.get(i).setProfissao(m.getProfissao());
                    }
                }

                if(m.getRenda()!=0){
                    morador.get(i).setRenda(m.getRenda());
                }

                if(!m.getEmail().equals("0")){
                    morador.get(i).setEmail(m.getEmail());
                }

                if(!m.getTelefones().isEmpty()){
                    for(int j = 0; j < m.getTelefones().size(); j++){
                        if(m.getTelefones().get(j)!=null){
                            morador.get(i).getTelefones().add(m.getTelefones().get(j));
                        }
                    }
                }

                return true;
            }

        }
        
        removerProvisorios();
        
        return false;
    }

    public void removerProvisorios(){
        for(int i = 0; i < morador.size(); i++){
            if(morador.get(i).getCodigo()==0){
                morador.remove(i);
            }
        }
    }

    public boolean excluir(long codigo){

        for(int i = 0; i < morador.size(); i++){
            if(morador.get(i).getCodigo()==codigo){
                morador.remove(i);

                return true;
            }            
        }

        return false;
    }

    public List<Morador> encontrarMoradores(){
        return this.morador;
    }

    public boolean checarEmail(Morador m){

        int count = 0;

        for(int i = 0; i < morador.size(); i++){
            if(morador.get(i).getEmail().equals(m.getEmail())){
                count++;
            }
        }

        if(count>0){

            return false;
        }

        return true;

    }

    public boolean checarEmail(String email){

        int count = 0;

        for(int i = 0; i < morador.size(); i++){
            if(morador.get(i).getEmail().equals(email)){
                count++;
            }
        }

        if(count>1){

            return false;
        }

        return true;

    }

    public boolean checarSenha(Morador m){

        int count = 0;

        for(int i = 0; i < morador.size(); i++){
            if(morador.get(i).getSenha().equals(m.getSenha())){
                count++;
            }
        }

        if(count>0){
            return false;
        }

        return true;

    }

    public boolean checarSenha(String senha, long codigo){

        int count = 0;

        for(int i = 0; i < morador.size(); i++){
            if(morador.get(i).getSenha().equals(senha) && morador.get(i).getCodigo()!=codigo){
                count++;
            }
        }

        if(count>0){
            return false;
        }

        return true;

    }

    public void alterarTelefone(long codigo, String telefone_velho, String telefone_novo){
        for(int i = 0; i < morador.size(); i++){
            if(morador.get(i).getCodigo() == codigo){
                morador.get(i).getTelefones().remove(telefone_velho);
                morador.get(i).getTelefones().add(telefone_novo);
            }
        }
    }
}
