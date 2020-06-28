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
     * <p>
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
            if (carrinhoCompras.size() > 0) {
                for (CarrinhoCompras carrinho : carrinhoCompras) {
                    if (carrinho.cpf.equals(identificacaoCliente)) {
                        carrinhoCompras.remove(carrinho);
                        return true;
                    }
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void menu() {
        int op = 0;
        do {
            try {
                do {
                    System.out.println("- MENU CARRINHO -");
                    System.out.println("1 - Criar");
                    System.out.println("2 - Adicionar itens");
                    System.out.println("3 - Remover itens");
                    System.out.println("4 - Valor total");
                    System.out.println("5 - Invalidar");
                    System.out.println("6 - Sair");
                    System.out.print("Escolha uma das opções acima e depois aperte ENTER: ");
                    Scanner scanner = new Scanner(System.in);
                    op = scanner.nextInt();
                } while (op > 5);
                switch (op) {
                    case 1:
                        try {
                            Scanner scanner = new Scanner(System.in);
                            String cpf = scanner.nextLine();
                            boolean exists = false;
                            if (cpf != null) {
                                if (carrinhoCompras.size() >= 0) {
                                    for (CarrinhoCompras carCompras : carrinhoCompras) {
                                        if (carCompras.cpf.equals(cpf)) {
                                            exists = true;
                                        }
                                    }
                                    if (exists == true) {
                                        System.out.println("Já existe um carrinho cadastrado com o mesmo CPF");
                                    } else {
                                        CarrinhoCompras carrinho = criar(cpf);
                                        carrinhoCompras.add(carrinho);
                                    }
                                }
                                for (CarrinhoCompras carrinhoCompras : carrinhoCompras) {
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
                                if (opcao == 1) {
                                    menu();
                                } else if (opcao == 2) {
                                    App.menu();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            if (carrinhoCompras.size() > 0) {
                                System.out.println("Informe o CPF: ");
                                Scanner scanner = new Scanner(System.in);
                                String cpf = scanner.nextLine();
                                for (CarrinhoCompras carrinho : carrinhoCompras) {
                                    if (carrinho.cpf.equals(cpf)) {
                                        int opcao = 0;
                                        do {
                                            System.out.print("Código do produto: ");
                                            Scanner scanner1 = new Scanner(System.in);
                                            long codigo = scanner1.nextLong();
                                            System.out.print("Valor unitário do produto: ");
                                            Scanner scanner2 = new Scanner(System.in);
                                            BigDecimal valorUnitario = scanner2.nextBigDecimal();
                                            System.out.print("Quantidade: ");
                                            Scanner scanner3 = new Scanner(System.in);
                                            int quantidade = scanner3.nextInt();
                                            for (Produto produto : ProdutoFactory.produtos) {
                                                if (produto.getCodigo().equals(codigo)) {
                                                    carrinho.adicionarItem(produto, valorUnitario, quantidade);
                                                    System.out.println("Produto adicionado!");
                                                }
                                            }
                                            System.out.println("Adicionar mais itens?");
                                            System.out.println("1 - Sim");
                                            System.out.println("2 - Não");
                                            Scanner scanner4 = new Scanner(System.in);
                                            opcao = scanner4.nextInt();
                                        } while (opcao == 1);
                                        menu();
                                    }
                                }
                            } else {
                                System.out.println("SEM CARRINHOS CADASTRADOS!");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                    case 3:
                        try {
                            if (carrinhoCompras.size() > 0) {
                                System.out.println("Informe o CPF: ");
                                Scanner scanner = new Scanner(System.in);
                                String cpf = scanner.nextLine();
                                System.out.println("|POS.   |COD    |QTDE   |DESCRIÇÃO                           |VALOR    ");
                                System.out.println("|-------|-------|-------|------------------------------------|---------");
                                for (CarrinhoCompras compras : carrinhoCompras) {
                                    if (compras.cpf.equals(cpf)) {
                                        int i = 1;
                                        for (Item item : compras.getItens()) {
                                            System.out.println("|"+i+"\t\t|"+item.getProduto().getCodigo() + "\t\t|" + item.getQuantidade() + "\t\t|" + item.getProduto().getDescricao() + "\t\t|");
                                            i++;
                                        }
                                        int opcao = 0;
                                        do {
                                            System.out.println("Deseja remover por: ");
                                            System.out.println("1 - Posição no carrinho de compra");
                                            System.out.println("2 - Código do produto");
                                            Scanner scanner1 = new Scanner(System.in);
                                            opcao = scanner1.nextInt();
                                        } while (opcao > 2);
                                        if(opcao == 1){
                                            int posicao;
                                            System.out.println("Qual a posição do produto no carrinho?");
                                            Scanner scanner1 = new Scanner(System.in);
                                            posicao = scanner1.nextInt();
                                            compras.removerItem(posicao);
                                        } else {
                                            int codigo;
                                            System.out.println("Qual a código do produto no carrinho?");
                                            Scanner scanner1 = new Scanner(System.in);
                                            codigo = scanner1.nextInt();
                                            for (Item item:compras.getItens()) {
                                                if(item.getProduto().getCodigo() == codigo){
                                                    if(compras.removerItem(item.getProduto()) == true){
                                                        compras.itens.remove(item);
                                                        System.out.println("Produto removido do carrinho!");
                                                    } else {
                                                        System.out.println("Item não encontrado no carrinho!");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        try {
                            if (carrinhoCompras.size() > 0) {
                                System.out.println("Informe o CPF: ");
                                Scanner scanner = new Scanner(System.in);
                                String cpf = scanner.nextLine();
                                if (cpf != null) {
                                    for (CarrinhoCompras compras : carrinhoCompras) {
                                        if (compras.cpf.equals(cpf)) {
                                            System.out.println(compras.getValorTotal());
                                        }
                                    }
                                }
                            } else {

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 5:
                        try {
                            Scanner scanner = new Scanner(System.in);
                            String cpf = scanner.nextLine();
                            if (cpf != null) {
                                boolean invalido = invalidar(cpf);
                                if (invalido == true) {
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
                                if (opcao == 1) {
                                    menu();
                                } else if (opcao == 2) {
                                    App.menu();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 6:
                        //delete();
                        break;
                    default:
                        System.exit(0);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (op > 5);
    }
}
