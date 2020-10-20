import java.util.ArrayList;
import java.util.List;

public class StudyByteCode {
    private String studentName = "lehman";

    public int add(int x, int y) {
        return x + y;
    }

    public int sub(int x, int y) {
        return x - y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    public double div(int x, double y) {
        return x / y;
    }

    public char div(int x) {
        return (char) x;
    }

    public void testFor() {
        List<String> strings = new ArrayList<>();
//        for(int i=0;i<strings.size();i++){
//            System.out.println(strings.toArray()[i]);
//        }

        System.out.println("------分隔符1-------");

        for (String temp : strings) {
            System.out.println(temp);
        }
        System.out.println("------分隔符2-------");
        strings.forEach(temp -> {
            System.out.println(temp);
        });


    }

    public void testIf() {
        int a = 10;
        if (a > 0 && a <= 100) {
            System.out.println("min value");
        } else if (a > 100 && a <= 200) {
            System.out.println("middle value");
        } else if (a < 0 || a > 300) {
            System.out.println("不合法数字 value");
        }
    }

    public void testSwitch() {
        String value = "123";
        switch (value) {
            case "a":
                System.out.println("a");
                break;
            case "b":
                System.out.println("b");
            case "c":
                System.out.println("c");
            default:
                System.out.println("unknown value");
                break;
        }
    }

    public void testException() {
        try {
            int i = 1 / 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
