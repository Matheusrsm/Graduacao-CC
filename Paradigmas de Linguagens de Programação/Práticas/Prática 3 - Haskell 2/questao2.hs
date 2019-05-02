filaDeIdosos :: [Int] -> [Int]
filaDeIdosos [] = []
filaDeIdosos (x:xs)
    | x >= 60 = x : filaDeIdosos xs
    | otherwise = [] ++ filaDeIdosos xs

removeIdososDaFila :: [Int] -> [Int]
removeIdososDaFila [] = []
removeIdososDaFila (x:xs)
    | x >= 60 = [] ++ removeIdososDaFila xs
    | otherwise = x : removeIdososDaFila xs

main = do
    fila <- getLine
    let filaIdosos = read fila :: [Int]
    print (filaDeIdosos filaIdosos ++ removeIdososDaFila filaIdosos)