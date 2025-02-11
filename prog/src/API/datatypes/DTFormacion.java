package API.datatypes;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import logica.entidades.Formacion;
import logica.entidades.Curso;

public class DTFormacion {

	public String nombreFormacion;
	public String descFormacion;
	public String fechaInicio;
	public String fechaFin;
	public String fechaAlta;
	public List cursos;

	public DTFormacion() {
		super();
	}

	public DTFormacion(Formacion formacion) {
		super();
		this.nombreFormacion = formacion.getNombreFormacion();
		this.descFormacion = formacion.getDescFormacion();
		this.fechaInicio = formacion.getFechaInicio().toString();
		this.fechaFin = formacion.getFechaFin().toString();
		this.fechaAlta = formacion.getFechaAlta().toString();

		List<Curso> cursosFormacion = formacion.getCursos();
		List<DTCurso> dtCursos = new ArrayList();

		for (Curso curso : cursosFormacion) {
			DTCurso dtCurso = new DTCurso(curso);
			dtCursos.add(dtCurso);
		}
		this.cursos = dtCursos;

	}

	@Override
	public String toString() {
		return "DTFormacion [nombreFormacion=" + nombreFormacion + ", descFormacion=" + descFormacion + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", fechaAlta=" + fechaAlta + ", cursos=" + cursos + "]";
	}

}
