import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
//4468972
public class Heap {
    private int n = 0;
    private Node[] A;

    public Heap() { }

    public int leftNode(int i) { return 2 * i; }
    public int rightNode(int i) { return 2 * i + 1; }

    public void swap(int i, int j) {
        Node temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public void buildUp(Node[] X, int sizeX) {
        this.A = new Node[sizeX + 1];
        this.n = sizeX;


        for (int i = 1; i <= sizeX; i++) {
            A[i] = X[i - 1];
        }


        for (int i = n / 2; i > 0; i--) {
            heapify(i);
        }
    }

    public void heapify(int i) {
        int l = leftNode(i);
        int r = rightNode(i);
        int top = i;

        // FIX: Use <= n for 1-based indexing
        // FIX: Use data.compareTo for alphabetical sorting
        if (l <= n && A[l].data.compareTo(A[top].data) > 0) {
            top = l;
        }

        if (r <= n && A[r].data.compareTo(A[top].data) > 0) {
            top = r;
        }

        if (top != i) {
            swap(i, top);
            heapify(top);
        }
    }

    public Node[] sort() {
        int originalSize = n;
        for (int i = n; i > 1; i--) {
            swap(1, i);
            n--;
            heapify(1);
        }
        n = originalSize;
        return A;
    }

    public static void main(String[] args) {
        ArrayList<Node> wordList = new ArrayList<>();

        try {
            long key = 0;
            Scanner scanner = new Scanner(new File("joyce1922_ulysses.text"));
            while (scanner.hasNext()) {
                String word = scanner.next();
                wordList.add(new Node(key++, word));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return;
        }

        Node[] wordsArray = wordList.toArray(new Node[0]);
        Heap heap = new Heap();


        heap.buildUp(wordsArray, wordsArray.length);
        Node[] sortedA = heap.sort();


        System.out.println("First 20 sorted words:");
        for (int i = 1; i <= Math.min(20, wordsArray.length); i++) {
            System.out.println(sortedA[i].data);
        }

        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");

        long start, finish;
        double runTime = 0, runTime2 = 0;
        int repetitions = 30;

        for (int repetition = 0; repetition < repetitions; repetition++) {
            heap.buildUp(wordsArray, wordsArray.length);

            start = System.currentTimeMillis();
            heap.sort();
            finish = System.currentTimeMillis();

            double time = (double) (finish - start);
            runTime += time;
            runTime2 += (time * time);
        }

        double aveRuntime = runTime / repetitions;
        double stdDeviation = Math.sqrt((runTime2 - repetitions * aveRuntime * aveRuntime) / (repetitions - 1));

        System.out.printf("\nStatistics\n");
        System.out.println("________________________________________________");
        System.out.println("Total time         = " + runTime / 1000 + "s.");
        System.out.println("Average time       = " + fiveD.format(aveRuntime / 1000) + "s.");
        System.out.println("Standard deviation = " + fourD.format(stdDeviation) + "ms.");
        System.out.println("n (Words)          = " + wordsArray.length);
        System.out.println("Repetitions        = " + repetitions);
        System.out.println("________________________________________________");
    }
}
