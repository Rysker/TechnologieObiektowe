import Commands.general.CommandManager;
import Active.*;
import Filesystem.*;
import Reader.Reader;

public class Main
{
    public static void main(String[] args)
    {
        AbstractElement fs = Example.generateFileSystem();
        IActive active = new Active(fs);
        CommandManager commandManager = new CommandManager(active);
        Reader reader = new Reader(commandManager, active);
        reader.read();
    }
}