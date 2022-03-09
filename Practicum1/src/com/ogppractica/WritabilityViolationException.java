package com.ogppractica;

/**
 * A class for exceptions in connection with violations of a file's writability. If a file is non-writable, yet an
 * attempt is made at changing the file, this exception must be thrown.
 */
public class WritabilityViolationException extends Exception {
}
