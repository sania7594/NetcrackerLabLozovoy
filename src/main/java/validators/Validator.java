package validators;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Validation class
 *
 * @author Alex Lozovoy
 */

public class Validator<T> {

    private static final Logger log =Logger.getLogger(Validator.class.getName());
    Object expected;
    Selector<Object, T> selector;
    Verifier<Object> verifier;


    /**
     * @param expected expected
     * @param selector selector
     * @param verifier verifier
     */
    public <U> Validator(U expected, Selector<U, T> selector, Verifier<U> verifier) {
        this.expected = expected;
        this.selector = (Selector<Object, T>) selector;
        this.verifier = (Verifier<Object>) verifier;
    }

    public Message validate(T target) {
        Object actual = selector.select(target);
        boolean isValid = verifier.verify(expected, actual);
        if (isValid) {
            log.log(Level.INFO, "Adding contract in repository");

            return new Message("Very good", Status.OK);
        } else {
            log.log(Level.WARNING, "Fatal error:expected"+expected);
            log.log(Level.INFO, "Contract cant be added in repository");
            return new Message("Fatal error: expected = " + expected, Status.ERROR);
        }

    }

}
