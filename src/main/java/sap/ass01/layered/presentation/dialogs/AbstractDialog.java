package sap.ass01.layered.presentation.dialogs;

import sap.ass01.layered.presentation.observers.InputObserver;
import sap.ass01.layered.services.dto.DTO;

import javax.swing.*;

public abstract class AbstractDialog<D extends DTO> extends JDialog {

    protected final InputObserver<D> controller;

    public AbstractDialog(final JFrame parentFrame, final InputObserver<D> controller, final String title) {
        super(parentFrame, title, true);
        this.controller = controller;
        this.initializeComponents();
        this.setUpLayout();
        this.addEventHandlers();
        this.pack();
        this.setLocationRelativeTo(parentFrame);
    }

    protected abstract void initializeComponents();

    protected abstract void setUpLayout();

    protected abstract void addEventHandlers();
}
