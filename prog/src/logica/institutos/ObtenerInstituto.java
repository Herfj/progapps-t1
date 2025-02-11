package logica.institutos;

import logica.entidades.Instituto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import API.datatypes.DTInstituto;

public class ObtenerInstituto {
    private String nombre;

    public ObtenerInstituto(String name) {
        nombre = name;
    }

    public Instituto getInstituto() {

        ExisteInstituto existeInstituto = new ExisteInstituto(nombre);
        if (existeInstituto.existe()) {
            Instituto ret = new Instituto();

            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("InstitutoJPA");
            EntityManager entitymanager = emfactory.createEntityManager();
            ret = entitymanager.find(Instituto.class, nombre);
            entitymanager.close();
            emfactory.close();
            return ret;

        } else {
            return null;
        }

    }

    public DTInstituto geDTInstituto() {
        Instituto instituto = getInstituto();
        return new DTInstituto(instituto);
    }
}