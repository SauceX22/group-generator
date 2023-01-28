import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // 1- read file of path lib/Studnet List.txt and store in a list
        List<String> students = readStudentsFile("lib/Student List.txt");
        // 2- Ask user for method of generating groups (by group number, or by studnets
        // per group)
        int methodOfChoice = askForGroupGenerationMethod(scanner);

        // 3- Ask for parameters according to choice of method
        int[] parameters = askForGroupGenerationParameters(scanner, methodOfChoice);

        // 4- Check type of choice and Generate according to parameters
        List<Group> groups = new ArrayList<Group>();
        if (methodOfChoice == 1) {
            // groups = distributeStudentsByGroupNumber(students, parameters[0],
            // parameters[1]);
        } else if (methodOfChoice == 2) {
            // groups = distributeStudentsByStudentsPerGroup(students, parameters[0],
            // parameters[1]);
        }
        // 5- Print groups in a loop, and Generate a file with output
        createOutputFile(groups, "lib/Output.txt");
        for (Group group : groups) {
            System.out.println(group.toString());
        }
    }

    public static int askForGroupGenerationMethod(Scanner scanner) {
        System.out.println("Please choose a method of generating groups:");
        System.out.println("1- By number of groups");
        System.out.println("2- By number of students per group");
        int method = scanner.nextInt();
        return method;
    }

    public static int[] askForGroupGenerationParameters(Scanner scanner, int method) {
        int[] parameters = new int[2];
        if (method == 1) {
            System.out.println("Please enter the number of groups:");
            parameters[0] = scanner.nextInt();
            System.out.println("Please enter the max number of members per group:");
            parameters[1] = scanner.nextInt();
        } else if (method == 2) {
            System.out.println("Please enter the number of students per group:");
            parameters[0] = scanner.nextInt();
            System.out.println("Please enter the max number of members per group:");
            parameters[1] = scanner.nextInt();
        }
        return parameters;
    }

    public static List<String> readStudentsFile(String path) {
        List<String> students = new ArrayList<String>();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                students.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred, Couldn't create file.");
            e.printStackTrace();
        }
        return students;
    }

    public static void createOutputFile(List<Group> groups, String path) {
        try {
            File file = new File(path);
            PrintWriter writer = new PrintWriter(file);
            for (Group group : groups) {
                writer.println(group.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred, Couldn't create file.");
            e.printStackTrace();
        }
    }

    public static String pickRandomStudent(List<String> students) {
        int randomIndex = (int) (Math.random() * students.size());
        String pickedStudent = students.get(randomIndex);
        students.remove(randomIndex);
        return pickedStudent;
    }

    // method to distribute students accross groups (according to nubmer of groups),
    // takes:
    // -list of students,
    // -number of groups,
    // -max number of studnets per group,
    // returns:
    // -list of group objects

    // method to distribute students accross groups (according to nubmer of studetns
    // per group),
    // takes:
    // -list of students,
    // -nubmer of studetns per group,
    // -max number of studnets per group,
    // returns:
    // -list of group objects

}

class Group {

    private List<String> members;
    private int groupNumber;
    private int maxMembers;

    public Group(int maxMembers, int groupNumber) {
        this.maxMembers = maxMembers;
        this.groupNumber = groupNumber;
        this.members = new ArrayList<String>();
    }

    public void addMember(String member) {
        if (this.members.size() < this.maxMembers) {
            this.members.add(member);
        }
    }

    public String toString() {
        String outputMembers = "";
        for (String member : this.members) {
            outputMembers += member + ", ";
        }
        outputMembers = outputMembers.substring(0, outputMembers.length() - 2);

        return String.format("Group #%02d: %s", this.groupNumber, outputMembers);
    }
}
