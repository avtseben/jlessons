package exceptions;

public class CallMum {
    private static final Phone phone = new Phone();

    public static void main(String[] args) {
        phone.call("Mum");
        try {
            cook();
        } catch (Exception e) {
            System.out.println("Произошло неожиданное событие " + e.getMessage());
        } finally {
            phone.disconnect();
        }

    }

    private static void talkToMum() {
        System.out.println(">>>--> Talking to Mum");
    }

    private static void cook() {

    }

    private static class Phone {
        public void call(String contact) {
            throw new MyCheckedError();
        }

        public void disconnect() {

        }
    }

    private static class MyCheckedException extends Exception {

    }

    private static class MyCheckedError extends Error {

    }
}
