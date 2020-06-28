package br.com.ithappens.carrinho;

import java.io.IOException;
import java.util.Scanner;

public class App {

    private static CarrinhoComprasFactory factory;

    public static void main(String args[]){
        menu();
    }

    public static void menu(){
        int op = 0;
        do {
            try {
                System.out.println("- MENU -");
                System.out.println("1 - Produtos");
                System.out.println("2 - Carrinho");
                System.out.println("3 - Sair");
                System.out.print("Escolha uma das opções acima e depois aperte ENTER: ");
                Scanner scanner = new Scanner(System.in);
                op = scanner.nextInt();
            } catch (Exception e){
                e.printStackTrace();
            }
        }while (op > 3);
        switch (op){
            case 1:
                ProdutoFactory.menu();
                break;
            case 2:
                factory = new CarrinhoComprasFactory();
                factory.menu();
                break;
            default:
                break;
        }
    }
}
