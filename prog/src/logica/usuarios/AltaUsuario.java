package logica.usuarios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.File;
import java.sql.Date;

import logica.entidades.Usuario;
import logica.institutos.ObtenerInstituto;
import logica.entidades.Docente;
import logica.entidades.Instituto;
import logica.entidades.Estudiante;

public class AltaUsuario {

    private String nick;
    private String name;
    private String ape;
    private String mail;
    private Date nacDate;
    private String passw;

    public AltaUsuario(String nickname, String nombre, String apellido, String email, Date nac, String passp) {
        nick = nickname;
        name = nombre;
        ape = apellido;
        mail = email;
        nacDate = nac;
        this.passw = passp;
    }

    private boolean hasErrorEmpty() {
        if (nick.isEmpty())
            return true;
        if (name.isEmpty())
            return true;
        if (mail.isEmpty())
            return true;
        if (nacDate.toString().isEmpty())
            return true;
        return false;
    }

    private String hasErrorAlredyExists() {
        ExisteUsuario existeUsuario = new ExisteUsuario();
        String ret = "";
        if (existeUsuario.existeNickname(nick))
            ret = ret + "ERROR: Ya existe el usuario con nickname: " + nick + "\n";
        if (existeUsuario.existeMail(mail))
            ret = ret + "ERROR: Ya existe el usuario con mail: " + mail + "\n";
        if (passw.isEmpty()) {
            ret += "ERROR: La Contrasenia no puede ser vacia!";
        }
        return ret;
    }

    public String createEstudiante() {
        String retorno = "";
        if (hasErrorEmpty()) {
            retorno = "ERROR: No se permiten campos nulos, por favor complete todos los campos! \n";
        }
        retorno = retorno + hasErrorAlredyExists();
        if (retorno.isEmpty()) {
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("InstitutoJPA");
            EntityManager entitymanager = emfactory.createEntityManager();
            entitymanager.getTransaction().begin();

            Estudiante estudiante = new Estudiante(nick, name, ape, mail, nacDate, passw);

            entitymanager.persist(estudiante);
            entitymanager.getTransaction().commit();

            entitymanager.close();
            emfactory.close();
        }
        return retorno;
    }

    public String createDocente(String nombreInstituto) {
        String retorno = "";
        if (hasErrorEmpty() || nombreInstituto.isEmpty()) {
            retorno = "ERROR: No se permiten campos nulos, por favor complete todos los campos! \n";
        }
        retorno = retorno + hasErrorAlredyExists();

        if (retorno.isEmpty()) {
            Instituto instituto = new ObtenerInstituto(nombreInstituto).getInstituto();
            if (instituto != null) {
                EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("InstitutoJPA");
                EntityManager entitymanager = emfactory.createEntityManager();
                entitymanager.getTransaction().begin();

                Docente docente = new Docente(nick, name, ape, mail, nacDate, instituto, passw);

                entitymanager.persist(docente);

                entitymanager.getTransaction().commit();
                entitymanager.close();
                emfactory.close();
            } else {
                return retorno + "ERROR: No se encontro el instituto, por favor ingrese uno correcto!";
            }

        }
        return retorno;
    }
}
