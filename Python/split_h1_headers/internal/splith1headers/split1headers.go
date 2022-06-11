package splith1headers

import (
	"bytes"
	"fmt"
	"os"
	"strings"

	"golang.org/x/net/html"
)

var constants = struct {
	baseFileName string
}{
	baseFileName: "body",
}

type nodesStruct struct {
	Nodes        [][]*html.Node
	curIndex     int
	notesSection bool
}

func NewNodesStruct() nodesStruct {
	return nodesStruct{
		Nodes:        make([][]*html.Node, 1),
		curIndex:     0,
		notesSection: false,
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
	n.renderNodes()
}

func (n *nodesStruct) parseNode(node *html.Node) {
	if node.Type == html.ElementNode && !isNodeSkipped(node) {
		if node.Data == "h1" {
			n.curIndex += 1
			n.Nodes = append(n.Nodes, make([]*html.Node, 0))
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
			}
		} else {
			// Simply append
			n.Nodes[n.curIndex] = append(n.Nodes[n.curIndex], node)
		}
	}
	for child := node.FirstChild; child != nil; child = child.NextSibling {
		n.parseNode(child)
	}
}

func (n *nodesStruct) renderNodes() {
	for i, nodesPerFile := range n.Nodes {
		// TODO: CHECK FOR NOTES
		fileName := fmt.Sprintf("%s%02d.xhtml", constants.baseFileName, i)
		rootNode := html.Node{
			Type: html.ElementNode,
			Data: "div",
		}

		for _, node := range nodesPerFile {
			rootNode.AppendChild(node)
		}

		fmt.Printf("Writing to file: %s\n", fileName)
		var builder strings.Builder
		html.Render(&builder, &rootNode)
		err := os.WriteFile(fileName, []byte(builder.String()), 0644)
		if err != nil {
			fmt.Printf("ERROR WRITING TO FILE %s\n", fileName)
		}
	}
}

func isNodeSkipped(node *html.Node) bool {
	// Skip svg
	if node.Data == "svg" {
		return true
	}

	// Skip <div class="svg_outer"
	if node.Data == "div" {
		attributes := node.Attr
		for _, attr := range attributes {
			if attr.Key == "class" && strings.Contains(attr.Val, "svg_outer") {
				return true
			}
		}
	}

	return false
}
