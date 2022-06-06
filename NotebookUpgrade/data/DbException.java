package NotebookUpgrade.data;

public class DbException extends Exception {
    public DbException() {
    }

    public DbException(String msg) {
        super(msg);
    }
}
