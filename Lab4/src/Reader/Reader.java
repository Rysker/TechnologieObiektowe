package Reader;

import Commands.general.CommandManager;
import java.util.Scanner;
import Active.*;

public class Reader implements IReader
{
    private CommandManager cmdManager;
    private IActive active;
    private Scanner scanner;

    public Reader(CommandManager cmdmng, IActive active)
    {
        this.cmdManager = cmdmng;
        this.scanner = new Scanner(System.in);
        this.active = active;
    }
    @Override
    public void read()
    {
        while(true)
        {
            if(this.active.getActiveElement().getPath() == null)
                System.out.println("Element is not directory!");
            else
            {
                System.out.println("Active Path: " + this.active.getActiveElement().getPath());
                String command = this.scanner.nextLine();
                try
                {
                    this.cmdManager.executeCommand(command);
                }
                catch (Exception e)
                {
                    System.out.println("Error 001");
                    System.exit(1);
                }
            }
        }
    }
}
