import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
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

    private void createNewRep() {
        this.matrix = new Sequence1L<Sequence<Integer>();
    }

    /*
     * Constructors
     * --------------------------------------------------------------
     */
    /**
     * Constructor
     */
    public void Matrix1L() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3a<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3a<T> localSource = (Set3a<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
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
