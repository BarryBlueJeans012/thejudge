package file;

import java.io.BufferedReader;
import java.io.FileReader;

public class Checker extends PathHolder {
    private String outputFile = projectDirectory + "/src/test/";
    private String answerFile = projectDirectory + "/src/test/";

    public Checker(String classTested) {
        this.answerFile = this.answerFile + classTested + "_answer";
        this.outputFile = this.outputFile + classTested + "_output";
    }

    public String getOutputFile() {
        return outputFile;
    }

    public String getAnswerFile() {
        return answerFile;
    }

    public int checkCorrect() {
        int numCorrect = 0;
        try {
            BufferedReader readAnswer = new BufferedReader(new FileReader(answerFile));
            BufferedReader readOutput = new BufferedReader(new FileReader(outputFile));
            String answerLine = readAnswer.readLine();
            String outputLine = readOutput.readLine();
            while (answerLine != null || outputLine != null) {
                if (answerLine.equalsIgnoreCase(outputLine)) {
                    numCorrect++;
                }
                answerLine = readAnswer.readLine();
                outputLine = readOutput.readLine();
            }
            readAnswer.close();
            readOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("A fatal error occurred while checking answers");
        }
        return numCorrect;
    }
}
