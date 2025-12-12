package ucam.musica;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

	private String nombre;
    private List<Cancion> canciones = new ArrayList<>();

    public Playlist(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void addCancion(Cancion cancion) {
        if (cancion != null) {
            canciones.add(cancion);
        }
    }

    @Override
    public String toString() {
        return nombre;
    }
}
