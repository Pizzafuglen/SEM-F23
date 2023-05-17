FlowController/src/main/java/com/f23/javaserver/FlowServer.java [18:25]:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            hs.setExecutor(tpe);
            hs.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -



WHModule/src/main/java/com/f23/javaserver/WHServer.java [18:26]:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            hs.setExecutor(tpe);
            hs.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -



