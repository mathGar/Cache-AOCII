package Cache;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ReadFile {

    private InputStream inputstream;
    private DataInputStream data;
    private ArrayList<String> address;
    private String aux;

    public ReadFile() throws FileNotFoundException, IOException {
        this.inputstream = new FileInputStream("arqBinario1.dat");
        this.data = new DataInputStream(inputstream);
        address = new ArrayList();

        while(data.available() != 0) {
            aux = Integer.toBinaryString(data.readInt());
            while (aux.length() != 32) {
                aux = '0' + aux;
            }
            address.add(aux);
        }   
        data.close();
    }
}
