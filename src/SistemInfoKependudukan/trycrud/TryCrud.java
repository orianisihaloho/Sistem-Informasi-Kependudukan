
package trycrud;

/**
 *
 * @author OrianiSihaloho
 */
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javax.swing.SwingConstants;
import java.awt.Color;

@SuppressWarnings("serial")
public class TryCrud extends JFrame {

    /**
     * @param args the command line arguments
     */
    private JPanel contentPane;
    private JTextField txtNIM;
    private JTextField txtNama;
    private JTable tabel;

    String header[] = {"NIM", "Nama", "Jurusan", "Alamat"};
    DefaultTableModel tabelModel;

    public static void main(String[] args) {
        // TODO code application logic here

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                    TryCrud frame = new TryCrud();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    public TryCrud() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 430);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNim = new JLabel("NIM");
		lblNim.setForeground(Color.WHITE);
		lblNim.setHorizontalAlignment(SwingConstants.CENTER);
		lblNim.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNim.setBounds(10, 7, 116, 42);
		contentPane.add(lblNim);
		
		JLabel lblNama = new JLabel("NAMA");
		lblNama.setForeground(Color.WHITE);
		lblNama.setHorizontalAlignment(SwingConstants.CENTER);
		lblNama.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNama.setBounds(10, 60, 116, 42);
		contentPane.add(lblNama);
		
		JLabel lblJurusan = new JLabel("JURUSAN");
		lblJurusan.setForeground(Color.WHITE);
		lblJurusan.setHorizontalAlignment(SwingConstants.CENTER);
		lblJurusan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblJurusan.setBounds(10, 113, 116, 50);
		contentPane.add(lblJurusan);
		
		JLabel lblAlamat = new JLabel("ALAMAT");
		lblAlamat.setForeground(Color.WHITE);
		lblAlamat.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlamat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlamat.setBounds(10, 169, 116, 42);
//                lblAlamat.setBounds()
		contentPane.add(lblAlamat);
		
		txtNIM = new JTextField();
		txtNIM.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNIM.setBounds(136, 7, 358, 42);
		contentPane.add(txtNIM);
		txtNIM.setColumns(10);
		
		txtNama = new JTextField();
		txtNama.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNama.setBounds(136, 64, 358, 43);
		contentPane.add(txtNama);
		txtNama.setColumns(10);
		
		JComboBox cbJurusan = new JComboBox();
		cbJurusan.setEditable(true);
		cbJurusan.setModel(new DefaultComboBoxModel(new String[] {"TI", "SI", "MR"}));
		cbJurusan.setBounds(136, 122, 358, 36);
		contentPane.add(cbJurusan);
		
		JTextArea textAlamat = new JTextArea();
		textAlamat.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textAlamat.setBounds(136, 169, 358, 38);
		contentPane.add(textAlamat);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(136, 218, 358, 128);
		contentPane.add(scrollPane);
		
		tabelModel = new DefaultTableModel(null,header); 
		tabel = new JTable(); 
		tabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getData(cbJurusan, textAlamat);
			}
		});
		tabel.setModel(tabelModel);
		scrollPane.setViewportView(tabel);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBackground(Color.GRAY);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Connection konek = (Connection) Koneksi.getKoneksi();
					String query = "DELETE FROM mahasiswa WHERE NIM = ?";
					PreparedStatement prepare = (PreparedStatement) konek.prepareStatement(query);
					
					prepare.setInt(1,Integer.parseInt(txtNIM.getText()));
					
					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
					prepare.close();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Data gagal dihapus");
					System.out.println(ex);
				}finally{
					getDataTable();
				}
			}
		});
		btnDelete.setBounds(405, 357, 89, 23);
		contentPane.add(btnDelete);
		getDataTable();
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBackground(Color.GRAY);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jurusan = "";
				if(cbJurusan.getSelectedIndex() == 0){
					jurusan = "TI";
				}else if(cbJurusan.getSelectedIndex() == 1){
					jurusan = "SI";
				}else if(cbJurusan.getSelectedIndex() == 2){
					jurusan = "Ekonomi";
				}try{
					Connection konek = (Connection) Koneksi.getKoneksi();
					String query = "UPDATE mahasiswa SET Nama = ?, Jurusan = ?, Alamat = ? WHERE NIM = ?";
					PreparedStatement prepare = (PreparedStatement) konek.prepareStatement(query);
					prepare.setString(1, txtNama.getText());
					prepare.setString(2, jurusan);
					prepare.setString(3, textAlamat.getText());
					prepare.setInt(4,Integer.parseInt(txtNIM.getText()));
					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
					prepare.close();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Data gagal diupdate");
					System.out.println(ex);
				}finally{
					getDataTable();
				}
			}
		});
		btnUpdate.setBounds(306, 357, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setBackground(Color.GRAY);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jurusan = "";
				if(cbJurusan.getSelectedIndex() == 0){
					jurusan = "TI";
				}else if(cbJurusan.getSelectedIndex() == 1){
					jurusan = "SI";
				}else if(cbJurusan.getSelectedIndex() == 2){
					jurusan = "Ekonomi";
				}try{
					Connection konek = (Connection) Koneksi.getKoneksi();
					String query = "INSERT INTO mahasiswa VALUES(?,?,?,?)";
					PreparedStatement prepare = (PreparedStatement) konek.prepareStatement(query);
					
					prepare.setInt(1,Integer.parseInt(txtNIM.getText()));
					prepare.setString(2, txtNama.getText());
					prepare.setString(3, jurusan);
					prepare.setString(4, textAlamat.getText());
					
					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data berhasil ditambahkan ke database");
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Data gagal ditambahkan ke database");
					System.out.println(ex);
				}finally{
					getDataTable();
				}
			}
		});
		btnSave.setBounds(207, 357, 89, 23);
		contentPane.add(btnSave);
		getDataTable();
	}
	
	public void getDataTable(){
		tabelModel.getDataVector().removeAllElements(); 
		tabelModel.fireTableDataChanged();
		try{
			Connection konek = (Connection) Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT * FROM mahasiswa";
			ResultSet rs = state.executeQuery(query);
			while(rs.next()){
				Object obj[] = new Object[4];
				obj[0] = rs.getInt(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getString(3);
				obj[3] = rs.getString(4);
				tabelModel.addRow(obj);
			}
			rs.close();
			state.close();
		}catch(Exception ex){
			
		}
	}
	
	public void getData(JComboBox cbJurusan, JTextArea textAlamat){
		int pilih = tabel.getSelectedRow();
		if(pilih == -1){
			return; 
		}
		int nim = (int) tabelModel.getValueAt(pilih, 0);
		txtNIM.setText("" + nim);
		String nama = (String) tabelModel.getValueAt(pilih, 1);
		txtNama.setText(nama);
		String jurusan = (String) tabelModel.getValueAt(pilih, 2);
		cbJurusan.setSelectedItem(jurusan);
		String alamat = (String) tabelModel.getValueAt(pilih, 3);
		textAlamat.setText(alamat);
	}

}