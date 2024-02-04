package Commands.general;

import Commands.general.ImplementedCommands.*;
import Active.IActive;

import java.util.ArrayList;
import java.util.List;

public class CommandManager
{
    private List<ICommand> commands;
    public CommandManager(IActive active)
    {
        this.commands = new ArrayList<>();
        ICommand cd = new Cd("cd", active);
        ICommand mkdir = new Mkdir("mkdir", active);
        ICommand ls = new Ls("ls", active);
        ICommand tree = new Tree("tree", active);
        ICommand more = new More("more", active);
        ICommand cp = new Cp("cp", active);
        ICommand mv = new Mv("mv", active);

        this.commands.add(cd);
        this.commands.add(mkdir);
        this.commands.add(ls);
        this.commands.add(tree);
        this.commands.add(more);
        this.commands.add(cp);
        this.commands.add(mv);
    }

    public void executeCommand(String command)
    {
        for (ICommand x : commands)
        {
            String commandName = x.getName();
            if (command.equals(commandName) || command.matches(commandName + "\\s.*"))
            {
                x.execute(command);
                return;
            }
        }
        System.out.println("Invalid command");
    }
}
