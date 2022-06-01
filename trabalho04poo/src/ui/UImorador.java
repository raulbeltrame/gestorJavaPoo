package ui;

import java.util.Scanner;
import negocio.Sistema;
import negocio.entity.Morador;
import java.util.List;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class UImorador {
    
    Sistema sistema;
    UIprincipal uiprincipal;
    Scanner scn;
    
    public UImorador(){
        this.sistema = Sistema.getInstance();
        this.scn = new Scanner(System.in);
    }

    public void menu(){
        
        int resposta;
        
        do{
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("MENU DE MORADOR");
            System.out.println();
            System.out.println("0. Voltar à tela principal");
            System.out.println("1. Inserir morador");
            System.out.println("2. Alterar morador");
            System.out.println("3. Excluir morador");
            System.out.println("4. Listar moradores");
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
                    imprimirMoradores(true);
                    break;
            }

        }while(resposta!=0);

    }

    public void inserir(){
        
        String sexo, nome, email, senha, profissao, resposta;
        double renda;
        int dia, mes, ano;
        Morador m;
        boolean insercao;
        int[] qtdias = {31,28,31,30,31,30,31,31,30,31,30,31};
        LocalDate c;
        List<String> telefone = new ArrayList<String>();

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("INSERÇÃO DE MORADOR");
        
        System.out.println();
        
        System.out.print("Nome:");
        nome = scn.next();

        System.out.print("Sexo:");
        sexo = scn.next();

        while(!sexo.equals("masculino") && !sexo.equals("Masculino") && !sexo.equals("feminino") && !sexo.equals("Feminino")){
            
            System.out.print("Resposta Inválida. Digite outra:");

            sexo = scn.next();
        }

        System.out.print("E-mail:");
        email = scn.next();

        System.out.print("Senha:");
        senha = scn.next();

        while(senha.length()<8){
            System.out.print("A senha deve ter no mínimo 8 caracteres");
            senha = scn.next();
        }

        System.out.print("Ano de nascimento: ");

        ano = scn.nextInt();

        while(ano>2021 && ano<1900){
            System.out.print("Ano inválido. Digite outro:");
            ano = scn.nextInt();
        }

        System.out.print("Mês de nascimento: ");

        mes = scn.nextInt();

        while(mes>12 || mes<1){
            System.out.print("Mês inválido. Digite outro:");
            mes = scn.nextInt();
        }

        System.out.print("Dia de nascimento: ");

        dia = scn.nextInt();

        while(qtdias[mes-1]<dia || dia<1){
            System.out.print("Dia inválido. Digite outro:");
            dia = scn.nextInt();
        }

        c = LocalDate.of(ano, mes, dia);

        System.out.println("Deseja adicionar telefones? [s/n]");

        resposta = validarResposta("s", "n");

        if(resposta.equals("s")){
            System.out.println();
            System.out.println("Digite um telefone por linha, ou 0 para finalizar a inserção de telefones");
            do{
                
                resposta = scn.next();

                if(!resposta.equals("0"))
                    telefone.add(resposta);

            }while(!resposta.equals("0"));

        }

        System.out.print("Você tem alguma profissão? [s/n]");

        resposta = validarResposta("s", "n");

        switch (resposta) {
            case "s":
                System.out.print("Digite-a abaixo:");
                profissao = scn.next();

                System.out.print("Renda: ");
                renda = scn.nextDouble();

                while(renda<=0){
                    System.out.print("Valor inválido. Digite outro:");
                    renda = scn.nextDouble();
                }

                m = Morador.getInstance(nome, c, sexo, profissao, email, senha, renda, telefone, false);
                if(m!=null){
                    insercao = sistema.inserir(m);
                    if(!insercao){
                        System.out.println("Inserção não realizada.");
                        System.out.println("\t - O e-mail e a senha de cada usuário deve ser individual");
                    }
                }else{
                    System.out.println("O usuário não foi criado. Esse deve ter mais de 18 anos de idade.");
                }
                break;
            case "n":
                m = Morador.getInstance(nome, c, sexo, email, senha, telefone, false);
                if(m!=null){
                    insercao = sistema.inserir(m);
                    if(!insercao){
                        System.out.println("Inserção não realizada.");
                        System.out.println("\t - O e-mail e a senha de cada usuário deve ser individual");
                    }
                }else{
                    System.out.println("O usuário não foi criado. Esse deve ter mais de 18 anos de idade.");
                }
                break;
        
        }
            
        System.out.println("");

    }

    public void imprimirMoradores(boolean impressao){

        List<Morador> moradores = sistema.listarMoradores();

        if(!moradores.isEmpty()){
            for(int i = 0; i < moradores.size(); i++){
                if(moradores.get(i)!=null)
                    listarMorador(moradores.get(i));
            }
        }else{
            if(impressao){
                System.out.println();
                System.out.println();
                System.out.println("Não há moradores para listar");
            }
        }

    }

    public void alterar(){

        long codigo;
        String sexo, nome, email, senha, profissao, resposta;
        double renda;
        int dia, mes, ano;
        Morador m;
        List<Morador> moradores;
        int[] qtdias = {31,28,31,30,31,30,31,31,30,31,30,31};
        LocalDate c;
        boolean alteracao;
        List<String> telefone = new ArrayList<String>();
        boolean telefone_existe = false;
        String telefone_novo;

        imprimirMoradores(false);
        System.out.println();
        System.out.println();

        moradores = sistema.encontrarMoradores();

        if(!moradores.isEmpty()){
            System.out.print("Qual o código do morador? ");

            codigo = scn.nextLong();

            // encontra o morador para realizar o preenchimento de campos
            m = sistema.encontrarMorador(codigo);

            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println();

            System.out.println();
            
            System.out.print("Nome:"+m.getNome()+". Digite 0 para não alterar o nome ou digite o novo nome:");
            nome = scn.next();

            System.out.print("Sexo:"+m.getSexo()+". Digite 0 para não alterar o sexo ou digite o novo sexo:");
            sexo = scn.next();

            if(!sexo.equals("0")){
                while(!sexo.equals("masculino") && !sexo.equals("Masculino") && !sexo.equals("feminino") && !sexo.equals("Feminino")){
                    
                    System.out.print("Resposta Inválida. Digite outra:");

                    sexo = scn.next();
                }
            }

            System.out.print("E-mail:"+m.getEmail()+". Digite 0 para não alterar o e-mail ou digite o novo e-mail:");
            email = scn.next();

            System.out.print("Senha:");
            senha = scn.next();

            while(senha.length()<8){
                System.out.print("A senha deve ter no mínimo 8 caracteres");
                senha = scn.next();
            }

            while(!(sistema.checarSenha(senha, codigo))){
                System.out.println("Senha inválida. Entre com outra: ");
                senha = scn.next();
            }

            System.out.println("Deseja alterar a data de nascimento? [s/n]");

            resposta = validarResposta("s", "n");

            if(resposta.equals("s")){
                System.out.print("Ano de nascimento: ");

                ano = scn.nextInt();

                while(ano>2021 && ano<1900){
                    System.out.print("Ano inválido. Digite outro:");
                    ano = scn.nextInt();
                }

                System.out.print("Mês de nascimento(número): ");

                mes = scn.nextInt();

                while(mes>12 && mes<1){
                    System.out.print("Mês inválido. Digite outro(número):");
                    mes = scn.nextInt();
                }

                System.out.print("Dia de nascimento: ");

                dia = scn.nextInt();

                while(qtdias[mes-1]>dia && dia<1){
                    System.out.print("Dia inválido. Digite outro:");
                    dia = scn.nextInt();
                }

                c = LocalDate.of(ano, mes, dia);

            }else{
                // Atribui a C a data de nascimento já atribuída ao morador que está sendo alterado. Isso impede erros na checagem de idade do getInstance.
                c = m.getDataNasc();
            }

            System.out.println("Deseja adicionar telefones? [s/n]");

            resposta = validarResposta("s", "n");
    
            if(resposta.equals("s")){
                System.out.println();
                System.out.println("Digite um telefone por linha, ou 0 para finalizar a inserção de telefones");
                do{
                    
                    resposta = scn.next();
    
                    if(!resposta.equals("0"))
                        telefone.add(resposta);
    
                }while(!resposta.equals("0"));
    
            }

            System.out.println("Deseja alterar telefones? [s/n]");

            resposta = validarResposta("s", "n");
    
            if(resposta.equals("s")){
                System.out.println("Digite o telefone que deseja alterar seguido do novo telefone, um por linha. Digite 0 para finalizar a inserção de telefones");

                do{
                    
                    resposta = scn.next();
    
                    if(!resposta.equals("0")){
                        for(int j = 0; j < m.getTelefones().size(); j++){
                            if(m.getTelefones().get(j).equals(resposta)){
                                telefone_existe = true;
                            }
                        }
                        
                        while(!telefone_existe){
                            System.out.println();
                            System.out.println("Telefone inexistente. Digite novamente: ");

                            resposta = scn.next();
        
                            for(int j = 0; j < m.getTelefones().size(); j++){
                                if(m.getTelefones().get(j).equals(resposta)){
                                    telefone_existe = true;
                                }
                            }
                        }
                    }else{
                        break;
                    }

                    telefone_novo = scn.next();

                    if(!telefone_novo.equals("0") && !resposta.equals("0"))
                        sistema.alterarTelefone(codigo, resposta, telefone_novo);
    

                    telefone_existe = false;                    
                }while(!resposta.equals("0"));
            }

            if(m.getProfissao()!=null){
                System.out.print("Profissão:"+m.getProfissao()+". Digite 0 para não alterar a profissão ou digite a nova profissão: ");
                profissao = scn.next();

                System.out.println("Renda:"+m.getRenda()+". Digite -1 para não alterar a renda ou digite a nova renda: ");
                renda = scn.nextDouble();
                
                if(renda==-1){
                    renda = m.getRenda();
                }
                // Cria um morador com os dados alterados, com o intuito de evitar ter de usar os dados como parâmetros na função de alterar
                m = Morador.getInstance(nome, c, sexo, profissao, email, senha, renda, telefone, true);
                alteracao = sistema.alterar(codigo, m);
            }else{
                
                System.out.print("Você tem alguma profissão? [s/n]");
        
                resposta = validarResposta("s", "n");

                if(resposta.equals("s")){
                    System.out.print("Digite-a abaixo: ");
                    profissao = scn.next();

                    System.out.print("Renda: ");
                    renda = scn.nextDouble();
            
                    while(renda<=0){
                        System.out.print("Valor inválido. Digite outro: ");
                        renda = scn.nextDouble();
                    }

                    // Cria um morador com os dados alterados, com o intuito de evitar ter de usar os dados como parâmetros na função de alterar
                    m = Morador.getInstance(nome, c, sexo, profissao, email, senha, renda, telefone, true);
                    alteracao = sistema.alterar(codigo, m);
                }else{

                    // Cria um morador com os dados alterados, com o intuito de evitar ter de usar os dados como parâmetros na função de alterar
                    m = Morador.getInstance(nome, c, sexo, email, senha, telefone, true);
                    alteracao = sistema.alterar(codigo, m);
                }
            
            }

            if(alteracao){
                System.out.println("Alteração bem sucedida.");
            }else{
                System.out.println("Alteração não realizada.");
            }
        }else{
            System.out.println("Não há moradores para alterar");
        }
            
    }

    public String validarResposta(String arg1, String arg2){

        String resposta = scn.next();

        while(!resposta.equals(arg1) && !resposta.equals(arg2)){
            System.out.print("Resposta inválida. Digite outra:");
            resposta = scn.next();
        }

        return resposta;

    }

    public void listarMorador(Morador m){
        
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Morador ID "+m.getCodigo());
        System.out.println("Nome: "+m.getNome());
        System.out.println("Data de Nascimento: "+m.getDataNasc().getDayOfMonth()+"/"+
                                                            m.getDataNasc().getMonthValue()+"/"+
                                                                m.getDataNasc().getYear());
        System.out.println("Sexo: "+m.getSexo());
        System.out.println("E-mail: "+m.getEmail());
        
        
        if(m.getRenda() != 0.0 || m.getProfissao() != null){
            System.out.println("Profissão: "+m.getProfissao());
            System.out.println("Renda: "+m.getRenda());
        }
        System.out.println();
        System.out.println("Gastos desse morador: ");
        if(m.getGastos()!=null) {
        for(int i=0;i<m.getGastos().size();i++) {
        		System.out.println();
        		System.out.println("C�digo: "+m.getGastos().get(i).getCodigo());
        		System.out.println("Nome: "+m.getGastos().get(i).getNome());
        		System.out.println("Valor: "+m.getGastos().get(i).getValor());
        		System.out.println("Data de vencimento: "+ m.getGastos().get(i).getdataVenc().getDayOfMonth()+"/"+
   	    			 m.getGastos().get(i).getdataVenc().getMonthValue()+"/"+
                     m.getGastos().get(i).getdataVenc().getYear());
        	}
        
        }
        System.out.println();
        if(!m.getTelefones().isEmpty()){
            
            System.out.println("TELEFONE(S)");
            for(int i = 0; i < m.getTelefones().size(); i++){
                if(m.getTelefones().get(i)!=null){
                    System.out.println("\tTelefone "+i+": "+m.getTelefones().get(i));
                }
            }
        }

        System.out.println("---------------------------------------------------------------------------------------------");
    }

    public void excluir(){
        
        long codigo;
        boolean exclusao;
        List<Morador> moradores;

        imprimirMoradores(false);
        moradores = sistema.encontrarMoradores();
        if(!moradores.isEmpty()){
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("MENU DE EXCLUSÃO");
            System.out.println();
            System.out.print("Qual o código do morador? ");

            codigo = scn.nextLong();

            exclusao = sistema.excluir(codigo);

            if(exclusao){
                System.out.println("Exclusão bem-sucedida");
            }else{
                System.out.println("Exclusão não realizada");
            }
        }else{
            System.out.println();
            System.out.println();
            System.out.println("Não há moradores para excluir");
        }
    }



}
