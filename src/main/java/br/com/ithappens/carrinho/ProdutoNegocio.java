package br.com.ithappens.carrinho;

import javax.rmi.PortableRemoteObject;
import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutoNegocio {

    public static List<Produto> produtos = new ArrayList<>();

    public ProdutoNegocio() {
    }

    public static void menu(){
        int op = 0;
        do {
            try {
                do {
                    System.out.println("- MENU -");
                    System.out.println("1 - Criar");
                    System.out.println("2 - Consultar");
                    System.out.println("3 - Editar");
                    System.out.println("4 - Excluir");
                    System.out.println("5 - Sair");
                    System.out.print("Escolha uma das opções acima e depois aperte ENTER: ");
                    Scanner scanner = new Scanner(System.in);
                    op = scanner.nextInt();
                } while (op > 5);
                switch (op){
                    case 1:
                        cria();
                        break;
                    case 2:
                        consulta();
                        break;
                    case 3:
                        edita();
                        break;
                    case 4:
                        delete();
                        break;
                    default:
                        System.exit(0);
                        break;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }while (op > 5);
    }

    public static void cria(){
        try {
            Scanner scanner1 = new Scanner(System.in);
            System.out.print("Informe o código do produto: ");
            long codigo = scanner1.nextLong();
            Scanner scanner2 = new Scanner(System.in);
            System.out.print("Informe a descrição do produto: ");
            String descricao = scanner2.nextLine();
            if(produtos.size() > 0){
                for (Produto prod:produtos) {
                    if(prod.getCodigo() == codigo){
                        System.out.println("Já existe um produto com código "+codigo);
                        int op = 0;
                        do {
                            System.out.println("Deseja cadastrar o produto novamente?");
                            System.out.println("1 - Sim");
                            System.out.println("2 - Não");
                            Scanner scanner = new Scanner(System.in);
                            op = scanner.nextInt();
                        } while (op > 2);
                        if(op == 1){
                            cria();
                        } else {
                            menu();
                        }
                    }
                }
            }
            Produto produto = new Produto(codigo, descricao);
            produtos.add(produto);
            if(produto != null){
                System.out.println("Produto adicionado com sucesso!");
                int op = 0;
                do {
                    System.out.println("Deseja retornar ao menu de produtos?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    Scanner scanner = new Scanner(System.in);
                    op = scanner.nextInt();
                } while (op > 2);
                if(op == 1){
                    menu();
                } else if (op == 2){
                    App.menu();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void consulta(){
        try {
            if(produtos.size() > 0){
                System.out.println("PRODUTOS CADASTRADOS");
                System.out.println("|COD|DESCRICAO|");
                System.out.println("|---|----------------------------------------------|");
                for (Produto produto: produtos) {
                    System.out.println("|" + produto.getCodigo()+"  | "+produto.getDescricao().toUpperCase());
                }
                int op = 0;
                do {
                    System.out.println("Deseja retornar ao menu de produtos?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    Scanner scanner = new Scanner(System.in);
                    op = scanner.nextInt();
                } while (op > 2);
                if(op == 1){
                    menu();
                } else if (op == 2){
                    App.menu();
                }
            } else {
                System.out.println("ZERO PRODUTOS CADASTRADOS!");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void edita(){
        long codigoProduto = 0;
        String descricao = null;
        try {
            if(produtos.size() > 0){
                System.out.println("PRODUTOS CADASTRADOS");
                System.out.println("|COD|DESCRICAO|");
                System.out.println("|---|----------------------------------------------|");
                for (Produto produto: produtos) {
                    System.out.println("|" + produto.getCodigo()+"  | "+produto.getDescricao().toUpperCase());
                }
                System.out.println("|--------------------------------------------------|");
                System.out.print("Informe o código do produto que deseja editar: ");
                Scanner scanner = new Scanner(System.in);
                codigoProduto = scanner.nextLong();
                for (Produto produto: produtos) {
                    if(produto.getCodigo() == codigoProduto){
                        System.out.print("Informe a nova descrição do produto: ");
                        Scanner scanner1 = new Scanner(System.in);
                        descricao = scanner1.nextLine();
                        produto.setDescricao(descricao);
                        consulta();
                    } else {
                        int op = 0;
                        System.out.println("Não foi possível encontrar o produto com o código informado!");
                        do{
                            System.out.println("DESEJA INFORMAR O CÓDIGO NOVAMENTE?");
                            System.out.println("1 - Sim");
                            System.out.println("2 - Não");
                            Scanner scanner2 = new Scanner(System.in);
                            op = scanner2.nextInt();
                        }while (op > 2);
                        if(op == 1){
                            edita();
                        } else {
                            menu();
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void delete(){
        long codigoProduto = 0;
        try {
            if(produtos.size() > 0){
                System.out.print("Informe o código do produto que deseja deletar: ");
                Scanner scanner = new Scanner(System.in);
                codigoProduto = scanner.nextLong();
                for (Produto produto:produtos) {
                    if(produto.getCodigo().equals(codigoProduto)){
                        produtos.remove(produto);
                    }
                }
            } else {
                System.out.println("Sem produtos para deletar");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
