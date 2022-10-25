import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlphabetizerTest {

	Alphabetizer alphabetizer;
	Pipe inPipe;
	Pipe outPipe;

	@BeforeEach
	void setUp() {
		inPipe = new Pipe();
		outPipe = new Pipe();
		alphabetizer = new Alphabetizer(inPipe, outPipe);
	}

	@Test
	void alpha_2_lines() {
		inPipe.write("hi bye foo");
		inPipe.write("bye hi");
		inPipe.close();

		alphabetizer.start();

		List<String> actual = new ArrayList<>();
		while (outPipe.isNotEmptyOrIsNotClosed()) {
			if (outPipe.hasNext()) {
				actual.add(outPipe.read());
			}
		}
		assertSame(2, actual.size());
		assertEquals("bye hi", actual.get(0));
		assertEquals("hi bye foo", actual.get(1));
	}

}
