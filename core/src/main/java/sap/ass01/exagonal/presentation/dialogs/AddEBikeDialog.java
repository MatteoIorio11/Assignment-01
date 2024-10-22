package sap.ass01.exagonal.presentation.dialogs;

import sap.ass01.exagonal.services.observers.InputObserver;
import sap.ass01.exagonal.services.dto.EBikeDTO;

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
        final JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        this.addLabelToPanel(inputPanel, this.idField, "E-Bike ID:");
        this.addLabelToPanel(inputPanel, this.xCoordField, "E-Bike location - X coord:");
        this.addLabelToPanel(inputPanel, this.yCoordField, "E-Bike location - Y coord:");

        final JPanel buttonPanel = new JPanel();
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
