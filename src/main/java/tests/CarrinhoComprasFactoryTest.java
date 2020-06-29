package tests;

import br.com.ithappens.carrinho.CarrinhoCompras;
import br.com.ithappens.carrinho.CarrinhoComprasFactory;
import org.junit.Assert;
import org.junit.Test;

public class CarrinhoComprasFactoryTest {

    @Test
    public void criar(){
        CarrinhoComprasFactory factory = new CarrinhoComprasFactory();
        CarrinhoCompras carrinhoCompras = factory.criar("12345678910");
        Assert.assertEquals(carrinhoCompras.getCpf(), "12345678910");
    }

}
