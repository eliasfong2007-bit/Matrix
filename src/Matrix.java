/**
 * Matrix Interface with enhanced secondary methods.
 */
public interface Matrix extends MatrixKernel {
    int numberOfRows = 0;

    /**
     * Returns the number of rows in this.
     *
     * @return Number of rows this has.
     */
    @Override
    int numberOfRows();

    /**
     * Returns the number of columns in this.
     *
     * @return Number of columns this has.
     */
    @Override
    int numberOfColumns();

    /**
     * Returns a 1 x this.numberOfColumns matrix representing the row at
     * specified index i.
     *
     * @param i
     * @return Matrix as described above.
     */
    @Override
    Matrix row(int i);

    /**
     * Returns a this.numberOfRows x 1 matrix representing the row at specified
     * index j.
     *
     * @param j
     * @return Matrix as described above
     */
    @Override
    Matrix column(int j);

    /**
     * Creates a new row of 0.0's for this after its final row.
     */
    @Override
    void createRow();

    /**
     * Creates a new row of 0.0's for this at a specified index i.
     *
     * @param i
     */
    @Override
    void createRow(int i);

    /**
     * Removes the last row of this and returns it as a 1 x this.numberOfColumns
     * Matrix.
     *
     * @return Matrix as described above.
     */
    @Override
    Matrix removeRow();

    /**
     * Removes the row at specified index i from this and returns it as a 1 x
     * this.numberOfColumns Matrix.
     *
     * @param i
     * @return Matrix as described above.
     */
    @Override
    Matrix removeRow(int i);

    /**
     * Creates a new column at the this.numberOfColumns + 1 index.
     */
    @Override
    void createColumn();

    /**
     * Creates a new column at the specified index j.
     *
     * @param j
     */
    @Override
    void createColumn(int j);

    /**
     * Removes the final column of this and returns it as a 1 x
     * this.numberOfRows Matrix.
     *
     * @return Matrix as described above.
     */
    @Override
    Matrix removeColumn();

    /**
     * Removes the column at specified index j and returns it as a 1 x
     * this.numberOfRows Matrix.
     *
     * @param j
     * @return Matrix as described above.
     */
    @Override
    Matrix removeColumn(int j);

    /**
     * Returns the value of an entry of this at coordinates i, j.
     *
     * @param j
     * @param i
     * @return Returns value as described above.
     */
    @Override
    double entry(int i, int j);

    /**
     * Changes the value of an entry of this at coordinates i, j.
     *
     * @param i
     * @param j
     * @param value
     */
    @Override
    void setEntry(int i, int j, double value);

    /**
     * Swaps two rows in this.
     *
     * @param row1
     *            Index for first row.
     * @param row2
     *            Index for second row.
     */
    void swapRows(int row1, int row2);

    /**
     *
     * Adds one row (rowToAdd) to another (rowToAddTo).
     *
     * @param rowToAddTo
     *            Index for row that is being added to.
     *
     * @param rowToAdd
     *            Index for row that is being added to.
     * @requires This is not an empty matrix.
     */
    void addRows(int rowToAddTo, int rowToAdd);

    /**
     *
     * Subtracts one row (rowToSubtract) from another (rowToSubtractFrom).
     *
     * @param rowToSubtractFrom
     *            Index for row being subtracted from.
     *
     * @param rowToSubtract
     *            Index for row that will be subtracted.
     * @requires This is not empty.
     */
    void subtractRows(int rowToSubtractFrom, int rowToSubtract);

    /**
     *
     * Multiplies a row at index i by some double, factor.
     *
     * @param i
     *
     * @param factor
     * @requires This is not empty.
     */
    void multiplyRow(int i, double factor);

    /**
     *
     * Adds a matrix, m, to this.
     *
     * @param m
     *            Matrix being added to this.
     * @requires this and m have the same dimensions.
     */
    void add(Matrix m);

    /**
     * Multiplies this my another matrix m.
     *
     * @param m
     *            Matrix to be multiplied.
     * @return The product of multiplying this by m.
     * @requires this.numberOfRows == this.numberOfColumns
     */
    Matrix multiply(Matrix m);

}
