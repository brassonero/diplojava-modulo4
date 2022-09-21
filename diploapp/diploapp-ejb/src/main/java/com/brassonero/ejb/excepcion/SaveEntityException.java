package com.brassonero.ejb.excepcion;

public class SaveEntityException extends Exception {
    public SaveEntityException(String msg) {
        super(msg);
    }

    public SaveEntityException(String msg, Throwable anid) {
        super(msg, anid);
    }
}