package sap.ass01.layered.presentation.observers;

import sap.ass01.layered.business.User;

public interface UserObserver extends InputObserver<User> {
    @Override
    public void notifyUpdateRequested(User newValue);
}
