import java.util.TreeSet;

public class Alphabetizer extends Filter {
	Pipe pipIn;
	Pipe pipOut;

	Output output;

	public Alphabetizer(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);

		this.pipIn = inPipe;
		this.pipOut = outPipe;
	}

	@Override
	public void start() {
		TreeSet<String> tree_set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
		while (this.pipIn.isNotEmptyOrIsNotClosed()) {
			tree_set.add(this.pipIn.read());
		}
		for (String string : tree_set) {
			this.pipOut.write(string);
		}
		this.pipIn.close();
		this.pipOut.close();
		this.stop();
	}

	@Override
	void filter() {
	}
}
