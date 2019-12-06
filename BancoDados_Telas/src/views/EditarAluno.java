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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarAluno {

	protected static final String frame = null;
	protected JFrame frmAltAluno;
	private static int ra;
	private static String nome;
	private static String email;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel_1;
	private JTextField txtEmailAlt;
	private JTextField txtNomeAlt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarAluno window = new EditarAluno(ra,nome,email);
					window.frmAltAluno.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditarAluno(int ra, String nome , String email) {
		this.ra = ra;
		this.nome = nome;
		this.email = email;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frmAltAluno = new JFrame();
		frmAltAluno.setTitle("Alterar Aluno");
		frmAltAluno.setBounds(100, 100, 487, 164);
		frmAltAluno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel_1 = new JPanel();
		
		panel_2 = new JPanel();
		
		JPanel panel = new JPanel();
		
		JLabel label = new JLabel("Nome:");
		panel.add(label);
		
		txtNomeAlt = new JTextField();
		txtNomeAlt.setColumns(10);
		panel.add(txtNomeAlt);
		txtNomeAlt.setText(nome);
		GroupLayout groupLayout = new GroupLayout(frmAltAluno.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addGap(63))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Aluno alt = new Aluno(ra,txtNomeAlt.getText(),txtEmailAlt.getText());
					Alunos.atualizar(alt);
					frmAltAluno.dispose();
				}catch(Exception err) {
					
				}
			}
		});
		panel_2.add(btnAlterar);
		
		JButton btnNewButton = new JButton("Limpar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNomeAlt.setText("");
				txtEmailAlt.setText("");
			}
		});
		panel_2.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("Email:");
		panel_1.add(lblNewLabel_1);
		
		txtEmailAlt = new JTextField();
		panel_1.add(txtEmailAlt);
		txtEmailAlt.setColumns(10);
		frmAltAluno.getContentPane().setLayout(groupLayout);
		txtEmailAlt.setText(email);
	}
}
