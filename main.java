import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите данные в формате: Фамилия Имя Отчество дата рождения номертелефона пол");
            String inputData = scanner.nextLine();
            scanner.close();

            String[] data = inputData.split(" ");

            if (data.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных. Должно быть 6.");
            }

            String surname = data[0];
            String name = data[1];
            String patronymic = data[2];
            String birthDate = data[3];
            long phoneNumber = Long.parseLong(data[4]);
            String gender = data[5];

            validateData(surname, name, patronymic, birthDate, phoneNumber, gender);

            writeToTextFile(surname, name, patronymic, birthDate, phoneNumber, gender);

            System.out.println("Данные успешно записаны в файл.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Введено неверное значение номера телефона.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void validateData(String surname, String name, String patronymic, String birthDate, long phoneNumber, String gender) {
        // Проверка форматов данных

        // Проверка формата даты рождения
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new IllegalArgumentException("Неверный формат даты рождения. Ожидается dd.mm.yyyy.");
        }

        // Проверка пола
        if (!gender.equals("m") && !gender.equals("f")) {
            throw new IllegalArgumentException("Неверный формат пола. Ожидается m или f.");
        }
    }

    private static void writeToTextFile(String surname, String name, String patronymic, String birthDate, long phoneNumber, String gender) throws IOException {
        String fileName = surname + ".txt";
        FileWriter writer = new FileWriter(fileName, true);

        String data = surname + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender + "\n";
        writer.write(data);

        writer.close();
    }
}