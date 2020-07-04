package br.ce.wcaquino.servicos.matchers;

import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class DataDiferencaDiasMatcher extends TypeSafeMatcher<Date> {

    private final Integer dias;

    public DataDiferencaDiasMatcher(Integer dias) {
        this.dias = dias;
    }

    @Override
    public void describeTo(Description description) {

    }

    @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(dias));
    }
}
