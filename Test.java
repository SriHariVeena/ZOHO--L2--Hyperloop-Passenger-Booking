import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Scanner;

//To store passenger details
class Passenger{
    String name;
    int age;
    int source;
    int dest;
    int position;

    Passenger(String name,int age,int source,int dest,int position){
            this.name=name;
            this.age=age;
            this.source=source;
            this.dest=dest;
            this.position=position;
    }
};
//To store the paths
class Path 
{
    List<Integer> arr;
    int length;
    Path(List<Integer> arr,int length){
        this.arr=arr;
        this.length=length;
    }

};


class SortBY implements Comparator<Path>{
        public int compare(Path a,Path b){
        if (a.length<b.length){
            return -1;
        }
        return 1;
    }
}

class SortBYAge implements Comparator<Passenger>{
        public int compare(Passenger a,Passenger b){
        if (a.age>b.age){
            return -1;
        }
        return 1;
    }
}

class SortBYQueue implements Comparator<Passenger>{
        public int compare(Passenger a,Passenger b){
        if (a.position<b.position){
            return -1;
        }
        return 1;
    }
}

class Graph {
    //adjList - To store the values
    private  ArrayList<ArrayList<Integer>> adjList; 
    //minsize - To maintain the minimum size
    private int minsize=-1;
    //paths - To give the sortest distance from source to destination
    private PriorityQueue<Path> paths;
    //queue - To maintain the position
    private PriorityQueue<Passenger> queue;
    //agequeue - To maintain the age order
    private PriorityQueue<Passenger> agequeue;
    //default Constructor
    public Graph()
    {
        //A - Z vertices
        adjList = new ArrayList<ArrayList<Integer>>(26);
        paths=new PriorityQueue<Path>(10,new SortBY());
        queue=new PriorityQueue<Passenger> (10,new SortBYQueue());
        agequeue=new PriorityQueue<Passenger> (10,new SortBYAge());
        //Intialization of adjacency matrix
        for (int i = 0; i <= 26; i++) adjList.add(new ArrayList<>());
        
    }
    public void printGraph(){
        for(ArrayList<Integer> x:adjList){
            for (Integer y:x){
                System.out.print(x+" ");
            }
            System.out.println();
        }
    }
    //addEdge - to append the values in the adjacency List 
    public void addEdge(int u, int v)
    {
         adjList.get(u).add(v);
         adjList.get(v).add(u);
    }
    //Deque - To pop the people from the pod
    public void Deque(){
        
        //Remove the aged person
        Passenger x=agequeue.poll();
        //Print the path    
        System.out.print(x.name+" ");
        printAllPaths(x.source, x.dest);
        List<Passenger> temp=new ArrayList<>();
        //age queue-50 40 30
        //pos queue-30(1) 50(2) 40(3)
        
        //age queue-40 30
        //pos queue- 50(2) 40(3)
        //30 ==?50
        //list-->30
        //pos queue-40(3)
        //50 ==50
        //age queue-40 30
        //pos queue-30(1) 40(3)
        //Remove the aged person
        while(!queue.isEmpty()){
            Passenger y=queue.poll();
            //To check whether he is the aged person
            if (y.name==x.name && x.age==y.age) {
                break;
            }
            else{
                temp.add(y);
            }
        } 
        for(Passenger z:temp){
            //Add them again
            queue.add(z);
        }
    }
    //To add the passenger in the queue
    public void Enqueue(String name,int age,int source,int dest,int position){
        
        queue.add(new Passenger(name,age,source,dest,position));
        agequeue.add(new Passenger(name,age,source,dest,position));
    }
    //To print the values in the queue
    public void print(){
        List<Passenger> temp=new ArrayList<>();
        System.out.println(queue.size());
        while(!queue.isEmpty()){
            Passenger x=queue.poll();
            temp.add(x);
            System.out.println(x.name+" "+x.age);

        }
        for(Passenger x:temp){
            queue.add(x);
        }
        temp.clear();

    }
    //To print the paths
    //source and destination of the aged Person
    public void printAllPaths(int source, int dest)
    {
        //isVisited for backtracking purpose
        boolean[] isVisited = new boolean[26];
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(source);
        //To find paths using backtracking algorithm
        minsize=-1;
        solve(source, dest, isVisited, pathList,1);
        //To print the paths
        printPaths();

    }
    //To Print the minimum paths
    private void printPaths(){
        int min=-1;
		int count=1;
        while(!paths.isEmpty()){

            Path x=paths.poll();
            if (min==-1 || min>=x.length){
			if (count==1){
            for (int y:x.arr){
                System.out.print((char)((char)y+'A')+" ");
            }
			count=2;
			}
			else{
				 System.out.print("OR (");
				 for (int y:x.arr){
					System.out.print((char)((char)y+'A')+" ");
					}
				 System.out.print(")");
			}
		
         
            //Upadate the minimum
            min=x.length;
            }
			
        }
			System.out.println();
         while(!paths.isEmpty()){
             paths.poll();
         }
    }
    //Recursive function
    private void solve(Integer u, Integer d,boolean[] isVisited,List<Integer> localPathList,int length)
    {
        //Base condition a-c-d-e //a-c-e //a-b-e //a-c-d-b-e(reject)
        if (minsize!=-1 && length>minsize) {
            //System.out.println("Base conditions");
            return;
            }
        if (u.equals(d)) {
            //System.out.println("Base conditions 2");
            paths.add(new Path (new ArrayList<>(localPathList),length));
            //Updating the minsize
            minsize=length;
            return;
        }
        //Backtracking
        isVisited[u] = true;
        for (Integer i : adjList.get(u)) {
            if (!isVisited[i]) {
                localPathList.add(i);
                solve(i, d, isVisited, localPathList,length+1);
                localPathList.remove(i);
            }
            //System.out.println("Base conditions 3");
        }
        isVisited[u] = false;
        //System.out.println("Base conditions 4");
    }


}


public class Test{
      public static void main(String[] args)
    {
        //Scanner object
        Scanner sc=new Scanner (System.in);
        //Graph object
        Graph g = new Graph();
        //position - To maintain the position of the person
        int position=0;
        //flag-To check whether the INIT statement is executed
        int flag=1;
        //input - To get the input value
        String input;
        //size - To get the size
        int size;
        //start - Starting position
        int start=0;
        
        while(true){
            input=sc.next();
            if (input.equals("INIT")){
                    size=sc.nextInt();
                    start=sc.next().charAt(0)-'A';//A-A ->0 B-A->1
                    for(int i=0;i<size;i++){
                        //Connect the edges
                        g.addEdge(sc.next().charAt(0)-'A', sc.next().charAt(0)-'A');
                    }
                    //To indidate that Init statement has worked 
                    flag=0;
            }
            else if (input.equals("ADD_PASSENGER") && flag==0){
                    size=sc.nextInt();
                    for (int i=0;i<size;i++){
                        //Add the passenger to the queue
                        //name,age,source,destination,position
                        g.Enqueue(sc.next(),sc.nextInt(),start,sc.next().charAt(0)-'A',position);
                        position++;
                    }
            }
            else if(input.equals("START_POD") && flag==0 ){
                size=sc.nextInt();
                //To remove the person from the pod
                for (int i=0;i<size;i++){
                    g.Deque();
                }
                
            }
            //To print the persons in the queue
            else if(input.equals("PRINT_Q") && flag==0){
                    g.print();
            }
            else{
                System.out.println("Initialize the paths");
            }

        }
    }  
 }
