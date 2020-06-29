package tests;

import br.com.ithappens.carrinho.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProdutoFactoryTest {

    @Test
    public void criar(){
        List<Produto> produtos = new ArrayList<>();
        Produto produto1 = new Produto((long) 1, "Café");
        produtos.add(produto1);
        Produto produto2 = new Produto((long) 2, "Leite");
        produtos.add(produto2);
        Produto produto3 = new Produto((long) 3, "Arroz");
        produtos.add(produto3);
        Assert.assertEquals(produto1.getDescricao(), "Café");
        Assert.assertEquals(produto2.getDescricao(), "Leite");
        Assert.assertEquals(produto3.getDescricao(), "Arroz");
    }

    @Test
    public void consultar(){
        List<Produto> produtos = new ArrayList<>();
        Produto produto1 = new Produto((long) 1, "Café");
        produtos.add(produto1);
        Produto produto2 = new Produto((long) 2, "Leite");
        produtos.add(produto2);
        Produto produto3 = new Produto((long) 3, "Arroz");
        produtos.add(produto3);
        Assert.assertEquals(produtos.size(), 3);
    }

    @Test
    public void edita(){
        List<Produto> produtos = new ArrayList<>();
        Produto produto1 = new Produto((long) 1, "Café");
        produtos.add(produto1);
        Produto produto2 = new Produto((long) 2, "Leite");
        produtos.add(produto2);
        Produto produto3 = new Produto((long) 3, "Arroz");
        produtos.add(produto3);
        for (Produto produto:produtos) {
            if(produto.getCodigo() == (3)){
                produto.setDescricao("Arroz Integral");
            }
        }
        Assert.assertEquals(produto3.getDescricao(), "Arroz Integral");
    }
}
