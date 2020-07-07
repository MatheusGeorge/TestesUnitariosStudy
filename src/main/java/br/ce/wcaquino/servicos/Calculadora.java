package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.DivisaoPorZeroException;

public class Calculadora {

    public int somar(int a, int b) {
        System.out.println("estou executando o metodo somar");
        return a + b;
    }

    public int subtrair(int a, int b) {
        return a - b;
    }

    public int dividir(int a, int b) throws DivisaoPorZeroException {
        if(b == 0) {
            throw new DivisaoPorZeroException();
        }
        return a / b;
    }

    public int dividir(String a, String b) {
        return Integer.parseInt(a) / Integer.parseInt(b);
    }

    public void imprime() {
        System.out.println("Passei aqui");
    }

}
