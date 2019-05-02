import qualified Data.List as DL
import qualified Data.Maybe as DM

stringToChar :: String -> Char
stringToChar string = string !! 0

inverteLista :: String -> String
inverteLista [] = []
inverteLista (x:xs) = inverteLista xs ++ [x]

inversao :: (String, String) -> String 
inversao (ateALetra, depoisDaLetra) = ateALetra ++ (inverteLista depoisDaLetra)

main = do
    letra <- getLine
    let letter = stringToChar letra
    palavra <- getLine
    let index = DL.findIndex (==letter) palavra
    let indexCorrect = DM.fromMaybe "" $ fmap show $ index
    putStrLn (inversao (splitAt ((read indexCorrect :: Int) + 1) palavra))