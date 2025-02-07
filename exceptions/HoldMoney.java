package exceptions;

import static java.lang.System.out;

public class HoldMoney {

    private static final FlightBookingService flightBookingService = new FlightBookingService();

    public static void main(String[] args) {
        Integer holdTransactionId = holdMoney();
        try {
            String ticketNumber = flightBookingService.bookTicket(); // Бронируем билет
            sendTicketToEmail(ticketNumber); // Отправляем билеты на почту
            completeTransaction(holdTransactionId); // Списываем средства
        } catch (Exception exception) {
            out.println("Исключение при попытке забронировать билет " + exception.getMessage());
            exception.printStackTrace();
        } finally {
            unHoldTransaction(holdTransactionId);
        }
    }


    private static Integer holdMoney() {
        return 12342134;
    }

    private static void unHoldTransaction(Integer transaction) {
    }

    private static void completeTransaction(Integer holdTransactionId) {

    }

    private static void sendTicketToEmail(String ticketNumber) {

    }

    private static class FlightBookingService {
        public String bookTicket() {
            throw new RuntimeException("Connection timeout");
        }
    }
}
