import java.io.*;
import java.util.*;

public class Main {

    public static String inputUser() throws IOException {   // принимает текстовый файл и возвращает его ввиде строки
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        String text = "";
        byte[] buffer = new byte[1000];

        try (FileInputStream fileInputStream = new FileInputStream(str)) {


            while (fileInputStream.available() > 0) {

                fileInputStream.read(buffer);
                String bufferString = new String(buffer, "UTF-8");

                text += bufferString;
            }

        } catch (Exception e) {
            System.out.println(e);

        }
        finally {
            reader.close();
        }

        return text;
    }

    public static String[] delSymbol(String text) {   // преобразует текст в массив

        text = text.replaceAll("[^a-zA-Zа-яА-Я]", " ").replaceAll("\\s+", " ");

        return text.split(" ");
    }

    public static void counter(String[] textFile) {     // считает повтор слов + вывод слов с максимальным повторением
        Map<String, Integer> map = new HashMap<>();


        int max = 0;
        int count;
        List<String> list = new ArrayList<>();

        for (int i = 0; i < textFile.length; i++) {
            count = 1;
            if (map.containsKey(textFile[i])) {
                count = map.get(textFile[i]) + 1;
            }
            map.put(textFile[i], count);
            if (count > max) {
                max = count;
                list.clear();
                list.add(textFile[i]);

            } else if (count == max) {
                list.add(textFile[i]);
            }
        }

        System.out.println("Самые частые слова:");
        for (String s : list) {
            System.out.println(s + " повторений = " + max);
        }

        System.out.println();

        System.out.println("Все слова и количество повторений:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }


    }

    public static void printSortText(String[] text) {  // выводит слова на экран в отсортированном порядке
        Arrays.sort(text);
        System.out.println("Список всех слов:");
        for (String s : text) {
            System.out.println(s);
        }
    }


    public static void main(String[] args) throws IOException {
       String text = inputUser();

        counter(delSymbol(text)); //выводим слова с кол-ом повторений + максимальное слово

        System.out.println();

        printSortText(delSymbol(text)); // вывод всех слов в отсортированном виде



    }
}

