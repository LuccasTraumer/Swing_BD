package views;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.Icon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dbos.*;
import daos.*;
import bd.*;
import bd.core.*;
import views.EditarAluno;
import javax.swing.BoxLayout;
public class JanelaPrincipal {

	private JFrame frmPrincipal;
	private JTextField txtEmail;
	private JTextField txtNome;
	private JTextField txtRa;
	private JTextField txtCodMateria;
	private JTextField txtNomeMateria;
	
	private Aluno modeloAluno;
	private Materia modeloMateria;
	private Fez modeloFez;
	private JTable table;
	private JTable table_M;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal window = new JanelaPrincipal();
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
	public JanelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setTitle("Principal");
		frmPrincipal.setBounds(100, 100, 746, 397);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(new BoxLayout(frmPrincipal.getContentPane(), BoxLayout.X_AXIS));
		
	
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmPrincipal.getContentPane().add(tabbedPane);
		
		JPanel panelAlunos = new JPanel();
		tabbedPane.addTab("Alunos", null, panelAlunos, null);
		panelAlunos.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel painelTabela = new JPanel();
		panelAlunos.add(painelTabela);
		painelTabela.setLayout(new BoxLayout(painelTabela, BoxLayout.X_AXIS));
		
		table = new JTable();
		painelTabela.add(table);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		painelTabela.add(scroll);
		JPanel panel = new JPanel();
		panelAlunos.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblRa = new JLabel("  RA:");
		panel.add(lblRa);
		
		txtRa = new JTextField();
		panel.add(txtRa);
		txtRa.setColumns(10);
		
		JLabel lblNome = new JLabel("  Nome:");
		panel.add(lblNome);
		
		txtNome = new JTextField();
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		
		JLabel lblEmail = new JLabel("  Email:");
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnLeitura_A = new JButton("Leitura");
		btnLeitura_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				MeuResultSet resultado;
				String[] colunas = {"RA","Nome Aluno","Email"};
				resultado = Alunos.mostrarAlunos();
				DefaultTableModel tabela = new DefaultTableModel(colunas,0);
				
