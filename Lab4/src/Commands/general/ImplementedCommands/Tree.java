package Commands.general.ImplementedCommands;

import Commands.general.Command;
import Active.IActive;
import Filesystem.Directory;

public class Tree extends Command
{
    public Tree(String command_name, IActive active)
    {
        super(command_name, active);
    }

    @Override
    public void execute(String command)
    {
        String[] parts = command.split(" ");

        if (command.equals("tree"))
            System.out.println(((Directory) active.getActiveElement()).tree());
        else if (command.matches("tree(?:\\.\\.|(?: [a-zA-Z0-9._/]*))"))
        {
            String correctPath = parts[1];
            Directory pathToTree = this.handleCdPath(correctPath);
            if (pathToTree != null)
            {
                System.out.println(pathToTree.tree());
            }
            else
                System.out.println("Invalid path for tree command");
        }
        else
            System.out.println("Invalid tree command");
    }
}
