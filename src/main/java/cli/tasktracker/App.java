package cli.tasktracker;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static final Scanner scanner = new Scanner(System.in);
    public static RequestHandler requestHandler;

    public static void main(String[] args) {
        boolean isCheck = true;
        do {
            System.out.print("<Task-Track> ");
            String request = scanner.nextLine();
            requestHandler.handleRequest(request);

            if (request.equals("\\exit")) {
                isCheck = false;
            }
            ;

        } while (isCheck);
    }
}
