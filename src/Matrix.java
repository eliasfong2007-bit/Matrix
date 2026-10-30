import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Minimum Viable product implementation for component Matrix is described in
 * brainstorming doc. Matrix
 */
public class Matrix {

    /**
     * Matrix that will be manipulated.
     */
    private Sequence<Sequence<Double>> matrix;

    /**
     * Stored number of rows for a matrix.
     */
    private int numberOfRows;

    /**
     * Stored number of columns for this matrix.
     */
    private int numberOfColumns;

    /**
     * Creates new representation for Matrix.
     */
    private void createNewRep() {
        this.matrix = new Sequence1L<Sequence<Double>>();
        this.numberOfColumns = 0;
        this.numberOfRows = 0;
    }

    /*
     * Constructors
     * --------------------------------------------------------------
     */

    /**
     * No argument constructor.
     */
    public void matrix1L() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    /**
     * Clears matrix representation.
     */
    public final void clear() {
        this.createNewRep();
    }

    /**
     * Transfers the representation from source into this.
     *
     * @param source
     * @clears source
     */
    public final void transferFrom(Sequence<Sequence<Double>> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        Matrix localSource = (Matrix) source;
        this.matrix = localSource.matrix;
        localSource.createNewRep();
    }

    /**
     * Copies the representation from source into this.
     *
     * @param source
     */
    public final void copyFrom(Sequence<Sequence<Double>> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        for (int i = 0; i < source.length(); i++) {
            Sequence<Double> sourceRow = source.entry(i);
            Sequence<Double> toCopyRow = this.matrix.entry(i);
            for (int j = 0; j < sourceRow.length(); j++) {
                toCopyRow.replaceEntry(j, sourceRow.entry(j));
            }
        }
    }

    /*
     * Kernel Methods ---------------------------------------------------------
     */

    /**
     * Returns the number of rows this has.
     *
     * @return the number of rows this has.
     */
    public int numberOfRows() {
        return this.numberOfRows;
    }

    /**
     * Returns the number of columns this has.
     *
     * @return The number of columns this has.
     */
    public int numberOfColumns() {
        return this.numberOfColumns;
    }

    /**
     * Returns the entry at Matrix entry i, j. Note that indexing for rows and
     * columns begins at 1 for the columns to align to mathematical conventions.
     *
     * @param i
     *            Row number index
     * @param j
     *            column number index
     * @requires i <= this.numberOfRows()), j <= this.numberOfColumns().
     * @return The entry as described above.
     */
    public double entry(int i, int j) {
        assert i <= this.numberOfRows : "Invalid index inputs";
        assert j <= this.numberOfColumns : "Invalid index inputs";
        return this.matrix.entry(i - 1).entry(j - 1);
    }

    /**
     * Replaces the value at Matrix entry i, j. Note that indexing for rows and
     * columns begins at 1 for the columns to align to mathematical conventions.
     *
     * @param i
     *            Tow number index
     * @param j
     *            Column number index
     * @param newValue
     *            Value to replace the old one
     * @requires i <= this.numberOfRows()), j <= this.numberOfColumns().
     */
    public void setEntry(int i, int j, double newValue) {
        assert i <= this.numberOfRows : "Invalid index inputs";
        assert j <= this.numberOfColumns : "Invalid index inputs";
        this.matrix.entry(i - 1).replaceEntry(j - 1, newValue);
    }

    /**
     * Main method used to showcase minimum viable component.
     *
     * @param args
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        in.close();
        out.close();
    }
}
