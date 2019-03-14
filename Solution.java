import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;

class Student {
    private int id;
    private String name;
    private double cgpa;
    public Student(int id, String name, double cgpa) {
       
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }
    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getCGPA() {
        return cgpa;
    }
}

class Priorities {
    public List getStudents(List events)
    {
        PriorityQueue studQue= new PriorityQueue(new Comparator(){
            public int compare(Student s1, Student s2) {
                if (s1.getCGPA() == s2.getCGPA())
                {
                    if(s1.getName().equals(s2.getName()))
                    {
                        return s1.getID() > s2.getID() ? -1 : 1;
                    }
                    else
                    {
                        return s1.getName().compareTo(s2.getName());
                    }
                }
                else
                {
                    return s1.getCGPA() > s2.getCGPA() ? -1 : 1;
                }
            }
        });					
            
        String [] arrayStr;
        for(String eventString : events)
        {
           arrayStr = eventString.split(" ");
           if(arrayStr[0].equals("ENTER"))
           {
            studQue.add(new Student(Integer.parseInt(arrayStr[3]), arrayStr[1], Double.parseDouble(arrayStr[2])));
           }
           else
           {
                studQue.poll();                  
           }
        }
        while(studQue.size()>1)
        {
            System.out.println(studQue.poll().getName());
        }
        return new ArrayList(studQue);
        
    }
}

public class Solution {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());    
        List events = new ArrayList<>();
        
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        
        List students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}

