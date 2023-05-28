package com.f23.javaserver;

import com.baeldung.soap.ws.client.generated.IEmulatorService_Service;

public class WSDLClient {
    private static final IEmulatorService_Service ies = new IEmulatorService_Service();

    public static String getWHInventory() {
        return ies.getBasicHttpBindingIEmulatorService().getInventory();
    }

    public static String pickSpecificTray(int trayId) {
        ies.getBasicHttpBindingIEmulatorService().pickItem(trayId);
        return "Picked tray: " + trayId + " successfully";
    }

    public static String putSpecificTray(int trayId, String itemName) {
        ies.getBasicHttpBindingIEmulatorService().insertItem(trayId, itemName);
        return "Item: " + itemName + " placed successfully into tray: " + trayId;
    }
}
