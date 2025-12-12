package ucam.musica;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String nombreUsuario;
    private String tipo_suscripcion; // Gratis o Premium
    private List<Playlist> playlists = new ArrayList<>();

    public Usuario(String nombreUsuario, String tipo_suscripcion) {
        this.nombreUsuario = nombreUsuario;
        this.tipo_suscripcion = tipo_suscripcion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getTipo_suscripcion() {
        return tipo_suscripcion;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public Playlist crearPlaylist(String nombrePlaylist) {
        Playlist p = new Playlist(nombrePlaylist);
        playlists.add(p);
        return p;
    }

    public void eliminarPlaylist(Playlist playlist) {
        playlists.remove(playlist);
    }

    public boolean compartirPlaylist(Playlist playlist, Usuario otroUsuario) {
        if (playlist == null || otroUsuario == null) {
            return false;
        }

        if (!"Premium".equalsIgnoreCase(this.tipo_suscripcion)) {
            return false;
        }

        otroUsuario.playlists.add(playlist);
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", tipo_suscripcion='" + tipo_suscripcion + '\'' +
                ", playlists=" + playlists +
                '}';
    }
}
