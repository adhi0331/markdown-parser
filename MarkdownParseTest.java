import static org.junit.Assert.*;

import java.beans.Transient;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.*;
public class MarkdownParseTest {
    @Test
    public void getLinkTest() throws IOException {
        ArrayList<String> answers = new ArrayList<>();
        answers.add("https://something.com");
        answers.add("some-thing.html"); 
        Path fileName = Path.of("test-file.md");
        String content = Files.readString(fileName);
        assertEquals(answers.get(0), MarkdownParse.getLinks(content).get(0)); 
        assertEquals(answers.get(1), MarkdownParse.getLinks(content).get(1));
	System.out.println("Hello There");
    }

    @Test
    public void checkNoSecondParen() throws IOException{
        ArrayList<String> answers = new ArrayList<>();
        Path fileName = Path.of("myFile2.md");
        String content = Files.readString(fileName);
        assertEquals(answers.size(), MarkdownParse.getLinks(content).size());
    }

    //Tests snippet 1 to see if code blocks affect getlinks
    @Test
    public void testSnippet1() throws IOException {
        ArrayList<String> answers = new ArrayList<>();
        answers.add("`google.com");
        Path fileName = Path.of("snippet1.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals(answers.get(0), links.get(0));
        assertEquals(1, links.size());
    }

    //Tests snippet 2 to see if nested links affect getLinks
    @Test
    public void testSnippet2() throws IOException {
        ArrayList<String> answers = new ArrayList<>();
        answers.add("a.com");
        answers.add("a.com(())");
        answers.add("example.com");
        Path fileName = Path.of("snippet2.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals(answers.get(0), links.get(0));
        assertEquals(answers.get(1), links.get(1));
        assertEquals(answers.get(2), links.get(2));
        assertEquals(3, links.size());
    }

    //Tests snippet3 to see if long lines affect getLinks
    @Test
    public void testSnippet3() throws IOException {
        ArrayList<String> answers = new ArrayList<>();
        answers.add("https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");
        Path fileName = Path.of("snippet3.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals(answers.get(0), links.get(0)); 
        assertEquals(1, links.size());
    }
    

}
