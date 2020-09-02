package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class ConsultaUsuario extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaUsuario frame = new ConsultaUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultaUsuario() {
		setTitle("Consulta de usuario");
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);

	}

}
