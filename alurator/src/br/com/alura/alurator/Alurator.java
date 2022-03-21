package br.com.alura.alurator;

import br.com.alura.alurator.protocolo.Request;
import br.com.alura.alurator.reflection.Reflection;

public class Alurator {

    private String basePackage;

    public Alurator(String basePackage) {
        this.basePackage = basePackage;
    }

    public Object executa(String url) {
        Request request = new Request(url);
        String controllerName = request.getControllerName();

        Object controleInstance = Reflection.Builder
                .create()
                .refleteClass(this.basePackage + controllerName)
                .getDefaultConstructor()
                .invoke();


        System.out.println(controleInstance);
        return null;
    }
}
