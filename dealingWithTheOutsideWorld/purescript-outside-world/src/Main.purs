module Main where

import Prelude
import Control.Monad.Eff (Eff)
import Control.Monad.Eff.Console (CONSOLE, log)
import Data.Path.Pathy

main :: forall e. Eff (console :: CONSOLE | e) Unit
main = do
  let dir = currentDir
  log $ show $ printPath $ dir
