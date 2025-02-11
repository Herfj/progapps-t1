package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import API.*;

import java.util.List;
import API.datatypes.*;
import java.sql.Date;

public class AltaEdicionCurso extends JInternalFrame {
	private JTextField textField;
	private JPanel panel;
	private JLabel lblInstituto;
	private JComboBox comboBox;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_50;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JSpinner spinner_2;
	private JSpinner spinner_3;
	private JSpinner spinner_4;
	private JSpinner spinner_5;
	private JSpinner spinner_6;
	private JComboBox comboBox_1;
	private JButton btnAgregar;
	private JButton btnAceptar;
	private JButton btnRefresh;
	private ArrayList<String> docentesAgregados;
	private ListaDocentes listaDocentes = null;
	private String institutoElegido;
	private ILogica Interfaz = new BizcochoEnARG().getInterface();
	JFileChooser jf = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF", "jpg", "gif");
	File imagen = null;

	private List<DTInstituto> getInstitutos() {
		return Interfaz.listaInstitutos();
	}

	private List<DTCurso> getCursos(String instituto) {
		return Interfaz.listaCursosPorInstituto(instituto);
	}

	/**
	 * Create the frame.
	 */
	public AltaEdicionCurso() {
		setTitle("Alta edicion de curso");
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 528, 357);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(30, 30, 350, 25);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		lblInstituto = new JLabel("Instituto");
		lblInstituto.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblInstituto);

		comboBox = new JComboBox();
		comboBox.addItem("");
		List<DTInstituto> institutos = getInstitutos();
		for (DTInstituto instituto : institutos) {
			comboBox.addItem(instituto.nombreInstituto);
		}

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String eleccion = comboBox.getSelectedItem().toString();
				institutoElegido = eleccion;
			}
		});
		panel.add(comboBox);

		panel_1 = new JPanel();
		panel_1.setBounds(29, 67, 350, 25);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel = new JLabel("Curso");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblNewLabel);

		comboBox_1 = new JComboBox();
		List<DTCurso> cursos = Interfaz.listaCursosPorInstituto(comboBox.getSelectedItem().toString());
		for (DTCurso curso : cursos) {
			comboBox_1.addItem(curso.nombreCurso);
		}
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

			}
		});
		panel_1.add(comboBox_1);

		panel_2 = new JPanel();
		panel_2.setBounds(30, 107, 350, 100);
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNombreEdicion = new JLabel("Nombre edicion");
		lblNombreEdicion.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblNombreEdicion);

		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);

		JLabel lblFechaInicio = new JLabel("Fecha inicio");
		lblFechaInicio.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblFechaInicio);

		panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 3, 0, 0));

		spinner_4 = new JSpinner();
		spinner_4.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), new Integer(31), new Integer(1)));
		panel_3.add(spinner_4);

		spinner_5 = new JSpinner();
		spinner_5.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), new Integer(12), new Integer(1)));
		panel_3.add(spinner_5);

		spinner_6 = new JSpinner();
		spinner_6.setModel(
				new SpinnerNumberModel(new Integer(1900), new Integer(1900), new Integer(2020), new Integer(1)));
		panel_3.add(spinner_6);

		JLabel lblFechaFin = new JLabel("Fecha fin");
		lblFechaFin.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblFechaFin);

		panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 3, 0, 0));

		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), new Integer(31), new Integer(1)));
		panel_4.add(spinner_1);

		spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), new Integer(12), new Integer(1)));
		panel_4.add(spinner_2);

		spinner_3 = new JSpinner();
		spinner_3.setModel(
				new SpinnerNumberModel(new Integer(1900), new Integer(1900), new Integer(2020), new Integer(1)));
		panel_4.add(spinner_3);

		JLabel lblCuposopcional = new JLabel("Cupos (Opcional)");
		lblCuposopcional.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblCuposopcional);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), new Integer(100), new Integer(1)));
		panel_2.add(spinner);

		panel_5 = new JPanel();
		panel_5.setBounds(30, 219, 350, 50);
		getContentPane().add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));

		panel_50 = new JPanel();
		panel_50.setBounds(40, 67, 40, 25);
		getContentPane().add(panel_50);
		panel_50.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblProfesores = new JLabel("Docentes");
		lblProfesores.setHorizontalAlignment(SwingConstants.LEFT);
		panel_5.add(lblProfesores);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listaDocentes == null) {
					listaDocentes = new ListaDocentes();
					VentanaPrincipal.desktopPane.add(listaDocentes);
					listaDocentes.setVisible(true);
				} else {
					listaDocentes.setVisible(true);
				}
			}
		});
		panel_5.add(btnAgregar);

		JLabel lblEmpty = new JLabel("Empty");
		panel_5.add(lblEmpty);

		JButton btnImagen = new JButton("Imagen");
		btnImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int r;
				JFileChooser jf = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG", "jpg", "png");
				jf.setFileFilter(filter);
				r = jf.showOpenDialog(AltaEdicionCurso.this);
				if (r == JFileChooser.APPROVE_OPTION) {
					imagen = jf.getSelectedFile();
					lblEmpty.setText(imagen.getName());
				} else {
					System.err.println("Te falta calle.");
				}
			}
		});
		panel_5.add(btnImagen);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (listaDocentes != null) {
					docentesAgregados = listaDocentes.getDocentesSeleccionados();
				}
				Date fechaIni = Date.valueOf(spinner_6.getValue().toString() + "-" + spinner_5.getValue().toString()
						+ "-" + spinner_4.getValue().toString());
				Date fechaFin = Date.valueOf(spinner_3.getValue().toString() + "-" + spinner_2.getValue().toString()
						+ "-" + spinner_1.getValue().toString());
				Calendar c = Calendar.getInstance();
				String dia = Integer.toString(c.get(Calendar.DATE));
				String mes = Integer.toString(c.get(Calendar.MONTH));
				String annio = Integer.toString(c.get(Calendar.YEAR));
				Date fechaAlta = Date.valueOf(annio + "-" + mes + "-" + dia);
				int cupo = Integer.parseInt(spinner.getValue().toString());
				if (cupo < 1) {
					cupo = 0;
				}
				if (!(comboBox_1.getSelectedItem().toString().equals(""))) {
					String ret = Interfaz.crearEdicion(textField.getText(), comboBox_1.getSelectedItem().toString(),
							fechaIni, fechaFin, cupo, fechaAlta, docentesAgregados,imagen);
					if (ret.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"Se ha agregado la edicion con nombre: " + textField.getText());
						resetDatos();
					} else {
						JOptionPane.showMessageDialog(null, ret);
					}
				} else {
					JOptionPane.showMessageDialog(null, "ERROR: No se ha selecionado un curso");
				}

			}
		});
		btnAceptar.setBounds(208, 273, 117, 25);
		getContentPane().add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(67, 273, 117, 25);
		getContentPane().add(btnCancelar);

		btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(389, 67, 94, 25);
		getContentPane().add(btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox_1.removeAllItems();
				List<DTCurso> cursos = getCursos(comboBox.getSelectedItem().toString());
				for (DTCurso curso : cursos) {
					comboBox_1.addItem(curso.nombreCurso);
				}
			}
		});

	}

	private void resetDatos() {
		if (listaDocentes != null) {
			docentesAgregados.clear();
		}
	}

	private boolean validarFormulario() {
		if (textField.getText().equals("")) {
			return false;
		}

		return true;
	}
}