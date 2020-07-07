package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Locacao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class CalculadoraMockTest {

    @Mock
    private Calculadora calculadoraMock;

    @Spy
    private Calculadora calculadoraSpy;

//    @Spy
//    private EmailService emailSpy;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void devoMostrarDiferencaEntreMockSpy (){
        Mockito.when(calculadoraMock.somar(1, 2)).thenReturn(5);
        //Mockito.when(calculadoraSpy.somar(1, 2)).thenReturn(5);
        Mockito.doReturn(5).when(calculadoraSpy).somar(1, 2);
        Mockito.doNothing().when(calculadoraSpy).imprime();
        System.out.println("Mock " + calculadoraMock.somar(1, 2));
        System.out.println("Spy " + calculadoraSpy.somar(1, 2));

        System.out.println("Mock");
        calculadoraMock.imprime();
        System.out.println("Spy");
        calculadoraSpy.imprime();
    }

    @Test
    public void teste() {
        Calculadora calculadora = Mockito.mock(Calculadora.class);
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        //Mockito.when(calculadora.somar(Mockito.eq(1), Mockito.anyInt())).thenReturn(5);
        Mockito.when(calculadora.somar(argumentCaptor.capture(), argumentCaptor.capture())).thenReturn(5);

        Assert.assertEquals(5 , calculadora.somar(1, 8));
        //System.out.println(argumentCaptor.getAllValues());
    }

}
