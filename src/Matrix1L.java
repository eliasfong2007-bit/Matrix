
// TOOD - Write shit up top, including convention and correspondence.

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * To appease my IDE overlords. Matrix1L
 */
public class Matrix1L extends MatrixSecondary {

    /*
     * Representation fields -----------------------------------------
     */

    /**
     * Representation of our Matrix.
     */
    private Sequence<Sequence<Double>> matrix;

    /**
     * Field for number of rows for matrix representation.
     */
    private int numberOfRows;

    /**
     * Field for number of columns for matrix representation.
     */
    private int numberOfColumns;

    /*
     * Private methods for representation -------------------------------
     */

    /**
     * Creates new representation for Matrix.
     */
    private void createNewRep() {
        this.matrix = new Sequence1L<Sequence<Double>>();
        this.numberOfColumns = 0;
        this.numberOfRows = 0;
    }

    /**
     * Public no argument constructor for Matrix1L.
     */
    public Matrix1L() {
        this.createNewRep();
    }

    /*
     * Methods inherited from standard
     * ------------------------------------------
     */

    /**
     * Implementation for newInstance from standard on Matrix1L.
     *
     * @see components.standard.Standard#newInstance()
     */
    @Override
    public Matrix newInstance() {
        return new Matrix1L();
    }

    /**
     * Transfers the representation from source into this.
     *
     * @param source
     * @clears source
     */
    @Override
    public final void transferFrom(Matrix source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        Matrix1L localSource = (Matrix1L) source;
        this.matrix = localSource.matrix;
        this.numberOfRows = localSource.numberOfRows;
        this.numberOfColumns = localSource.numberOfColumns;
        localSource.createNewRep();
    }

    /**
     * Clears matrix representation.
     */
    @Override
    public final void clear() {
        this.createNewRep();
    }

    /*
     * Kernel methods ------------------------------
     */

    /**
     * Returns the number of rows this has.
     *
     * @return the number of rows this has.
     */
    @Override
    public int numberOfRows() {
        return this.numberOfRows;
    }

    /**
     * Returns the number of columns this has.
     *
     * @return The number of columns this has.
     */
    @Override
    public int numberOfColumns() {
        return this.numberOfColumns;
    }

    /**
     * Returns a particular row for a matrix at index i.
     *
     * @param i
     *            index of row.
     * @requires i <= this.numberOfRows()
     * @return A sequence of doubles representing a particular row.
     */
    @Override
    public Matrix row(int i) {
        assert 1 <= i && i <= this.numberOfRows : "Invalid index input";
        Sequence<Double> original = this.matrix.entry(i - 1);
        Sequence<Double> rowCopy = new Sequence1L<Double>();
        for (int k = 0; k < original.length(); k++) {
            rowCopy.add(k, original.entry(k));
        }
        Matrix1L result = new Matrix1L();
        result.matrix.add(0, rowCopy);
        result.numberOfRows = 1;
        result.numberOfColumns = this.numberOfColumns;
        return result;
    }

    /**
     * Returns a this.numberOfRows x 1 matrix representing the row at specified
     * index j.
     *
     * @return Matrix as described above.
     */
    @Override
    public Matrix column(int j) {
        Matrix1L result = new Matrix1L();
        result.numberOfColumns = 1;
        while (result.numberOfRows < this.numberOfRows) {
            result.matrix.add(0, new Sequence1L<Double>());
            result.numberOfRows += 1;
        }
        for (int i = 0; i < this.numberOfRows; i++) {
            result.matrix.entry(i).add(0, this.matrix.entry(i).entry(j));
        }
        return result;
    }

    /**
     * Creates a new row of 0.0's for this after its final row.
     */
    @Override
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
     * Creates a new row of 0.0's for this at a specified index i.
     *
     * @param i
     */
    @Override
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
     * Removes the last row of this and returns it as a 1x this.numberOfColumns
     * Matrix.
     *
     * @return Matrix as described above.
     */
    @Override
    public Matrix removeRow() {
        Matrix1L result = new Matrix1L();
        result.matrix.add(0, this.matrix.remove(this.numberOfRows - 1));
        this.numberOfRows--;
        if (this.numberOfColumns == 1) {
            this.numberOfColumns--;
        }
        return result;
    }

    /**
     * Removes the row at specified index i from this and returns it as a 1 x
     * this.numberOfColumns Matrix.
     *
     * @param i
     * @return Matrix as described above.
     */
    @Override
    public Matrix removeRow(int i) {
        Matrix1L result = new Matrix1L();
        Sequence<Double> removedRow = this.matrix.remove(i - 1);
        result.matrix.add(0, removedRow);
        this.numberOfRows--;
        if (this.numberOfColumns == 1) {
            this.numberOfColumns--;
        }
        return result;
    }

    /**
     * Creates a new column at the this.numberOfColumns + 1 index.
     */
    @Override
    public void createColumn() {
        if (this.numberOfRows > 0) {
            for (int i = 0; i < this.numberOfRows; i++) {
                this.matrix.entry(i).add(this.matrix.entry(i).length(), 0.0);
            }
        } else {
            this.matrix.add(0, new Sequence1L<Double>());
            this.matrix.entry(0).add(0, 0.0);
            this.numberOfRows++;
        }
    }

    /**
     * Creates a new column at the specified index j.
     *
     * @param j
     */
    @Override
    public void createColumn(int j) {
        if (this.numberOfRows > 0) {
            for (int i = 0; i < this.numberOfRows; i++) {
                this.matrix.entry(i).add(j - 1, 0.0);
            }
        } else {
            this.matrix.add(0, new Sequence1L<Double>());
            this.matrix.entry(0).add(0, 0.0);
            this.numberOfRows++;
        }
    }

    /**
     * Removes the final column of this and returns it as a 1 x
     * this.numberOfRows Matrix.
     *
     * @return Matrix as described above.
     */
    @Override
    public Matrix removeColumn() {
        Matrix1L result = new Matrix1L();
        while (result.numberOfRows < this.numberOfRows) {
            result.matrix.add(0, new Sequence1L<Double>());
            result.numberOfRows++;
        }
        result.numberOfColumns = 1;
        for (int i = 0; i < this.numberOfRows; i++) {
            result.matrix.entry(i).add(0, this.matrix.entry(i)
                    .remove(this.matrix.entry(i).length() - 1));
        }
        return result;
    }

    /**
     * Removes the column at specified index j and returns it as a 1 x
     * this.numberOfRows Matrix.
     *
     * @param j
     * @return Matrix as described above.
     */
    @Override
    public Matrix removeColumn(int j) {
        Matrix1L result = new Matrix1L();
        while (result.numberOfRows < this.numberOfRows) {
            result.matrix.add(0, new Sequence1L<Double>());
            result.numberOfRows++;
        }
        result.numberOfColumns = 1;
        for (int i = 0; i < this.numberOfRows; i++) {
            result.matrix.entry(i).add(0, this.matrix.entry(i).remove(j - 1));
        }
        return result;
    }

    /**
     * Returns the value of an entry of this at coordinates i, j.
     *
     * @param i
     * @param j
     * @return Returns value as described above.
     */
    @Override
    public double entry(int i, int j) {
        return this.matrix.entry(i).entry(j);
    }

    /**
     * Changes the value of an entry of this at coordinates i, j.
     *
     * @param i
     * @param j
     * @param value
     */
    @Override
    public void setEntry(int i, int j, double value) {
        this.matrix.entry(i).replaceEntry(j, value);
    }

}
