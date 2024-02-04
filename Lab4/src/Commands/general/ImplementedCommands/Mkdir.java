package Commands.general.ImplementedCommands;

import Active.IActive;
import Commands.general.Command;
import Filesystem.Directory;

public class Mkdir extends Command
{
    public Mkdir(String command_name, IActive active)
    {
        super(command_name, active);
    }

    @Override
    public void execute(String command)
    {
        String[] parts = command.split(" ");

        if (command.matches("mkdir [a-zA-Z0-9.l\\/_]*"))
        {
            String directoryPath = parts[1];
            createDirectories(directoryPath);
        } else
            System.out.println("Invalid mkdir command");
    }

    private void createDirectories(String path)
    {
        Directory activeElement = (Directory) active.getActiveElement();
        String[] directories = path.split("/");

        for (String directory : directories)
        {
            if (directory.equals(".."))
            {
                activeElement = (Directory) activeElement.findFromPath(directory);
                if (activeElement == null)
                {
                    System.out.println("Cannot move back from root directory");
                    return;
                }
            }
            else
            {
                Directory foundElement = (Directory) activeElement.findFromPath(directory);

                if (foundElement == null)
                {
                    Directory newDirectory = new Directory(directory);
                    activeElement.addElement(newDirectory);
                    activeElement = newDirectory;
                }
                else if (foundElement instanceof Directory)
                    activeElement = foundElement;
                else
                {
                    System.out.println(directory + " is not a directory");
                    return;
                }
            }
        }
        System.out.println("Directories created");
    }
}
