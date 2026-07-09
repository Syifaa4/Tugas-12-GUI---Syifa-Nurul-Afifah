import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AplikasiBiodata extends JFrame {

    // Deklarasi komponen input dan output
    private JTextField txtNim, txtNama, txtProdi;
    private JTextArea txtOutput;
    private JButton btnTampilkan, btnReset;

    public AplikasiBiodata() {
        // 1. Pengaturan Jendela Utama (JFrame)
        setTitle("Aplikasi Biodata Mahasiswa");
        setSize(500, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Agar aplikasi muncul di tengah layar

        // Panel Utama dengan padding di sekelilingnya
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ==========================================
        // 2. PANEL INPUT DATA
        // ==========================================
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Data"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6); // Jarak antar komponen input
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Komponen NIM
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        JLabel lblNim = new JLabel("NIM");
        lblNim.setPreferredSize(new Dimension(100, 20)); // Set lebar label agar seragam
        inputPanel.add(lblNim, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1;
        txtNim = new JTextField();
        inputPanel.add(txtNim, gbc);

        // Komponen Nama
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        inputPanel.add(new JLabel("Nama"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1;
        txtNama = new JTextField();
        inputPanel.add(txtNama, gbc);

        // Komponen Program Studi
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        inputPanel.add(new JLabel("Program Studi"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1;
        txtProdi = new JTextField();
        inputPanel.add(txtProdi, gbc);

        mainPanel.add(inputPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Jeda jarak komponen

        // ==========================================
        // 3. PANEL TOMBOL (BUTTONS)
        // ==========================================
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnTampilkan = new JButton("Tampilkan");
        btnReset = new JButton("Reset");
        
        // Atur ukuran tombol sedikit lebih proporsional
        btnTampilkan.setPreferredSize(new Dimension(100, 30));
        btnReset.setPreferredSize(new Dimension(100, 30));
        
        buttonPanel.add(btnTampilkan);
        buttonPanel.add(btnReset);

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // ==========================================
        // 4. PANEL OUTPUT
        // ==========================================
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));

        txtOutput = new JTextArea(12, 30);
        txtOutput.setEditable(false); // Mengunci output area agar tidak bisa diketik manual
        
        // WAJIB menggunakan Monospaced agar jarak spasi antar huruf sama/sejajar sempurna
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(outputPanel);

        // Memasukkan seluruh layout ke dalam jendela utama
        add(mainPanel);

        // ==========================================
        // 5. LOGIKA & AKSI TOMBOL
        // ==========================================
        
        // Aksi Tombol Tampilkan
        btnTampilkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = txtNim.getText();
                String nama = txtNama.getText();
                String prodi = txtProdi.getText();

                // Menggunakan format string %-16s untuk membuat perataan titik dua otomatis sejajar
                String hasil = "========== BIODATA MAHASISWA ==========\n\n" +
                               String.format("%-16s: %s\n", "NIM", nim) +
                               String.format("%-16s: %s\n", "Nama", nama) +
                               String.format("%-16s: %s\n", "Program Studi", prodi);

                txtOutput.setText(hasil);
            }
        });

        // Aksi Tombol Reset
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNim.setText("");
                txtNama.setText("");
                txtProdi.setText("");
                txtOutput.setText("");
                txtNim.requestFocus(); // Mengembalikan kursor fokus ke field NIM
            }
        });
    }

    public static void main(String[] args) {
        // Mengubah tema GUI Java bawaan menjadi tema bawaan Windows/Sistem Operasi
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Menjalankan aplikasi secara thread-safe
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AplikasiBiodata().setVisible(true);
            }
        });
    }
}