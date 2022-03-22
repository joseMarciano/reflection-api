package br.com.alura.alurator.conversor;

import java.lang.reflect.Field;
import java.util.Collection;

import static java.lang.String.format;

public class XMLConversor {
    public String converte(Object object) {
        try {
            Class<?> aClass = object.getClass();
            StringBuilder xmlBuilder = new StringBuilder();

            if (object instanceof Collection) {
                Collection<?> collection = (Collection<?>) object;

                xmlBuilder.append("<lista>");

                for (Object o : collection) {
                    String xml = converte(o);
                    xmlBuilder.append(xml);
                }

                xmlBuilder.append("</lista>");

            } else {
                String className = aClass.getSimpleName().toLowerCase();

                xmlBuilder.append(format("<%s>", className));

                for (Field atribute : aClass.getDeclaredFields()) {
                    atribute.setAccessible(true);

                    String atributeName = atribute.getName();
                    Object valueAtribute = atribute.get(object);

                    xmlBuilder.append(format("<%s>", atributeName));
                    xmlBuilder.append(valueAtribute);
                    xmlBuilder.append(format("</%s>", atributeName));
                }

                xmlBuilder.append(format("</%s>", className));
            }

            return xmlBuilder.toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error on generate XML");
        }

    }

}
