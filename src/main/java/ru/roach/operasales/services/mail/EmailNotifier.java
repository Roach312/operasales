package ru.roach.operasales.services.mail;

import org.springframework.stereotype.Service;

@Service
public class EmailNotifier {

    public static void sendSimpleEmail(String toAddress, String subject, String message) {
        System.out.println("@@@MESSAGE@@@"+
                           "\nАдрес: "+ toAddress +
                           "\nТема: "+ subject +
                           "\nСодержание: " + message +
                           "\n@@@MESSAGE@@@");
    }

}
