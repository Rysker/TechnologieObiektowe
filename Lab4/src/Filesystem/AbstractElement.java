package Filesystem;

public abstract class AbstractElement
{
    protected String name;
    protected AbstractElement owner;
    public String getName()
    {
        return null;
    }
    public void AddElement(AbstractElement element){}
    public void setOwner(AbstractElement owner) {}
    public AbstractElement getOwner()
    {
        return null;
    }
    public String getPath()
    {
        return null;
    }
    public AbstractElement clone(AbstractElement owner)
    {
        return null;
    }
}
