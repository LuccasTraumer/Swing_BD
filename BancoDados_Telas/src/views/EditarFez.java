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


import daos.Fezs;
import dbos.Fez;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarFez {
	
	protected static final String frame = null;
	protected JFrame frmAltFez;
	private JTextField txtFreq_Ed;
	private JTextField txtNota_Ed;
	
	private static int ra;
	private static int codMat;
	private static int frequencia;
	private static float nota;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarFez window = new EditarFez(ra,codMat,nota,frequencia);
					window.frmAltFez.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditarFez(int ra,int codM,float nota,int freq) {
		this.ra = ra;
		this.codMat = codM;
		this.nota = nota;
		this.frequencia = freq;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAltFez = new JFrame();
		frmAltFez.setTitle("Editar Fez");
		frmAltFez.setBounds(100, 100, 465, 112);
		frmAltFez.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAltFez.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		frmAltFez.getContentPane().add(panel);
		
		
		JLabel lblFrequencia = new JLabel("Frequencia");
		panel.add(lblFrequencia);
		
		txtFreq_Ed = new JTextField();
		panel.add(txtFreq_Ed);
		txtFreq_Ed.setColumns(10);
		txtFreq_Ed.setText(frequencia+"");
		JLabel lblNota = new JLabel("Nota:");
		panel.add(lblNota);
		
		txtNota_Ed = new JTextField();
		panel.add(txtNota_Ed);
		txtNota_Ed.setColumns(10);
		txtNota_Ed.setText(nota+"");
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFreq_Ed.setText("");
				txtNota_Ed.setText("");
			}
		});
		panel.add(btnLimpar);
		
		JButton btnAlterar_Ed = new JButton("Alterar");
		btnAlterar_Ed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float no = Float.parseFloat(txtNota_Ed.getText());
					int frq = Integer.parseInt(txtFreq_Ed.getText());
				Fez alt = new Fez(ra,codMat,no,frq);
				Fezs.atualizar(alt);
				frmAltFez.dispose();
				}catch(Exception err) {}
			}
		});
		panel.add(btnAlterar_Ed);
	}

}
