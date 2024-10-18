package sap.ass01.layered.presentation.dialogs;

import sap.ass01.layered.presentation.observers.InputObserver;
import sap.ass01.layered.services.EBikeService;
import sap.ass01.layered.services.dto.EBikeDTO;

import javax.swing.*;
import java.awt.*;

public class AddEBikeDialog extends AbstractDialog<EBikeDTO> {

    private JTextField idField;
    private JTextField xCoordField;
    private JTextField yCoordField;
    private JButton okButton;
    private JButton cancelButton;

    public AddEBikeDialog(final JFrame parentFrame, final InputObserver<EBikeDTO> controller) {
        super(parentFrame, controller, "Adding E-Bike");
    }

    @Override
    protected void initializeComponents() {
        idField = new JTextField(15);
        xCoordField = new JTextField(15);
        yCoordField = new JTextField(15);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
    }

    @Override
    protected void setUpLayout() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.add(new JLabel("E-Bike ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("E-Bike location - X coord:"));
        inputPanel.add(xCoordField);
        inputPanel.add(new JLabel("E-Bike location - Y coord:"));
        inputPanel.add(yCoordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }


    @Override
    protected void addEventHandlers() {
        okButton.addActionListener((e) -> {
            final var bikeDto = new EBikeDTO(
                idField.getText(),
                xCoordField.getText(),
                yCoordField.getText()
            );
            this.controller.notifyUpdateRequested(bikeDto);
            dispose();
        });

        cancelButton.addActionListener((e) -> dispose());
    }
}
