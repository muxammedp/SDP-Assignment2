package application;

import transport.Logistics;
import ui.Button;
import ui.Checkbox;
import ui.GUIFactory;


public class DeliveryApplication {
    private final Logistics logistics;
    private final GUIFactory guiFactory;

    
    public DeliveryApplication(Logistics logistics, GUIFactory guiFactory) {
        this.logistics = logistics;
        this.guiFactory = guiFactory;
    }

    
    public void run() {
        
        Button button = guiFactory.createButton();
        Checkbox checkbox = guiFactory.createCheckbox();
        button.paint();
        checkbox.paint();

        
        logistics.planDelivery("laboratory equipment", "Astana warehouse");
    }
}

