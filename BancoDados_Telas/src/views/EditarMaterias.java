package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


import dbos.*;
import daos.Alunos;
import daos.Materias;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class EditarMaterias {

	protected static final String frame = null;
	protected JFrame frmAltMat;
	private JTextField txtNomeAlt_M;
	private static int cod;
	private static String nome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarMaterias window = new EditarMaterias(cod,nome);
					window.frmAltMat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditarMaterias(int cod,String nome) {
		this.cod = cod;
		this.nome = nome;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAltMat = new JFrame();
		frmAltMat.setTitle("Alterar Materia");
		frmAltMat.setBounds(100, 100, 539, 154);
		frmAltMat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		frmAltMat.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		panel.add(lblNewLabel);
		
		txtNomeAlt_M = new JTextField();
		panel.add(txtNomeAlt_M);
		txtNomeAlt_M.setColumns(10);
		txtNomeAlt_M.setText(nome);
		/*
		GroupLayout groupLayout = new GroupLayout(frmAltMat.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
						.addGap(2))
						);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
								)
						)
		);*/
		
		JButton btnLimpar_M = new JButton("Limpar");
		btnLimpar_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNomeAlt_M.setText("");
			}
		});
		panel.add(btnLimpar_M);
		
		JButton btnAlterar_M = new JButton("Alterar");
		btnAlterar_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Materia alt = new Materia(cod,txtNomeAlt_M.getText());
				Materias.atualizar(alt);
				frmAltMat.dispose();
				}catch(Exception err) {}
			}
		});
		panel.add(btnAlterar_M);
	}

}
