package sap.ass01.layered.presentation;

import sap.ass01.layered.services.dto.RideDTO;
import sap.ass01.layered.services.observers.ActionObserver;
import sap.ass01.layered.services.observers.ActionObserverSource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RideSimulationControlPanel extends JFrame implements ActionObserverSource {

    private final List<ActionObserver> observers = new ArrayList<>();

    public RideSimulationControlPanel(final RideDTO ride) {
    	setSize(400, 200);

        JButton stopButton = new JButton("Stop Riding");
    	
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.add(new JLabel("Rider name: " + ride.userId()));
        inputPanel.add(new JLabel("Riding e-bike: " + ride.bikeId()));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(stopButton);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        stopButton.addActionListener((e) -> {
            this.observers.forEach(ActionObserver::notifyUpdateRequested);
            dispose();
        });
    }
    
    public void display() {
    	SwingUtilities.invokeLater(() -> {
    		this.setVisible(true);
    	});
    }

    @Override
    public void addObserver(final ActionObserver observer) {
        this.observers.add(observer);
    }
}
