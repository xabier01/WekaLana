package Aurreprozesamendua;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

import java.io.File;

public class fssInfoGain {
    public static void main(String[] args) throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(args[0]);
        Instances data = source.getDataSet();
        data.setClassIndex(data.numAttributes()-1);
        System.out.println("Atributu kopurua train: " + (data.numAttributes() - 1));
        AttributeSelection attributeSelection = new AttributeSelection();
        attributeSelection.setInputFormat(data);
        data = Filter.useFilter(data, attributeSelection);
        System.out.println("Atributu kopurua berria train: " + (data.numAttributes() - 1));
        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);
        saver.setFile(new File(args[1]));
        saver.writeBatch();
    }
}
