package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import dbos.*;
import daos.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Principal {

	private JFrame frmPrincipal;
	private JTextField txtRa;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtCod;
	private JTextField txtNome_1;

	
	private Aluno modeloAluno;
	private Materia modeloMateria;
	private Fez modeloFez;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setTitle("Principal");
		frmPrincipal.setBounds(100, 100, 513, 424);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmPrincipal.getContentPane().add(tabbedPane);
		
		JPanel alunos = new JPanel();
		tabbedPane.addTab("Alunos", null, alunos, null);
		alunos.setLayout(new GridLayout(0, 6, 0, 0));
		
		JLabel lblResultado = new JLabel("Resultado");
		alunos.add(lblResultado);
		
		JLabel lblRa = new JLabel("RA:");
		alunos.add(lblRa);
		
		txtRa = new JTextField();
		txtRa.setText("Informe o RA");
		alunos.add(txtRa);
		txtRa.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		alunos.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setText("Informe o Nome");
		alunos.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		alunos.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setText("Informe o Email");
		alunos.add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		alunos.add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		alunos.add(btnAlterar);
		
		JButton btnListar = new JButton("Listar");
		alunos.add(btnListar);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try{
					int ra = Integer.parseInt(txtRa.getText());
					modeloAluno = new Aluno(ra,txtNome.getText(),txtEmail.getText());
					
					Alunos.incluir(modeloAluno);
				}catch(Exception err) {}
			}
		});
		alunos.add(btnIncluir);
		
		JPanel materias = new JPanel();
		tabbedPane.addTab("Materias", null, materias, null);
		materias.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnIncluir_1 = new JButton("Incluir");
		materias.add(btnIncluir_1);
		
		txtCod = new JTextField();
		txtCod.setText("cod");
		materias.add(txtCod);
		txtCod.setColumns(10);
		
		JButton btnExcluir_1 = new JButton("Excluir");
		materias.add(btnExcluir_1);
		
		JLabel lblResultado_1 = new JLabel("Resultado");
		materias.add(lblResultado_1);
		
		txtNome_1 = new JTextField();
		txtNome_1.setText("nome");
		materias.add(txtNome_1);
		txtNome_1.setColumns(10);
		
		JButton btnAlterar_1 = new JButton("Alterar");
		materias.add(btnAlterar_1);
		
		JButton btnListar_1 = new JButton("Listar");
		materias.add(btnListar_1);
		
		JLabel lblNomeMateria = new JLabel("Nome Materia");
		materias.add(lblNomeMateria);
		
		JLabel lblNewLabel = new JLabel("CodigoMateria");
		materias.add(lblNewLabel);
		
		JPanel fez = new JPanel();
		tabbedPane.addTab("Fez", null, fez, null);
		fez.setLayout(new GridLayout(1, 0, 0, 0));
	}

}
