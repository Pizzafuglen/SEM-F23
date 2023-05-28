ASModule/src/main/java/org/example/ASHandler.java [20:32]:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private void httpResponse(HttpExchange he, String reVal) throws IOException {
        OutputStream os = he.getResponseBody();

        String hr = StringEscapeUtils.escapeHtml4(reVal);

        he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        he.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        he.sendResponseHeaders(200, hr.length());

        os.write(hr.getBytes());
        os.flush();
        os.close();
    }
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -



WHModule/src/main/java/com/f23/javaserver/WHHandler.java [28:40]:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private void httpResponse(HttpExchange he, String reVal) throws IOException {
        OutputStream os = he.getResponseBody();

        String hr = StringEscapeUtils.escapeHtml4(reVal);

        he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        he.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        he.sendResponseHeaders(200, hr.length());

        os.write(hr.getBytes());
        os.flush();
        os.close();
    }
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -



