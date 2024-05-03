package Java15;

public class TextBlocksRunner {

    public static void main(String[] args) {
        System.out.println("\"FirstLine\"\n Second Line \n Third Line");

        //after intro of text blocks
        System.out.println("""
                "First Line"
                 Second Line
                 Third Line """);

       //Rule- 3 quotes and then a new line and then start writing. You cannot have a text block on a single line like
        // """ abcdefg""".At the end if you need a new line then after the end of statement next line will have 3 quotes else same line can have quotes.
        //Specific formatting is retained.
        //Trailing whitespace is stripped of.
        //All operations on strings can be applied on text  blocks.
        String str= """
                Line1:%s
                "Line2":%s
                Line3
                """.formatted("SomeValue", "Some Other Value");

        System.out.println(str);
    }
}
