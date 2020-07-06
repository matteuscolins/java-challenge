package br.com.ithappens.carrinho;

import java.math.BigDecimal;

/**
 * Classe que representa um item no carrinho de compras.
 */
public class Item {

    private Produto produto;
    private BigDecimal valorUnitario;
    private int quantidade;

    /**
     * Construtor da classe Item.
     * 
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */
    public Item(Produto produto, BigDecimal valorUnitario, int quantidade) {
        this.produto = produto;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
    }

    /**
     * Retorna o produto.
     *
     * @return Produto
     */
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        /*
            COMENTARIO: Considerar imutabilidade. Criação de um setter para produto permite que um item troque de produto.
            Considerando a lógica de um carrinho de compras um Item do carrinho não pode mudar de produto, é possível adicionar
            um novo item e remover um antigo. Trabalhando com imutabilidade é possível refletir esta regra de negócio no código.
         */
        this.produto = produto;
    }

    /**
     * Retorna o valor unitário do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * Retorna a quantidade dos item.
     *
     * @return int
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
