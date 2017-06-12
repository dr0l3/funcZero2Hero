module Main where

import Lib
import System.Directory
import System.Environment
import System.FilePath
import Control.Monad
import Control.Applicative

printContentsOfCurrentDirectory :: IO ()
printContentsOfCurrentDirectory = do
    dir <- getCurrentDirectory
    contents <- getDirectoryContents dir
    print contents

getDirectoryContentsAbs :: FilePath -> IO [FilePath]
getDirectoryContentsAbs dir =
  getDirectoryContents dir >>= mapM (canonicalizePath . (dir </>))

getDirectoryContentsNoOnlyDots :: FilePath -> IO [FilePath]
getDirectoryContentsNoOnlyDots dir = do
  contents <- getDirectoryContents dir
  return (filter (`notElem` [".",".."]) contents)

toAbsFilePath :: FilePath -> FilePath -> IO FilePath
toAbsFilePath dir fileName  = canonicalizePath (dir </> fileName)

printFile :: Int -> FilePath -> IO ()
printFile indent fileName = putStrLn $ replicate indent '-' ++ takeFileName fileName

prettyPrintDir :: Int -> FilePath -> IO ()
prettyPrintDir indent path = do
        absContents <- mapM (toAbsFilePath path) =<< getDirectoryContentsNoOnlyDots path
        files <- filterM doesFileExist absContents
        subDirs <- filterM doesDirectoryExist absContents
        _ <- printFile indent path
        mapM_ (printFile (indent + 2)) files
        mapM_ (prettyPrintDir (indent + 2)) subDirs


main :: IO ()
main = do
  dir <- getCurrentDirectory
  prettyPrintDir 0 dir
