package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import javax.swing.border.Border;
import kenken.Board;
import kenken.Operation;

final class KenKenGrid extends JPanel {

    private static final Font FONT = new Font("Verdana",
            Font.CENTER_BASELINE,
            25);

    private JTextArea[][] grid;

    private final int dimension;
    private JPanel gridPanel;
    private JPanel buttonPanel;
    private JButton solveButton;
    private JButton clearButton;
    private JPanel[][] minisquarePanels;
    public final Board gameBoard;

    KenKenGrid(Board Savedgame) {
        this.dimension = Savedgame.size;
        this.gameBoard = Savedgame;
        setup();
    }

    KenKenGrid(int dimension, int pProb1, int pProb2, int pProb4, int pProbSum, int pProbSub, int pProbDiv, int pProbMult, int pProbMod) {

        this.dimension = dimension;
        this.gameBoard = new Board(dimension, pProb1, pProb2, pProb4, pProbSum, pProbSub, pProbDiv, pProbMult, pProbMod);
        setup();

    }

    public void setup() {
        this.grid = new JTextArea[dimension][dimension];
        for (int y = 0; y < dimension; ++y) {
            for (int x = 0; x < dimension; ++x) {
                JTextArea field = new JTextArea();
                grid[y][x] = field;
            }
        }

        this.gridPanel = new JPanel();
        this.buttonPanel = new JPanel();

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Dimension fieldDimension = new Dimension(40, 40);

        for (int y = 0; y < dimension; ++y) {
            for (int x = 0; x < dimension; ++x) {
                JTextArea field = grid[y][x];
                field.setBorder(border);
                field.setFont(FONT);
                field.setPreferredSize(fieldDimension);

            }
        }

        this.gridPanel.setLayout(new GridLayout(dimension,
                dimension));

        this.minisquarePanels = new JPanel[dimension][dimension];

        for (int y = 0; y < dimension; ++y) {
            for (int x = 0; x < dimension; ++x) {
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(1, 1));
                minisquarePanels[y][x] = panel;
                gridPanel.add(panel);
            }
        }

        for (int y = 0; y < dimension; ++y) {
            for (int x = 0; x < dimension; ++x) {
                minisquarePanels[y][x].add(grid[y][x]);
            }
        }

        this.gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,
                2));
        this.clearButton = new JButton("Clear");
        this.solveButton = new JButton("Solve");

        this.buttonPanel.setLayout(new BorderLayout());
        this.buttonPanel.add(clearButton, BorderLayout.WEST);
        this.buttonPanel.add(solveButton, BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        this.add(gridPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
        desplegarSinSolucion();

        clearButton.addActionListener((ActionEvent e) -> {
            desplegarSinSolucion();
        });
        solveButton.addActionListener((ActionEvent e) -> {
            desplegarConSolucion();
        });
    }

    private void desplegarConSolucion() {
        int numb;
        int k;
        int j;
        for (int y = 0; y < dimension; ++y) {
            for (int x = 0; x < dimension; ++x) {
                numb = gameBoard.cells[y][x].number;
                if (numb < 0) {
                    grid[y][x].setText("" + numb);
                } else {
                    grid[y][x].setText(" " + numb);
                }
                Color bgColor = Color.decode(Board.indexcolors[gameBoard.cells[y][x].operationId % 128]);
                grid[y][x].setBackground(bgColor);
                if (bgColor.getRed() * 0.299 + bgColor.getGreen() * 0.587 + bgColor.getBlue() * 0.114 > 186) {
                    grid[y][x].setForeground(Color.black);
                } else {
                    grid[y][x].setForeground(Color.white);
                }
            }
        }
        for (Operation op : gameBoard.operations) {
            k = op.cells.get(0).posX;
            j = op.cells.get(0).posY;
            grid[j][k].setFont(new Font("Verdana",
                    Font.CENTER_BASELINE,
                    10));
            grid[j][k].setText(op.toStringFirstCell());
            Color bgColor = Color.decode(Board.indexcolors[op.operationId % 128]);
            grid[j][k].setBackground(bgColor);
            if (bgColor.getRed() * 0.299 + bgColor.getGreen() * 0.587 + bgColor.getBlue() * 0.114 > 186) {
                grid[j][k].setForeground(Color.black);
            } else {
                grid[j][k].setForeground(Color.white);
            }
        }
    }

    private void desplegarSinSolucion() {
        int numb;
        int k;
        int j;
        for (int y = 0; y < dimension; ++y) {
            for (int x = 0; x < dimension; ++x) {
                numb = gameBoard.cells[y][x].number;

                Color bgColor = Color.decode(Board.indexcolors[gameBoard.cells[y][x].operationId % 128]);
                grid[y][x].setText("");
                grid[y][x].setBackground(bgColor);
                if (bgColor.getRed() * 0.299 + bgColor.getGreen() * 0.587 + bgColor.getBlue() * 0.114 > 186) {
                    grid[y][x].setForeground(Color.black);
                } else {
                    grid[y][x].setForeground(Color.white);
                }
            }
        }
        for (Operation op : gameBoard.operations) {
            k = op.cells.get(0).posX;
            j = op.cells.get(0).posY;
            grid[j][k].setFont(new Font("Verdana",
                    Font.CENTER_BASELINE,
                    10));
            grid[j][k].setText(op.toString());
            Color bgColor = Color.decode(Board.indexcolors[op.operationId % 128]);
            grid[j][k].setBackground(bgColor);
            if (bgColor.getRed() * 0.299 + bgColor.getGreen() * 0.587 + bgColor.getBlue() * 0.114 > 186) {
                grid[j][k].setForeground(Color.black);
            } else {
                grid[j][k].setForeground(Color.white);
            }
        }
    }

}
