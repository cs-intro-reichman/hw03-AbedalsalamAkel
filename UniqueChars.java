public class UniqueChars {

    public static void main(String[] args) {
        String str = args[0];
        System.out.println(uniqueChars(str));
    }

    public static String uniqueChars(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (result.indexOf(currentChar) == -1 || currentChar == ' ') {
                result += currentChar;
            }
        }
        return result;
    }
}
