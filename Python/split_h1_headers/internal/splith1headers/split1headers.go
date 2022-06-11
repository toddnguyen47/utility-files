package splith1headers

import (
	"fmt"
	"strings"

	"github.com/beevik/etree"
)

var constants = struct {
	baseFileName string
}{
	baseFileName: "body",
}

type splitStruct struct {
	elems [][]*etree.Element
	index int
}

func NewSplitStruct() splitStruct {
	return splitStruct{
		elems: make([][]*etree.Element, 1),
		index: 0,
	}
}

func (s *splitStruct) Split(inputFile string) {
	doc := etree.NewDocument()
	err := doc.ReadFromFile(inputFile)
	if err != nil {
		fmt.Println("ERROR reading from file")
	}

	s.parseTree(&doc.Element)
	// TODO: WRITE TO FILES
}

func (s *splitStruct) parseTree(root *etree.Element) {
	if !isElemSkipped(root) {
		elemToAppend := root
		// If h1, advance to the next file
		if root.Tag == "h1" {
			s.elems = append(s.elems, make([]*etree.Element, 1))
			s.index += 1
		} else if root.Tag == "image" {
			newElem := etree.NewElement("img")
			newAttr := etree.Attr{
				Key:   "src",
				Value: root.SelectAttrValue("href", ""),
			}
			newElem.Attr = append(newElem.Attr, newAttr)
			elemToAppend = newElem
		}

		s.elems[s.index] = append(s.elems[s.index], elemToAppend)
	}

	for _, childElem := range root.ChildElements() {
		s.parseTree(childElem)
	}
}

func isElemSkipped(root *etree.Element) bool {
	// Skip <svg
	if root.Tag == "svg" {
		return true
	}

	// Skip <div class=svg_outer
	if root.Tag == "div" {
		attributes := root.Attr
		for _, attribute := range attributes {
			if attribute.Key == "class" && strings.Contains(attribute.Value, "svg_outer") {
				return true
			}
		}
	}

	return false
}
