package br.com.alura.alurator;

import br.com.alura.alurator.conversor.XMLConversor;
import br.com.alura.alurator.protocolo.Request;
import br.com.alura.alurator.reflection.Reflection;

import java.util.Map;

public class Alurator {

    private String basePackage;

    public Alurator(String basePackage) {
        this.basePackage = basePackage;
    }

    public Object executa(String url) {
        Request request = new Request(url);
        String controllerName = request.getControllerName();
        String methodName = request.getMethodName();
        Map<String, Object> queryParams = request.getQueryParams();

        Object returnMethod = Reflection.Builder
                .create()
                .refleteClass(this.basePackage + controllerName)
                .createInstance()
                .getMethod(methodName, queryParams)
                .invoke();

        returnMethod = new XMLConversor().converte(returnMethod);
        return returnMethod;
    }
}
