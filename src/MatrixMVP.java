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
public class MatrixMVP {

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
    public Matrix() {
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
    public final void transferFrom(Matrix source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        this.matrix = source.matrix;
        this.numberOfRows = source.numberOfRows;
        this.numberOfColumns = source.numberOfColumns;
        source.createNewRep();
    }

    /**
     * Copies the representation from source into this.
     *
     * @param source
     */
    public final void copyFrom(Matrix source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        this.matrix = new Sequence1L<Sequence<Double>>();
        for (int i = 0; i < source.matrix.length(); i++) {
            Sequence<Double> newRow = new Sequence1L<Double>();
            Sequence<Double> sourceRow = source.matrix.entry(i);
            for (int j = 0; j < sourceRow.length(); j++) {
                newRow.add(j, sourceRow.entry(j));
            }
            this.matrix.add(i, newRow);
        }
        this.numberOfRows = source.numberOfRows;
        this.numberOfColumns = source.numberOfColumns;
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
        assert 0 <= i && i <= this.numberOfRows : "Invalid index inputs";
        assert 0 <= j && j <= this.numberOfColumns : "Invalid index inputs";
        this.matrix.entry(i - 1).replaceEntry(j - 1, newValue);
    }

    /**
     * Returns a particular row for a matrix at index i.
     *
     * @param i
     *            index of row.
     * @requires i <= this.numberOfRows()
     * @return A sequence of doubles representing a particular row.
     */
    public Sequence<Double> row(int i) {
        assert i <= this.numberOfRows : "Invalid index input";
        return this.matrix.entry(i - 1);
    }

    /**
     * Adds a row of 0's at the end of a matrix.
     */
    public void createRow() {
        if (this.numberOfRows > 0) {
            Sequence<Double> row = this.matrix.entry(0).newInstance();
            for (int k = 0; k < this.matrix.entry(0).length(); k++) {
                row.add(k, 0.0);
            }
            this.matrix.add(this.matrix.length(), row);
        } else {
            this.matrix.add(0, new Sequence1L<Double>());
            this.matrix.entry(0).add(0, 0.0);
            this.numberOfColumns++;
        }
        this.numberOfRows++;
    }

    /**
     * Adds a row of 0's at a particular index, i, for this. Note that indexing
     * begins at 1.
     *
     * @param i
     *            Index empty row will be added in.
     * @requires 1 <= i <= this.numberOfRows
     */
    public void createRow(int i) {
        assert 1 <= i && i <= this.numberOfRows + 1 : "Invalid index input";
        if (this.numberOfRows > 0) {
            Sequence<Double> row = this.matrix.entry(0).newInstance();
            for (int k = 0; k < this.matrix.entry(0).length(); k++) {
                row.add(k, 0.0);
            }
            this.matrix.add(i - 1, row);
        } else {
            this.matrix.add(i - 1, new Sequence1L<Double>());
            this.matrix.entry(i - 1).add(0, 0.0);
            this.numberOfColumns++;
        }
        this.numberOfRows++;
    }

    /**
     * Adds a Column of 0.0 at particular index j.
     */
    public void createColumn() {
        for (int k = 0; k < this.matrix.length(); k++) {
            Sequence<Double> row = this.matrix.entry(k);
            row.add(row.length(), 0.0);
        }
        this.numberOfColumns++;
    }

    /**
     * Adds a Column of 0.0 at particular index j.
     *
     * @param j
     */
    public void createColumn(int j) {
        assert 1 <= j && j <= this.numberOfColumns : "Invalid index input";
        for (int k = 0; k < this.matrix.length(); k++) {
            this.matrix.entry(k).add(j - 1, 0.0);
        }
        this.numberOfColumns++;
    }

    /**
     * Adds a Column of 0.0 at particular index j.
     */
    public void removeColumn() {
        for (int k = 0; k < this.matrix.length(); k++) {
            Sequence<Double> row = this.matrix.entry(k);
            row.remove(row.length() - 1);
        }
        this.numberOfColumns--;
    }

    /**
     * Adds a Column of 0.0 at particular index j.
     *
     * @param j
     */
    public void removeColumn(int j) {
        assert 1 <= j && j <= this.numberOfColumns : "Invalid index input";
        for (int k = 0; k < this.matrix.length(); k++) {
            this.matrix.entry(k).remove(j - 1);
        }
        this.numberOfColumns--;
    }

    /**
     * Removes the last row from this and returns it.
     *
     * @return The removed row.
     */
    public Sequence<Double> removeRow() {
        this.numberOfRows--;
        return this.matrix.remove(this.matrix.length() - 1);
    }

    /**
     * Removes the row at specified index of this and returns it. Note indexing
     * begins at 1 for the client.
     *
     * @param i
     * @requires 1 <= i <= this.numberOfRows
     * @return The removed row.
     */
    public Sequence<Double> removeRow(int i) {
        assert 1 <= i && i <= this.numberOfRows : "Invalid Index input";
        this.numberOfRows--;
        return this.matrix.remove(i - 1);
    }

    /**
     * Multiplies the row of a matrix at index i by some index d.
     *
     * @param i
     *            Index of row to be multiply.
     * @param d
     *            Double to multiply row by.
     * @requires 1 <= i <= this.numberOfRows
     */
    public void multiplyRow(int i, double d) {
        assert 1 <= i && i <= this.numberOfRows : "Invalid Index input";
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
     * @requires 1 <= i <= this.numberOfRows, 1 <= a <= this.numberOfRows, i !=
     *           a
     */
    public void addRows(int i, int a) {
        assert 1 <= i && i <= this.numberOfRows : "Invalid Index input for i";
        assert 1 <= a && a <= this.numberOfRows : "Invalid Index input for a";
        assert i != a : "Violation of: i != a";
        Sequence<Double> row = this.matrix.entry(i - 1);
        Sequence<Double> rowToAdd = this.matrix.entry(a - 1);
        for (int k = 0; k < row.length(); k++) {
            row.replaceEntry(k, row.entry(k) + rowToAdd.entry(k));
        }
        this.matrix.replaceEntry(i - 1, row);
    }

    /**
     * Subtracts row a from this to row i of this. Note indexes start at 1 for
     * the client.
     *
     * @param i
     *            Index of matrix that is being subtracted from.
     * @param a
     *            Index of matrix that is being subtracted.
     * @requires 1 <= i <= this.numberOfRows, 1 <= a <= this.numberOfRows, i !=
     *           a
     */
    public void subtractRows(int i, int a) {
        assert 1 <= i && i <= this.numberOfRows : "Invalid Index input for i";
        assert 1 <= a && a <= this.numberOfRows : "Invalid Index input for a";
        assert i != a : "Violation of: i != a";
        Sequence<Double> row = this.matrix.entry(i - 1);
        Sequence<Double> rowToSubtract = this.matrix.entry(a - 1);
        for (int k = 0; k < row.length(); k++) {
            row.replaceEntry(k, row.entry(k) - rowToSubtract.entry(k));
        }
        this.matrix.replaceEntry(i - 1, row);
    }

    /**
     * Secondary method that prints out a matrix as a string representation.
     *
     * @param out
     * @param m
     */
    public static void prettyPrint(SimpleWriter out, Matrix m) {
        for (int k = 1; k < m.matrix.length() + 1; k++) {
            out.println(m.row(k));
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
        Matrix matrix = new Matrix();
        out.println(
                "Hello user, here is a showcase of the MVP for the Matrix component."
                        + " Please input the number of the method you wish to use.");
        out.println("Below is a list of methods to try"
                + "alongside a printout of the matrix.");
        boolean programActive = true;
        while (programActive) {
            prettyPrint(out, matrix);
            out.println("1. Number of Rows");
            out.println("2. Number of Columns");
            out.println("3. Create New Row");
            out.println("4. Remove Row");
            out.println("5. Create New Column");
            out.println("6. Remove Column");
            out.println("7. Entry");
            out.println("8. Set Entry");
            out.println("9. Multiply Row");
            out.println("10. Add Rows");
            out.println("11. Subtract Rows");
            out.println("12. Stop");
            out.print("Input: ");
            String input = in.nextLine();
            programActive = processInput(input, matrix, out, in);
        }
        in.close();
        out.close();
    }

    /**
     * Helper method written here to showcase the MVP.
     *
     * @param input
     * @param m
     * @param in
     * @param out
     * @return Whether or not the code should continue executing
     */
    private static boolean processInput(String input, Matrix m,
            SimpleWriter out, SimpleReader in) {
        boolean programContinues = true;
        switch (input) {
            case "1": // Number of Rows
                out.println("The number of rows is: "
                        + Integer.toString(m.numberOfRows));
                break;
            case "2": // Number of Columns
                out.println("The number of columns is: "
                        + Integer.toString(m.numberOfColumns()));
                break;
            case "3": // Add Row
                out.println("Would you like to specify an index? [y/n]");
                out.print("Input: ");
                String yesOrNoCase3 = in.nextLine();
                if (yesOrNoCase3.equals("y")) {
                    out.println();
                    out.print("What index?: ");
                    int desiredIndex = in.nextInteger();
                    m.createRow(desiredIndex);
                } else if (yesOrNoCase3.equals("n")) {
                    m.createRow();
                } else {
                    out.println("invalid input");
                    processInput("3", m, out, in);
                }
                break;
            case "4": // Remove Row
                out.println("Would you like to specify an index? [y/n]");
                out.print("Input: ");
                String yesOrNoCase4 = in.nextLine();
                if (yesOrNoCase4.equals("y")) {
                    out.println();
                    out.print("What index?: ");
                    int desiredIndex = in.nextInteger();
                    m.removeRow(desiredIndex);
                } else if (yesOrNoCase4.equals("n")) {
                    m.removeRow();
                } else {
                    out.println("invalid input");
                    processInput("4", m, out, in);
                }
                break;
            case "5": // Create Column
                out.println("Would you like to specify an index? [y/n]");
                out.print("Input: ");
                String yesOrNoCase5 = in.nextLine();
                if (yesOrNoCase5.equals("y")) {
                    out.println();
                    out.print("What index?: ");
                    int desiredIndex = in.nextInteger();
                    m.createColumn(desiredIndex);
                } else if (yesOrNoCase5.equals("n")) {
                    m.createColumn();
                } else {
                    out.println("invalid input");
                    processInput("5", m, out, in);
                }
                break;
            case "6": // Remove Column
                out.println("Would you like to specify an index? [y/n]");
                out.print("Input: ");
                String yesOrNoCase6 = in.nextLine();
                if (yesOrNoCase6.equals("y")) {
                    out.println();
                    out.print("What index?: ");
                    int desiredIndex = in.nextInteger();
                    m.removeColumn(desiredIndex);
                } else if (yesOrNoCase6.equals("n")) {
                    m.removeColumn();
                } else {
                    out.println("invalid input");
                    processInput("6", m, out, in);
                }
                break;
            case "7": // Entry
                out.print("What Row?: ");
                int case7RowInput = in.nextInteger();
                out.println();
                out.print("What Column?: ");
                int case7ColumnInput = in.nextInteger();
                out.println();
                out.println("The entry at " + Integer.toString(case7RowInput)
                        + ", " + Integer.toString(case7ColumnInput) + " is"
                        + Double.toString(
                                m.entry(case7RowInput, case7ColumnInput)));
                break;
            case "8": // Set Entry
                out.print("What Row?: ");
                int case8RowInput = in.nextInteger();
                out.println();
                out.print("What Column?: ");
                int case8ColumnInput = in.nextInteger();
                out.println();
                out.print("New Double Value?: ");
                double case8NewDouble = in.nextDouble();
                m.setEntry(case8RowInput, case8ColumnInput, case8NewDouble);
                out.println();
                out.println("New Entry Set!");
                break;
            case "9": // Multiply Row
                out.print("What Row?: ");
                int case9RowInput1 = in.nextInteger();
                out.println();
                out.print("By What Double Value?: ");
                double case9NewDouble = in.nextDouble();
                m.multiplyRow(case9RowInput1, case9NewDouble);
                out.println();
                out.println("Done!");
                break;
            case "10": // Add Rows
                out.print("What Row is being added to?: ");
                int case10RowInput1 = in.nextInteger();
                out.println();
                out.print("What row are we adding to it?: ");
                int case10RowInput2 = in.nextInteger();
                out.println();
                m.addRows(case10RowInput1, case10RowInput2);
                out.println();
                out.println("Done!");
                break;
            case "11": // Subtract Rows
                out.print("What Row is being subtracted from?: ");
                int case11RowInput1 = in.nextInteger();
                out.println();
                out.print("What row are we subtracting from it?: ");
                int case11RowInput2 = in.nextInteger();
                out.println();
                m.subtractRows(case11RowInput1, case11RowInput2);
                out.println();
                out.println("Done!");
                break;
            case "12": // Stop
                out.println("Stopping Program");
                programContinues = false;
                break;
            default:
                out.println(
                        "Invalid input, please put in just the desired integer");
                out.print("Input: ");
                String newInput = in.nextLine();
                processInput(newInput, m, out, in);
        }
        return programContinues;
    }
}
