package br.com.alura.alurator.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorManipulator {
    private Constructor<?> constructor;

    public ConstructorManipulator(Constructor<?> constructor) {
        this.constructor = constructor;
    }

    public Object invoke() {
        try {
            return this.constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
