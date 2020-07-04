package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.servicos.matchers.MatchersProprios;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.servicos.matchers.MatchersProprios.*;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class LocacaoServiceTest {

//    @Rule
//    public ErrorCollector error = new ErrorCollector();

    LocacaoService service;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        System.out.println("Before");
        service = new LocacaoService();
    }

//    @After
//    public void after() {
//        System.out.println("After");
//    }
//
//    @BeforeClass
//    public static void setupClass() {
//        System.out.println("Before Class");
//    }
//
//    @AfterClass
//    public static void afterClass() {
//        System.out.println("After Class");
//    }

    @Test
    public void deveAlugarFilme() throws Exception {
        Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
        //cenario
        Usuario usuario = new Usuario("Usuario");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        //acao
        Locacao locacao = null;
        locacao = service.alugarFilme(usuario, filmes);

        //verificacao
        assertThat(locacao.getValor(), is(equalTo(5.0)));
        assertThat(locacao.getDataLocacao(), ehHoje());
        assertThat(locacao.getDataRetorno(), ehHojeComDiferencaDias(1));

        //error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        //error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        //error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));


        //Assert.assertEquals(5.0, locacao.getValor(), 0.01);
        //assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        //assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void naoDeveAlugarFilmesSemEstoque() throws Exception {
        //cenario
        Usuario usuario = new Usuario("Usuario");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));

        //acao
        service.alugarFilme(usuario, filmes);
    }

    @Test
    public void naoDeveAlugarFilmesSemUsuario() throws FilmeSemEstoqueException {
        //cenario
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        //acao
        try {
            service.alugarFilme(null, filmes);
            fail();
        } catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Usuario vazio"));
        }
    }

    @Test
    public void naoDeveAlugarFilmesSemFilmes() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario("Usuario");
        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        //acao
        service.alugarFilme(usuario, null);

    }


    @Test
    public void naoDeveDevolverFilmeNoDomingo() throws LocadoraException, FilmeSemEstoqueException {
        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(
                new Filme("Filme 1", 2, 4.0)
        );

        //acao
        Locacao resultado = service.alugarFilme(usuario, filmes);


        //verificacao
        assertThat(resultado.getDataRetorno(), caiEm(Calendar.MONDAY));
        assertThat(resultado.getDataRetorno(), caiNumaSegunda());
    }



}
