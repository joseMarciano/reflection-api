package br.com.alura.alurator.conversor;

import br.com.alura.alurator.conversor.anotacao.NameTagXML;

import java.lang.reflect.Field;
import java.util.Collection;

import static java.lang.String.format;

public class XMLConversor {

    private StringBuilder xmlBuilder;

    public XMLConversor() {
        this.xmlBuilder = new StringBuilder();
    }

    public String converte(Object object) {
        try {
            Class<?> aClass = object.getClass();

            if (object instanceof Collection) {
                Collection<?> collection = (Collection<?>) object;

                appendNameXML(collection.getClass(), false);

                for (Object o : collection) {
                    String xml = converte(o);
                    this.xmlBuilder.append(xml);
                }

                appendNameXML(collection.getClass(), true);

            } else {

                appendNameXML(aClass, false);
                for (Field atribute : aClass.getDeclaredFields()) {
                    atribute.setAccessible(true);
                    Object valueAtribute = atribute.get(object);
                    appendNameXML(atribute, false);
                    xmlBuilder.append(valueAtribute);
                    appendNameXML(atribute, true);
                }
                appendNameXML(aClass, true);
            }

            return xmlBuilder.toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error on generate XML");
        }

    }


    public void appendNameXML(Object object, boolean isEnd) {

        if (object instanceof Class) {
            Class<?> aClass = (Class<?>) object;
            appendClassTypeXml(aClass, isEnd);
            return;
        }

        if (object instanceof Field) {
            Field field = (Field) object;
            appendFieldTypeXml(field, isEnd);
            return;
        }

        throw new RuntimeException("Error on parse to XML");


    }

    private void appendFieldTypeXml(Field field, boolean isEnd) {
        boolean annotationPresent = field.isAnnotationPresent(NameTagXML.class);
        if (annotationPresent) {
            NameTagXML annotation = field.getAnnotation(NameTagXML.class);
            if (isEnd) {
                xmlBuilder.append(format("</%s>", annotation.value()));
                return;
            }
            xmlBuilder.append(format("<%s>", annotation.value()));

        } else {
            if (isEnd) {
                xmlBuilder.append(format("</%s>", field.getName().toLowerCase()));
                return;
            }
            xmlBuilder.append(format("<%s>", field.getName().toLowerCase()));
        }

    }

    private void appendClassTypeXml(Class<?> aClass, boolean isEnd) {
        boolean annotationPresent = aClass.isAnnotationPresent(NameTagXML.class);
        if (annotationPresent) {
            NameTagXML annotation = aClass.getAnnotation(NameTagXML.class);
            if (isEnd) {
                xmlBuilder.append(format("</%s>", annotation.value()));
                return;
            }
            xmlBuilder.append(format("<%s>", annotation.value()));

        } else {
            if (isEnd) {
                xmlBuilder.append(format("</%s>", aClass.getSimpleName().toLowerCase()));
                return;
            }
            xmlBuilder.append(format("<%s>", aClass.getSimpleName().toLowerCase()));
        }
    }

}
