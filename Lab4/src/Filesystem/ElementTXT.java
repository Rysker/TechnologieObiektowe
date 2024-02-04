package Filesystem;

import java.util.ArrayList;

public class ElementTXT extends Element
{
    private String text;

    public String getText()
    {
        return this.text;
    }
    public ElementTXT(String name, String text)
    {
        this.name = name;
        this.text = text;
    }
    public ElementTXT(ElementTXT other, AbstractElement owner)
    {
        this.name = other.name;
        this.text = other.text;
        this.owner = owner;
    }
    public void setContent(String text)
    {
        this.text = text;
    }
    @Override
    public AbstractElement clone(AbstractElement owner)
    {
        return new ElementTXT(this, owner);
    }
}
