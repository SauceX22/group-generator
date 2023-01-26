import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        // 1- read file
        // 2- Ask for method of generating groups (by group number, or by studnets per
        // group)
        // 3- Ask for parameters according to choice of method
        // 4- Generate according to parameters
        // 5- Print groups in a loop, and Generate a file with output
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

    // method to create output file, with try catch
    // takes:
    // -path to file (same input path),
    public static void createOutputFile(List<Group> groups, String path) {
        // create output text file using the group list tostring method, and output to
        // the file at the path
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

    // method to pick a student from given list, and remove him from the list:
    // takes:
    // -list
    // returns:
    // -picked student

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

    // TODO: check the discussed formatting using String.format
    public String toString() {
        String output = "Group #" + this.groupNumber + ": ";
        for (String member : this.members) {
            output += member + ", ";
        }
        return output;
    }
}
