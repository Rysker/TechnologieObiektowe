package Commands.general.ImplementedCommands;

import Commands.general.Command;
import Active.IActive;
import Filesystem.*;

public class Cd extends Command
{
    public Cd(String command_name, IActive active)
    {
        super(command_name, active);
    }
    @Override
    public void execute(String command)
    {
        String[] parts = command.split(" ");

        if (command.matches("cd \\.\\."))
        {
            if(active.getActiveElement().getOwner() == null)
            {
                System.out.println("Root has no owner");
                return;
            }
            active.setActiveElement(active.getActiveElement().getOwner());
        }

        else if (command.matches("cd [a-zA-Z0-9.l\\/_]*"))
        {
            String correctPath = parts[1];
            AbstractElement destination = ((Directory) (this.active.getActiveElement())).findFromPath(correctPath);
            if(destination == null)
            {
                System.out.println("Path is invalid " + active.getActiveElement().getPath());
                return;
            }
            active.setActiveElement(this.handleCdPath(correctPath));
        }

        else
            System.out.println("Invalid cd command");
    }
}
