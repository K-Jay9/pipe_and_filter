import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputTest {

	String FILE_NAME = "input.txt";
	List<String> actual;
	Input input;
	Pipe outPipe;

	@BeforeEach
	void setUp() {
		actual = new ArrayList<>();
		outPipe = new Pipe();
	}

	@Test
	void input_from_scanner() throws IOException {
		List<String> lines = new ArrayList<>();
		lines.add("Descent of Man");
		lines.add("The Ascent of Man");
		lines.add("The Old Man and The Sea");
		lines.add("A Portrait of The Artist As a Young Man");
		FileWriter fileWriter = new FileWriter(FILE_NAME);
		for (String line : lines) {
			fileWriter.write(line);
			fileWriter.write("\n");
		}
		fileWriter.close();

		input = new Input(null, outPipe, FILE_NAME);
		input.start();

		while (outPipe.isNotEmptyOrIsNotClosed()) {
			if (outPipe.hasNext()) {
				actual.add(outPipe.read());
			}
		}

		assertSame(4, actual.size());
		assertEquals("Descent of Man", actual.get(0));
		assertEquals("The Ascent of Man", actual.get(1));
		assertEquals("The Old Man and The Sea", actual.get(2));
		assertEquals("A Portrait of The Artist As a Young Man", actual.get(3));
	}

}
