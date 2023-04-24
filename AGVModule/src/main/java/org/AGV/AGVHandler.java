package org.AGV;


import java.io.IOException;

public class AGVHandler {

    int idle = 1;
    int executing = 2;
    int charging = 3;


    private String moveToChargerOperation() throws IOException {

        if (httpExchange.getRequestURI().toString().contains("MoveToChargerOperation")) {
            Write.writeCntrlCmd(Integer.parseInt(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1]));

            // efter split smid i string

            String json = "MoveToChargerOperation";
            return AGVController.handlePostRequest(json);





        /*
        if (URL.contains("moveToChargerOperation) {
            insert into json = "{\"State\":1,\"Program name\":\"MoveToChargerOperation\"}"
                    */
    }



        /* Executing
        if (URL.contains("State:2")) {
            insert into json
*/

        return null;
    }
}