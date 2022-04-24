import static org.junit.Assert.*;

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
    }
}
