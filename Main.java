import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line1 = scanner.nextLine().split(" ");
        int size = Integer.valueOf(line1[0]);
        int k = Integer.valueOf(line1[1]);
        String[] line2 = scanner.nextLine().split(" ");

        Heap myHeap = new Heap(size);
        for(int i=0; i<k-1; i++){
            myHeap.insert(Integer.valueOf(line2[i]),i);
            System.out.print("_"+" ");
        }

        myHeap.insert(Integer.valueOf(line2[k-1]),k-1);
        System.out.print(myHeap.kLargest(k-1+1,k )+ " ");

        for(int i=k; i<size; i++){
            myHeap.insert(Integer.valueOf(line2[i]),i);
            System.out.print(myHeap.kLargest(i+1,k )+ " ");
        }
    }

}

class Heap {
    private static int size;
    private static int[] heap;

    Heap(int a) {
        heap = new int[a];
        size = a;
    }

    static int parent(int i) {
        return Math.floorDiv(i-1, 2);
    }

    static int left(int i) {
        return 2 * i +1;
    }

    static int right(int i) {
        return 2 * i + 2;
    }

    static void heapify(int[] heap, int i) {
        int l = left(i);
        int r = right(i);
        int largest;


        if (l < heap.length && heap[l] > heap[i]) {
            largest = l;
        } else {
            largest = i;
        }
        if (r < heap.length && heap[r] > heap[largest]) {
            largest = r;
        }
        if (largest != i) {
            exchange(heap, i, largest);
            heapify(heap, largest);
        }

    }

    public int kLargest(int len,int k){
        int[] A = Arrays.copyOf(heap,len);
        for (int i=len-1;i > len-k;i--){
            A[0] = -100001;
            heapify(A,0);
        }

        return A[0];
    }

    public void insert(int a, int i){
        heap[i] = a;
        while (i> 0 && heap[parent(i)]<heap[i]){
            exchange(heap,i,parent(i));
            i = parent(i);
        }

    }

    static void exchange(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
