package main

import "github.com/toddnguyen47/splith1headers/internal/splith1headers"

func main() {
	inputFile := `Z:/DocumentsAndStuff/Desktop/1.xhtml`

	nodesStruct := splith1headers.NewNodesStruct()
	nodesStruct.Split(inputFile)
}
