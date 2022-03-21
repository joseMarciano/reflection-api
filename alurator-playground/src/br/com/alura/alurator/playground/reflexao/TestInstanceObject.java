package br.com.alura.alurator.playground.reflexao;

import br.com.alura.alurator.playground.controle.Controle;

public class TestInstanceObject {
    public static void main(String[] args) {
        Class<Controle> controleClass = Controle.class;
        Controle controle = new Controle();
        Class<? extends Controle> aClass = controle.getClass();
    }
}
