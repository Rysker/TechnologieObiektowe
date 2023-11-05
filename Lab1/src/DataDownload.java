import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import javax.swing.JOptionPane;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.List;

public class DataDownload
{

    private static DataDownload instance;

    public static DataDownload getInstance()
    {
        if (instance == null)
            instance = new DataDownload();
        return instance;
    }

    private DataDownload()
    {

    }

    public List<String> getData()
    {
        List<String> data = new ArrayList<>();
        try
        {
            String website = "https://www.nbp.pl/kursy/xml/lasta.xml";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(website);
            NodeList pozycje = document.getElementsByTagName("pozycja");

            for (int i = 0; i < pozycje.getLength(); i++)
            {
                Element pozycja = (Element) pozycje.item(i);
                data.add(pozycja.getElementsByTagName("nazwa_waluty").item(0).getTextContent());
                data.add(pozycja.getElementsByTagName("przelicznik").item(0).getTextContent());
                data.add(pozycja.getElementsByTagName("kod_waluty").item(0).getTextContent());
                data.add(pozycja.getElementsByTagName("kurs_sredni").item(0).getTextContent());
            }
            addPLN(data);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Couldn't download data from NBP's website!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        return data;
    }

    private void addPLN(List<String> list)
    {
        list.add("polski zloty");
        list.add("1");
        list.add("PLN");
        list.add("1");
    }
}