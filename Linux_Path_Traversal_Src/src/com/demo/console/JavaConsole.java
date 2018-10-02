package com.demo.console;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @brief A Console using Swing.
 * 
 * 
 * @author Shubham Kadlag
 *
 * 
 */

public class JavaConsole extends WindowAdapter implements WindowListener, ActionListener, Runnable {
	private JFrame frame;
	private JTextArea textArea;
	private Thread reader;
	private Thread reader2;
	private boolean quit;

	private final PipedInputStream pin = new PipedInputStream();
	/**
	 * InputStream to take user input from console in order to pass the same to
	 * OutputStream.
	 */
	private final PipedInputStream pin2 = new PipedInputStream();
	/** Second InputStream in case the first is not available. */
	private final PipedOutputStream pout3 = new PipedOutputStream();

	/** OutputStream to print the STDOUT to console. */

	/**
	 * @brief Class Constructor
	 */
	public JavaConsole() {
		// create all components and add them
		frame = new JFrame("Java Console");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = new Dimension((int) (screenSize.width / 2), (int) (screenSize.height / 2));
		int x = (int) (frameSize.width / 2);
		int y = (int) (frameSize.height / 2);
		frame.setBounds(x, y, frameSize.width, frameSize.height);

		textArea = new JTextArea();
		textArea.setCaret(new BlockCaret());
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.white);
		textArea.setCaretColor(textArea.getForeground());
		textArea.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(true);
		textArea.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"), "none");
		textArea.addMouseListener(new MouseAdapter() {
		});
		textArea.addMouseMotionListener(new MouseMotionAdapter() {
		});

