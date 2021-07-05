package edu.wctc.independentcopy.writer;

public class ListWriter implements Writer {
    @Override
    public void writeln(String line) {
        String[] list = line.split("\n");
        String output = "Below is the list that you entered:\n";
        for (int l = 0; l < list.length; l++) {
            output += (l+1) + ": " + list[l] + "\n";
        }

        System.out.println(output);
    }
}
