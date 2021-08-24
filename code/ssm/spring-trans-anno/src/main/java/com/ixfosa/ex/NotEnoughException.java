package com.ixfosa.ex;

/**
 * Created by ixfosa on 2021/4/23 18:07
 */
public class NotEnoughException extends RuntimeException {
    public NotEnoughException() {
        super();
    }

    public NotEnoughException(String message) {
        super(message);
    }
}
