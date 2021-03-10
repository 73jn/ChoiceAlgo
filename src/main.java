import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

enum Students {Steve, Valerie, Brice, Antoine, Kenny, Lenny, Alexandre,
    Yoann, David, Gaetan, Aurelien, Sylvana, Cedric, Jean, Rashit, Bastian,
    Laura, Romain, Jimmy
};
enum Projects {IT1, IT2, IT3, IT4, IT5, IT6,
                IT7, IT8, IT9,IT10,IT11,IT12,
                IT13,IT15,IT16,IT17,IT18,IT19,IT20,
                IT21,IT22};

public class main {
    public static int PROJECTSIZE = 21;
    public static int STUDENTSIZE = 19;
    public static void main(String[] args) throws IOException {
        String fName = "c:\\Users\\jeann\\Downloads\\test.csv";
        String thisLine;

        FileInputStream fis = new FileInputStream(fName);
        DataInputStream myInput = new DataInputStream(fis);
        int i = 0;//line count of csv
        String[][] tab = new String[0][];//csv data line count=0 initially
        while ((thisLine = myInput.readLine()) != null) {
            ++i;//increment the line count when new line found

            String[][] newdata = new String[i][2];//create new array for data

            String strar[] = thisLine.split(";");//get contents of line as an array
            newdata[i - 1] = strar;//add new line to the array

            System.arraycopy(tab, 0, newdata, 0, i - 1);//copy previously read values to new array
            tab = newdata;//set new array as csv data
        }

        //Students students = Students.Aurelien;
        //Projects projects = Projects.IT1;
        //System.out.println(students.ordinal());
        /*
        int[][] tab = new int[STUDENTSIZE][PROJECTSIZE];
        tab[Students.Jean.ordinal()][Projects.IT2.ordinal()] = 1;
        tab[Students.Jean.ordinal()][Projects.IT3.ordinal()] = 2;
        tab[Students.Jean.ordinal()][Projects.IT1.ordinal()] = 3;
        tab[Students.Aurelien.ordinal()][Projects.IT2.ordinal()] = 2;
        tab[Students.Aurelien.ordinal()][Projects.IT3.ordinal()] = 1;
        tab[Students.Aurelien.ordinal()][Projects.IT1.ordinal()] = 3;

        tab[Students.Maxime.ordinal()][Projects.IT1.ordinal()] = 1;
        tab[Students.Maxime.ordinal()][Projects.IT2.ordinal()] = 2;
        tab[Students.Maxime.ordinal()][Projects.IT4.ordinal()] = 3;

        tab[Students.Maryline.ordinal()][Projects.IT1.ordinal()] = 2;
        tab[Students.Maryline.ordinal()][Projects.IT4.ordinal()] = 1;
        tab[Students.Maryline.ordinal()][Projects.IT2.ordinal()] = 3;

         */
        String assignements = "";
        String notFound = "";
        Vector<Integer> projectTakenVect = new Vector<Integer>();
        Vector<calc> calcVect = new Vector<calc>();
        int rate = 0;
        int highestrate = 0;
        for (int start = 0; start < STUDENTSIZE; start++){
            //System.out.println("Start = "+start);
            for (int k = 0; k < 3; k++) {
                assignements = "";
                notFound="";
                rate = 0;
                //System.out.println("K :" + k);
                int counter = k;
                projectTakenVect.removeAllElements();
                for (int a = start; a < STUDENTSIZE+start; a++) {
                    if (a >= STUDENTSIZE){
                        i = a - STUDENTSIZE;
                    } else {
                        i = a;
                    }
                    for (int j = 0; j < PROJECTSIZE; j++) {
                        if (Integer.parseInt(tab[i][j])!=0 && !projectTakenVect.contains(j)) {
                            //System.out.println("Pass");
                            if (counter == 0) {
                                projectTakenVect.add(j);
                                assignements = assignements + Students.values()[i] + " : " + Projects.values()[j] + " priority : " + tab[i][j] + "\n";
                                //System.out.println(Students.values()[i] + " : " + Projects.values()[j] + " priority : " + tab[i][j]);
                                if (Integer.parseInt(tab[i][j])!=0)
                                    rate = rate + 4 - Integer.parseInt(tab[i][j]);

                                    if (rate > highestrate)
                                        highestrate = rate;
                                break;
                            } else {
                                counter--;
                                //System.out.println("Counter--");
                            }
                        } else if (j == PROJECTSIZE - 1 ){
                            notFound = notFound + Students.values()[i] + "\n";
                        }
                    }

                }
                //System.out.println("Rate is : " + rate);
                calcVect.add(new calc(start, k, rate, assignements,notFound ));
            }
        }
        System.out.println("HIGHEST : "+ highestrate);
        int numberOfHighestRate = 0;
        for (int u = 0; u < calcVect.size(); u++) {
            if (calcVect.elementAt(u).rate == highestrate) {
                numberOfHighestRate++;
            }
        }
        System.out.println("There is "+ numberOfHighestRate+" possibilities !");
        for (int u = 0; u < calcVect.size(); u++){
            if(calcVect.elementAt(u).rate == highestrate){
                System.out.println("===========================================");
                System.out.println("Rate : " + calcVect.elementAt(u).rate);

                System.out.println(calcVect.elementAt(u).assignements);
                System.out.println("Not found :");
                System.out.println(calcVect.elementAt(u).notFound);

            }
        }
    }
}
