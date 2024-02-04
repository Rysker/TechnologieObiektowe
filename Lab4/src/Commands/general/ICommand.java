package Commands.general;

public interface ICommand
{
    void execute(String command);
    String getName();
}
