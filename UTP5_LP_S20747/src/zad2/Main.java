/**
 *
 *  @author Leszczyński Patryk S20747
 *
 */

package zad2;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

  public static void main(String[] args) {

    JFrame jf = new JFrame("Task List");
    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    jf.setPreferredSize(new Dimension(300, 350));
    jf.setLocationRelativeTo(null);

    DefaultListModel<Task> model = new DefaultListModel<>();
    JList<Task> list = new JList<>(model);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
    renderer.setHorizontalAlignment(JLabel.CENTER);
    //list.setCellRenderer(new Renderer());
    JScrollPane scroll = new JScrollPane(list);
    scroll.setPreferredSize(new Dimension(300,200));

    JButton addButton = new JButton("Add task");
    JButton statusButton = new JButton("Show status");
    JButton resultButton = new JButton("Show result");
    JButton cancelButton = new JButton("Cancel task");

    JLabel label = new JLabel("", SwingConstants.CENTER);
    label.setPreferredSize(new Dimension(300,50));

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addButton);
    buttonPanel.add(statusButton);
    buttonPanel.add(resultButton);
    buttonPanel.add(cancelButton);

    jf.add(scroll, BorderLayout.NORTH);
    jf.add(buttonPanel, BorderLayout.CENTER);
    jf.add(label, BorderLayout.SOUTH);

    jf.pack();
    jf.setVisible(true);

    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        label.setText("");
        Task t = new Task();
        model.addElement(t);
        t.start();
      }
    });

    statusButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        label.setText("");
        label.setText(model.get(list.getSelectedIndex()).getStatus());
      }
    });

    resultButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        label.setText("");
        Task selectedTask = model.get(list.getSelectedIndex());

        if (selectedTask.getStatus().equals("Done")) {
          label.setText(String.valueOf(selectedTask.result));
        } else {
          label.setText("Cannot show result. Task's status is not 'Done'.");
        }
      }
    });

    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        label.setText("");
        model.get(list.getSelectedIndex()).cancel();
      }
    });
  }

}
