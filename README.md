# ANDROID_JUNE24_UOM
Λύση της εξέτασης Android June 2024 ΠαΜακ

![Screenshot of the app](img/scr1.png)

## Σημεία προσοχής!

Πρέπει να γίνεται έλεγχος σε περίπτωση που δεν περάστηκε κανένα μάθημα

<code>    
private double calcAverageGrade(Student st){
        int sum=0, num=0;
        for (int i : st.grades) {
            if (i >= 5){
                sum += i;
                num ++;
            }
        }
        // Calculate average
        // In case there is no grade return 0
        return num==0 ? 0 : (double) sum/num;
    }
    </code>