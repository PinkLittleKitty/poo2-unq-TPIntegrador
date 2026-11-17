package org.viajesOnline.utilsTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.utils.Usuario;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    void usuarioConstructor() {
        Usuario usuario = new Usuario(
                "Nombre",
                "mail@test.com",
                "1111111",
                "123456",
                true
        );

        assertEquals("Nombre", usuario.getNombre());
        assertEquals("mail@test.com", usuario.getEmail());
        assertEquals("1111111", usuario.getCUIT());
        assertEquals("123456", usuario.getDNI());
        assertTrue(usuario.getEsResponsableInscripto());
    }
}
