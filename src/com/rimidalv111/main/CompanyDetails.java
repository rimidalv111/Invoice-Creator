package com.rimidalv111.main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.rimidalv111.util.customclasses.CustomJPanel;

public class CompanyDetails extends JFrame {
	private static final long serialVersionUID = 1L;

	private Main main;

	private JLabel companyName, companyAddress, companyCity, companyState,
			companyZip, companyPhone, companyEmail;
	private JTextField nameField, addressField, cityField, zipField,
			phoneField, emailField;
	private JComboBox customerInfoStateComboBox;
	private JFileChooser fc = new JFileChooser();

	public CompanyDetails(Main m) {
		main = m;
		setTitle("Company Details");
		setLayout(null);
		setSize(600, 332);
		setResizable(false);
		setLocationRelativeTo(m.getFrame());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		CustomJPanel mainPanel = new CustomJPanel();
		mainPanel.setBounds(0, 0, getWidth(), getHeight());
		mainPanel.setBorder(new MatteBorder(0, 1, 1, 1,
				new Color(180, 180, 180)));
		mainPanel.setLayout(null);

		companyName = new JLabel("Name: ");
		companyName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		companyName.setBounds(10, 20, 60, 18);
		nameField = new JTextField("");
		nameField.setBounds(70, 20, 175, 20);

		companyAddress = new JLabel("Address: ");
		companyAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		companyAddress.setBounds(10, 50, 60, 18);
		addressField = new JTextField("");
		addressField.setBounds(70, 50, 175, 20);

		companyCity = new JLabel("City: ");
		companyCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		companyCity.setBounds(10, 80, 60, 18);
		cityField = new JTextField("");
		cityField.setBounds(70, 80, 175, 20);

		companyState = new JLabel("State: ");
		companyState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		companyState.setBounds(10, 110, 60, 18);
		customerInfoStateComboBox = new JComboBox();
		customerInfoStateComboBox.setBounds(70, 110, 175, 20);
		customerInfoStateComboBox.addItem("Select State");
		customerInfoStateComboBox.addItem("Alabama");
		customerInfoStateComboBox.addItem("Alaska");
		customerInfoStateComboBox.addItem("Arizona");
		customerInfoStateComboBox.addItem("Arkansas");
		customerInfoStateComboBox.addItem("California");
		customerInfoStateComboBox.addItem("Colorado");
		customerInfoStateComboBox.addItem("Connecticut");
		customerInfoStateComboBox.addItem("Delaware");
		customerInfoStateComboBox.addItem("Florida");
		customerInfoStateComboBox.addItem("Georgia");
		customerInfoStateComboBox.addItem("Hawaii");
		customerInfoStateComboBox.addItem("Idaho");
		customerInfoStateComboBox.addItem("Illinois");
		customerInfoStateComboBox.addItem("Indiana");
		customerInfoStateComboBox.addItem("Iowa");
		customerInfoStateComboBox.addItem("Kansas");
		customerInfoStateComboBox.addItem("Kentucky");
		customerInfoStateComboBox.addItem("Louisiana");
		customerInfoStateComboBox.addItem("Maine");
		customerInfoStateComboBox.addItem("Maryland");
		customerInfoStateComboBox.addItem("Massachusetts");
		customerInfoStateComboBox.addItem("Michigan");
		customerInfoStateComboBox.addItem("Minnesota");
		customerInfoStateComboBox.addItem("Mississippi");
		customerInfoStateComboBox.addItem("Missouri");
		customerInfoStateComboBox.addItem("Montana");
		customerInfoStateComboBox.addItem("Nebraska");
		customerInfoStateComboBox.addItem("Nevada");
		customerInfoStateComboBox.addItem("New Hampshire");
		customerInfoStateComboBox.addItem("New Jersey");
		customerInfoStateComboBox.addItem("New Mexico");
		customerInfoStateComboBox.addItem("New York");
		customerInfoStateComboBox.addItem("North Carolina");
		customerInfoStateComboBox.addItem("North Dakota");
		customerInfoStateComboBox.addItem("Ohio");
		customerInfoStateComboBox.addItem("Oklahoma");
		customerInfoStateComboBox.addItem("Oregon");
		customerInfoStateComboBox.addItem("Pennsylvania");
		customerInfoStateComboBox.addItem("Rhode Island");
		customerInfoStateComboBox.addItem("South Carolina");
		customerInfoStateComboBox.addItem("South Dakota");
		customerInfoStateComboBox.addItem("Tennessee");
		customerInfoStateComboBox.addItem("Texas");
		customerInfoStateComboBox.addItem("Utah");
		customerInfoStateComboBox.addItem("Vermont");
		customerInfoStateComboBox.addItem("Virginia");
		customerInfoStateComboBox.addItem("Washington");
		customerInfoStateComboBox.addItem("West Virginia");
		customerInfoStateComboBox.addItem("Wisconsin");
		customerInfoStateComboBox.addItem("Wyoming");

		companyZip = new JLabel("Zipcode: ");
		companyZip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		companyZip.setBounds(10, 140, 60, 18);
		zipField = new JTextField("");
		zipField.setBounds(70, 140, 175, 20);

		companyPhone = new JLabel("Phone: ");
		companyPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		companyPhone.setBounds(10, 170, 60, 18);
		phoneField = new JTextField("");
		phoneField.setBounds(70, 170, 175, 20);

		companyEmail = new JLabel("Email: ");
		companyEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		companyEmail.setBounds(10, 200, 60, 18);
		emailField = new JTextField("");
		emailField.setBounds(70, 200, 175, 20);

		JButton save = new JButton("Apply");
		save.setBounds(263, 270, 55, 25);

		JButton infoClear = new JButton("Clear Info");
		infoClear.setBounds(120, 238, 75, 25);
		infoClear.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				nameField.setText("");
				addressField.setText("");
				cityField.setText("");
				customerInfoStateComboBox.setSelectedIndex(0);
				zipField.setText("");
				phoneField.setText("");
				emailField.setText("");
			}
		});

		// Load Image Panel
		// Start--------------------------------------------------------------------------------------------//

		CustomJPanel imageLoadPanel = new CustomJPanel();
		imageLoadPanel.setBounds(260, 10, 320, 142);
		imageLoadPanel.setLayout(null);
		TitledBorder logoBorder = new TitledBorder(new MatteBorder(1, 1, 1, 1,
				Color.GRAY), "Company Logo");
		imageLoadPanel.setBorder(logoBorder);

		final JLabel logoImage = new JLabel("");
		logoImage.setBounds(192, 13, 122, 122);
		logoImage.setLayout(null);
		imageLoadPanel.add(logoImage);
		logoImage.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));

		final JButton load = new JButton("Load Logo");
		load.setBounds(50, 45, 95, 25);
		imageLoadPanel.add(load);
		load.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPEG file", "jpg", "jpeg");
				fc.setFileFilter(filter);
				int response = fc.showOpenDialog(null);
				try {
					if (response == JFileChooser.APPROVE_OPTION) {
						String pathName = fc.getSelectedFile().getPath();
						ImageIcon icon = new ImageIcon(pathName);
						Image image = icon.getImage(); // transform it
						Image newimg = image.getScaledInstance(120, 120,
								java.awt.Image.SCALE_SMOOTH); // scale it the
																// smooth way
						icon = new ImageIcon(newimg);
						logoImage.setIcon(icon);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JButton imageRemove = new JButton("Remove Logo");
		imageRemove.setBounds(50, 85, 95, 25);
		imageLoadPanel.add(imageRemove);
		imageRemove.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				logoImage.setIcon(null);
			}
		});

		// Load Image Panel
		// Finish--------------------------------------------------------------------------------------------//

		// Load Signature Panel
		// Start-----------------------------------------------------------------------------------------//

		CustomJPanel signatureLoadPanel = new CustomJPanel();
		signatureLoadPanel.setBounds(260, 150, 320, 114);
		signatureLoadPanel.setLayout(null);
		TitledBorder signatureBorder = new TitledBorder(new MatteBorder(1, 1,
				1, 1, Color.GRAY), "Signature");
		signatureLoadPanel.setBorder(signatureBorder);

		final JLabel signatureBox = new JLabel("");
		signatureBox.setBounds(19, 53, 292, 45);
		signatureLoadPanel.setLayout(null);
		signatureLoadPanel.add(signatureBox);

		final JButton signatureLoad = new JButton("Load Signature");
		signatureLoad.setBounds(50, 20, 95, 25);
		signatureLoadPanel.add(signatureLoad);
		signatureLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPEG file", "jpg", "jpeg", "gif", "Gif", "GIF", "png",
						"Png", "PNG");
				fc.setFileFilter(filter);
				int response = fc.showOpenDialog(null);
				try {
					if (response == JFileChooser.APPROVE_OPTION) {
						String pathName = fc.getSelectedFile().getPath();
						ImageIcon icon = new ImageIcon(pathName);
						Image image = icon.getImage(); // transform it
						Image newimg = image.getScaledInstance(290, 42,
								java.awt.Image.SCALE_SMOOTH); // scale it the
																// smooth way
						icon = new ImageIcon(newimg);
						signatureBox.setIcon(icon);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JButton signatureRemove = new JButton("Remove Signature");
		signatureRemove.setBounds(160, 20, 115, 25);
		signatureLoadPanel.add(signatureRemove);
		signatureRemove.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				signatureBox.setIcon(null);
			}
		});

		JLabel signatureLine = new JLabel(
				"X__________________________________________");
		signatureLine.setBounds(10, 81, 350, 25);
		signatureLoadPanel.add(signatureLine);

		// Load Signature Panel
		// Finish-----------------------------------------------------------------------------------------//

		mainPanel.add(companyName);
		mainPanel.add(companyAddress);
		mainPanel.add(companyCity);
		mainPanel.add(companyState);
		mainPanel.add(companyZip);
		mainPanel.add(companyPhone);
		mainPanel.add(companyEmail);
		mainPanel.add(nameField);
		mainPanel.add(addressField);
		mainPanel.add(cityField);
		mainPanel.add(customerInfoStateComboBox);
		mainPanel.add(zipField);
		mainPanel.add(phoneField);
		mainPanel.add(emailField);
		mainPanel.add(save);
		mainPanel.add(infoClear);
		mainPanel.add(imageLoadPanel);
		mainPanel.add(signatureLoadPanel);
		add(mainPanel);
	}

	/**
	 * @return the main
	 */
	public Main getMain() {
		return main;
	}

	/**
	 * @param main the main to set
	 */
	public void setMain(Main main) {
		this.main = main;
	}

	/**
	 * @return the companyName
	 */
	public JLabel getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(JLabel companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyAddress
	 */
	public JLabel getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * @param companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(JLabel companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * @return the companyCity
	 */
	public JLabel getCompanyCity() {
		return companyCity;
	}

	/**
	 * @param companyCity the companyCity to set
	 */
	public void setCompanyCity(JLabel companyCity) {
		this.companyCity = companyCity;
	}

	/**
	 * @return the companyState
	 */
	public JLabel getCompanyState() {
		return companyState;
	}

	/**
	 * @param companyState the companyState to set
	 */
	public void setCompanyState(JLabel companyState) {
		this.companyState = companyState;
	}

	/**
	 * @return the companyZip
	 */
	public JLabel getCompanyZip() {
		return companyZip;
	}

	/**
	 * @param companyZip the companyZip to set
	 */
	public void setCompanyZip(JLabel companyZip) {
		this.companyZip = companyZip;
	}

	/**
	 * @return the companyPhone
	 */
	public JLabel getCompanyPhone() {
		return companyPhone;
	}

	/**
	 * @param companyPhone the companyPhone to set
	 */
	public void setCompanyPhone(JLabel companyPhone) {
		this.companyPhone = companyPhone;
	}

	/**
	 * @return the companyEmail
	 */
	public JLabel getCompanyEmail() {
		return companyEmail;
	}

	/**
	 * @param companyEmail the companyEmail to set
	 */
	public void setCompanyEmail(JLabel companyEmail) {
		this.companyEmail = companyEmail;
	}

	/**
	 * @return the nameField
	 */
	public JTextField getNameField() {
		return nameField;
	}

	/**
	 * @param nameField the nameField to set
	 */
	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}

	/**
	 * @return the addressField
	 */
	public JTextField getAddressField() {
		return addressField;
	}

	/**
	 * @param addressField the addressField to set
	 */
	public void setAddressField(JTextField addressField) {
		this.addressField = addressField;
	}

	/**
	 * @return the cityField
	 */
	public JTextField getCityField() {
		return cityField;
	}

	/**
	 * @param cityField the cityField to set
	 */
	public void setCityField(JTextField cityField) {
		this.cityField = cityField;
	}

	/**
	 * @return the zipField
	 */
	public JTextField getZipField() {
		return zipField;
	}

	/**
	 * @param zipField the zipField to set
	 */
	public void setZipField(JTextField zipField) {
		this.zipField = zipField;
	}

	/**
	 * @return the phoneField
	 */
	public JTextField getPhoneField() {
		return phoneField;
	}

	/**
	 * @param phoneField the phoneField to set
	 */
	public void setPhoneField(JTextField phoneField) {
		this.phoneField = phoneField;
	}

	/**
	 * @return the emailField
	 */
	public JTextField getEmailField() {
		return emailField;
	}

	/**
	 * @param emailField the emailField to set
	 */
	public void setEmailField(JTextField emailField) {
		this.emailField = emailField;
	}

	/**
	 * @return the customerInfoStateComboBox
	 */
	public JComboBox getCustomerInfoStateComboBox() {
		return customerInfoStateComboBox;
	}

	/**
	 * @param customerInfoStateComboBox the customerInfoStateComboBox to set
	 */
	public void setCustomerInfoStateComboBox(JComboBox customerInfoStateComboBox) {
		this.customerInfoStateComboBox = customerInfoStateComboBox;
	}

	/**
	 * @return the fc
	 */
	public JFileChooser getFc() {
		return fc;
	}

	/**
	 * @param fc the fc to set
	 */
	public void setFc(JFileChooser fc) {
		this.fc = fc;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
