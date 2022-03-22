package br.com.alura.alurator.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ContainerIoC {

    public Object getInstance(Class<?> tipoFonte) {
        Stream<Constructor<?>> declaredConstructors =
                Stream.of(tipoFonte.getDeclaredConstructors());

        //get first default contructor
        Optional<Constructor<?>> constructorDefault = declaredConstructors.filter((constructor) -> constructor.getParameterCount() == 0)
                .findFirst();


        try {

            if (constructorDefault.isPresent()) {
                Object instance = constructorDefault.get().newInstance();
                return instance;
            }

            Constructor<?> declaredConstructor = tipoFonte.getDeclaredConstructors()[0];

            List<Object> params = new ArrayList<>();

            for (Parameter param : declaredConstructor.getParameters()) {
                Class<?> parameterType = param.getType();
                params.add(getInstance(parameterType));
            }

            return declaredConstructor.newInstance(params.toArray());

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
