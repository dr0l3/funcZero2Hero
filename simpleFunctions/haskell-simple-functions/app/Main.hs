module Main where

import Lib

myConcreteFunction :: Int -> Int -> Int
myConcreteFunction x y = x + y

myFirstFunction :: (Num a) => a -> a -> a
myFirstFunction x y = x + y

mySecondFunction x y = x + y

myThirdFunction :: (Num a) => a -> a -> (a -> a -> a) -> a
myThirdFunction x y f = f x y

myFourthFunction :: (Num a) => a -> a -> a
myFourthFunction x y = x - y

multCreator :: (Num a) =>  a -> (a -> a -> a) -> a -> a
multCreator x f y = f x y

generic :: a -> a -> (a -> a -> a) -> a
generic x y f = f x y

stringAppender :: [a] -> [a] -> [a]
stringAppender x y = y ++ x

stringAppender2 :: String -> String -> String
stringAppender2 x y = x ++ y

main :: IO ()
main = do
    print $ myConcreteFunction 1 2
    print $ myFirstFunction 1 2
    print $ mySecondFunction 1 2
    print $ myThirdFunction 1 2 myFirstFunction
    print $ myFourthFunction 2 1
    print $ multCreator 1 myFirstFunction 2
    print $ func 2
    print $ multCreator 1 myFirstFunction $ 2
    print $ generic 1 2 myFirstFunction
    print $ stringAppender "world!" "hello, "
    print $ stringAppender [1,2,3] [4,5,6]
    print $ stringAppender2 "hello, " "world!"
    print $ stringFunc "hello, "
    print $ stringFunc "Go away "
    where
        func = multCreator 1 myFirstFunction
        stringFunc = stringAppender "world!"
