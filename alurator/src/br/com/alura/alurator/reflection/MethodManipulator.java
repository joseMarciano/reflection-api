package br.com.alura.alurator.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MethodManipulator {

    private Object instance;
    private Method method;

    public MethodManipulator(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }

    public Object invoke(){
        try {
            return method.invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
