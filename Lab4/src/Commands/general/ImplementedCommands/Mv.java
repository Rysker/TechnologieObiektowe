package Commands.general.ImplementedCommands;

import Commands.general.Command;
import Active.IActive;
import Filesystem.AbstractElement;
import Filesystem.Directory;

public class Mv extends Command
{
    public Mv(String command_name, IActive active)
    {
        super(command_name, active);
    }

    @Override
    public void execute(String command)
    {
        String[] parts = command.split(" ");

        if (command.matches("mv [a-zA-Z0-9.l\\/_]* [a-zA-Z0-9.l\\/_]*"))
        {
            String originPath = parts[1];
            String destinationPath = parts[2];
            AbstractElement origin = ((Directory) (this.active.getActiveElement())).findFromPath(originPath);
            AbstractElement destination = ((Directory) (this.active.getActiveElement())).findFromPath(destinationPath);
            if(origin == null)
            {
                System.out.println("No files found to move");
                return;
            }

            if(destination instanceof Directory)
            {
                if(((Directory)destination).hasChildren(origin.getName()))
                {
                    System.out.println("Object with the same name already exist in directory!");
                    return;
                }

                if(((Directory)destination).checkOwnersToRoot(origin.getName()))
                {
                    System.out.println("owner object cannot be moved to it's child!");
                    return;
                }

                if(((Directory)destination).getName() == origin.getName())
                {
                    System.out.println("Can't move object to itself");
                    return;
                }

                AbstractElement tmp = origin.clone(null);
                ((Directory) origin.getOwner()).removeElement(origin);
                ((Directory) destination).addElement(tmp);
                System.out.println("Object has been successfully moved!");
            }
            else
                System.out.println("Can't move objects to non directory");
        }
        else
            System.out.println("Invalid mv command");
    }
}
