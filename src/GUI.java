import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	final private JLabel statusLabel = new JLabel("Idle");
	final private ErrorLabel errorLabel = new ErrorLabel("");
	final private JButton downloadButton = new JButton("Download!");

	// errors
	final Error noDirError = new Error("Not a valid Directory!", Color.RED, 8);
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI("C:");
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
	public GUI(final String currentPath) {

		// gui init
		setTitle("Simple Downloader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(111, 26, 252, 31);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("URL");
		lblNewLabel.setBounds(10, 34, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Save Directory");
		lblNewLabel_1.setBounds(10, 76, 81, 14);
		contentPane.add(lblNewLabel_1);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(111, 152, 252, 25);
		contentPane.add(progressBar);

		textField_1 = new JTextField();
		textField_1.setBounds(111, 68, 252, 31);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(currentPath);

		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setBounds(10, 188, 353, 19);
		contentPane.add(statusLabel);

		JLabel lblPro = new JLabel("Progress");
		lblPro.setBounds(10, 163, 61, 14);
		contentPane.add(lblPro);

		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setBounds(10, 218, 353, 19);
		contentPane.add(errorLabel);

		downloadButton.setBounds(44, 248, 276, 31);
		contentPane.add(downloadButton);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(111, 110, 252, 31);
		contentPane.add(textField_2);
		textField_2.setText("example.txt");
		
		JLabel lblNewLabel_2 = new JLabel("Save FileName");
		lblNewLabel_2.setBounds(10, 118, 102, 14);
		contentPane.add(lblNewLabel_2);

		// listeners

		textField_1.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				checkForErrors();
			}
		});

		textField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				checkForErrors();
			}
		});

	}

	public void checkForErrors() {
		File path = new File(textField_1.getText());

		if (!path.exists()) {
			errorLabel.addError(noDirError);
			textField_1.setBackground(Color.RED);
		} else {
			errorLabel.removeError(noDirError);
			textField_1.setBackground(Color.WHITE);
		}

		downloadButton.setEnabled(errorLabel.getErrorList().isEmpty());
	}

	public void download() throws MalformedURLException, IOException {

		String fileUrl = textField.getText();
		String fileName = textField_1.getText();
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		try {
			in = new BufferedInputStream(new URL(fileUrl).openStream());
			fout = new FileOutputStream(fileName);

			byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
			}
		} finally {
			if (in != null)
				in.close();
			if (fout != null)
				fout.close();
		}
	}
}
