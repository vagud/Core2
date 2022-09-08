import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };
        /*  Raw data:
        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {

        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Task1");
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();
        System.out.println();



        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */

        Map<Object, Long> newSorted = Arrays.stream(RAW_DATA)
                .collect(Collectors.toSet())
                .stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()));
        newSorted.forEach((key, value) -> System.out.println("Key: " + key + "\nValue: " + value));
        /*
        Task2
            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */
        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Task2");
        System.out.println("print the pair exactly in brackets that give the sum - 10:");
        System.out.println();
        System.out.println();

        int [] array = {3,4,2,7};
        boolean finish = false;
        for (int i = 0; i < array.length && !finish; i++) {
            for (int j = 0; j < array.length; j++) {
                if (j != i && (array[i] + array[j]) == 10) {
                    System.out.println("["+array[i] +", " +array[j]+"]");
                    finish = true;
                    break;
                }

            }
        }
     /*   AbstractMap.SimpleEntry<Integer, Integer> entry = numbers.stream()
                .flatMap(i -> numbers.stream().map(j -> new AbstractMap.SimpleEntry<>(Math.min(i, j), Math.max(i, j))))
                .filter(e -> e.getKey() + e.getValue() == 10)
                .findFirst()
                .orElse(new AbstractMap.SimpleEntry<>(null, null));
        System.out.printf(outputFormat, entry.getKey(), entry.getValue());

      */


    /*    int[] array = new int[]{3, 4, 2, 7};
        int sum = 10;
        int x = 0;
        for (int i = 0; i < array.length; ) {
            if (array[i] + array[x] == sum) {
                System.out.println(Arrays.toString(array) + ", " + sum + " -> [" + array[i] + ", " + array[x] + "]");
                return;
            }
            if (x == array.length - 1) {
                i++;
                x = 0;
            } else {
                x++;
            }
        }


        /*
        Task3
            Реализовать функцию нечеткого поиска
            
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */
        System.out.println("\n\n**************************************************");
        System.out.println();
        System.out.println("Task3");
        System.out.println("Implement fuzzy search function:");
        System.out.println();
        System.out.println();

        Stream.of(
                fuzzySearch("car", "ca6$$#_rtwheel"),
                fuzzySearch("cwhl", "cartwheel"),
                fuzzySearch("cwhee", "cartwheel"),
                fuzzySearch("cartwheel", "cartwheel"),
                fuzzySearch("cwheeel", "cartwheel"),
                fuzzySearch("lw", "cartwheel")
        ).forEach(System.out::println);


    }
    public static boolean fuzzySearch(String x, String y) {
        int searchIndex = 0;
        for (int i = 0; i < y.length(); i++) {
            if (y.charAt(i) == x.charAt(searchIndex)) {
                searchIndex++;
            }
            if (searchIndex >= x.length()) {
                return true;
            }
        }
        return false;
    }

}

