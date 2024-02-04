package Filesystem;

public class Element extends AbstractElement
{
    public Element()
    {
        this.name = "";
        this.owner = null;
    }

    public Element(AbstractElement owner, String name)
    {
        this.name = name;
        this.owner = owner;
    }

    public Element(AbstractElement other, AbstractElement owner)
    {
        this.name = other.name;
        this.owner = owner;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public AbstractElement getOwner()
    {
        return this.owner;
    }

    @Override
    public void setOwner(AbstractElement owner)
    {
        this.owner = owner;
    }

    @Override
    public String getPath()
    {
        if(this.owner == null)
            return this.name;
        else
            return this.owner.getPath() + "/" + this.name;
    }

    @Override
    public AbstractElement clone(AbstractElement owner)
    {
        return null;
    }

}