		String[] keys = { "UP", "DOWN", "LEFT", "RIGHT" };
		for (String key : keys) {
			textArea.getInputMap().put(KeyStroke.getKeyStroke(key), "none");
		}

		

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);

		frame.setVisible(true);

		frame.addWindowListener(this);
		

		try {
			PipedOutputStream pout = new PipedOutputStream(this.pin);
			System.setOut(new PrintStream(pout, true));
		} catch (java.io.IOException io) {
			textArea.append("Couldn't redirect STDOUT to this console\n" + io.getMessage());
			textArea.setCaretPosition(textArea.getDocument().getLength());
		} catch (SecurityException se) {
			textArea.append("Couldn't redirect STDOUT to this console\n" + se.getMessage());
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}

		try {
			PipedOutputStream pout2 = new PipedOutputStream(this.pin2);
			System.setErr(new PrintStream(pout2, true));
		} catch (java.io.IOException io) {
			textArea.append("Couldn't redirect STDERR to this console\n" + io.getMessage());
			textArea.setCaretPosition(textArea.getDocument().getLength());
		} catch (SecurityException se) {
			textArea.append("Couldn't redirect STDERR to this console\n" + se.getMessage());
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}

		try {
			System.setIn(new PipedInputStream(this.pout3));
		} catch (java.io.IOException io) {
			textArea.append("Couldn't redirect STDIN to this console\n" + io.getMessage());
			textArea.setCaretPosition(textArea.getDocument().getLength());
		} catch (SecurityException se) {
			textArea.append("Couldn't redirect STDIN to this console\n" + se.getMessage());
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}

		
		/** Disable combination keys Ctrl, Shift, Alt. However not guaranteed as the hot
		 keys are finally handled by OS .*/
		textArea.addKeyListener(new KeyListener() {

			 
			boolean ctrlPressed = false;
			boolean shiftPressed = false;
			boolean altPressed = false;

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					ctrlPressed = true;

				}

				if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					shiftPressed = true;

				}

				if (e.getKeyCode() == KeyEvent.VK_ALT) {
					altPressed = true;

				}

				if (ctrlPressed || shiftPressed || altPressed) {
					e.consume();
				}
			}

			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					ctrlPressed = false;

				}
				;

				if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					shiftPressed = false;

				}

				if (e.getKeyCode() == KeyEvent.VK_ALT) {
					altPressed = false;

				}

				if (ctrlPressed || shiftPressed || altPressed) {
					e.consume();
				}
			}

			public void keyTyped(KeyEvent e) {

				try {
					pout3.write(e.getKeyChar());
				} catch (IOException ex) {
				}
			}
		});

		quit = false; // signals the Threads that they should exit

		// Starting two seperate threads to read from the PipedInputStreams
		reader = new Thread(this);
		reader.setDaemon(true);
		reader.start();

		reader2 = new Thread(this);
		reader2.setDaemon(true);
		reader2.start();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowAdapter#windowClosed(java.awt.event.WindowEvent)
	 */
	public synchronized void windowClosed(WindowEvent evt) {
		quit = true;
		this.notifyAll(); // stop all threads
		try {
			reader.join(1000);
			pin.close();
		} catch (Exception e) {
		}
		try {
			reader2.join(1000);
			pin2.close();
		} catch (Exception e) {
		}
		try {
			pout3.close();
		} catch (Exception e) {
		}
		System.exit(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
	 */
	public synchronized void windowClosing(WindowEvent evt) {
		frame.setVisible(false); // default behaviour of JFrame
		frame.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public synchronized void actionPerformed(ActionEvent evt) {
		this.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public synchronized void run() {
		try {
			while (Thread.currentThread() == reader) {
				try {
					this.wait(100);
				} catch (InterruptedException ie) {
				}
				if (pin.available() != 0) {
					String input = this.readLine(pin);
					textArea.append(input);
					textArea.setCaretPosition(textArea.getDocument().getLength());
				}
				if (quit)
					return;
			}

			while (Thread.currentThread() == reader2) {
				try {
					this.wait(100);
				} catch (InterruptedException ie) {
				}
				if (pin2.available() != 0) {
					String input = this.readLine(pin2);
					textArea.append(input);
					textArea.setCaretPosition(textArea.getDocument().getLength());
				}
				if (quit)
					return;
			}
		} catch (Exception e) {
			textArea.append("\nConsole reports an Internal error.");
			textArea.append("The error is: " + e);
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}

	}

	/**
	 * @brief Read a line of text from the input stream
	 * @param in The PipedInputStream to read
	 * @return String A line of text
	 * @throws IOException
	 */
	private synchronized String readLine(PipedInputStream in) throws IOException {
		String input = "";
		do {
			int available = in.available();
			if (available == 0)
				break;
			byte b[] = new byte[available];
			in.read(b);
			input = input + new String(b, 0, b.length);
		} while (!input.endsWith("\n") && !input.endsWith("\r\n") && !quit);
		return input;
	}

	/**
	 * @brief Clear the console window
	 */
	public void clear() {
		textArea.setText("");
		textArea.append("$ ");
	}

	/**
	 * @return the consol's background color
	 */
	public Color getBackground() {
		return textArea.getBackground();
	}

	/**
	 * @param bg the desired background Color
	 */
	public void setBackground(Color bg) {
		this.textArea.setBackground(bg);
	}

	/**
	 * @return the consol's foreground color
	 */
	public Color getForeground() {
		return textArea.getForeground();
	}

	/**
	 * @param fg the desired foreground Color
	 */
	public void setForeground(Color fg) {
		this.textArea.setForeground(fg);
		this.textArea.setCaretColor(fg);
	}

	/**
	 * @return the consol's font
	 */
	public Font getFont() {
		return textArea.getFont();
	}

	/**
	 * @param f the font to use as the current font
	 */
	public void setFont(Font f) {
		textArea.setFont(f);
	}

	/**
	 * @param i the icon image to display in console window's corner
	 */
	public void setIconImage(Image i) {
		frame.setIconImage(i);
	}

	/**
	 * @param title the console window's title
	 */
	public void setTitle(String title) {
		frame.setTitle(title);
	}
}