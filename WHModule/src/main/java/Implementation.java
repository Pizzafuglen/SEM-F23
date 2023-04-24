import com.baeldung.soap.ws.client.generated.*;
public class Implementation {
    public static void main(String[] args) {
        IEmulatorService_Service ies = new IEmulatorService_Service();

        ies.getBasicHttpBindingIEmulatorService().pickItem(1);
        ies.getBasicHttpBindingIEmulatorService().insertItem(1,"sommerfugl");

        System.out.println(ies.getBasicHttpBindingIEmulatorService().getInventory());
    }
}
