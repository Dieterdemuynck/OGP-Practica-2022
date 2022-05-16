package main.java.ingredient;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

import java.io.Serial;

public class IncorrectStateIDException extends Exception {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 2L;
    private final State state;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/
    public IncorrectStateIDException(State state) {
        this.state = state;
    }

    /* *********************************************************
     * STATE
     * *********************************************************/
    /**
     *
     */
    @Basic
    @Immutable
    public State getState() {
        return state;
    }

    /**
     *
     */
    @Immutable
    public int getStateID() {
        return getState().getID();
    }

}
