import java.util.Scanner;

public class studentgrdcalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of subjects that you want to find average of: ");
        int numSubjects = scanner.nextInt();
        int[] marks = new int[numSubjects];
        int totMarks = 0;

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject  " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt();
            totMarks += marks[i];
        }
        double avgPercentage = totMarks / (double) numSubjects;
        System.out.println("Your average pecentage is  "+avgPercentage);
        String grade = calculateGrade(avgPercentage);

        System.out.println("Total Marks: " + totMarks);
        System.out.println("Average Percentage: " + avgPercentage + "%");
        System.out.println("Grade: " + grade);
        scanner.close();
    }

    public static String calculateGrade(double avgPercentage) {
        if (avgPercentage >= 90) {
            return "A+";
        } else if (avgPercentage >= 80) {
            return "A";
        } else if (avgPercentage >= 70) {
            return "B";
        } else if (avgPercentage >= 60) {
            return "C";
        } else if (avgPercentage >= 50) {
            return "D";
        } else {
            System.out.println("You have failed .");
            return "F";
        }
    }
}
