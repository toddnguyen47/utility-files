package com.github.toddnguyen47.javatabulator;

import java.util.ArrayList;

public class JavaTabulator {
    /**
     * Return a tabulated string
     * @param rows The total amount of rows
     * @param header Header row
     * @return The formatted string
     */
    public String getTabulatedString(ArrayList<String[]> rows, String[] header) {
        ArrayList<String[]> outputArrayList = new ArrayList<>();

        int headerLength = header.length;
        int arrMaxColumnWidth[] = new int[headerLength];
        String[] arrSeparator = new String[headerLength];

        // Initialize by using the header as the length
        for (int i = 0; i < headerLength; i++) {
            int maxLength = header[i].length();
            arrMaxColumnWidth[i] = maxLength;
            // Repeated minus. Replace the null character with -
            // Ref: https://stackoverflow.com/a/4903603
            String repeatedMinus = new String(new char[maxLength]).replace("\0", "-");
            repeatedMinus = padRight(repeatedMinus, maxLength);
            arrSeparator[i] = repeatedMinus;
        }

        outputArrayList.add(header.clone());
        outputArrayList.add(arrSeparator);

        // Now we iterate the rows, going back to fix the previous rows as needed
        for (int i = 0, i2 = rows.size(); i < i2; i++) {
            String[] curRow = rows.get(i).clone();
            if (curRow.length != headerLength) {
                System.out.println("Row and header length does not match.");
                return "";
            }

            for (int colIndex = 0, colIndexMax = curRow.length; colIndex < colIndexMax; colIndex++) {
                int maxLength = arrMaxColumnWidth[colIndex];
                String curCell = curRow[colIndex];
                if (curCell.length() > maxLength) {
                    maxLength = curCell.length();
                    arrMaxColumnWidth[colIndex] = maxLength;

                    // Go back to previous rows and fix this column only
                    for (int prevIndex = outputArrayList.size() - 1; prevIndex >= 0; prevIndex--) {
                        // at index 1, that should be the separator
                        if (prevIndex == 1) {
                            String repeatedMinus = new String(new char[maxLength]).replace("\0", "-");
                            repeatedMinus = padRight(repeatedMinus, maxLength);
                            outputArrayList.get(prevIndex)[colIndex] = repeatedMinus;
                        } else {
                            String s2 = outputArrayList.get(prevIndex)[colIndex];
                            s2 = this.padRight(s2, maxLength);
                            outputArrayList.get(prevIndex)[colIndex] = s2;
                        }
                    }
                }
                curRow[colIndex] = this.padRight(curCell, maxLength);
            }

            // Now we append the current row to the output list
            outputArrayList.add(curRow);
        }

        // Now we combine every arraylist together with a newline to separate them
        StringBuilder sb = new StringBuilder();
        for (String[] curRow : outputArrayList) {
            sb.append(String.join("  ", curRow));
            sb.append("\n");
        }

        return sb.toString();
    }

    // Ref: https://stackoverflow.com/a/391978
    // the minus sign "-" indicates a right padded string
    public String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }
}
