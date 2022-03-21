package br.com.alura.alurator.reflection;

import java.lang.reflect.Constructor;

public class ClassManipulator {

    private Class<?> aClass;

    public ClassManipulator(Class<?> aClass) {
        this.aClass = aClass;
    }

    public ConstructorManipulator getDefaultConstructor() {
        try {
            return new ConstructorManipulator(this.aClass.getConstructor());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
