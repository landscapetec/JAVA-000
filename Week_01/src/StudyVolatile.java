
public class StudyVolatile {

    public static volatile int counter = 1;

    public static void main(String[] args) {
        counter = 2;
        System.out.println(counter);
    }

}
