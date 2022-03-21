package br.com.alura.alurator.reflection;

import java.lang.reflect.Method;

public class ObjectManipulator {
    private Object instance;

    public ObjectManipulator(Object instance) {
        this.instance = instance;
    }

    public MethodManipulator getMethod(String name) {
        try {
            Method method = this.instance.getClass().getDeclaredMethod(name);
            return new MethodManipulator(instance, method);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
