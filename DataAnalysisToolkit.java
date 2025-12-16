import java.io.*;
import java.util.*;

public class DataAnalysisToolkit {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        Dataset ds = new CSVDataset("students.csv");
        Analyzer az = new StatAnalyzer();

        System.out.println("Available Students: " + ds.getStudentNames());
        System.out.print("Enter first student: ");
        String s1 = sc.nextLine();
        List<Double> m1 = ds.getMarks(s1);

        System.out.println("\nSelect Operation:");
        System.out.println("1 Sum (Pass / Fail)");
        System.out.println("2 Average");
        System.out.println("3 Variance (Best Student)");
        System.out.println("4 Correlation (Study Hours)");
        System.out.println("5 Slope (Extra Marks)");
        System.out.println("6 Filter (Failed Subjects)");
        System.out.print("Enter choice: ");

        String ch = sc.nextLine().toLowerCase();
        FileWriter fw = new FileWriter("analysis.txt", true);

        switch (ch) {

            // 🔹 SUM
            case "1":
            case "sum":
                double total = az.sum(m1);
                fw.write("Student: " + s1 + "\n");
                fw.write("Total Marks: " + total + "\n");
                fw.write(total < 140 ? "Result: FAIL\n\n" : "Result: PASS\n\n");
                break;

            // 🔹 AVERAGE
            case "2":
            case "average":
                fw.write("Student: " + s1 + "\n");
                fw.write("Average Marks: " + az.average(m1) + "\n\n");
                break;

            // 🔹 VARIANCE → BEST STUDENT + MARKS
            case "3":
            case "variance":
                System.out.print("Enter second student: ");
                String s2 = sc.nextLine();

                List<Double> m2 = ds.getMarks(s2);
                double avg1 = az.average(m1);
                double avg2 = az.average(m2);

                String best = avg1 > avg2 ? s1 : s2;
                double bestAvg = Math.max(avg1, avg2);

                fw.write("Variance Comparison between " + s1 + " & " + s2 + "\n");
                fw.write("Best Student: " + best + "\n");
                fw.write("Average Marks of Best Student: " + bestAvg + "\n\n");
                break;

            // 🔹 CORRELATION → STUDY HOURS + MARKS
            case "4":
            case "correlation":
                System.out.print("Enter second student: ");
                String s3 = sc.nextLine();

                System.out.print("Study hours of " + s1 + ": ");
                int h1 = sc.nextInt();
                System.out.print("Study hours of " + s3 + ": ");
                int h2 = sc.nextInt();

                double corr = az.correlation(m1, ds.getMarks(s3));

                String topper = h1 > h2 ? s1 : s3;
                int extraHours = Math.abs(h1 - h2);
                double topperAvg = h1 > h2 ? az.average(m1) : az.average(ds.getMarks(s3));

                fw.write("Correlation Analysis\n");
                fw.write("Correlation Value: " + corr + "\n");
                fw.write("Student studied more: " + topper + "\n");
                fw.write("Extra study hours: " + extraHours + "\n");
                fw.write("Average marks of that student: " + topperAvg + "\n\n");
                break;

            // 🔹 SLOPE → EXTRA MARKS
            case "5":
            case "slope":
                System.out.print("Enter second student: ");
                String s4 = sc.nextLine();

                double slope = az.slope(m1, ds.getMarks(s4));

                fw.write("Slope Analysis between " + s1 + " & " + s4 + "\n");
                fw.write(s1 + " scored approximately "
                        + slope + " extra marks per subject\n\n");
                break;

            // 🔹 FILTER → FAILED MARKS + COUNT + ALL MARKS
            case "6":
            case "filter":
                System.out.print("Enter pass mark threshold: ");
                double t = sc.nextDouble();

                List<Double> failed = az.filterBelow(m1, t);

                fw.write("Student: " + s1 + "\n");
                fw.write("All Marks: " + m1 + "\n");
                fw.write("Failed Marks (< " + t + "): " + failed + "\n");
                fw.write("Number of subjects failed: " + failed.size() + "\n\n");
                break;

            default:
                System.out.println("Invalid choice");
        }

        fw.close();
        System.out.println("Analysis saved in analysis.txt");
    }
}
