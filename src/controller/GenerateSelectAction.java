package controller;

import model.Model;
import model.generators.Generator;
import model.generators.GeneratorAlgorithms;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateSelectAction implements ActionListener {

    private final Model model;
    private final View view;

    public GenerateSelectAction(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String algoString = ((JComboBox) e.getSource()).getSelectedItem().toString();
        GeneratorAlgorithms algoEnum = GeneratorAlgorithms.valueOf(algoString);
        Class<Generator> algoClass = (Class<Generator>) algoEnum.getClazz();
        this.model.changeSelectedGenerationAlgo(algoClass);
    }
}
