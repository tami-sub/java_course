package academy.elqoo.java8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Stream8 {

    public static List<Integer> returnSquareRoot(List<Integer> numbers){
        return numbers.stream().map( x -> (int)Math.sqrt(x)).collect(Collectors.toList());
    }

    public static List<Integer> getAgeFromUsers(List<User> user){
        return user.stream().map(User::getAge).collect(Collectors.toList());
    }

    public static List<Integer> getDistinctAges(List<User> users){
        return users.stream().map(User::getAge).distinct().collect(Collectors.toList());
    }

    public static List<User> getLimitedUserList(List<User> users, int limit){
        return users.stream().limit(2).collect(Collectors.toList());
    }

    public static Integer countUsersOlderThen25(List<User> users){
        return (int) users.stream().filter(x -> x.getAge() > 25).count();
    }

    public static List<String> mapToUpperCase(List<String> strings){
      return strings.stream().map(String::toUpperCase).collect(Collectors.toList());
    };

    public static Integer sum(List<Integer> integers){
        return integers.stream().mapToInt(Integer::intValue).sum();
    }

    public static List<Integer> skip(List<Integer> integers, Integer toSkip){
        return integers.stream().skip(toSkip).collect(Collectors.toList());
    }

    public static List<String> getFirstNames(List<String> names){
        return names.stream().map(x->x.split(" ")[0]).collect(Collectors.toList());
    }

    public static List<String> getDistinctLetters(List<String> names){
        return names.stream().map(x-> (x.split(""))).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
    }

    public static String separateNamesByComma(List<User> users){
        return users.stream().map(User::getName).collect(Collectors.joining(", "));
    }

    public static double getAverageAge(List<User> users){
        return users.stream().map(User::getAge).collect(Collectors.averagingDouble(num->num));
    }

    public static Integer getMaxAge(List<User> users){
        return users.stream().map(User::getAge).max(Integer::compareTo).orElse(null);
    }

    public static Integer getMinAge(List<User> users) {
        return users.stream().map(User::getAge).min(Integer::compareTo).orElse(null);
    }

    public static Map<Boolean, List<User>> partionUsersByGender(List<User> users){
        Map<Boolean, List<User>> map = new HashMap<>();
        map.put(false, users.stream().filter(x-> !x.isMale()).collect(Collectors.toList()));
        map.put(true,users.stream().filter(User::isMale).collect(Collectors.toList()));
        return map;
    }

    public static Map<Integer, List<User>> groupByAge(List<User> users){
        Map<Integer, List<User>> map = new HashMap<>();
        for (int i = 0; i< users.size(); i++)
            map.put(users.stream().map(User::getAge).toArray(Integer[]::new)[i], Collections.singletonList(users.get(i)));
        return map;
    }

    public static Map<Boolean, Map<Integer, List<User>>> groupByGenderAndAge(List<User> users){
        Map<Boolean, Map<Integer, List<User>>> map = new HashMap<>();
        for (Boolean bool: partionUsersByGender(users).keySet())
            map.put(bool, groupByAge(users.stream().filter(x-> x.isMale() == bool).collect(Collectors.toList())));
        return map;
    }

    public static Map<Boolean, Long> countGender(List<User> users){
        Map<Boolean, Long > map = new HashMap<>();
        for (Boolean bool:partionUsersByGender(users).keySet())
            map.put(bool, Long.parseLong(String.valueOf(partionUsersByGender(users).get(bool).size())));
        return map;
    }

    public static boolean anyMatch(List<User> users, int age){
        return users.stream().anyMatch(x -> x.getAge() == age);
    }

    public static boolean noneMatch(List<User> users, int age){
        return users.stream().noneMatch(x -> x.getAge() == age);
    }

    public static Optional<User> findAny(List<User> users, String name){
        return users.stream().findAny();
    }

    public static List<User> sortByAge(List<User> users){
        return users.stream().sorted(Comparator.comparingInt(User::getAge)).collect(Collectors.toList());
    }

    public static Stream<Integer> getBoxedStream(IntStream stream){
        return stream.boxed();
    }

    public static List<Integer> generateFirst10PrimeNumbers(){
        return IntStream.rangeClosed(2, 30).filter(Stream8::isPrime).boxed().collect(Collectors.toList());
    }

    public static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, number/2).noneMatch(i -> number%i == 0);
    }

    public static List<Integer> generate10RandomNumbers(){
        return new Random().ints().limit(10).boxed().collect(Collectors.toList());
    }

    public static User findOldest(List<User> users){
        return users.stream().filter(x-> x.getAge().equals(getMaxAge(users))).collect(Collectors.toList()).get(0);
    }

    public static int sumAge(List<User> users){
        return users.stream().map(User::getAge).mapToInt(Integer::intValue).sum();
    }

    public static IntSummaryStatistics ageSummaryStatistics(List<User> users){
        return new IntSummaryStatistics(users.size(),getMinAge(users),getMaxAge(users),sumAge(users));
    }
}