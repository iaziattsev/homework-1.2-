package NotebookUpgrade.data;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDb implements Db {

    protected final String path;

    public FileDb(String path) {
        this.path = path;
    }

    @Override
    public void save(Object obj) throws DbException {
        Object[] data = findAll();
        try (FileOutputStream stream = new FileOutputStream(this.path)) {
            Object[] newData = new Object[data.length + 1];
            System.arraycopy(data, 0, newData, 0, data.length);
            newData[newData.length - 1] = obj;
            stream.write(convertToBytes(newData));
        } catch (IOException ex) {
            throw new DbException("DB error: " + ex.getMessage());
        }
    }

    @Override
    public Object[] findAll() throws DbException {
        try {
            Path path = Paths.get(this.path);
            byte[] data = Files.readAllBytes(path);
            if (data.length > 0) {
                return (Object[]) this.convertFromBytes(data);
            } else {
                return new Object[0];
            }
        } catch (IOException ex) {
            throw new DbException("DB error: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            throw new DbException("DB error: " + ex.getMessage());
        }
    }

    private byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        }
    }

    private Object convertFromBytes(byte[] data) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInput in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }

    @Override
    public void removeAll() throws DbException {
        try (FileOutputStream stream = new FileOutputStream(this.path)) {
            PrintWriter writer = new PrintWriter(stream);
            writer.write("");
        } catch (IOException ex) {
            throw new DbException("DB error: " + ex.getMessage());
        }
    }

    @Override
    public void removeById(int id) throws DbException, ArrayIndexOutOfBoundsException {
        try {
            Object[] data = findAll();
            Object[] newData = new Object[data.length - 1];

            System.arraycopy(data, 0, newData, 0, id);
            System.arraycopy(data, id + 1, newData, id, data.length - id -1);

            FileOutputStream stream = new FileOutputStream(this.path);
            stream.write(convertToBytes(newData));
            stream.close();

            //<< readAll
            //>> [test, test1, test2, test3]
            //<< removeById
            //<< 2
            //<< readAll
            //>> [test, test1, test3]
        }
        catch (IOException ex) {
            throw new DbException("DB error: " + ex.getMessage());
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new DbException("DB error: There is no such id in DB");
        }
    }
}
