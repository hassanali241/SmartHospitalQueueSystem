import java.util.*;

class Patient {

    String patient_name;

    String patient_problem;

    int patient_token_number;

    int patient_priority;

    public Patient(String patient_name, String patient_problem, int patient_token_number, int patient_priority) {

        this.patient_name = patient_name;

        this.patient_problem = patient_problem;

        this.patient_token_number = patient_token_number;

        this.patient_priority = patient_priority;

    }

    @Override
    public String toString() {

        System.out.println("\t\n\n");

        return " Token: " + patient_token_number + ", Name: " + patient_name + ", Problem: " + patient_problem

                + ", Priority: " + (patient_priority == 1 ? "Emergency" : "Standard") + "\n\n";

    }

}

public class HospitalManagementSystem2 {

    Queue<Patient> simple_queue = new LinkedList<>();

    PriorityQueue<Patient> priority_queue = new PriorityQueue<>(
            (p1, p2) -> Integer.compare(p1.patient_priority, p2.patient_priority));

    Stack<Patient> last_visitedPatients = new Stack<>();

    void addPatient(String patient_name, String patient_problem, int patient_token_number, int patient_priority) {

        Patient patient = new Patient(patient_name, patient_problem, patient_token_number, patient_priority);

        if (patient.patient_priority == 1) {

            priority_queue.add(patient);
        }

        else {

            simple_queue.add(patient);
        }

        System.out.println("\n\tpatient added : " + patient);

    }

    void process_next_patient() {

        Patient nextPatient;

        if (!priority_queue.isEmpty()) {

            nextPatient = priority_queue.poll();

        }

        else if (!simple_queue.isEmpty()) {

            nextPatient = simple_queue.poll();

        }

        else {

            System.out.println("No next patient avaliable!");

            return;
        }

        last_visitedPatients.push(nextPatient);

    }

    void all_checked_patient() {

        if (last_visitedPatients.empty()) {

            System.out.println("No any patient still checked!");

            return;
        }

        Stack<Patient> tempStack = (Stack<Patient>) last_visitedPatients.clone();

        while (!tempStack.isEmpty()) {

            System.out.println(tempStack.pop());

        }

    }

    void last_checked_patient() {

        if (last_visitedPatients.empty()) {

            System.out.println("No any patient still checked!");

            return;
        }

        Stack<Patient> tempStack = (Stack<Patient>) last_visitedPatients.clone();

        System.out.println(tempStack.pop());

    }

    void last_visitedPatients_with_problem(String problem) {

        System.out.println("Search for last_visitedPatients_with_problem " + problem);

        Stack<Patient> temp_Stack = (Stack<Patient>) last_visitedPatients.clone();

        while (!temp_Stack.empty()) {

            Patient patient = temp_Stack.pop();

            if (patient.patient_problem.equalsIgnoreCase(problem)) {

                System.out.println("Found Patient : " + patient);

                return;
            }

            System.out.println("No patient found with the problem : " + problem);
        }

    }

    public static void main(String[] args) {

        HospitalManagementSystem2 system = new HospitalManagementSystem2();

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {

            System.out.println("---- Hospital Management System ---- ");

            System.out.println("1. Add Patient");

            System.out.println("2. Process Next Patient");

            System.out.println("3. View All Checked Patients");

            System.out.println("4. View Recently Checked Patient");

            System.out.println("5. View Most Recent Patient with a Specific Problem");

            System.out.println("6. Exit");

            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Patient Name: ");

                    String name = scanner.nextLine();

                    System.out.print("Enter Patient Problem: ");

                    String problem = scanner.nextLine();

                    System.out.print("Enter Token Number: ");

                    int tokenNumber = scanner.nextInt();

                    System.out.print("Enter Priority (1 = Emergency, 2 = Standard): ");

                    int priority = scanner.nextInt();

                    system.addPatient(name, problem, tokenNumber, priority);

                    break;

                case 2:

                    system.process_next_patient();

                    break;

                case 3:
                    system.all_checked_patient();

                    break;

                case 4:
                    system.last_checked_patient();

                    break;

                case 5:
                    System.out.print("Enter the problem to search for: ");

                    String searchProblem = scanner.nextLine();

                    system.last_visitedPatients_with_problem(searchProblem);

                    break;

                case 6:
                    exit = true;

                    System.out.println("Exiting the system. Take care!");

                    break;

                default:

                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

}