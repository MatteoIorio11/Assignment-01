package sap.ass01.layered.presentation.dialogs;

import sap.ass01.layered.presentation.observers.InputObserver;
import sap.ass01.layered.services.dto.RideDTO;

import javax.swing.*;
import java.awt.*;

public class RideDialog extends AbstractDialog<RideDTO> {

    private JTextField idEBikeField;
    private JTextField userName;
    private JButton startButton;
    private JButton cancelButton;
    private String userRiding;
    private String bikeId;

    public RideDialog(final JFrame parentFrame, final InputObserver<RideDTO> controller) {
        super(parentFrame, controller, "Start Riding an EBike");
    }

    @Override
    protected void initializeComponents() {
        idEBikeField = new JTextField(15);
        userName = new JTextField(15);
        startButton = new JButton("Start Riding");
        cancelButton = new JButton("Cancel");
    }

    @Override
    protected void setUpLayout() {
        final JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        this.addLabelToPanel(inputPanel, this.userName, "User name:");
        this.addLabelToPanel(inputPanel, this.idEBikeField, "E-Bike to ride:");

        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void addEventHandlers() {
        startButton.addActionListener((e) -> {
                bikeId = idEBikeField.getText();
	            userRiding = userName.getText();
	            cancelButton.setEnabled(false);
                this.controller.notifyUpdateRequested(new RideDTO(bikeId, userRiding));
	            dispose();
        });
        
        cancelButton.addActionListener((e) -> dispose());
    }
}
