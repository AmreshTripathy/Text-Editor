import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/*
 * @Amresh Tripathy
 */

public class TextEditor implements ActionListener {
	// Declaring the properties of TextEditor
	JFrame frame;

	JMenuBar menuBar;

	JMenu file, edit;

	// File Menu Items
	JMenuItem newFile, openFile, saveFile;
	// Edit Menu Items
	JMenuItem cut, copy, paste, selectAll, close;

	JTextArea textArea;

	TextEditor() {
		// initialize a frame
		frame = new JFrame();

		// Initilize the menubar
		menuBar = new JMenuBar();
		// Initilize text area
		textArea = new JTextArea();

		// Initialize menus
		file = new JMenu("File");
		edit = new JMenu("Edit");

		// Initilize file menu Items
		newFile = new JMenuItem("New Window");
		openFile = new JMenuItem("Open File");
		saveFile = new JMenuItem("Save File");

		// add Action listener to file menu items
		newFile.addActionListener(this);
		openFile.addActionListener(this);
		saveFile.addActionListener(this);

		// Add menu items to file menu
		file.add(newFile);
		file.add(openFile);
		file.add(saveFile);

		// Initialize edit menu Items
		cut = new JMenuItem("Cut");
		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		selectAll = new JMenuItem("Select All");
		close = new JMenuItem("Close");

		// add action listiner to edit menu items
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		selectAll.addActionListener(this);
		close.addActionListener(this);

		// Add menu items to edit menu
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(selectAll);
		edit.add(close);

		// Add menus to menubar
		menuBar.add(file);
		menuBar.add(edit);

		// set menu bar to frame
		frame.setJMenuBar(menuBar);
		// Create a content Pane
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));

		// Add text area to the panel
		panel.add(textArea, BorderLayout.CENTER);

		// Create a scroll Pane
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// add scroll pane to the panel
		panel.add(scrollPane);

		// add panel to frame
		frame.add(panel);

		// set dimensions of frame
		frame.setBounds(200, 50, 800, 600);
		frame.setTitle("Text Editor by @Amresh");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == cut) {
			// perform cut operation
			textArea.cut();
		}

		if (actionEvent.getSource() == copy) {
			// perform copy operation
			textArea.copy();
		}

		if (actionEvent.getSource() == paste) {
			// perform paste operation
			textArea.paste();
		}

		if (actionEvent.getSource() == selectAll) {
			// perform select all operation
			textArea.selectAll();
		}

		if (actionEvent.getSource() == close) {
			// perform close operation
			System.exit(0);
		}

		if (actionEvent.getSource() == openFile) {
			// open a file chooser
			JFileChooser fileChooser = new JFileChooser("C:\\Users\\Amresh Tripathy\\Documents\\Text_Editor_Project\\");
			int chooseOption = fileChooser.showOpenDialog(null);

			// if we have clocked on open button
			if (chooseOption == JFileChooser.APPROVE_OPTION) {
				// getting the selected file
				File file = fileChooser.getSelectedFile();

				// get the path of the selected file
				String filePath = file.getPath();
				try {
					// Intitlize file reader
					FileReader fileReader = new FileReader(filePath);
					// Intitlize Buffered Reader
					BufferedReader bufferedReader = new BufferedReader(fileReader);

					String intermidiate = "", output = "";
					// Read Content of file line by line
					while ((intermidiate = bufferedReader.readLine()) != null) {
						output += intermidiate + "\n";
					}
					bufferedReader.close();

					// set the output String to text area
					textArea.setText(output);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (actionEvent.getSource() == saveFile) {
			// open a file chooser
			JFileChooser fileChooser = new JFileChooser("C:\\Users\\Amresh Tripathy\\Documents\\Text_Editor_Project\\");
			// get choose option from file chooser
			int chooseOption = fileChooser.showSaveDialog(null);

			if (chooseOption == JFileChooser.APPROVE_OPTION) {
				// Create a new file with choosen directory path with file name
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
				try {
					// intialize file writer
					FileWriter fileWriter = new FileWriter(file);
					// Initilize Buffered writer
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					// write contents of text area
					textArea.write(bufferedWriter);
					bufferedWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (actionEvent.getSource() == newFile) {
			// we need to create another text Editor
			new TextEditor();
		}
	}

	public static void main(String[] args) {
		new TextEditor();
	}
}
