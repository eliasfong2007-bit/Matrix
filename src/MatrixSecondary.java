import components.simplewriter.SimpleWriter;

/**
 * Secondary Methods for Matrix interface. MatrixSecondary
 */
public abstract class MatrixSecondary implements Matrix {

    /**
     * Returns a string representation of this.
     *
     * @see java.lang.Object#toString()
     * @return String representation of this.
     */
    @Override
    public String toString() {
        String result = "";
        assert this != null : "This is null";
        for (int i = 1; i <= this.numberOfRows(); i++) {
            for (int j = 1; j <= this.numberOfColumns(); j++) {
                result += Double.toString(this.entry(i, j)) + ", ";
            }
        }
        return result;
    }

    /**
     * Evaluate whether two matrices are equivalent.
     *
     * @param m
     *            matrix this is compared to.
     *
     * @return Whether or not two matrices are equal.
     */
    public boolean equals(Matrix m) {
        boolean result = true;
        if (this.numberOfRows() != m.numberOfRows()
                || this.numberOfColumns() != m.numberOfColumns()) {
            result = false;
        } else {
            for (int i = 0; i < this.numberOfRows(); i++) {
                for (int j = 0; j < this.numberOfColumns(); j++) {
                    if (this.entry(i, j) != m.entry(i, j)) {
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Pretty prints this.
     *
     * @param out
     */
    public void prettyPrint(SimpleWriter out) {
        for (int i = 0; i < this.numberOfRows(); i++) {
            out.println(this.row(i).toString());
        }
    }

    /**
     * Swaps two rows in this.
     *
     * @param row1
     *            Index for first row.
     * @param row2
     *            Index for second row.
     */
    @Override
    public void swapRows(int row1, int row2) {
        assert this != null : "Violation of: this is not null";
        Matrix holderRow = this.row(row2);
        for (int k = 1; k <= this.numberOfColumns(); k++) {
            this.setEntry(row2, k, this.entry(row1, k));
        }
        for (int k = 1; k <= this.numberOfColumns(); k++) {
            this.setEntry(row1, k, holderRow.entry(1, k));
        }
        holderRow.clear();
    }

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
    @Override
    public void addRows(int rowToAddTo, int rowToAdd) {
        assert this != null : "Violation of: this is not null";
        assert this
                .numberOfRows() != 0 : "Violation of: this is not an empty matrix";
        for (int k = 1; k <= this.numberOfColumns(); k++) {
            this.setEntry(rowToAddTo, k,
                    this.entry(rowToAddTo, k) + this.entry(rowToAdd, k));
        }
    }

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
    @Override
    public void subtractRows(int rowToSubtractFrom, int rowToSubtract) {
        assert this != null : "Violation of: this is not null";
        assert this.numberOfRows() != 0 : "Violation of: this is not empty";
        for (int k = 1; k <= this.numberOfColumns(); k++) {
            this.setEntry(rowToSubtractFrom, k, this.entry(rowToSubtractFrom, k)
                    - this.entry(rowToSubtract, k));
        }
    }

    /**
     *
     * Multiplies a row at index i by some double, factor.
     *
     * @param i
     *
     * @param factor
     * @requires This is not empty.
     */
    @Override
    public void multiplyRow(int i, double factor) {
        assert this != null : "Violation of: this is not null";
        assert this.numberOfRows() != 0 : "Violation of: this is not empty";
        for (int k = 1; k <= this.numberOfColumns(); k++) {
            this.setEntry(i, k, this.entry(i, k) * factor);
        }
    }

    /**
     *
     * Adds a matrix, m, to this.
     *
     * @param m
     *            Matrix being added to this.
     * @requires this and m have the same dimensions.
     */
    @Override
    public void add(Matrix m) {
        assert this != null : "Violation of: this is not null";
        assert this.numberOfRows() == m
                .numberOfRows() : "Violation of: this and m have the same dimensions";
        assert this.numberOfColumns() == m
                .numberOfColumns() : "Violation of: this and m have the same dimensions";
        for (int i = 0; i < this.numberOfRows(); i++) {
            for (int j = 0; j < this.numberOfColumns(); j++) {
                this.setEntry(i, j, this.entry(i, j) + m.entry(i, j));
            }
        }
    }

    /**
     * Multiplies this my another matrix m.
     *
     * @param m
     *            Matrix to be multiplied.
     * @return The product of multiplying this by m.
     * @requires this.numberOfRows == this.numberOfColumns
     */
    @Override
    public Matrix multiply(Matrix m) {
        assert this != null : "Violation of: this is not null";
        assert this.numberOfRows() == this
                .numberOfColumns() : "Violation of this.numberOfRows()"
                        + " == this.numberOfColumns()";
        // Create empty return matrix
        Matrix result = this.newInstance();
        // Build an empty (zero-filled) result of the right size
        while (result.numberOfRows() < this.numberOfRows()) {
            result.createRow();
        }
        while (result.numberOfColumns() < m.numberOfColumns()) {
            result.createColumn();
        }
        // Find value of each entry by calculated corresponding dot products
        for (int i = 1; i <= result.numberOfRows(); i++) {
            for (int j = 1; j <= result.numberOfColumns(); j++) {
                result.setEntry(i, j,
                        this.dotProduct(this.row(i), this.column(j)));
            }
        }
        return result;
    }

    /**
     * Helper method for multiply method for matrices. Calculates the dot
     * product of a row and column.
     *
     * @param row
     * @param column
     * @return The double value for the dot product.
     */
    private double dotProduct(Matrix row, Matrix column) {
        double result = 0;
        for (int k = 1; k <= row.numberOfColumns(); k++) {
            result += (row.entry(1, k) * row.entry(k, 1));
        }
        return result;
    }
}
