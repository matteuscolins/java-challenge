package br.com.ithappens.carrinho;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
public class CarrinhoComprasFactory {

    List<CarrinhoCompras> carrinhoCompras = new ArrayList<>();

	public CarrinhoComprasFactory() {
	}

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param identificacaoCliente
     * @return CarrinhoCompras
     */
    public CarrinhoCompras criar(String identificacaoCliente) {
        CarrinhoCompras carrinhoCompras = new CarrinhoCompras(identificacaoCliente);
        return carrinhoCompras;
    }

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTicketMedio() {
        return null;
    }

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param identificacaoCliente
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidar(String identificacaoCliente) {
        try {
            if(carrinhoCompras.size() > 0){
                for (CarrinhoCompras carrinho:carrinhoCompras) {
                    if(carrinho.cpf.equals(identificacaoCliente)){
                        carrinhoCompras.remove(carrinho);
                        return true;
                    }
                }
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public void menu(){
        int op = 0;
        do {
            try {
                do {
                    System.out.println("- MENU CARRINHO -");
                    System.out.println("1 - Criar");
                    System.out.println("2 - Adicionar itens");
                    System.out.println("3 - Invalidar");
                    System.out.println("4 - Sair");
                    System.out.print("Escolha uma das opções acima e depois aperte ENTER: ");
                    Scanner scanner = new Scanner(System.in);
                    op = scanner.nextInt();
                } while (op > 4);
                switch (op){
                    case 1:
                        try {
                            Scanner scanner = new Scanner(System.in);
                            String cpf = scanner.nextLine();
                            boolean exists = false;
                            if(cpf != null){
                                if(carrinhoCompras.size() >= 0){
                                    for (CarrinhoCompras carCompras:carrinhoCompras){
                                        if(carCompras.cpf.equals(cpf)){
                                            exists = true;
                                        }
                                    }
                                    if(exists == true){
                                        System.out.println("Já existe um carrinho cadastrado com o mesmo CPF");
                                    } else {
                                        CarrinhoCompras carrinho = criar(cpf);
                                        carrinhoCompras.add(carrinho);
                                    }
                                }
                                for (CarrinhoCompras carrinhoCompras:carrinhoCompras) {
                                    System.out.println(carrinhoCompras.cpf);
                                }
                                int opcao = 0;
                                do {
                                    System.out.println("Deseja retornar ao menu de carrinhos?");
                                    System.out.println("1 - Sim");
                                    System.out.println("2 - Não");
                                    Scanner scanner1 = new Scanner(System.in);
                                    opcao = scanner1.nextInt();
                                } while (opcao > 2);
                                if(opcao == 1){
                                    menu();
                                } else if (opcao == 2){
                                    App.menu();
                                }
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            if(carrinhoCompras.size() > 0) {
                                System.out.println("Informe o CPF: ");
                                Scanner scanner = new Scanner(System.in);
                                String cpf = scanner.nextLine();
                                for (CarrinhoCompras carrinho:carrinhoCompras) {
                                    if(carrinho.cpf.equals(cpf)){
                                        op = 0;
                                        do{
                                            System.out.println("Código do produto: ");
                                            Scanner scanner1 = new Scanner(System.in);
                                            long codigo = scanner1.nextLong();
                                            System.out.println("Quantidade: ");
                                            Scanner scanner2 = new Scanner(System.in);
                                            int quantidade = scanner2.nextInt();
                                            for (Produto produto:ProdutoFactory.produtos) {
                                                if(produto.getCodigo().equals(codigo)){
                                                    carrinho.adicionarItem(produto, produto.getValor(), quantidade);
                                                }
                                            }
                                        }while (op > 2);
                                    }
                                }
                            } else {
                                System.out.println("SEM CARRINHOS CADASTRADOS!");
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }

                        break;
                    case 3:
                        try {
                            Scanner scanner = new Scanner(System.in);
                            String cpf = scanner.nextLine();
                            if(cpf != null){
                                boolean invalido = invalidar(cpf);
                                if(invalido == true){
                                    System.out.println("Carrinho invalidado!");
                                } else {
                                    System.out.println("Erro ao encontrar o carrinho com o código identificador!");
                                }
                                int opcao = 0;
                                do {
                                    System.out.println("Deseja retornar ao menu de carrinhos?");
                                    System.out.println("1 - Sim");
                                    System.out.println("2 - Não");
                                    Scanner scanner1 = new Scanner(System.in);
                                    opcao = scanner1.nextInt();
                                } while (opcao > 2);
                                if(opcao == 1){
                                    menu();
                                } else if (opcao == 2){
                                    App.menu();
                                }
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        //delete();
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
}
