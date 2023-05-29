package com.f23.javaserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class FlowHttpClient {
    public static String ASStartProd(String prodId) {
        URL url;
        StringBuilder content;
        try {
            url = new URL("http://localhost:8003/3/startProd?" + prodId);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "Initiated prod with ID " + prodId;
    }

    public static String AGVMoveToAssemblyStation() {
        URL url;
        StringBuilder content;
        try {
            url = new URL("http://localhost:8002/2/moveToAssemblyStationOperation");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "Moved to AssemblyStation";
    }

    public static String AGVMoveToWarehouse() {
        URL url;
        StringBuilder content;
        try {
            url = new URL("http://localhost:8002/2/moveToWarehouseOperation");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "Moved to Warehouse";
    }

    public static String AGVPickAssemblyStation() {
        URL url;
        StringBuilder content;
        try {
            url = new URL("http://localhost:8002/2/pickAssemblyOperation");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "Assembly picked";
    }

    public static String AGVPickWarehouse() {
        URL url;
        StringBuilder content;
        try {
            url = new URL("http://localhost:8002/2/pickWarehouseOperation");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Warehouse picked";
    }

    public static String AGVPutAssemblyStation() {
        URL url;
        StringBuilder content;
        try {
            url = new URL("http://localhost:8002/2/putAssemblyOperation");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Assembly Station put";
    }

    public static String AGVPutWarehouse() {
        URL url;
        StringBuilder content;
        try {
            url = new URL("http://localhost:8002/2/putWarehouseOperation");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Warehouse put";
    }

    public static String AGVMoveToCharger() {
        URL url;
        try {
            url = new URL("http://localhost:8002/2/moveToChargerOperation");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "AGV move to charger";
    }

    public static String WHPutTray(int trayId, String itemName) {
        URL url;
        StringBuilder content;
        try {
            url = new URL("http://localhost:8001/1/putSpecificTray?" + trayId + "?" + itemName);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content.toString();
    }

    public static String WHPickTray(int trayId) {
        URL url;
        StringBuilder content;
        try {
            url = new URL("http://localhost:8001/1/pickSpecificTray?" + trayId);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content.toString();
    }

    public static String WHGetInventory() {
        URL url;
        StringBuilder content;
        try {
            url = new URL("http://localhost:8001/1/getInventory");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content.toString();
    }
}
