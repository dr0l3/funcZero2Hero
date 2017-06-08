module Main where

import Lib
import System.Directory

main :: IO ()
main = do
    dir <- getCurrentDirectory
    print $ dir
    contents <- getDirectoryContents dir
    print $ contents
