import java.lang.Math; import java.io.FileReader; import java.util.Scanner; import java.text.DecimalFormat;
public class HeapSortProject {
        private final int maxSize = 1 << 20;
        private int n = 0; // n is the size of the heap
        private Node[] A = new Node[maxSize+1]
        public HeapSortProject(){ } //Constructor
             int getSize(){
                return n; }
            void setSize(int i){
                n = i; }
            public int leftNode(int i){
                return 2 * i + 1;
            }
            public int rightNode(int i){
                return 2 * i + 2;
            }
        public void swap(Node[] A, int i, int top){
                Node temp = A[top];
                A[top] = A[i];
                A[i] = temp;
            }
        public void heapify(int i) {
                int l = leftNode(i);
                int r = rightNode(i);
                int top;
        
                if (l < n && A[l].key > A[i].key) {
                    top = l;
                } else {
                    top = i;
                }
        
                if (r < n && A[r].key > A[top].key) {
                    top = r;
                }
        
                if (top != i) {
                    swap(A, i, top);
                    heapify(top);
                }
            }
        public void buildUp(Node[] X, int sizeX) {
                this.A = new Node[sizeX + 1];
        
                for (int i = 1; i <= sizeX; i++) {
                    A[i] = X[i];
                }
                this.n = sizeX;
                
                for (int i = n / 2; i > 0; i--) {
                    heapify(i);
                }
            }
        public Node[] sorted(Node[] A, int sizeX){
                this.n = sizeX;
                buildUp(A, sizeX);
                for (int i = n; i > 1; i--){
                    swap(A, 1, i);
                    n--;
                    heapify(i);
                }
                return A;
            }

        
        public static void main(String[] args){
                Heap heap = new Heap();
                ArrayList<Node> wordList = new ArrayList<>();
                try {
                    long key = 0;
                    Scanner scanner = new Scanner(new File("joyce1922_ulysses.text"));
                    while (scanner.hasNext()) {
                        String word = scanner.next();
                        wordList.add(new Node(key++, word));
                    }
                    scanner.close();
                } catch (
                        FileNotFoundException e) {
                    System.out.println("File not found!");
        
                }
        
                Node[] wordsArray = wordList.toArray(new Node[0]);
                
                Heap heap = new Heap();
                heap.buildUp(wordsArray,wordsArray.length ); // This organizes them into a heap
        
                heap.sort(wordsArray, wordsArray.length );
                for (Node node : wordsArray)
                    System.out.println(node);

                DecimalFormat twoD = new DecimalFormat("0.00");
                DecimalFormat fourD = new DecimalFormat("0.0000");
                DecimalFormat fiveD = new DecimalFormat("0.00000");
                
                long start, finish;
                double runTime = 0, runTime2 = 0, time;
                double totalTime = 0.0;
                int repetition, repetitions = 30;
                
                runTime = 0;
                for(repetition = 0; repetition < repetitions; repetition++) {
                start = System.currentTimeMillis();
                
                
                finish = System.currentTimeMillis();
                
                time = (double)(finish - start);
                runTime += time;
                runTime2 += (time*time); }
                
                double aveRuntime = runTime/repetitions;
                double stdDeviation =
                Math.sqrt(runTime2 - repetitions*aveRuntime*aveRuntime)/(repetitions-1);
                
                System.out.printf("\n\n\fStatistics\n");
                System.out.println("________________________________________________");
                System.out.println("Total time   =           " + runTime/1000 + "s.");
                System.out.println("Total time\u00b2  =           " + runTime2);
                System.out.println("Average time =           " + fiveD.format(aveRuntime/1000)
                + "s. " + '\u00B1' + " " + fourD.format(stdDeviation) + "ms.");
                System.out.println("Standard deviation =     " + fourD.format(stdDeviation));
                System.out.println("n            =           " + n);
                System.out.println("Average time / run =     " + fiveD.format(aveRuntime/n*1000)
                + '\u00B5' + "s. ");
                
                System.out.println("Repetitions  =             " + repetitions);
                System.out.println("________________________________________________");
                System.out.println();
                System.out.println();
                }
    }
