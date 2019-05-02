filtro :: (a -> Bool) -> [a] -> [a]
filtro _ [] = []  
filtro p (x:xs)  
    | p x       = x : filtro p xs
    | otherwise = filtro p xs

stringToChar :: String -> Char
stringToChar string = string !! 0

main :: IO()
main = do
    letra <- getLine
    let letter = stringToChar letra
    palavra <- getLine
    putStrLn (filtro (/= letter) palavra)