package br.com.alura.alurator.protocolo;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String controllerName;
    private String methodName;
    private Map<String, Object> queryParams;

    public Request(String url) {
        String[] urlParts = url.replaceFirst("/", "").split("[?]");
        String[] controleAndMethod = urlParts[0].split("/");


        this.controllerName = Character.toUpperCase(controleAndMethod[0].charAt(0)) + controleAndMethod[0].substring(1) + "Controller";
        this.methodName = controleAndMethod[1];
        this.queryParams = urlParts.length > 1
                ? new QueryParamsBuilder().withParams(urlParts[1]).build()
                : new HashMap<>();
    }

    public String getControllerName() {
        return controllerName;
    }

    public String getMethodName() {
        return this.methodName;
    }

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}
}
