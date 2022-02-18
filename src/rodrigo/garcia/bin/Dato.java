package rodrigo.garcia.bin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dato {
    private BufferedReader bufferedReader;

    public Dato() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String dato_string(){
        try{
            return bufferedReader.readLine();
        } catch (IOException ioe){
            ioe.printStackTrace();
            return null;
        }
    }

    public int dato_int(){
        try {
            return Integer.parseInt(dato_string());
        } catch (NumberFormatException error) {
            return 0;
        }
    }
}
