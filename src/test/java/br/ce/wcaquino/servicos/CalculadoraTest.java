package br.ce.wcaquino.servicos;

import br.ce.wcaquino.daos.LocacaoDAO;
import br.ce.wcaquino.daos.LocacaoDAOFake;
import br.ce.wcaquino.exceptions.DivisaoPorZeroException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculadoraTest {

    Calculadora calculadora;

    @Before
    public void setup() {
        calculadora = new Calculadora();
    }


    @Test
    public void deveSomarDoisValores() {
        //cenario
        int a = 5;
        int b = 3;

        //acao
        int resultado = calculadora.somar(a, b);

        //verificacao
        Assert.assertEquals(8, resultado);
    }

    @Test
    public void deveSubtrairDoisValores() {
        //cenario
        int a = 8;
        int b = 5;

        //acao
        int resultado = calculadora.subtrair(a, b);

        //verificacao
        Assert.assertEquals(3, resultado);
    }

    @Test
    public void deveDividirDoisValores() throws DivisaoPorZeroException {
        int a = 6;
        int b = 3;

        int resultado = calculadora.dividir(a, b);

        Assert.assertEquals(2, resultado);
    }

    @Test(expected = DivisaoPorZeroException.class)
    public void deveLancarExcecaoAoDividirPorZero() throws DivisaoPorZeroException {
        int a = 10;
        int b = 0;

        calculadora.dividir(a, b);

    }

    @Test
    public void DeveDividir() {
        String a = "6";
        String b = "3";

        int resultado = calculadora.dividir(a, b);

        Assert.assertEquals(2, resultado);
    }

}
