package application;

import transport.Logistics;
import transport.RoadLogistics;
import transport.SeaLogistics;
import ui.GUIFactory;
import ui.WindowsFactory;
import ui.MacOSFactory;

public class Main {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("Usage: java Main <delivery_mode> <ui_platform>");
            System.err.println("Delivery mode: ROAD or SEA");
            System.err.println("UI platform: WINDOWS or MACOS");
            System.exit(1);
        }

        String deliveryMode = args[0].toUpperCase();
        String uiPlatform = args[1].toUpperCase();

        Logistics logistics;
        GUIFactory guiFactory;


        switch (deliveryMode) {
            case "ROAD":
                logistics = new RoadLogistics();
                break;
            case "SEA":
                logistics = new SeaLogistics();
                break;
            default:
                System.err.println("Unsupported delivery mode: " + deliveryMode);
                System.err.println("Supported modes: ROAD, SEA");
                System.exit(1);
                return;
        }


        switch (uiPlatform) {
            case "WINDOWS":
                guiFactory = new WindowsFactory();
                break;
            case "MACOS":
                guiFactory = new MacOSFactory();
                break;
            default:
                System.err.println("Unsupported UI platform: " + uiPlatform);
                System.err.println("Supported platforms: WINDOWS, MACOS");
                System.exit(1);
                return;
        }


        DeliveryApplication app = new DeliveryApplication(logistics, guiFactory);
        app.run();
    }
}