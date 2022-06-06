package NotebookUpgrade.utils;

public interface UserInteract {
    String readCommand() throws UserInteractReadException;

    void print(String output) throws UserInteractWriteException;
}
