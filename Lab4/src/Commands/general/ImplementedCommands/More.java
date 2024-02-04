package Commands.general.ImplementedCommands;

import Commands.general.Command;
import Active.IActive;
import Filesystem.AbstractElement;
import Filesystem.Directory;
import Filesystem.ElementTXT;

public class More extends Command
{
    public More(String command_name, IActive active)
    {
        super(command_name, active);
    }

    @Override
    public void execute(String command)
    {
        String[] parts = command.split(" ");

        if (command.matches("more [a-zA-Z0-9.l\\/_]*"))
        {
            String correctPath = parts[1];
            AbstractElement file = ((Directory) (this.active.getActiveElement())).findFromPath(correctPath);
            if(file instanceof ElementTXT)
            {
                System.out.println("Content of file " + file.getName() + ":");
                System.out.println(((ElementTXT) file).getText());
            }
        }
        else
            System.out.println("Invalid more command");

    }
}
