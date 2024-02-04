package Active;

import Filesystem.AbstractElement;

public class Active implements IActive
{
    private AbstractElement active;

    public Active(AbstractElement active)
    {
        this.active = active;
    }
    public AbstractElement getActiveElement()
    {
        return active;
    }
    public void setActiveElement(AbstractElement active)
    {
        this.active = active;
    }
}
