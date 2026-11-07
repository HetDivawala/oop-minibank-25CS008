@FunctionalInterface
interface Notifier
{
    void send(String msg);
}

interface Urgent
{
}

public class NotificationSender {
    public static void main(String[] args){

        Notifier[] senders = {
            msg -> System.out.println("Email: " + msg),
            (Notifier & Urgent) msg -> System.out.println("SMS: " + msg),
            msg -> System.out.println("Push: " + msg)
        };

        String message = "Server maintenance at 10 PM";

        for(Notifier n : senders){
            n.send(message);

            if(n instanceof Urgent){
                n.send(message);
            }
        }
    }
}