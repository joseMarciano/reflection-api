package br.com.alura.alurator.playground.controle;

import java.util.Arrays;
import java.util.List;

public class Controle {
    private List<String> lista = Arrays.asList("item 1", "item 2", "item 3");

    public Controle() {
    }

    private Controle(String s) {
    }

    public Controle(String s, String s1) {
    }

    public List<String> getLista() {
        return lista;
    }
}
