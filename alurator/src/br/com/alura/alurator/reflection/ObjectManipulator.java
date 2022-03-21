package br.com.alura.alurator.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ObjectManipulator {
    private Object instance;

    public ObjectManipulator(Object instance) {
        this.instance = instance;
    }

    public MethodManipulator getMethod(String name, Map<String, Object> queryParams) {
        Stream<Method> declaredStreamMethods = Stream.of(this.instance.getClass().getDeclaredMethods());

        Method selectecMethod = declaredStreamMethods.filter(getMatchMethod(name, queryParams))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No method was not found"));


        return new MethodManipulator(instance, selectecMethod, queryParams);

    }

    private Predicate<Method> getMatchMethod(String name, Map<String, Object> queryParams) {

        Predicate<Parameter> allParameterMatchWithAllQueryParams = parameter -> queryParams.containsKey(parameter.getName())
                && queryParams.get(parameter.getName()).getClass().equals(parameter.getType());

        return method -> name.equals(method.getName())
                && method.getParameterCount() == queryParams.values().size()
                && Stream.of(method.getParameters()).allMatch(allParameterMatchWithAllQueryParams);

    }

}
