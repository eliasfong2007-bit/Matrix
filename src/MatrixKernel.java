import components.standard.Standard;

/**
 * Interface for Matrix Kernel. Note that as a package wide convention row and
 * column indices begin at 1 rather than zero to adhere to typical math
 * conventions. MatrixKernel
 */
public interface MatrixKernel extends Standard<Matrix> {

    /**
     * Returns the number of rows in this.
     *
     * @return Number of rows this has.
     */
    int numberOfRows();

    /**
     * Returns the number of columns in this.
     *
     * @return Number of columns this has.
     */
    int numberOfColumns();

    /**
     * Returns a 1 x this.numberOfColumns matrix representing the row at
     * specified index j.
     *
     * @param i
     * @return Matrix as described above.
     */
    Matrix row(int i);

    /**
     * Returns a this.numberOfRows x 1 matrix representing the row at specified
     * index j.
     *
     * @return Matrix as described above.
     */
    Matrix column(int j);

    /**
     * Creates a new row of 0.0's for this after its final row.
     */
    void createRow();

    /**
     * Creates a new row of 0.0's for this at a specified index i.
     *
     * @param i
     */
    void createRow(int i);

    /**
     * Removes the last row of this and returns it as a 1x this.numberOfColumns
     * Matrix.
     *
     * @return Matrix as described above.
     */
    Matrix removeRow();

    /**
     * Removes the row at specified index i from this and returns it as a 1 x
     * this.numberOfColumns Matrix.
     *
     * @param i
     * @return Matrix as described above.
     */
    Matrix removeRow(int i);

    /**
     * Creates a new column at the this.numberOfColumns + 1 index.
     */
    void createColumn();

    /**
     * Creates a new column at the specified index j.
     *
     * @param j
     */
    void createColumn(int j);

    /**
     * Removes the final column of this and returns it as a 1 x
     * this.numberOfRows Matrix.
     *
     * @return Matrix as described above.
     */
    Matrix removeColumn();

    /**
     * Removes the column at specified index j and returns it as a 1 x
     * this.numberOfRows Matrix.
     *
     * @param j
     * @return Matrix as described above.
     */
    Matrix removeColumn(int j);

    /**
     * Returns the value of an entry of this at coordinates i, j.
     *
     * @param i
     * @param j
     * @return Returns value as described above.
     */
    double entry(int i, int j);

    /**
     * Changes the value of an entry of this at coordinates i, j.
     *
     * @param i
     * @param j
     * @param value
     */
    void setEntry(int i, int j, double value);

}
