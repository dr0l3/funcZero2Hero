The difference in languages start to shine through.
Purescript has no way to access the file system outside of the foreign function interface
Haskell only supports outside actions through monads.
Scala does impure IO and has no native support for pure IO. However cats-effects provides good support for wrapping impure IO in pure IO blocks.

Notes on monads in general
- Monads seem to be about standardized notation for things.
  - One could also say they constrain the amount of implementations for behavior.
- With the right syntax actually some quite dense notation.
  - Error handling and retry is more general.
- Quite good at forcing proper abstactions and separation of concern
  - Syntactically hard (maybe impossible) to create mutable variables without doing it explicitly (statemonad vs var)
- Monads are about sequencing operations and hiding boiler plate of a certain kind while making the effect the boiler plate is trying to achieve explicit in the type. <-- Bingo!
  - Examples
    - Options -> dont need if x == null aka dont need the flattenting
    - List -> Dont need the flattening
    - Future -> Dont need the flattening

Interesting implementation exercises
- Recursive printing of directory contents [Done, could be prettier]
  - Better printing of files
    - Only next x levels deeep
    - If more than x files only print x and a message saying how many extra files are present
    - Ability to specify what extra information should be show (size, last edited etc.)
