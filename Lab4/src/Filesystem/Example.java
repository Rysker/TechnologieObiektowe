package Filesystem;
public abstract class Example
{
    public static AbstractElement generateFileSystem()
    {
        ElementTXT dane = new ElementTXT("dane.txt", "Przykladowy tekst");
        AbstractElement shared = new Directory("shared", dane);
        AbstractElement downloads = new Directory("downloads", shared);
        AbstractElement documents = new Directory("documents");
        AbstractElement root = new Directory("root", downloads);
        ((Directory) root).addElement(documents);
        return root;
    }
}
