package Commands.general.ImplementedCommands;

import Commands.general.Command;
import Active.IActive;
import Filesystem.AbstractElement;
import Filesystem.Directory;

public class Cp extends Command
{
    public Cp(String command_name, IActive active)
    {
        super(command_name, active);
    }


    @Override
    public void execute(String command)
    {
        String[] parts = command.split(" ");

        if (command.matches("cp [a-zA-Z0-9.l\\/_]* [a-zA-Z0-9.l\\/_]*"))
        {
            String originPath = parts[1];
            String destinationPath = parts[2];
            AbstractElement origin = ((Directory) (this.active.getActiveElement())).findFromPath(originPath);
            AbstractElement destination = ((Directory) (this.active.getActiveElement())).findFromPath(destinationPath);
            if(origin == null)
            {
                System.out.println("No files found to copy");
                return;
            }

            if(destination instanceof Directory)
            {
                if(((Directory)destination).hasChildren(origin.getName()))
                {
                    System.out.println("Object with the same name already exist in directory!");
                    return;
                }
                AbstractElement tmp = origin.clone(null);
                ((Directory) destination).addElement(tmp);
                System.out.println("Object has been successfully copied!");
            }

            else
                System.out.println("Can't copy objects to non directory");
        }
        else
            System.out.println("Invalid cp command");
    }
}
