import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class XlassLoader extends ClassLoader {

    private byte[] decodeXlassBytes(byte[] xlassBytes){
        byte[] classBytes = new byte[xlassBytes.length];
        for (int i=0; i<xlassBytes.length; i++){
            classBytes[i] = (byte)(255 - xlassBytes[i]);
        }

        return classBytes;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (!name.equals("Hello"))
            return super.loadClass(name);

        File file = new File(name+".xlass");
        if (!file.exists())
            throw new ClassNotFoundException(name);

        byte[] xlassBytes;
        try{
            xlassBytes =  Files.readAllBytes(file.toPath());
        }catch (IOException e){
            throw new ClassNotFoundException(e.getMessage());
        }

        byte[] classBytes = decodeXlassBytes(xlassBytes);
        return defineClass(name, classBytes,0,classBytes.length);
    }
}
