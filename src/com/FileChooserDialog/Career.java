import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Career {
    private String name, subject, hobby, food;
    private int age;

    static Map<String, int[]> subjects = new HashMap<>();
    static Map<String, int[]> hobbies = new HashMap<>();
    static Map<String, int[]> foods = new HashMap<>();

    static Map<int[], String> careers = new HashMap<>();

    static {
        subjects.put("Math", new int[] {0, 1, -1, 0, 0});
        subjects.put("Language Arts", new int[] {0, 0, 1, 1, 2});
        subjects.put("Science", new int[] {2, 1, 0, 1, 1});

        hobbies.put("A Sport", new int[] {2, -1, 1, 0, -1});
        hobbies.put("Performing Arts", new int[] {0, 0, 2, 1, 1});
        hobbies.put("Computer Science", new int[] {-1, 2, 0, 1, 1});

        foods.put("Pizza", new int[] {0, 0, 0, 0, 0});
        foods.put("B R E A D", new int[] {0, 0, 0, 0});


        careers.put(new int[] {-1, 1, 2, 2, 1}, "Teacher");
        careers.put(new int[] {-1, 0, 2, 2, -1}, "Cook");
        careers.put(new int[] {-2, 3, -1, 1, 1}, "Software Engineer");
        careers.put(new int[] {-1, 1, 3, 1, 0}, "Sales");
        careers.put(new int[] {-2, 2, -1, 2, 1}, "Math Researcher");
        careers.put(new int[] {2, -1, 2, 1, 0}, "Professional Athlete");
        careers.put(new int[] {-2, 0, -2, 2, -1}, "Janitor");
        careers.put(new int[] {0, 0, 2, 3, -1}, "Police Officer");
        careers.put(new int[] {0, 1, 2, 2, 0}, "Doctor/Nurse");
        careers.put(new int[] {-1, -1, 3, 3, -1}, "Retail");
        careers.put(new int[] {2, -1, -1, 2, -1}, "Manual Labor");
        careers.put(new int[] {1, 0, 2, 2, 2}, "Therapist");
        careers.put(new int[] {-1, 2, 1, 1, 1}, "Accountant");

    }


    private int nature, technology, humanInteraction, patience, insightfulness;

    public Career(String name, int age, String subject, String hobby, String food) {
        updateProfile(name, age, subject, hobby, food);
    }

    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Favorite Subject: " + subject);
        System.out.println("Favorite hobby: " + hobby);
        System.out.println("Favorite food: " + food);
        System.out.println("\nCaculating Career...\n");
        printCareer();
    }

    private void printCareer() {
        int[] subjectValue = subjects.get(subject);
        int[] hobbyValue = hobbies.get(hobby);
        int[] foodValue = foods.get(food);

        int[] values = new int[5];

        for (int i = 0; i<5; i++) {
            values[i] = subjectValue[i] + hobbyValue[i] + foodValue[i];
        }

        Set<int[]> cv = careers.keySet();
        Integer[][] careerValues = (Integer[][]) (cv.toArray());

        Map<Integer, String> deviations = new HashMap<>();

        for (int l = 0; l < careerValues.length; l++) {
            Integer[] m = careerValues[l];
            int deviation = 0;
            for (int x = 0; x<m.length; x++ ) {
                deviation += Math.abs(values[x] - m[x]);
            }
            deviations.put(deviation, careers.get(m));
        }

        Set<Integer> dv = deviations.keySet();
        Integer[] deviationS = (Integer[]) (dv.toArray());

        System.out.println("You will be a " + deviations.get(getMinValue(deviationS)));

    }


    public void updateProfile(String name, int age, String subject, String hobby, String food) {
        this.name = name;
        this.age = age;
        this.subject = subject;
        this.hobby = hobby;
        this.food = food;
    }

    private int getMinValue(Integer[] array) {
        int minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }




}