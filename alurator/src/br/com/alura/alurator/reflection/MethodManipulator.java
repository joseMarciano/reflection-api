package br.com.alura.alurator.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class MethodManipulator {

    private Object instance;
    private Method method;
    private Map<String, Object> queryParams;

    public MethodManipulator(Object instance, Method method, Map<String, Object> queryParams) {
        this.instance = instance;
        this.method = method;
        this.queryParams = queryParams;
    }

    public Object invoke() {
        try {
            List<Object> params = new ArrayList<>();
            Stream.of(method.getParameters())
                    .forEach(parameter -> params.add(queryParams.get(parameter.getName())));
            return method.invoke(instance, params.toArray());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
