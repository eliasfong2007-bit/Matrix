/**
 * Matrix Interface with enhanced secondary methods.
 */
public interface Matrix {
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
     * specified index i.
     *
     * @param i
     * @return Matrix as described above.
     */
    Matrix row(int i);

    /**
     * Returns a this.numberOfRows x 1 matrix representing the row at specified
     * index j.
     * @return
     * Matrix as described above
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
     * Removes the last row of this and returns it as a 1 x this.numberOfColumns
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
     * @param  j
     * @return
     *        Returns value as described above.
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

    /* 
     * 
     * Multiplies a row at index i by some double, factor.
     * @param i
     * @param factor
     */
    void multiplyRow(int i, double factor);

    /* 
     * 
     *            ToAdd) to another (rowToAddTo).
     * @param rowToAddTo
     *            Index for row that is being added to.
     * @param rowToAdd
     *                  Index for row that is being added to.
     */
    void addRows(int rowToAddTo, int rowToAdd);

    /* 
     * 
     *            Subtract) from another (rowToSubtractFrom).
     * @param rowToSubtractFrom
     *                Index for row being subtracted from.
     * @param rowToSubtract
     *                     Index for row that will be subtracted.
     */
    void subtractRows(int rowToSubtractFrom, int rowToSubtract);

    /* 
     * 
     *            rix a to this.
     * @param m
     *         Matrix being added to this.
     */
    void add(Matrix m);

    /**
     * Multiplies this my another matrix m.
     * @param m
     *         Matrix to be multiplied.
     */
    void multiply(Matrix m)

     *  
     * Returns a matrix that is the corresponding row echelon form of this (provided
     * it is possible to do so.)
     */
    Matrix ref();

    /**
     * Returns a matrix that corresponds to the reduced row echelon form of this
     * (provided it is possible to do so).
     */
    Matrix rref();

     * 
    /**
     * Returns a matrix corresponding to this (provided it is possible to do so).
     */
    double det();

}