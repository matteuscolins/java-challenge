package br.com.ithappens.carrinho;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {

    Collection<Item> itens = new ArrayList<>();
    String cpf;
    BigDecimal valorTotal;

    public CarrinhoCompras() {
    }

    public CarrinhoCompras(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */
    public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) {
        try {
            Item item = new Item(produto, valorUnitario, quantidade);
            itens.add(item);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param produto
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(Produto produto) {
        try {
            for (Item item:itens) {
                if(item.getProduto().equals(produto)){
                    return true;
                }
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na 
     * coleção, em que zero representa o primeiro item.
     *
     * @param posicaoItem
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(int posicaoItem) {
        try {
            if(itens.size() > posicaoItem){
                System.out.println("Posição não existe");
            } else {
                Object[] item = itens.toArray();
                for(int i = 0; i < item.length; i++){
                    if(i == (posicaoItem - 1)){
                        itens.remove(item[i]);
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
        Double valor = 0.00;
        for (Item item:itens) {
            valor = valor + (Double.parseDouble(String.valueOf(item.getValorUnitario())) * item.getQuantidade());
        }
        return new BigDecimal(valor);
    }

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return itens
     */
    public Collection<Item> getItens() {
        return itens;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}