import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueInput;do {
            continueInput = false;
            try {
            System.out.println("Введите данные в формате: Фамилия Имя Отчество дата рождения номер телефона пол");
                System.out.println("Пример: Петров Илья Сидорович 01.12.2000 2321243 m");
            String inputData = scanner.nextLine();
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
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
            if (!continueInput) {
                System.out.println("Хотите ввести данные еще одного человека? (yes/no)");
                String answer = scanner.nextLine();
                continueInput = answer.equalsIgnoreCase("yes");
            }
        } while (continueInput);
        scanner.close();
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