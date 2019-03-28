package hw4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUICF extends CFGame {
	private GameBoard board;
	private CFPlayer p1;
	private CFPlayer p2;

	public GUICF(CFPlayer ai) {
		super();
		p1 = ai;
		//p2 = ai;
		board = new GameBoard();

		board.frame = new JFrame("Connect Four Game");
		
		board.panel = new JPanel(new BorderLayout());
		board.frame.setSize(400,300);
		
		board.labels = new JLabel[7][6];
		board.pane = board.frame.getContentPane();
		board.panel.setLayout(new GridLayout(0,7));


		JButton n1 = new JButton("\u2193");
		n1.addActionListener(new n1listenAI());
		JButton n2 = new JButton("\u2193");
		n2.addActionListener(new n2listenAI());
		JButton n3 = new JButton("\u2193");
		n3.addActionListener(new n3listenAI());
		JButton n4 = new JButton("\u2193");
		n4.addActionListener(new n4listenAI());
		JButton n5 = new JButton("\u2193");
		n5.addActionListener(new n5listenAI());
		JButton n6 = new JButton("\u2193");
		n6.addActionListener(new n6listenAI());
		JButton n7 = new JButton("\u2193");
		n7.addActionListener(new n7listenAI());

		for (int j = 6; j >= 0; j--) {
			for (int i = 0; i < 7; i++) {
				if (j != 6) {
					board.pane.add(board.panel, BorderLayout.SOUTH);
					board.labels[i][j] = new JLabel(" ");
					board.labels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
					board.labels[i][j].setBackground(Color.white);
					board.labels[i][j].setOpaque(true);
					board.labels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
					board.panel.add(board.labels[i][j]);		
				}
				else {
					board.panel.add(n1);
					board.panel.add(n2);
					board.panel.add(n3);
					board.panel.add(n4);
					board.panel.add(n5);
					board.panel.add(n6);
					board.panel.add(n7);
				}
			}
		}

		if (Math.round(Math.random()) == 0) {
			this.playGUI(p1.nextMove(this));
		}

		board.frame.setVisible(true);


		board.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		

	}


	class n1listenAI implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			if (GUICF.this.playGUI(1)) {
				playGUI(GUICF.this.p1.nextMove(GUICF.this));
			}
		}
	}

	class n2listenAI implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			if (GUICF.this.playGUI(2)) {
				playGUI(GUICF.this.p1.nextMove(GUICF.this));
			}
		}
	}

	class n3listenAI implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			if (GUICF.this.playGUI(3)) {
				playGUI(GUICF.this.p1.nextMove(GUICF.this));
			}
		}
	}

	class n4listenAI implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			if (GUICF.this.playGUI(4)) {
				playGUI(GUICF.this.p1.nextMove(GUICF.this));
			}
		}
	}

	class n5listenAI implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			if (GUICF.this.playGUI(5)) {
				playGUI(GUICF.this.p1.nextMove(GUICF.this));
			}
		}
	}

	class n6listenAI implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			if (GUICF.this.playGUI(6)) {
				playGUI(GUICF.this.p1.nextMove(GUICF.this));
			}
		}
	}

	class n7listenAI implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			if (GUICF.this.playGUI(7)) {
				playGUI(GUICF.this.p1.nextMove(GUICF.this));
			}
		}
	}


	public GUICF(CFPlayer ai1, CFPlayer ai2) {
		super();
		board = new GameBoard();
		JButton n = new JButton("Play");

		board.frame.getContentPane().add(n, BorderLayout.NORTH);
		board.frame.setVisible(true);

		if (Math.round(Math.random()) == 0) {
			p1 = ai1;
			p2 = ai2;
		}
		else {
			p1 = ai2;
			p2 = ai1;
		}
		n.addActionListener(new nlistenAI());
	}

	class nlistenAI implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			if (GUICF.this.isRedTurn()) {
				playGUI(GUICF.this.p1.nextMove(GUICF.this));
			}

			else {
				playGUI(GUICF.this.p2.nextMove(GUICF.this));
			}
		}
	}

	private boolean playGUI(int c) {
		for (int j = 0; j < 6; j++) {
			if (this.getState()[c-1][j] == 0) {
				if (this.isRedTurn()) {
					board.paint(c-1, j--, 1);
				}
				else {
					board.paint(c-1, j--, -1);
				}
				this.play(c);
				if (this.isGameOver()) {
					if(this.winner() == 1) {
						board.frame = new JFrame("Ending");
						board.frame.getContentPane().add(new JLabel("Red Wins!"), BorderLayout.CENTER);
						board.frame.setSize(300,200);
						board.frame.setVisible(true);
					}
					else if(this.winner() == -1) {
						board.frame = new JFrame("Ending");
						board.frame.getContentPane().add(new JLabel("Black Wins!"), BorderLayout.CENTER);
						board.frame.setSize(300,200);
						board.frame.setVisible(true);
					}

					else {
						board.frame = new JFrame("Ending");
						board.frame.getContentPane().add(new JLabel("Draw!"), BorderLayout.CENTER);
						board.frame.setSize(300,200);
						board.frame.setVisible(true);
					}
				}
				return true;
			}
		}
		return false;
	}

	private class GameBoard extends javax.swing.JPanel {
		JLabel[][] labels;
		JFrame frame;
		Container pane;
		JPanel panel;
		private GameBoard() {
			frame = new JFrame("Connect Four Game");
			panel = new JPanel(new BorderLayout());
			frame.setSize(300,200);
			labels = new JLabel[7][6];

			pane = frame.getContentPane();
			panel.setLayout(new GridLayout(0,7));
			for (int j = 5; j>=0; j--) {
				for (int i = 0; i < 7; i++) {
					pane.add(panel, BorderLayout.SOUTH);
					labels[i][j] = new JLabel(" ");
					labels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
					labels[i][j].setBackground(Color.white);
					labels[i][j].setOpaque(true);
					panel.add(labels[i][j]);

				}
			}

			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		private void paint(int x, int y, int color) {
			if (color == 1) {
				labels[x][y].setBackground(Color.red);
			}
			if (color == -1) {
				labels[x][y].setBackground(Color.black);
			}
		}
	}

}