package splith1headers

import (
	"bytes"
	"fmt"
	"os"
	"strings"

	"golang.org/x/net/html"
)

type nodesStruct struct {
	Nodes    [][]*html.Node
	curIndex int
}

func NewNodesStruct() nodesStruct {
	return nodesStruct{
		Nodes:    make([][]*html.Node, 0),
		curIndex: -1,
	}
}

func (n *nodesStruct) Split(inputFile string) {
	data, err := os.ReadFile(inputFile)
	if err != nil {
		fmt.Println("ERROR IN READING FILE")
		return
	}

	rootNode, err := html.Parse(bytes.NewReader(data))
	if err != nil {
		fmt.Println("ERROR GOQUERY")
		return
	}

	n.parseNode(rootNode)
	fmt.Println(len(n.Nodes))
}

func (n *nodesStruct) parseNode(node *html.Node) {
	if node.Type == html.ElementNode {
		if node.Data == "h1" {
			n.curIndex += 1
			n.Nodes = append(n.Nodes, make([]*html.Node, 0))
			n.Nodes[n.curIndex] = append(n.Nodes[n.curIndex], node)
		} else if node.Data == "p" {
			// Simply append
			n.Nodes[n.curIndex] = append(n.Nodes[n.curIndex], node)
		} else if node.Data == "image" {
			src := ""
			for _, attr := range node.Attr {
				if strings.EqualFold(attr.Key, "href") {
					src = attr.Val
					break
				}
			}
			if len(src) > 0 {
				newAttr := html.Attribute{
					Key: "src",
					Val: src,
				}
				newNode := &html.Node{
					Type: html.ElementNode,
					Attr: []html.Attribute{newAttr},
					Data: "img",
				}
				n.Nodes[n.curIndex] = append(n.Nodes[n.curIndex], newNode)
				fmt.Printf("%v\n", newNode)
				panic("")
			}
		}
	}
	for child := node.FirstChild; child != nil; child = child.NextSibling {
		n.parseNode(child)
	}
}
