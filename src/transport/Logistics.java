package transport;


public abstract class Logistics {
    
    public abstract Transport createTransport();

    
    public void planDelivery(String cargo, String destination) {
        Transport transport = createTransport();
        transport.deliver(cargo, destination);
    }
}
