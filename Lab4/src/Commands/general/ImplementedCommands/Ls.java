package Commands.general.ImplementedCommands;

import Commands.general.Command;
import Active.IActive;
import Filesystem.Directory;

public class Ls extends Command
{
    public Ls(String command_name, IActive active)
    {
        super(command_name, active);
    }

    @Override
    public void execute(String command)
    {
        String[] parts = command.split(" ");

        if(command.matches("ls"))
            System.out.println(((Directory) active.getActiveElement()).ls());
        else if (command.matches("ls \\.\\."))
        {
            if(active.getActiveElement().getOwner() == null)
            {
                System.out.println("Root has no owner");
                return;
            }
            System.out.println(((Directory) active.getActiveElement().getOwner()).ls());
        }

        else if (command.matches("ls [a-zA-Z0-9.l\\/_]*"))
        {
            String correctPath = parts[1];
            Directory pathToLs = this.handleCdPath(correctPath);
            if(pathToLs != null)
            {
                System.out.println(pathToLs.ls());
            }
        }
        else
            System.out.println("Invalid ls command");
    }
}
