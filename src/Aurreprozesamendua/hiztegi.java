package Aurreprozesamendua;

import weka.core.Instances;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.converters.ArffLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class hiztegi {
    public static void main(String[] args) throws Exception {
        // Cargar el archivo ARFF después de aplicar StringToWordVector y AttributeSelection
        ArffLoader loader = new ArffLoader();
        loader.setFile(new File(args[0]));
        Instances data = loader.getDataSet();

        /*
        // Obtener los nombres de los atributos
        ArrayList<String> wordAttributes = new ArrayList<>();
        for (int i = 0; i < data.numAttributes(); i++) {
            Attribute attribute = data.attribute(i);
            if (attribute.isNumeric()) { // Verificar si es un atributo numérico (término del documento)
                wordAttributes.add(attribute.name());
            }
        }
        */

        // Obtener el nombre del atributo que contiene los términos del documento
        Attribute wordsAttribute = data.attribute("message"); // Reemplaza "your_words_attribute_name" con el nombre real del atributo

        // Crear un diccionario para almacenar los términos y sus frecuencias
        Map<String, Integer> dictionary = new HashMap<>();

        // Iterar sobre todas las instancias del conjunto de datos
        for (int i = 0; i < data.numInstances(); i++) {
            Instance instance = data.instance(i);
            // Obtener los valores del atributo de palabras
            String[] words = instance.stringValue(wordsAttribute).split("\\s+"); // Dividir la cadena en palabras

            // Actualizar el diccionario con las palabras y sus frecuencias
            for (String word : words) {
                // Si la palabra ya está en el diccionario, incrementar su frecuencia
                if (dictionary.containsKey(word)) {
                    dictionary.put(word, dictionary.get(word) + 1);
                } else { // Si la palabra no está en el diccionario, añadirla con frecuencia 1
                    dictionary.put(word, 1);
                }
            }
        }


        // Imprimir el diccionario
        System.out.println("Diccionario de palabras con frecuencias:");
        for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
