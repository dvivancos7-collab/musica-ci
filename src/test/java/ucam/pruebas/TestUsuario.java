package ucam.pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ucam.musica.Playlist;
import ucam.musica.Usuario;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;

import java.util.List;



public class TestUsuario {

	// Usuario1 para los primeros métodos y el toString
    private Usuario usuario1_gratis;
    private Playlist playlist1;

    // Usuario2 necesario para el método compartir
    private Usuario usuario2_premium;
    private Playlist playlist2;

    // PRIMERO DE TODO Y UNA ÚNICA VEZ
    @BeforeAll
    public static void inicio_pruebas() {
        System.out.println("-COMIENZAN LAS PRUEBAS DE LA CLASE USUARIO-");
    }

    // ÚLTIMO DE TODO Y UNA ÚNICA VEZ
    @AfterAll
    public static void fin_pruebas() {
        System.out.println("-PRUEBAS DE CLASE USUARIO FINALIZADAS-");
    }

    // ANTES DE CADA TEST
    @BeforeEach
    public void rellenar() {
        usuario1_gratis = new Usuario("Osama", "Gratis");
        playlist1 = usuario1_gratis.crearPlaylist("Opera");

        usuario2_premium = new Usuario("June", "Premium");
        playlist2 = usuario2_premium.crearPlaylist("Reggaeton");
    }

    // DESPUÉS DE CADA TEST
    @AfterEach
    public void vaciar() {
        usuario1_gratis = null;
        usuario2_premium = null;
        playlist1 = null;
        playlist2 = null;
    }

    // ---------- TESTS PRINCIPALES ----------

    @Test
    public void testGetNombreUsuario() {
        System.out.println("Ejecutando testGetNombreUsuario...");
        assertEquals("Osama", usuario1_gratis.getNombreUsuario());
    }

    @Test
    public void testGetTipo_suscripcion() {
        System.out.println("Ejecutando testGetTipo_suscripcion...");
        assertEquals("Gratis", usuario1_gratis.getTipo_suscripcion());
    }

    @Test
    public void testGetPlaylists() {
        System.out.println("Ejecutando testGetPlaylists...");
        List<Playlist> lista = usuario1_gratis.getPlaylists();
        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertTrue(lista.contains(playlist1));
    }

    @Test
    public void testCrearPlaylist() {
        System.out.println("Ejecutando testCrearPlaylist...");
        Playlist nueva = usuario1_gratis.crearPlaylist("Metal");
        assertNotNull(nueva);
        assertEquals("Metal", nueva.getNombre());
        assertTrue(usuario1_gratis.getPlaylists().contains(nueva));
    }

    @Test
    public void testEliminarPlaylist() {
        System.out.println("Ejecutando testEliminarPlaylist...");
        assertNotNull(usuario1_gratis.getPlaylists());
        usuario1_gratis.eliminarPlaylist(playlist1);
        assertFalse(usuario1_gratis.getPlaylists().contains(playlist1));
    }

    // Método compartirPlaylist - varias ramas

    @Test
    public void testCompartirPlaylist_PlaylistNull() {
        System.out.println("Ejecutando testCompartirPlaylist_PlaylistNull...");
        boolean resultado = usuario2_premium.compartirPlaylist(null, usuario1_gratis);
        assertFalse(resultado);
    }

    @Test
    public void testCompartirPlaylist_UsuarioNull() {
        System.out.println("Ejecutando testCompartirPlaylist_UsuarioNull...");
        boolean resultado = usuario2_premium.compartirPlaylist(playlist2, null);
        assertFalse(resultado);
    }

    @Test
    public void testCompartirPlaylist_premium_a_gratis() {
        System.out.println("Ejecutando testCompartirPlaylist_premium_a_gratis...");
        boolean resultado = usuario2_premium.compartirPlaylist(playlist2, usuario1_gratis);
        assertTrue(resultado);
        assertTrue(usuario1_gratis.getPlaylists().contains(playlist2));
    }

    @Test
    public void testCompartirPlaylist_gratis_a_premium() {
        System.out.println("Ejecutando testCompartirPlaylist_gratis_a_premium...");
        boolean resultado = usuario1_gratis.compartirPlaylist(playlist1, usuario2_premium);
        assertFalse(resultado);
        assertFalse(usuario2_premium.getPlaylists().contains(playlist1));
    }

    @Test
    public void testToString() {
        System.out.println("Ejecutando testToString...");
        String esperado = "Usuario{nombreUsuario='Osama', tipo_suscripcion='Gratis', playlists=[" + playlist1.toString() + "]}";
        assertEquals(esperado, usuario1_gratis.toString());
    }

    // ------------- TESTS DISEÑADOS PARA FALLAR -------------
    // Los dejamos como en la práctica anterior, pero DESHABILITADOS
    // para que el pipeline normal (CI) pase. S e pueden habilitar
    // para demostrar un caso de fallo.

    @Disabled("Prueba diseñada para fallar a propósito")
    @Test
    public void testFallo_GetPlaylists() {
        System.out.println("Ejecutando Test fallo 1...");
        List<Playlist> lista = usuario1_gratis.getPlaylists();
        assertNotSame(lista, lista); // siempre falla
    }


    @Test
    public void testFallo_crearPlaylist1() {
        System.out.println("Ejecutando Test fallo 2...");
        Playlist nueva = usuario1_gratis.crearPlaylist("Pop");
        assertNull(nueva); // falla porque no es null
    }

    @Disabled("Prueba diseñada para fallar a propósito")
    @Test
    public void testFallo_crearPlaylist2() {
        System.out.println("Ejecutando Test fallo 3...");
        Playlist nueva = usuario1_gratis.crearPlaylist("Pop");
        assertSame(nueva, playlist1); // falla
    }

    @Disabled("Prueba diseñada para fallar a propósito")
    @Test
    public void testFallo_crearPlaylist3() {
        System.out.println("Ejecutando Test fallo 4...");
        Playlist[] array1 = { playlist1 };
        Playlist[] array2 = { playlist2 };
        assertArrayEquals(array1, array2); // falla
    }
}
