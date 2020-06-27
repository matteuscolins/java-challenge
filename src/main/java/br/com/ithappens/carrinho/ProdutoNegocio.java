package br.com.ithappens.carrinho;

import java.util.Scanner;

public class ProdutoNegocio {
    public ProdutoNegocio() {
    }

    public static void menu(){
        int op = 0;
        do {
            try {
                System.out.println("- MENU -");
                System.out.println("1 - Criar");
                System.out.println("2 - Consultar");
                System.out.println("3 - Editar");
                System.out.println("4 - Excluir");
                System.out.println("5 - Sair");
                System.out.print("Escolha uma das opções acima e depois aperte ENTER: ");
                Scanner scanner = new Scanner(System.in);
                op = scanner.nextInt();
            } catch (Exception e){
                e.printStackTrace();
            }
        }while (op > 5);
    }
}
