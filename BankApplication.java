
/**
 * APPLICATION FOR BANKING SYSTEM
 * Developed By : S.KAVITHA
 */
import java.util.*;

class Account {
	String accountNumber;
	String accountName;
	double balance;
	static int id = 100;

	Account() {
	}

	Account(String number, double amount) {
		id++;
		accountNumber = id + "";
		accountName = number;
		balance = amount;
	}

	void getDetails() {
		id++;
		accountNumber = id + "";

		System.out.print("\tEnter Account Name : ");
		accountName = new Scanner(System.in).nextLine();

		System.out.print("\tEnter Initial Balance : ");
		balance = new Scanner(System.in).nextDouble();
	}

	void deposit() {
		System.out.print("\tEnter Deposit Amount : ");
		double amount = new Scanner(System.in).nextDouble();
		balance = balance + amount;
		System.out.println("\tSuccess! Amount deposited at " + this.accountName + " account.\n\tAvailable Balance :"
				+ balance + "\n");
	}

	void withdraw() {
		System.out.println();
		System.out.print("\tEnter Withdraw Amount : ");
		double amount = new Scanner(System.in).nextDouble();
		if (amount > balance) {
			System.out.println("Insufficient Funds!!");
		} else {
			balance = balance - amount;
			System.out.println("\tAmount withdrawn! Available Balance : " + balance + "\n");
		}
	}

	void balance() {
		System.out.println("\tBalance available in Account Number " + accountNumber + " is:" + balance);
	}

	public String toString() {
		String details = "";
		details += "\n\tAccount Details";
		details += "\n\t---------------";
		details += "\n\tNumber\t: " + accountNumber;
		details += "\n\tName\t: " + accountName;
		details += "\n\tBalance\t: " + String.format("%,.2f", balance);
		details += "\n";

		return details;
	}
}

class BankApplication {
	static HashMap<String, Account> accounts = new HashMap<String, Account>();
	static Account selectedAccount;

	public static void main(String args[]) {

		System.out.println("\t////////////////////////////////////////////////////////////");
		System.out.println("\t//////////////////// Welcome to Banking ////////////////////");
		System.out.println("\t////////////////////////////////////////////////////////////");

		Account account = new Account();
		account = new Account("AAA", 10000);
		accounts.put(account.accountNumber + "", account);
		account = new Account("BBB", 20000);
		accounts.put(account.accountNumber + "", account);
		account = new Account("CCC", 30000);
		accounts.put(account.accountNumber + "", account);
		account = new Account("DDD", 40000);
		accounts.put(account.accountNumber + "", account);
		account = new Account("EEE", 50000);
		accounts.put(account.accountNumber + "", account);

		BankApplication app = new BankApplication();
		app.showList();

		if (selectedAccount != null) {
			app.accountAction();
		} else {
			app.adminAction();
		}
	}

	void adminAction() {
		boolean entry = true;
		BankApplication app = new BankApplication();
		do {
			entry = true;
			System.out.println("\n\t##### MAIN CHOICES :=> \t"
					+ " 1 - List" + "\t"
					+ " 2 - Add" + "\t"
					+ " 3 - Open" + "\t"
					+ " 4 - Delete" + "\t"
					+ " 5 - Exit");
			System.out.print("\n\tSelect Choice : ");
			int ch = new Scanner(System.in).nextInt();

			switch (ch) {
				case 1: {
					app.showList();
					break;
				}
				case 2: {
					Account account = new Account();
					account.getDetails();
					accounts.put(account.accountNumber + "", account);
					System.out.println("\n\tAdded successfully. Account Number : " + account.accountNumber);
					System.out.print("\n\tDo you want to continue with this account (Y/N) ? ");
					String o = new Scanner(System.in).nextLine();
					if (o.equalsIgnoreCase("y")) {
						selectedAccount = account;
						entry = false;
						accountAction();
					} else {
						selectedAccount = null;
					}
					break;
				}
				case 3: {
					System.out.print("\tEnter Account Number you want to Open : ");
					String o = new Scanner(System.in).nextLine();
					if (accounts.containsKey(o)) {
						selectedAccount = accounts.get(o);
						System.out.println("\t" + selectedAccount.toString());
						entry = false;
						accountAction();
					} else {
						System.out.print("\tAccount Not Found.");
					}
					break;
				}
				case 4: {
					System.out.print("\tEnter Account Number you want to Delete : ");
					String o = new Scanner(System.in).nextLine();
					if (accounts.containsKey(o)) {
						System.out.println("\t" + accounts.get(o).toString());
						System.out.println("\tAbove Account Removed");
						accounts.remove(o);
					} else {
						System.out.print("\tAccount Not Found.");
					}
					break;
				}
				case 5: {
					entry = false;
					System.out.println("\t////////////////////////////////////////////////////////////");
					System.out.println("\t//////////////////// Thanks for Banking ////////////////////");
					System.out.println("\t////////////////////////////////////////////////////////////");
					System.exit(0);
					break;
				}
				default:
					System.out.println("\tEnter a valid Option!" + "\n\n");
			}

		} while (entry);
	}

	void accountAction() {
		boolean entry = true;
		do {
			System.out.println("\n\t##### " + (selectedAccount.accountName).toUpperCase() + "'s CHOICES :=> \t"
					+ " 1 - Balance Check" + "\t"
					+ " 2 - Deposit" + "\t"
					+ " 3 - Withdraw" + "\t"
					+ " 4 - Exit");
			System.out.print("\n\tSelect Choice : ");
			int ch = new Scanner(System.in).nextInt();
			switch (ch) {
				case 1: {
					selectedAccount.balance();
					break;
				}
				case 2: {
					selectedAccount.deposit();
					accounts.put(selectedAccount.accountNumber + "", selectedAccount);
					break;
				}
				case 3: {
					selectedAccount.withdraw();
					accounts.put(selectedAccount.accountNumber + "", selectedAccount);
					break;
				}
				case 4: {
					System.out.println("\t" + selectedAccount.accountName + "'s account action closed...");
					selectedAccount = null;
					entry = false;
					adminAction();
					break;
				}
				default:
					System.out.println("\tInvalid Selection.! Please selecte the correct option");
			}

		} while (entry);
	}

	private void showList() {
		System.out.println("\t************** LIST OF ACCOUNTS **************");
		System.out.println("\t----------------------------------------------");
		System.out.println("\tNumber\tName\tBalance");
		System.out.println("\t----------------------------------------------");
		for (Account account : accounts.values()) {
			System.out.println("\t" + account.accountNumber + "\t" + account.accountName + "\t"
					+ String.format("%,.2f", account.balance));
		}
		System.out.println("\t----------------------------------------------");
	}
}
