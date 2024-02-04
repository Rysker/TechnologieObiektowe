package Active;

import Filesystem.AbstractElement;

public interface IActive
{
    public void setActiveElement(AbstractElement active);
    public AbstractElement getActiveElement();
}
