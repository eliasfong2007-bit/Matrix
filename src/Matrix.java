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
    private Sequence<Sequence<Integer>> matrix;

    /**
     * Creates new representation for Matrix.
     */
    private void createNewRep() {
        this.matrix = new Sequence1L<Sequence<Integer>>();
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
    public final void transferFrom(Sequence<Sequence<Integer>> source) {
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
    public final void copyFrom(Sequence<Sequence<Integer>> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";

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
