package Filesystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Directory extends Element
{
    private List<AbstractElement> children;

    public Directory()
    {
        this.children = new ArrayList<>();
    }

    public Directory(String name)
    {
        this.children = new ArrayList<>();
        this.name = name;
    }

    public Directory(String name, AbstractElement other)
    {
        this.children = new ArrayList<>();
        this.name = name;
        this.addElement(other);
    }

    public Directory(Directory other, AbstractElement owner)
    {
        this.name = other.name;
        this.children = other.cloneChildren(this);
        this.owner = owner;
    }

    public void addElement(AbstractElement element)
    {
        if (this.children.contains(element))
            System.out.println("Element " + element.getName() + " already exists in path " + this.getPath());
        else
        {
            element.setOwner(this);
            this.children.add(element);
        }
    }

    public void removeElement(AbstractElement element)
    {
        if (!this.children.contains(element))
            System.out.println("Element " + element.getName() + " doesn't exist in path " + this.getPath());
        else
        {
            this.children.remove(element);
            element.setOwner(null);
        }
    }

    public AbstractElement findFromPath(String path)
    {
        String[] pathElements = path.split("/");
        AbstractElement find = this;
        if(pathElements[0].matches("root"))
        {
            find = this.getRoot();
            pathElements = Arrays.copyOfRange(pathElements, 1, pathElements.length);
        }

        for (String element : pathElements)
        {
            if(element.equals("."))
            {

            }
            else if (element.equals(".."))
            {
                find = find.getOwner();
            }
            else
            {

                boolean found = false;
                if (find instanceof Directory)
                {
                    for (AbstractElement child : ((Directory) find).children)
                    {
                        if (child.getName().equals(element))
                        {
                            find = child;
                            found = true;
                            break;
                        }
                    }
                }

                if (!found)
                {
                    return null;
                }
            }
        }

        return find;
    }
    public String ls()
    {
        StringBuilder result = new StringBuilder("Listing contents of " + this.getPath() + ":\n");

        for (AbstractElement child : this.children)
            result.append(getElementColored(child));

        return result.toString();
    }

    public String tree()
    {
        StringBuilder result = new StringBuilder("Listing contents of " + this.getPath() + ":\n");
        treeRecursive(this.children, result, 1);
        return result.toString();
    }

    private boolean hasAnyChildren()
    {
        if(this.children != null)
        {
            if (this.children.size() != 0)
            {
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean hasChildren(String name)
    {
        for(AbstractElement child: this.children)
        {
            if(child.getName() == name)
                return true;
        }
        return false;
    }

    private String getElementColored(AbstractElement element)
    {
        StringBuilder result = new StringBuilder();

        if(element instanceof ElementTXT)
            result.append("\u001B[32m");
        if(element instanceof Directory)
            result.append("\u001B[34m");

        result.append("\t"+element.getName());
        result.append("\u001B[0m\n");
        return result.toString();
    }

    private void treeRecursive(List<AbstractElement> elements, StringBuilder result, int indentationLevel)
    {
        String indentation = "\t".repeat(indentationLevel);
        for (AbstractElement child :elements)
        {
            result.append(indentation).append(getElementColored(child));
            if (child instanceof Directory && ((Directory) child).hasAnyChildren())
            {
                treeRecursive(((Directory) child).children, result, indentationLevel + 1);
            }
        }
    }

    private List<AbstractElement> cloneChildren(AbstractElement owner)
    {
        ArrayList<AbstractElement> tmp = new ArrayList<>();
        for(AbstractElement child: this.children)
            tmp.add(child.clone(owner));
        return tmp;
    }

    @Override
    public AbstractElement clone(AbstractElement owner)
    {
        return new Directory(this, owner);
    }

    private AbstractElement getRoot()
    {
        AbstractElement active = this;
        while (active.getOwner() != null) {
            active = active.getOwner();
        }
        return active;
    }

    public boolean checkOwnersToRoot(String name)
    {
        AbstractElement active = this;
        while (active.getOwner() != null)
        {
            active = active.getOwner();
            if (active.getName().equals(name))
                return true;
        }
        return false;
    }
}
