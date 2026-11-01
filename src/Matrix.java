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
     * Constructors ------------------------------------------------------------
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
     * Returns a particular row for a matrix at index i.
     *
     * @param i
     *            index of row.
     * @return A sequence of doubles representing a particular row.
     */
    public Sequence<Double> row(int i) {
        return this.matrix.entry(i - 1);
    }

    /**
     * Adds an empty row at the end of a matrix.
     */
    public void createRow() {
        Sequence<Double> row = this.matrix.entry(1).newInstance();
        for (int k = 0; k < this.matrix.entry(1).length(); k++) {
            row.add(k, 0.0);
        }
        this.matrix.add(this.matrix.length(), row);
    }

    /**
     * Adds an empty row at a particular index, i, for this. Note that indexing
     * begins at 1.
     *
     * @param i
     *            Index empty row will be added in.
     */
    public void createRow(int i) {
        Sequence<Double> row = this.matrix.entry(1).newInstance();
        for (int k = 0; k < this.matrix.entry(1).length(); k++) {
            row.add(k, 0.0);
        }
        this.matrix.add(i - 1, row);
    }

    /**
     * Removes the last row from this and returns it.
     *
     * @return The removed row.
     */
    public Sequence<Double> removeRow() {
        return this.matrix.remove(this.matrix.length() - 1);
    }

    /**
     * Removes the row at specified index of this and returns it. Note indexing
     * begins at 1 for the client.
     *
     * @param i
     * @return The removed row.
     */
    public Sequence<Double> removeRow(int i) {
        return this.matrix.remove(i - 1);
    }

    /**
     * Multiplies the row of a matrix at index i by some index d.
     *
     * @param i
     *            Index of row to be multiply.
     * @param d
     *            Double to multiply row by.
     */
    public void multiplyRow(int i, double d) {
        Sequence<Double> row = this.matrix.entry(i - 1);
        for (int k = 0; k < row.length(); k++) {
            row.replaceEntry(k, row.entry(k) * d);
        }
        this.matrix.replaceEntry(i - 1, row);
    }

    /**
     * Adds row a from this to row i of this. Note indexes start at 1 for the
     * client.
     *
     * @param i
     *            Index of matrix that is being added to.
     * @param a
     *            Index of matrix being added.
     */
    public void addRows(int i, int a) {
        Sequence<Double> row = this.matrix.entry(i - 1);
        Sequence<Double> rowToAdd = this.matrix.entry(a - 1);
        for (int k = 0; k < row.length(); k++) {
            row.replaceEntry(k, row.entry(k) + rowToAdd.entry(k));
        }
        this.matrix.replaceEntry(i, row);
    }

    /**
     * Subtracts row a from this to row i of this. Note indexes start at 1 for
     * the client.
     *
     * @param i
     *            Index of matrix that is being subtracted from.
     * @param a
     *            Index of matrix that is being subtracted.
     */
    public void subtractRows(int i, int a) {
        Sequence<Double> row = this.matrix.entry(i - 1);
        Sequence<Double> rowToAdd = this.matrix.entry(a - 1);
        for (int k = 0; k < row.length(); k++) {
            row.replaceEntry(k, row.entry(k) - rowToAdd.entry(k));
        }
        this.matrix.replaceEntry(i, row);
    }

    /**
     * Secondary method that prints out a matrix as a string representation.
     *
     * @param out
     * @param m
     */
    public void prettyPrint(SimpleWriter out, Matrix m) {
        for (int k = 0; k < this.matrix.length(); k++) {
            out.println(m.row(k - 1));
        }
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
