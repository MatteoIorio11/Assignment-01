package sap.ass01.layered.presentation.dialogs;

import sap.ass01.layered.presentation.RideSimulationControlPanel;
import sap.ass01.layered.services.observers.InputObserver;
import sap.ass01.layered.services.dto.RideDTO;
import sap.ass01.layered.services.observers.StopSimulationObserver;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class RideDialog extends AbstractDialog<RideDTO> {

    private JTextField idEBikeField;
    private JTextField userName;
    private JButton startButton;
    private JButton cancelButton;

    private Optional<StopSimulationObserver> actionObservers;

    public RideDialog(final JFrame parentFrame, final InputObserver<RideDTO> controller) {
        super(parentFrame, controller, "Start Riding an EBike");
        this.actionObservers = Optional.empty();
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
            this.cancelButton.setEnabled(false);
            final var bikeId = idEBikeField.getText();
            final var userRiding = userName.getText();
            final var ride = new RideDTO(bikeId, userRiding);
            this.controller.notifyUpdateRequested(ride); // InputObserver<RideDTO>
            dispose();
            var rdcp = new RideSimulationControlPanel(ride);
            this.actionObservers.ifPresent(rdcp::addObserver);
            rdcp.display();
        });
        
        cancelButton.addActionListener((e) -> dispose());
    }

    public void attachObserver(final StopSimulationObserver obs) {
        this.actionObservers = Optional.of(obs);
    }
}
