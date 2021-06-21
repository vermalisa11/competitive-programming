/*The government of a certain developing nation wants to improve transportation in one of its most inaccessible areas, in an attempt to attract investment. The region consists of several important locations that must have access to an airport.
Of course, one option is to build an airport in each of these places, but it may turn out to be cheaper to build fewer airports and have roads link them to all of the other locations. Since these are long distance roads connecting major locations in the country (e.g. cities, large villages, industrial areas), all roads are two-way. Also, there may be more than one direct road possible between two areas. This is because there may be several ways to link two areas (e.g. one road tunnels through a mountain while the other goes around it etc.) with possibly differing costs.
A location is considered to have access to an airport either if it contains an airport or if it is possible to travel by road to another location from there that has an airport. You are given the cost of building an airport and a list of possible roads between pairs of locations and their corresponding costs. The government now needs your help to decide on the cheapest way of ensuring that every location has access to an airport. The aim is to make airport access as easy as possible, so if there are several ways of getting the minimal cost, choose the one that has the most airports.
Input
The first line of input contains the integer T (T < 25), the number of test cases. The rest of the input consists of T cases. Each case starts with two integers N, M and A (0 < N ≤ 10, 000, 0 ≤ M ≤ 100, 000, 0 < A ≤ 10, 000) separated by white space. N is the number of locations, M is the number of possible roads that can be built, and A is the cost of building an airport.
The following M lines each contain three integers X, Y and C (1 ≤ X, Y ≤ N, 0 < C ≤ 10, 000), separated by white space. X and Y are two locations, and C is the cost of building a road between X and Y .
Output
Your program should output exactly T lines, one for each case. Each line should be of the form ‘Case #X: Y Z’, where X is the case number Y is the minimum cost of making roads and airports so that all locations have access to at least one airport, and Z is the number of airports to be built. As mentioned earlier, if there are several answers with minimal cost, choose the one that maximizes the number of airports.
Sample Input
2
4 4 100
1 2 10
4 3 12
4 1 41
2 3 23
5 3 1000
1 2 20
4 5 40
3 2 30
Sample Output
Case #1: 145 1
Case #2: 2090 2
*/
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main{
    static Scanner in = new Scanner(System.in);
    static Edge[] roads = new Edge[100004];
    static int[] set = new int[10004];
    public static void main(String[] args) {
        int t = in.nextInt();
        for(int k = 1;k<=t;k++){
            int n = in.nextInt();
            int v = in.nextInt();
            int cost = in.nextInt();

            for(int i = 0;i<v;i++){
                int sv = in.nextInt();
                int ev = in.nextInt();
                int weight = in.nextInt();
                roads[i] = new Edge(sv,ev,weight);
            }
            Arrays.sort(roads, 0, v, Comparator.comparingInt(edge -> edge.weight));

            for(int i = 1;i<=n;i++){
                set[i] = i;
            }
            int total = 0;
            int j = 0;
            int air = 0;

            for(int i=0; i<v && j <n-1 && roads[i].weight<cost; i++) {
                if(!isUnion(roads[i].ev,roads[i].sv,set)) {
                    total+=roads[i].weight;
                    makeUnion(roads[i].ev,roads[i].sv,set);
                    j++;
                }
            }
            for(int i=1; i<=n; i++) {
                total+=cost* ((set[i]==i)?1:0);
                air+= ((set[i]==i)?1:0);
            }
            System.out.println("Case #"+k+": "+ total+" "+air);
        }
    }
    static int getParent(int i, int[] set) {
        if(i==set[i])
            return i;
        else
            return (set[i]=getParent(set[i],set));
    }
    static boolean isUnion(int a, int b, int[] set) {
        return getParent(a,set)==getParent(b,set);
    }
    static void makeUnion(int a,int b,int[] set) {
        set[getParent(a,set)] = getParent(b,set);
    }
    static class Edge{
        int sv,ev,weight;

        public Edge(int sv, int ev, int weight) {
            this.sv = sv;
            this.ev = ev;
            this.weight = weight;
        }
    }
}
