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
import java.awt.Insets;
import java.awt.FlowLayout;
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
	private JTextField txtCodMat_F;
	private JTextField txtFrequencia_F;
	private JTextField txtNota_F;
	private JTextField txtRa_F;
	private JTable table_F;
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
		frmPrincipal.setBounds(100, 100, 1089, 414);
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
		
		JScrollPane scrollAluno = new JScrollPane(table);
		scrollAluno.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		painelTabela.add(scrollAluno);
		
		
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
						JOptionPane.showOptionDialog(null,"� necessario seleconar um Aluno para Excluir","Selecionar Aluno",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR,null, options, options[0]);
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
						JOptionPane.showOptionDialog(null,"� necessario seleconar um Aluno para Excluir","Selecionar Aluno",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR,null, options, options[0]);
					}
					
				}catch(Exception erro) {}
				
			}
		});
		panel.add(btnExcluir_A);
		
		JButton btnZeroFreq = new JButton("Alunos com 0% de Frequencia");
		btnZeroFreq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MeuResultSet resultado;
					String[] colunas = {"Nome","Frequencia"};
					resultado = Alunos.frequenciaZero();
					DefaultTableModel tabela = new DefaultTableModel(colunas,0);
					
					while(resultado.next()) {
						tabela.addRow(new String[] {resultado.getString(1),  ""+resultado.getInt(2)});
					}
					table.setModel(tabela);
					}catch(Exception err) {
						err.printStackTrace();
					}
			}
		});
		panel.add(btnZeroFreq);
		
		JButton btnAlunosCrescente = new JButton("nome dos alunos ordenados crescente pela m\u00E9dia de suas mat\u00E9rias");
		btnAlunosCrescente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MeuResultSet resultado;
					String[] colunas = {"Nome Aluno","Nome Materia","Media"};
					resultado = Alunos.alunosPorMateriaMedia();
					DefaultTableModel tabela = new DefaultTableModel(colunas,0);
					
					while(resultado.next()) {
						tabela.addRow(new String[] {resultado.getString(1),resultado.getString(2),""+resultado.getFloat(3)});
					}
					table.setModel(tabela);
				}catch(Exception err) {
					err.printStackTrace();
				}
			}
		});
		panel.add(btnAlunosCrescente);
		btnIncluir_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ra = 0;
				if(txtRa.getText().length() < 5 || txtRa == null || txtRa.getText().equals("")) {
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,"Ra invalido","RA",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);
				}else {
					ra = Integer.parseInt(txtRa.getText());
				}
				if(txtNome.getText().length() < 1 || txtNome == null || txtNome.getText().equals(""))
				{
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,"Nome invalido","Nome",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);
				}
				if(txtEmail.getText().length() < 1 || txtEmail == null || txtEmail.getText().equals(""))
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
		
		JScrollPane scrollMateria = new JScrollPane(table_M);
		panel_3.add(scrollMateria);
		scrollMateria.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
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
				if(txtCodMateria.getText().length() < 0 || txtCodMateria == null || txtCodMateria.getText().equals("")) {
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,"Codigo Invalido invalido","Codigo Materia",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);
				}else {
					codMateria = Integer.parseInt(txtCodMateria.getText());
				}
				if(txtNomeMateria.getText().length() < 1 || txtNomeMateria == null || txtNomeMateria.getText().equals(""))
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
						JOptionPane.showOptionDialog(null,"� necessario seleconar uma Materia para Excluir","Selecionar Materia",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR,null, options, options[0]);
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
						JOptionPane.showOptionDialog(null,"� necessario seleconar uma Materia para Excluir","Selecionar Materia",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR,null, options, options[0]);
					}
					
				}catch(Exception erro) {
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,erro.getMessage(),"Exception",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);erro.getMessage();
				}
			}
		});
		panel_2.add(btnExcluir_M);
		
		JButton btnMatSemRep = new JButton("nome das materias sem reprovacao materias");
		btnMatSemRep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MeuResultSet resultado;
					String[] colunas = {"NomeMateria"};
					resultado = Materias.materiasSemReprovacao();
					DefaultTableModel tabela = new DefaultTableModel(colunas,0);
					
					while(resultado.next()) {
						tabela.addRow(new String[] {resultado.getString(1)});
					}
					table_M.setModel(tabela);
					}catch(Exception err) {
						err.printStackTrace();
					}
			}
		});
		panel_2.add(btnMatSemRep);
		
		JButton btnCrescenteMed = new JButton("nome das materias ordenado de forma crescente pela media dos alunos");
		btnCrescenteMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MeuResultSet resultado;
					String[] colunas = {"NomeMateria","Media Alunos"};
					resultado = Materias.mediaCrescenteAlunos();
					DefaultTableModel tabela = new DefaultTableModel(colunas,0);
					
					while(resultado.next()) {
						tabela.addRow(new String[] {""+ resultado.getString(1),  ""+resultado.getFloat(2)});
					}
					table_M.setModel(tabela);
					}catch(Exception err) {
						err.printStackTrace();
					}
			}
		});
		panel_2.add(btnCrescenteMed);
		
		JPanel panelFez = new JPanel();
		tabbedPane.addTab("Fez", null, panelFez, null);
		panelFez.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panelFez.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		table_F = new JTable();
		panel_1.add(table_F);
		
		JScrollPane scrollFez = new JScrollPane(table_F);
		panel_1.add(scrollFez);
		scrollFez.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel panel_4 = new JPanel();
		panelFez.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel("Codigo Materia");
		panel_4.add(label);
		
		txtCodMat_F = new JTextField();
		txtCodMat_F.setColumns(10);
		panel_4.add(txtCodMat_F);
		
		JLabel label_5 = new JLabel("RA:");
		panel_4.add(label_5);
		
		txtRa_F = new JTextField();
		txtRa_F.setColumns(10);
		panel_4.add(txtRa_F);
		
		JLabel label_1 = new JLabel("Frequencia");
		panel_4.add(label_1);
		
		JLabel label_2 = new JLabel("");
		panel_4.add(label_2);
		
		txtFrequencia_F = new JTextField();
		txtFrequencia_F.setColumns(10);
		panel_4.add(txtFrequencia_F);
		
		JLabel label_3 = new JLabel("Nota:");
		panel_4.add(label_3);
		
		txtNota_F = new JTextField();
		txtNota_F.setColumns(10);
		panel_4.add(txtNota_F);
		
		JButton btnLeitura_F = new JButton("Leitura");
		btnLeitura_F.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MeuResultSet resultado;
					String[] colunas = {"RA","Cod Materia","NOTA","FREQUENCIA"};
					resultado = Fezs.mostraFez();
					DefaultTableModel tabela = new DefaultTableModel(colunas,0);
					
					while(resultado.next()) {
						tabela.addRow(new String[] {""+ resultado.getInt(1),  ""+resultado.getInt(2), ""+resultado.getFloat(3), ""+resultado.getInt(4)});
					}
					table_F.setModel(tabela);
					}catch(Exception err) {
						err.printStackTrace();
					}
			}
		});

		panel_4.add(btnLeitura_F);
		
		JButton btnIncluir_F = new JButton("Incluir");
		btnIncluir_F.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ra = 0;
				int codM = 0;
				float nota = 0.0f;
				int freq = 0;
				if(txtRa_F.getText().length() < 5 || txtRa_F == null || txtRa_F.getText().equals("")) {
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,"Ra invalido","RA",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);
				}else {
					ra = Integer.parseInt(txtRa_F.getText());
				}
				if(txtCodMat_F.getText().length() < 1 || txtCodMat_F == null|| txtCodMat_F.getText().equals(""))
				{
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,"Codigo invalido","Cod Mat",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);
				}else {
					codM = Integer.parseInt(txtCodMat_F.getText());
				}
				if(txtNota_F.getText().length() < 1 || txtNota_F == null || txtNota_F.getText().equals(""))
				{
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,"Nota invalida","Nota",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);
				}else {
					nota = Float.parseFloat(txtNota_F.getText());
				}
				if(txtFrequencia_F.getText().length() < 1 || txtFrequencia_F == null || txtFrequencia_F.getText().equals(""))
				{
					Object[] options = {"Confirmar"};
					JOptionPane.showOptionDialog(null,"Frequencia invalida","Frequencia",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null, options, options[0]);
				}
				else {
					freq = Integer.parseInt(txtFrequencia_F.getText());
					try {
					modeloFez = new Fez(ra,codM,nota,freq);
					Fezs.incluir(modeloFez);
					btnLeitura_F.doClick();
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
		panel_4.add(btnIncluir_F);
		
		JButton btnAlterar_F = new JButton("Alterar");
		btnAlterar_F.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelc = table_F.getSelectedRow();
					
					if(linhaSelc != -1) {
						int ra = Integer.parseInt((String)table_F.getModel().getValueAt(linhaSelc, 0));
						int codMat = Integer.parseInt((String)table_F.getModel().getValueAt(linhaSelc, 1));
						float nota = Float.parseFloat((String)table_F.getModel().getValueAt(linhaSelc, 2));
						int freq = Integer.parseInt((String)table_F.getModel().getValueAt(linhaSelc, 3));
						
						EditarFez edit = new EditarFez(ra,codMat,nota,freq);
						edit.frmAltFez.setVisible(true);
						btnLeitura_F.doClick();
					}else {
						Object[] options = {"Confirmar"};
						JOptionPane.showOptionDialog(null,"� necessario seleconar uma Materia para Excluir","Selecionar Materia",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR,null, options, options[0]);
					}
					
				}catch(Exception err) {}
			}
		});
		panel_4.add(btnAlterar_F);
		
		JButton btnExcluir_F = new JButton("Excluir");
		btnExcluir_F.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelc = table_F.getSelectedRow();
					if(linhaSelc != -1) {
						int resp = JOptionPane.showConfirmDialog(null,"Deseja Realmente excluir ?","Exluir?",JOptionPane.YES_NO_OPTION);
						if(resp == JOptionPane.YES_NO_OPTION) {
							int ra = Integer.parseInt((String)table_F.getModel().getValueAt(linhaSelc,0));
							int codMat = Integer.parseInt((String)table_F.getModel().getValueAt(linhaSelc,1));
							Fezs.excluir(ra,codMat);
							btnLeitura_F.doClick();
						}
					}else {
						Object[] options = {"Confirmar"};
						JOptionPane.showOptionDialog(null,"� necessario seleconar um Registro para Excluir","Selecione registro",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR,null, options, options[0]);
					}
					
				}catch(Exception erro) {}
				
			}
		});
		panel_4.add(btnExcluir_F);
	}
}
