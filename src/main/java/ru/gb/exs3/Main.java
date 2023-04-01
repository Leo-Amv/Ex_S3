package ru.gb.exs3;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            try {
                String userInput = input("\nEnter the data separated by a SPACE in the following format:" +
                        "\n\t<Surname>,<Name>,<Patronymic>" +
                        "\n\t<Date of birth in this format dd.mm.yyyy>" +
                        "\n\t<Phone number. Integer>" +
                        "\n\t<Gender. f - female. или m - male>\n--->");
                String[] userData = checkData(userInput);
                checkUserName(userData);
                checkBirthDate(userData);
                checkNumber(userData);
                checkGender(userData);
                writeData(userData);
                String exit = input("\nDo you want to continue?:\n\tEnter 'y' or 'n':").toLowerCase();
                if (exit.equals("n")) {
                    flag = false;
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (DataException e) {
                System.out.println(e.getMessage());
            } catch (UserNameException e) {
                System.out.println(e.getMessage());
            } catch (GenderException e) {
                System.out.println(e.getMessage());
            }
        }


    }

    private static String[] checkData(String userInput) {
        int length = 4;
        String[] result = userInput.split(" ");
        if (result.length != length) {
            throw new DataException();
        }
        return result;
    }

    private static String[] checkUserName(String[] data) {
        int length = 3;
        String[] result = data[0].split(",");
        if (result.length != length) {
            throw new UserNameException();
        }
        return result;
    }

    private static void checkBirthDate(String[] data) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        LocalDate ld = LocalDate.parse(data[1], formatter);
    }

    private static void checkNumber(String[] data) throws NumberFormatException {
        Integer.parseInt(data[2]);
    }

    private static void checkGender(String[] data) {
        if (!data[3].equals("f") && !data[3].equals("m")) {
            throw new GenderException();
        }
    }

    public static void writeData(String[] data) {
        String fileName = checkUserName(data)[1];
        String userData = convertToString(data);
        try (FileWriter writer = new FileWriter(String.format("src/main/java/ru/gb/exs3/%s.txt", fileName), true)) {
            writer.append(userData);
            writer.append("\n");
            System.out.printf("\nThe entered data is correct and written to the file:%s.txt", fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertToString(String[] data) {
        StringBuilder sb = new StringBuilder();
        for (String s : data) {
            sb.append(s + " ");
        }
        return sb.toString();
    }

    private static String input(String message) {
        String input = "";
        Scanner sc = new Scanner(System.in, "Cp866");
        System.out.print(message);
        try {
            input = sc.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sc.close();
        }
        return input;
    }
}