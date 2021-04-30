package serialization;

import java.io.*;

/**
 * Methods to serialize and deserialize object
 * @author Nathan
 */
public class Serializer {
    /**
     * Writes an obect to the specified file path
     * @param filePath the path to write to
     * @param object the object to write
     * @param <T> the type of the object
     */
    public static <T extends Serializable> void serialize(String filePath, T object) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch(IOException | IllegalArgumentException e){
            System.err.println(e);
        }
    }
    
    /**
     * Returns a java object of type <code>T</code> from a serialized file
     * @param filePath the path to the file to read from
     * @param <T> the type of object to be read in
     * @return An object of the specified type
     */
    public static <T extends Serializable> T deserialize(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            T obj = (T) objectInputStream.readObject();
            
            objectInputStream.close();
            fileInputStream.close();
            
            return obj;
        } catch(IOException | ClassNotFoundException | IllegalArgumentException e){
            System.err.println(e);
        }
        return null;
    }

}
