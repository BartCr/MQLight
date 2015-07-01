package be.cremers.mqlight.client;

public class ResponseHandler {
    private byte[] rsp = null;

    public synchronized boolean handleResponse(byte[] rsp) {
        this.rsp = rsp;
        this.notify();
        return true;
    }

    public synchronized void waitForResponse() {
        while(this.rsp == null) {
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }

        System.out.println(new String(this.rsp));
    }
}