package fi.hallo.mapgen;

import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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

        Element graph = new Element("g");
        graph.setAttribute("id", "hexes");

        int i = 0;
        int[] ax = {0, 100, 150, 100, -100, -150};
        int[] aye = {150, -150, 0, 150, 150, 0};
        int[] ayo = {300, -150, 0, 150, 150, 0};
        for (int y = 0; y < hexMap.getHeight(); y++) {
            for (int x = 0; x < hexMap.getWidth(); x++) {
                Element polygon = new Element("polygon");
                polygon.setAttribute("id", i + "");
                polygon.setAttribute("style", "fill:none;stroke:#000000;stroke-width:1.5px");
                if (x%2 == 0) {
                    int sx = x*250;
                    int sy = y*300;
                    StringBuilder points = new StringBuilder();
                    for (int j = 0; j < 6; j++) {
                        sx += ax[j];
                        sy += aye[j];
                        points.append(sx + "," + sy + " ");
                    }
                    polygon.setAttribute("points", points.toString().trim());
                } else {
                    int sx = x*250;
                    int sy = y*300;
                    StringBuilder points = new StringBuilder();
                    for (int j = 0; j < 6; j++) {
                        sx += ax[j];
                        sy += ayo[j];
                        points.append(sx + "," + sy + " ");
                    }
                    polygon.setAttribute("points", points.toString().trim());
                }graph.addContent(polygon);
                i += 1;
            }
        }


        svg.addContent(graph);

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
        docType.setPublicID("-//W3C//DTD SVG 1.1//EN http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd");
        Document doc = new Document(svg, docType);
        XMLOutputter serializer = new XMLOutputter();
        Format f = serializer.getFormat();
        f.setOmitDeclaration(false);
        f.setIndent("  ");
        f.setLineSeparator("\n");
        f.setEncoding("UTF-8");
        f.setExpandEmptyElements(true);
        serializer.setFormat(f);
        File file = new File(fileName);
        serializer.output(doc, new PrintWriter(new FileWriter(file)));
        format(file);
    }

    public void format(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        String text;
        FileInputStream fs = new FileInputStream(file);
        InputStreamReader in = new InputStreamReader(fs);
        BufferedReader br = new BufferedReader(in);
        while (true) {
            text = br.readLine();
            if (text == null)
                break;
            if (text.contains("-//W3C")) {
                text = "\n" +
                        "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"50in\" height=\"50in\" version=\"1.1\">";
            }
            if (text.contains("xmlns=\"\" ")) {
                text = text.replace("xmlns=\"\" ", "");
            }
            text = text.replaceAll("</polygon>", "</polygon>\n    ");

            sb.append(text + "\n");
        }
        fs.close();
        in.close();
        br.close();
        FileWriter writer = new FileWriter(file);
        BufferedWriter output = new BufferedWriter(writer);
        output.write(sb.toString());
        output.close();
    }

}
