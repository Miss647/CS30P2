import javax.swing.*;

import java.awt.*;

public class bank extends JFrame {

    public class Bank {

		public String addAccount(String trim, String trim2, double bal) {
			// TODO Auto-generated method stub
			return null;
		}

		public String checkBalance(String acct) {
			// TODO Auto-generated method stub
			return null;
		}

		public String deleteAccount(String acct) {
			// TODO Auto-generated method stub
			return null;
		}

		public String transaction(int code, String acct, double amt) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Bank bank = new Bank();

    private final JTextField tfFirst = new JTextField(10);
    private final JTextField tfLast = new JTextField(10);
    private final JTextField tfInitBal = new JTextField(8);

    private final JTextField tfAcctId = new JTextField(10);
    private final JTextField tfAmount = new JTextField(8);

    private final JTextArea out = new JTextArea(10, 50);

    public void LocalBankGUI() {
        super("LocalBankGUI");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        // Top panel for creating a new account
        JPanel pCreate = new JPanel(new GridBagLayout());
        pCreate.setBorder(BorderFactory.createTitledBorder("Create Account"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);

        c.gridy = 0; c.gridx = 0; c.anchor = GridBagConstraints.LINE_END; pCreate.add(new JLabel("First Name:"), c);
        c.gridx = 1; c.anchor = GridBagConstraints.LINE_START; pCreate.add(tfFirst, c);

        c.gridy = 1; c.gridx = 0; c.anchor = GridBagConstraints.LINE_END; pCreate.add(new JLabel("Last Name:"), c);
        c.gridx = 1; c.anchor = GridBagConstraints.LINE_START; pCreate.add(tfLast, c);

        c.gridy = 2; c.gridx = 0; c.anchor = GridBagConstraints.LINE_END; pCreate.add(new JLabel("Initial Balance:"), c);
        c.gridx = 1; c.anchor = GridBagConstraints.LINE_START; pCreate.add(tfInitBal, c);

        JButton btnCreate = new JButton("Create");
        c.gridy = 0; c.gridx = 2; c.gridheight = 3; c.fill = GridBagConstraints.VERTICAL;
        pCreate.add(btnCreate, c);

        // Middle panel for operations on an account
        JPanel pOps = new JPanel(new GridBagLayout());
        pOps.setBorder(BorderFactory.createTitledBorder("Account Operations"));
        GridBagConstraints d = new GridBagConstraints();
        d.insets = new Insets(4, 4, 4, 4);

        d.gridy = 0; d.gridx = 0; d.anchor = GridBagConstraints.LINE_END; pOps.add(new JLabel("Account ID:"), d);
        d.gridx = 1; d.anchor = GridBagConstraints.LINE_START; pOps.add(tfAcctId, d);

        d.gridy = 1; d.gridx = 0; d.anchor = GridBagConstraints.LINE_END; pOps.add(new JLabel("Amount:"), d);
        d.gridx = 1; d.anchor = GridBagConstraints.LINE_START; pOps.add(tfAmount, d);

        JPanel buttons = new JPanel(new GridLayout(1, 0, 6, 0));
        JButton btnDeposit = new JButton("Deposit");
        JButton btnWithdraw = new JButton("Withdraw");
        JButton btnCheck = new JButton("Check Balance");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");
        buttons.add(btnDeposit);
        buttons.add(btnWithdraw);
        buttons.add(btnCheck);
        buttons.add(btnDelete);
        buttons.add(btnClear);

        d.gridy = 0; d.gridx = 2; d.gridheight = 2; d.fill = GridBagConstraints.VERTICAL;
        pOps.add(buttons, d);

        // Bottom area for output messages from Bank methods
        out.setEditable(false);
        out.setLineWrap(true);
        out.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(out);
        scroll.setBorder(BorderFactory.createTitledBorder("Output"));

        add(pCreate, BorderLayout.NORTH);
        add(pOps, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // Button actions
        btnCreate.addActionListener(e -> {
            try {
                double bal = parseMoney(tfInitBal.getText());
                String id = bank.addAccount(tfFirst.getText().trim(), tfLast.getText().trim(), bal);
                log("Created account. ID: " + id);
                tfAcctId.setText(id);
            } catch (Exception ex) {
                log("ERROR: " + ex.getMessage());
            }
        });

        btnDeposit.addActionListener(e -> doTrans(1));
        btnWithdraw.addActionListener(e -> doTrans(2));

        btnCheck.addActionListener(e -> {
            String acct = tfAcctId.getText().trim();
            if (acct.isEmpty()) { log("Enter Account ID"); return; }
            log(bank.checkBalance(acct));
        });

        btnDelete.addActionListener(e -> {
            String acct = tfAcctId.getText().trim();
            if (acct.isEmpty()) { log("Enter Account ID"); return; }
            log(bank.deleteAccount(acct));
        });

        btnClear.addActionListener(e -> {
            tfFirst.setText(""); tfLast.setText(""); tfInitBal.setText("");
            tfAcctId.setText(""); tfAmount.setText("");
            out.setText("");
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private <bank> void doTrans(int code) {
        String acct = tfAcctId.getText().trim();
        if (acct.isEmpty()) { log("Enter Account ID"); return; }
        try {
            double amt = parseMoney(tfAmount.getText());
            String res = bank.transaction(code, acct, amt);
            log(res);
        } catch (Exception ex) {
            log("ERROR: " + ex.getMessage());
        }
    }

    private static double parseMoney(String s) {
        if (s == null || s.trim().isEmpty()) throw new IllegalArgumentException("Amount required");
        return Double.parseDouble(s.trim());
    }

    private void log(String msg) { out.append(msg + "\n"); }

    public static <LocalBankGUI> void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignore) {}
        SwingUtilities.invokeLater(LocalBankGUI::new);
    }
}
