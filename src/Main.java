import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        System.out.println("Колличество несовершеннолетних: ");
        long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(count);

        System.out.println();
        System.out.println("Список фамилий призывников:");
        List<String> newPersons = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(newPersons);

        System.out.println();
        System.out.println("С высшим образованием:");
        List<Person> worker = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() >= 18)
                .filter(x -> (x.getSex() == Sex.WOMAN && x.getAge() <= 60)
                        || (x.getSex() == Sex.MAN && x.getAge() <= 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(worker);
    }
}
