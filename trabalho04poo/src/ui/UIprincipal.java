package ui;
import java.util.Scanner;

// Davy Carvalho Marçal Salgado - Luan Gabriel Teixeira de Vasconcelos

import negocio.Sistema;
import negocio.entity.Morador;
import java.util.List;
import java.util.ArrayList;

public class UIprincipal {
    
    UImorador uimorador;
    UiGasto uigastos;
    Scanner scn;
    
    public UIprincipal(){
        this.uimorador = new UImorador();
        this.uigastos = new UiGasto();
        scn = new Scanner(System.in);
    }
    
    public void iniciar(){

        int resposta;

        System.out.println("SEJA BEM-VINDO AO SISTEMA DE CONTROLE FINANCEIRO DOMÉSTICO. Escolha alguma das opções abaixo:");
        
        do{
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("MENU PRINCIPAL");
            System.out.println("0. Sair");
            System.out.println("1. Executar operações na seção de Moradores");
            System.out.println("2. Executar operações na seção de Gastos");
            System.out.println();
            System.out.print("Opção: ");
            resposta = scn.nextInt();

            switch (resposta) {
                case 1:
                    uimorador.menu();                    
                    break;
                case 2:
                    uigastos.menu();
                    break;
            }
        }while(resposta!=0);
    }

}
