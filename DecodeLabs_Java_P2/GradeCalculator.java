import java.util.*;

class Student {
    private String name;
    private List<Double> marks;

    public Student(String name) {
        this.name = name;
        this.marks = new ArrayList<>();
    }

    public void addMark(double mark) {
        if (mark < 0 || mark > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        marks.add(mark);
    }

    public double getTotalMarks() {
        double total = 0;
        // enhanced for-loop
        for (double m : marks) {
            total += m;
        }
        return total;
    }

    public double getAveragePercentage() {
        return marks.isEmpty() ? 0 : getTotalMarks() / marks.size();
    }

    public String getGrade() {
        double avg = getAveragePercentage();
        if (avg >= 90) return "A+";
        else if (avg >= 80) return "B+";
        else if (avg >= 70) return "C+";
        else if (avg >= 60) return "D+";
        else return "F";
    }

    public void displayReport() {
        System.out.println("\n******** STUDENT REPORT ********");
        System.out.println("Name              : " + name);
        System.out.println("Subjects Entered  : " + marks.size());
        System.out.printf("Total Marks       : %.2f%n", getTotalMarks());
        System.out.printf("Average Percentage: %.2f%%%n", getAveragePercentage());
        System.out.println("Grade Awarded     : " + getGrade());
        System.out.println("********************************");
    }
}

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        Student student = new Student(name);

        int subjects;
        while (true) {
            System.out.print("Enter Number of Subjects: ");
            if (sc.hasNextInt()) {
                subjects = sc.nextInt();
                if (subjects > 0) break;
                else System.out.println("Number of subjects must be greater than 0.");
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                sc.next();
            }
        }

        System.out.println("\n--- Enter Marks ---");
        for (int i = 1; i <= subjects; i++) {
            while (true) {
                System.out.print("Marks for Subject " + i + " (0-100): ");
                if (sc.hasNextDouble()) {
                    double mark = sc.nextDouble();
                    try {
                        student.addMark(mark);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Invalid input! Please enter numeric marks only.");
                    sc.next();
                }
            }
        }

        student.displayReport();
        sc.close();
    }
}