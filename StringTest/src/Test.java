public class Test {
    public static void main(String[] args) {
        String str = "abc.defdsf.jpg";

        System.out.println(str.substring(1));
        int lastindex = str.lastIndexOf(".");
        System.out.println(str.substring(0,lastindex));
        System.out.println(str.substring(lastindex));

    }
}
