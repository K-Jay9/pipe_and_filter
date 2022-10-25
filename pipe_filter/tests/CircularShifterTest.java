import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircularShifterTest {

	CircularShifter circularShifter;
	Pipe inPipe;
	Pipe outPipe;

	@BeforeEach
	void setUp() {
		inPipe = new Pipe();
		outPipe = new Pipe();
		circularShifter = new CircularShifter(inPipe, outPipe);
	}

	@Test
	void shift_2_lines_completely() {
		inPipe.write("hi bye foo");
		inPipe.write("hi bye");
		inPipe.close();

		circularShifter.start();

		List<String> actual = new ArrayList<>();
		while (outPipe.isNotEmptyOrIsNotClosed()) {
			if (outPipe.hasNext()) {
				actual.add(outPipe.read());
			}
		}
		assertSame(5, actual.size());
		assertTrue(actual.contains("bye hi"));
		assertTrue(actual.contains("hi bye"));
		assertTrue(actual.contains("foo hi bye"));
		assertTrue(actual.contains("bye foo hi"));
		assertTrue(actual.contains("hi bye foo"));
	}

}
