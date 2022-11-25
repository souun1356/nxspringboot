package com.example.demo.exception;

public class addOrderException extends Exception {

  public addOrderException() {
    super();
  }

  public addOrderException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
    super(arg0, arg1, arg2, arg3);
  }

  public addOrderException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public addOrderException(String arg0) {
    super(arg0);
  }

  public addOrderException(Throwable arg0) {
    super(arg0);
  }

}
