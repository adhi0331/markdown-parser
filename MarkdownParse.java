//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            if (openBracket == -1){
                break;
            }
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            if (closeBracket + 1 != openParen){
                currentIndex = closeBracket;
                continue;
            }
            int closeParen = markdown.indexOf(")", openParen);
            String link = markdown.substring(openParen + 1, closeParen);
            
            
            if(link.contains("[") || link.contains("]") ||
                link.contains("{") || link.contains("}") || 
                link.contains("|") || link.contains("\\") || 
                link.contains("~") || link.contains("^") || 
                link.contains("`")) break;
            
            if (markdown.substring(openParen + 1, closeParen).indexOf("[") == -1
                && markdown.substring(openParen + 1, closeParen).indexOf("]") == -1 
                && markdown.substring(openParen + 1, closeParen).indexOf("(") == -1
                && markdown.substring(openParen + 1, closeParen).indexOf(")") == -1){
                toReturn.add(link);
            }
            
            currentIndex = closeParen + 1;
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of("snippet2.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	System.out.println(links);
    }
}
