package fi.hallo.mapgen;

import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SvgWriter {

    private HexMap hexMap;
    private Element svg;

    public SvgWriter(HexMap hm) {
        this.hexMap = hm;
        this.svg = new Element("svg", "http://www.w3.org/2000/svg");
    }


    public void writeMap(){

        svg.setAttribute("width", "50in");
        svg.setAttribute("height", "50in");
        svg.setAttribute("version", "1.1");

        svg.addContent(new Element("g"));

        try {
            writeOut();
        } catch (Exception e) {

        }
    }

    private void writeOut() throws IOException {
        this.writeOut(hexMap.getRegion().toString() + "_" + System.currentTimeMillis() + ".svg");
    }

    private void writeOut(String fileName) throws IOException {
        DocType docType = new DocType(svg.getName());
        docType.setPublicID("http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd");
        Document doc = new Document(svg, docType);
        XMLOutputter serializer = new XMLOutputter();
        Format f = serializer.getFormat();
        f.setOmitDeclaration(false);
        f.setIndent("  ");
        f.setLineSeparator("\n");
        f.setEncoding("UTF-8");
        f.setExpandEmptyElements(false);
        serializer.setFormat(f);
        serializer.output(doc, new PrintWriter(new FileWriter(fileName)));
    }

}
