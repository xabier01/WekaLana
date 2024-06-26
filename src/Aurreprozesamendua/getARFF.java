package Aurreprozesamendua;

import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.*;

public class getARFF{
    public static void main(String[] args) throws Exception{
        try {
            if (args.length != 2){
                System.out.println("Helburua: .txt fitxategi bat .arff fitxategi formatura egokitzea/pasatzea.");
                System.out.println("Aurre-baldintzak: ");
                System.out.println("    Sartu beharreko argumentuak hurrengoak dira: ");
                System.out.println("    0. txt fitxategiaren path-a.");
                System.out.println("    1. arff fitxategiaren path-a.");
                System.out.println("Post-baldintzak: ");
                System.out.println("    Sartutako .arff fitxategian .txt-ko datuak izatea formatu egokian.");
                System.out.println("    Sartutako 0 argumentua train bada --> \"testua\", klasea egituradun .arff fitxategia lortu.");
                System.out.println("    Sartutako 0 argumentua dev bada --> \"testua\", klasea egituradun .arff fitxategia lortu.");
                System.out.println("    Sartutako 0 argumentua test (blind) bada --> Test blind-ean klasea iragarri nahi dugunez .arff fitxategian testua baiño ez dugu izango.");
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(args[0]));
                BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));

                // Escribir encabezado del archivo ARFF
                writer.write("@relation spam\n\n");
                writer.write("@attribute message string\n\n");
                writer.write("@attribute klasea {spam, ham}\n");
                writer.write("@data\n");

                String line;
                while ((line = reader.readLine()) != null) {
                    // Separar la línea en clase y mensaje
                    String[] parts = line.split("\t", 2);
                    String clase = parts[0];
                    String mensaje = parts[1];

                    // Escapar comillas dobles en el mensaje
                    mensaje = mensaje.replace("\"", "\\\"");

                    if (args[0].contains("train")) {
                        // Escribir la línea en el archivo ARFF
                        writer.write("\"" + mensaje + "\", " + clase + "\n");
                    } else if (args[0].contains("dev")) {
                        // Escribir la línea en el archivo ARFF
                        writer.write("\"" + mensaje + "\", " + clase + "\n");
                    } else if (args[0].contains("test")) {
                        // Escribir la línea en el archivo ARFF
                        writer.write("\"" + parts[0] + "\", ?\n");
                    }

                }

                reader.close();
                writer.close();
                ConverterUtils.DataSource source = new ConverterUtils.DataSource(args[1]);
                Instances data = source.getDataSet();
                data.setClassIndex(data.numAttributes()-1);
                System.out.println("Sortutako .arff-ak dituen instantzia kopurua: ");
                System.out.println(data.numInstances());
                System.out.println("Conversión completada.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}