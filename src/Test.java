public class Test {
    public static void main(String[] args) {
        System.out.println(new Test().getDate("23"));
        System.out.println(Boolean.valueOf("false"));
        System.out.println(String.valueOf(true));

    }
    public int getDate(String dateCell){
        String[] str;
        str = dateCell.split(" ");
        return Integer.parseInt(str[0]);
    }
}

