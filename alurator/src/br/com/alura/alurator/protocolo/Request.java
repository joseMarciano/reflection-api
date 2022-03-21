package br.com.alura.alurator.protocolo;

public class Request {
	private String controllerName;

	public Request(String url) {
		String[] urlParts = url.replaceFirst("/", "").split("/");
		this.controllerName = Character.toUpperCase(urlParts[0].charAt(0))  + urlParts[0].substring(1)  + "Controller";
	}

	public String getControllerName() {
		return controllerName;
	}
}
