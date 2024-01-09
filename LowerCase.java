public class LowerCase {

    public static void main(String[] args) {
        if (args.length > 0) {
            String str = args[0];
            System.out.println(lowerCase(str));
        } else {
            System.out.println("No string provided!");
        }
    }

    public static String lowerCase(String s) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (currentChar >= 'A' && currentChar <= 'Z') {
                // Convert uppercase to lowercase by adding 32 (based on ASCII values)
                result.append((char) (currentChar + 32));
            } else {
                // If not uppercase, add the character as is
                result.append(currentChar);
            }
        }
        
        return result.toString();
    }
}
