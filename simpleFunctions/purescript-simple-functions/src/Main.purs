module Main where

import Prelude
import Control.Monad.Eff (Eff)
import Control.Monad.Eff.Console (CONSOLE, log)

myFirstFunction :: Int -> Int -> Int
myFirstFunction x y = x + y

mySecondFunction x y = x + y

myThirdFunction :: Int -> Int -> (Int -> Int -> Int) -> Int
myThirdFunction x y f = f x y

stringAppender :: String -> String -> String
stringAppender x y = x <> y

generic :: forall a.  a -> a -> (a -> a -> a) -> a
generic x y f = f x y

main :: forall e. Eff (console :: CONSOLE | e) Unit
main = do
    let adder = myFirstFunction 1
    log (show (myFirstFunction 1 2))
    log (show (mySecondFunction 1 2))
    log (show (myThirdFunction 1 2 myFirstFunction))
    log $ show $ adder 2
    log (show (stringAppender "hello, " "world!"))
    log (show (generic "hello, " "world!" stringAppender))
    log (show (generic 1 2 myFirstFunction))
