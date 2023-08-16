import java.util.Scanner;

public class BankingApp {
    private static final Scanner scan = new Scanner(System.in);

    // ANSI escape codes for text colors
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {

        final String clear = "\033[H\033[2J";

        final String DASHBOARD = "WELCOME TO SMART BANKING";
        final String NEWACCOUNT = "CREATE NEW ACCOUNT";
        final String DEPOSIT = "DEPOSITS";
        final String WITHDRAWAL = "WITHDRAWAL";
        final String TRANSFER = "TRANSFER";
        final String ACCOUNTBALANCE = "CHECK ACCOUNT BALANCE";
        final String DELETEACCOUNT = "DELETE ACCOUNT";
        String screen = DASHBOARD;

        String[][] customers = new String[0][3]; // 2D array to store customer data: [Account, Name, Amount]

        do {
            System.out.println(clear);
            System.out.println("-".repeat(100));
            System.out.printf("|%40s%30s%30s|\n", " ", ANSI_BLUE + screen + ANSI_RESET, " ");
            System.out.println("-".repeat(100));

            switch (screen) {
                case DASHBOARD:
                    System.out.println("\n[1] Create New Account");
                    System.out.println("[2] Deposit");
                    System.out.println("[3] Withdrawal");
                    System.out.println("[4] Transfer");
                    System.out.println("[5] Check Account Balance");
                    System.out.println("[6] Delete Account");
                    System.out.println("[7] Exit\n\n");
                    System.out.print("Enter your Option : ");
                    int input = scan.nextInt();
                    scan.nextLine();

                    switch (input) {
                        case 1:
                            screen = NEWACCOUNT;
                            break;
                        case 2:
                            screen = DEPOSIT;
                            break;
                        case 3:
                            screen = WITHDRAWAL;
                            break;
                        case 4:
                            screen = TRANSFER;
                            break;
                        case 5:
                            screen = ACCOUNTBALANCE;
                            break;
                        case 6:
                            screen = DELETEACCOUNT;
                            break;
                        case 7:
                            System.exit(0);
                            break;
                        default:
                            continue;
                    }
                    break;
                case NEWACCOUNT:
                    String account = String.format("SDB-%05d", customers.length + 1);
                    System.out.println("Bank Account Number : " + account);

                    String name;
                    loop1:
                    do {
                        System.out.print("Name : ");
                        name = scan.nextLine().strip();
                        if (name.isEmpty()) {
                            System.out.println(ANSI_RED + "Name can't be Empty" + ANSI_RESET);
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i)))) {
                                System.out.println(ANSI_RED + "Invalid input" + ANSI_RESET);
                                continue loop1;
                            }
                        }
                        break;

                    } while (true);

                    String initialAmount;
                    loop2:
                    do {
                        System.out.print("Initial Deposit : ");
                        initialAmount = scan.nextLine().strip();
                        if (initialAmount.isEmpty()) {
                            System.out.println(ANSI_RED + "Initial Deposit can't be empty" + ANSI_RESET);
                            continue;
                        }
                        for (int i = 0; i < initialAmount.length(); i++) {
                            if (!(Character.isDigit(initialAmount.charAt(i)) || initialAmount.charAt(i) == '.')) {
                                System.out.println(ANSI_RED + "Invalid input" + ANSI_RESET);
                                continue loop2;
                            }
                        }
                        if (Double.valueOf(initialAmount) <= 5000.00) {
                            System.out.println(ANSI_RED + "Initial amount should be greater than Rs. 5000.00" + ANSI_RESET);
                            continue;
                        }
                        break;

                    } while (true);

                    String[][] newCustomers = new String[customers.length + 1][3];
                    for (int i = 0; i < customers.length; i++) {
                        newCustomers[i] = customers[i];
                    }
                    newCustomers[newCustomers.length - 1] = new String[]{account, name, initialAmount};

                    customers = newCustomers;
                    System.out.println();
                    System.out.println(account + " : " + name + " has been created Successfully\n");

                    System.out.print("Do you want to add another Account [Y/N] : ");

                    if (scan.nextLine().strip().equalsIgnoreCase("Y")) {
                        continue;
                    }
                    screen = DASHBOARD;
                    break;
                default:
                    System.exit(0);
            }

        } while (true);

    }
}