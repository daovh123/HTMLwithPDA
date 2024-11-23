package org.example.htmlfx;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Checked {
    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@gmail.com";
        return email.matches(emailRegex);
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("0\\d{9}"); // Bắt đầu bằng chữ số '0' và theo sau là 9 chữ số.
    }

}
