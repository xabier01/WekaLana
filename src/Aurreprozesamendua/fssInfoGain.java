package Aurreprozesamendua;

import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

import java.io.File;
import java.util.Random;

public class fssInfoGain {
    public static void main(String[] args) throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(args[0]);
        Instances data = source.getDataSet();
        //TODO
        data.setClassIndex(data.numAttributes()-1);
        System.out.println("Atributu kopurua train: " + (data.numAttributes() - 1));
        AttributeSelection attributeSelection = new AttributeSelection();
        InfoGainAttributeEval eval = new InfoGainAttributeEval();
        attributeSelection.setEvaluator(eval);
        Ranker ranker = new Ranker();
        //TODO
        ranker.setNumToSelect(2000);
        attributeSelection.setSearch(ranker);
        attributeSelection.setInputFormat(data);
        Instances filteredData = Filter.useFilter(data, attributeSelection);
        System.out.println("Atributu kopuru berria train: " + (filteredData.numAttributes() - 1));
        ArffSaver saver = new ArffSaver();
        saver.setInstances(filteredData);
        saver.setFile(new File(args[1]));
        saver.writeBatch();
    }
}
