package sap.ass01.exagonal;

import sap.ass01.exagonal.presentation.EBikeAppView;
import sap.ass01.exagonal.services.ServiceProvider;

public class TempRunner {
    public static void main(String[] args) {
        final ServiceProvider serviceProvider = ServiceProvider.getJSONSerializableServiceProvider();
        // Attach repos to SP
        final EBikeAppView mainView = new EBikeAppView(serviceProvider);
        mainView.display();
    }
}
