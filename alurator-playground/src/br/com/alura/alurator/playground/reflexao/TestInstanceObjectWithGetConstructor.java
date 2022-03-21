package br.com.alura.alurator.playground.reflexao;

import br.com.alura.alurator.playground.controle.SubControle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestInstanceObjectWithGetConstructor {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<SubControle> subControleClass1 = SubControle.class;
        Class<?> subControleClass2 = Class.forName("br.com.alura.alurator.playground.controle.SubControle");
        Class<?> controleClass1 = Class.forName("br.com.alura.alurator.playground.controle.Controle");

        //Return the default cosntructor (public constructors)
        Constructor<SubControle> subControleConstructor1 = subControleClass1.getConstructor();

        //Retusn the constructor that matches with the parameters (include private constructors)
        Constructor<SubControle> subControleConstructor2 = subControleClass1.getDeclaredConstructor(String.class);

        //Set accessible tru because this constructor is private (be careful with this method because some JVM block this using Security Manager)
        subControleConstructor2.setAccessible(true);
        subControleConstructor2.newInstance("Teste");


    }
}
