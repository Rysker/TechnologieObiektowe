package Commands.general;

import Active.IActive;
import Filesystem.AbstractElement;
import Filesystem.Directory;

public class Command implements ICommand
{
    protected String command_name;
    protected IActive active;

    public Command(String command_name, IActive active)
    {
        this.command_name = command_name;
        this.active = active;
    }

    public void execute(String command)
    {

    }

    @Override
    public String getName()
    {
        return this.command_name;
    }

    protected Directory handleCdPath(String path)
    {
        AbstractElement destination = ((Directory) (this.active.getActiveElement())).findFromPath(path);

        if (destination == null)
        {
            System.out.println("Directory not found: " + path);
            return null;
        }
        else
        {
            if (destination instanceof Directory)
                return (Directory) destination;
            else
            {
                System.out.println(path + " is not a directory");
                return null;
            }
        }
    }
}
