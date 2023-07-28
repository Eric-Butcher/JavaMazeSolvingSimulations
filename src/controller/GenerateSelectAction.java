package controller;

import model.Model;
import model.generators.Generator;
import model.generators.GeneratorAlgorithms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateSelectAction implements ActionListener {

    private final Model model;

    public GenerateSelectAction(Model model){
        this.model = model;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String algoString = ((JComboBox)e.getSource()).getSelectedItem().toString();
        GeneratorAlgorithms algoEnum = GeneratorAlgorithms.valueOf(algoString);
        Class<Generator> algoClass = (Class<Generator>) algoEnum.getClazz();
        this.model.changeSelectedGenerationAlgo(algoClass);
    }
}