				while(resultado.next()) {
					tabela.addRow(new String[] {""+ resultado.getInt(1),  resultado.getString(2), resultado.getString(3)});
				}
				table.setModel(tabela);
				}catch(Exception err) {
					err.printStackTrace();
				}
			}
		});
		panel.add(btnLeitura_A);
		
		JButton btnIncluir_A = new JButton("Incluir");
		panel.add(btnIncluir_A);
		
		JButton btnAlterar_A = new JButton("Alterar");
		btnAlterar_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelc = table.getSelectedRow();
					
					if(linhaSelc != -1) {
						int ra = Integer.parseInt((String)table.getModel().getValueAt(linhaSelc, 0));
						String nome = (String)table.getModel().getValueAt(linhaSelc, 1);
						String email = (String)table.getModel().getValueAt(linhaSelc, 2);
						EditarAluno edit = new EditarAluno(ra,nome,email);
						edit.frmAltAluno.setVisible(true);
						btnLeitura_A.doClick();
					}else {
						Object[] options = {"Confirmar"};
						JOptionPane.showOptionDialog(null,"É necessario seleconar um Aluno para Excluir","Selecionar Aluno",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR,null, options, options[0]);
					}
					
					
				}catch(Exception err) {}
			}
		});
		panel.add(btnAlterar_A);
		
		JButton btnExcluir_A = new JButton("Excluir");
		btnExcluir_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelc = table.getSelectedRow();
					if(linhaSelc != -1) {
						int resp = JOptionPane.showConfirmDialog(null,"Deseja Realmente excluir ?","Exluir?",JOptionPane.YES_NO_OPTION);
						if(resp == JOptionPane.YES_NO_OPTION) {
							int ra = Integer.parseInt((String)table.getModel().getValueAt(linhaSelc,0));
							Alunos.excluir(ra);
							btnLeitura_A.doClick();
						}
					}else {
						Object[] options = {"Confirmar"};
						JOptionPane.showOptionDialog(null,"É necessario seleconar um Aluno para Excluir","Selecionar Aluno",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR,null, options, options[0]);
					}
					
				}catch(Exception erro) {}
				
			}
		});
		panel.add(btnExcluir_A);
		btnIncluir_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ra = 0;
				if(txtRa.getText().length() < 5) {
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,"Ra invalido","RA",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);
				}else {
					ra = Integer.parseInt(txtRa.getText());
				}
				if(txtNome.getText().length() < 1)
				{
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,"Nome invalido","Nome",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);
				}
				if(txtEmail.getText().length() < 1)
				{
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,"Email invalido","Email",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);
				}else {
					try {
					modeloAluno = new Aluno(ra,txtNome.getText(),txtEmail.getText());
					Alunos.incluir(modeloAluno);
					btnLeitura_A.doClick();
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,"Aluno Incluido","Sucesso",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null, options, options[0]);
					}
					catch(Exception err) {
						Object[] options = {"Confirmar"};
						JOptionPane.showOptionDialog(null,err.getMessage(),"Exception",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);err.getMessage();
					}
				}
				
				}
		});
		
		JPanel panelMaterias = new JPanel();
		tabbedPane.addTab("Materias", null, panelMaterias, null);
		panelMaterias.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel Exibir = new JPanel();
		panelMaterias.add(Exibir);
		Exibir.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		Exibir.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		table_M = new JTable();
		panel_3.add(table_M);
		
		JPanel panel_2 = new JPanel();
		Exibir.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblCodigoMateria = new JLabel("  Codigo Materia");
		panel_2.add(lblCodigoMateria);
		
		txtCodMateria = new JTextField();
		panel_2.add(txtCodMateria);
		txtCodMateria.setColumns(10);
		
		JLabel lblNome_1 = new JLabel("  Nome:");
		panel_2.add(lblNome_1);
		
		txtNomeMateria = new JTextField();
		panel_2.add(txtNomeMateria);
		txtNomeMateria.setColumns(10);
		
		JButton btnLeitura_M = new JButton("Leitura");
		btnLeitura_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MeuResultSet resultado;
					String[] colunas = {"Codigo Materia","Nome Materia"};
					resultado = Materias.mostrarMaterias();
					DefaultTableModel tabela = new DefaultTableModel(colunas,0);
					
					while(resultado.next()) {
						tabela.addRow(new String[] {""+ resultado.getInt(1),  resultado.getString(2)});
					}
					table_M.setModel(tabela);
					}catch(Exception err) {
						err.printStackTrace();
					}
			}
		});
		
		panel_2.add(btnLeitura_M);
		
		JButton btnIncluir_M = new JButton("Incluir");
		btnIncluir_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codMateria = 0;
				if(txtCodMateria.getText().length() < 0) {
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,"Codigo Invalido invalido","Codigo Materia",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);
				}else {
					codMateria = Integer.parseInt(txtCodMateria.getText());
				}
				if(txtNomeMateria.getText().length() < 1)
				{
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,"Nome invalido","Nome",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);
				}
				else {
					try {
					modeloMateria = new Materia(codMateria,txtNomeMateria.getText());
					Materias.incluir(modeloMateria);
					btnLeitura_M.doClick();
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,"Materia Incluida","Sucesso",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null, options, options[0]);
					}
					catch(Exception err) {
						Object[] options = {"Confirmar"};
						JOptionPane.showOptionDialog(null,err.getMessage(),"Exception",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);err.getMessage();
					}
				}
			}
		});
		panel_2.add(btnIncluir_M);
		
		JButton btnAlterar_M = new JButton("Alterar");
		btnAlterar_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelc = table_M.getSelectedRow();
					
					if(linhaSelc != -1) {
						int cod = Integer.parseInt((String)table_M.getModel().getValueAt(linhaSelc, 0));
						String nome = (String)table_M.getModel().getValueAt(linhaSelc, 1);
						
						EditarMaterias edit = new EditarMaterias(cod,nome);
						edit.frmAltMat.setVisible(true);
						btnLeitura_M.doClick();
					}else {
						Object[] options = {"Confirmar"};
						JOptionPane.showOptionDialog(null,"É necessario seleconar uma Materia para Excluir","Selecionar Materia",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR,null, options, options[0]);
					}
					
				}catch(Exception err) {}
			}
		});
		panel_2.add(btnAlterar_M);
		
		JButton btnExcluir_M = new JButton("Excluir");
		btnExcluir_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelc = table_M.getSelectedRow();
					if(linhaSelc != -1) {
						int resp = JOptionPane.showConfirmDialog(null,"Deseja Realmente excluir ?","Exluir?",JOptionPane.YES_NO_OPTION);
						if(resp == JOptionPane.YES_NO_OPTION) {
							int cod = Integer.parseInt((String)table_M.getModel().getValueAt(linhaSelc,0));
							Materias.excluir(cod);
							btnLeitura_M.doClick();
						}
					}else {
						Object[] options = {"Confirmar"};
						JOptionPane.showOptionDialog(null,"É necessario seleconar uma Materia para Excluir","Selecionar Materia",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR,null, options, options[0]);
					}
					
				}catch(Exception erro) {
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,erro.getMessage(),"Exception",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);erro.getMessage();
				}
			}
		});
		panel_2.add(btnExcluir_M);
		
		JPanel panelFez = new JPanel();
		tabbedPane.addTab("Fez", null, panelFez, null);
		panelFez.setLayout(new GridLayout(1, 0, 0, 0));
	}
}
