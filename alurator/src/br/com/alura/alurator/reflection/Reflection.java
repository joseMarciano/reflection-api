package br.com.alura.alurator.reflection;

public class Reflection {

    public ClassManipulator refleteClass(String fullQualifierName) {
        try {
            Class<?> aClass = Class.forName(fullQualifierName);
            return new ClassManipulator(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    public static class Builder {
        private Reflection reflection;

        public static Reflection create() {
            return new Reflection();
        }
    }
}
