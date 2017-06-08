The difference in languages start to shine through.
Purescript has no way to access the file system outside of the foreign function interface
Haskell only supports outside actions through monads.
Scala does impure IO and has no native support for pure IO. However cats-effects provides good support for wrapping impure IO in pure IO blocks.
