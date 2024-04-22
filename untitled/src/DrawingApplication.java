import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class DrawingApplication extends JFrame {
    JComboBox<String> scaleOptions;
    DrawingCanvas canvas;

    public DrawingApplication() {
        setTitle("Drawing Program");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Setup top panel with JComboBox
        JPanel topPanel = new JPanel();
        scaleOptions = new JComboBox<>();
        scaleOptions.addItem("2");
        scaleOptions.addItem("3");
        scaleOptions.addItem("4");
        scaleOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double selectedScale = Double.parseDouble((String)scaleOptions.getSelectedItem());
                for (ScalableRectangle rect : canvas.rectangles) {
                    rect.scale = selectedScale;
                }
                canvas.repaint();
            }
        });
        topPanel.add(scaleOptions);

        // Setup drawing canvas
        canvas = new DrawingCanvas();
        canvas.setBackground(Color.WHITE);

        add(topPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
    }

    class DrawingCanvas extends JPanel {
        List<ScalableRectangle> rectangles;

        public DrawingCanvas() {
            rectangles = new ArrayList<>();
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    double initialScale = Double.parseDouble((String)scaleOptions.getSelectedItem());
                    rectangles.add(new ScalableRectangle(e.getX(), e.getY(), 50, 50, initialScale));
                    repaint();
                }
            });

            addMouseWheelListener(new MouseWheelListener() {
                public void mouseWheelMoved(MouseWheelEvent e) {
                    int rotation = e.getWheelRotation();
                    int currentIndex = scaleOptions.getSelectedIndex() - rotation;
                    if (currentIndex >= 0 && currentIndex < scaleOptions.getItemCount()) {
                        scaleOptions.setSelectedIndex(currentIndex);
                    }
                }
            });
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (ScalableRectangle rect : rectangles) {
                rect.draw(g);
            }
        }
    }

    class ScalableRectangle {
        int x, y, width, height;
        double scale;

        public ScalableRectangle(int x, int y, int width, int height, double scale) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.scale = scale;
        }

        public void draw(Graphics g) {
            g.drawRect(x, y, (int)(width * scale), (int)(height * scale));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DrawingApplication().setVisible(true);
            }
        });
    }
}
