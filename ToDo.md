# TODO List #

## Implement all GlobalPlatform's commands ##

GP Comm will reach version 1.0 when it defines all of the 11 GlobalPlatform's commands.

## Create a utility method that handles GP Comm initialization and finalization ##

When writing simple GP Comm code, there's boilerplate code related to the initialization and finalization of a `GpComm` object. I'm proposing a new method that'll make this task easier. The method's signature may be `public static void withGpComm(DoWithGpComm doer)`. The interface `DoWithGpComm` has only one method, `void doIt(GpComm gpcomm)`, which does something with a `GpComm` object. This object is created and disposed by the `withGpComm` method. An example of using such method would be (supposing it's defined in the `GpComm` class):

```
GpComm.withGpComm(new DoWithGpComm() {
  @Override
  public void doIt(GpComm gpcomm) {
    System.out.println("There are " + gpcomm.getAvailableTerminals().size() + " available terminals");
  }
});
```

The code below does the same as the code above, but it's implemented using the current version of GP Comm:

```
GpComm gpcomm = null;
try {
  gpcomm = new GpComm();
  // Begin: using the GpComm object
  System.out.println("There are " + gpcomm.getAvailableTerminals().size() + " available terminals");
  // End: using the GpComm object
}
catch (GpCommException e) {
  System.err.println(e.getMessage());
}
finally {
  if (gpcomm != null) {
    try {
      gpcomm.close();
    }
    catch (GpCommException e) {
      System.err.println(e.getMessage());
    }
  }
}
```

And when the Java Programming Language has closures, this new method call would be even more compact:

```
GpComm.withGpComm({GpComm gpcomm =>
  System.out.println("There are " + gpcomm.getAvailableTerminals().size() + " available terminals");
});
```

:)


