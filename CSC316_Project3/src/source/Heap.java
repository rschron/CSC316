package source;

public class Heap {
	
	private Record[] heap = new Record[5000];
	private int heapSize = 0;
	
	/**
	public void setRoot(Record r){
		heap[0] = r;
		heapSize++;
	}
	**/
	public int getSize(){
		return heapSize;
	}
	
	public void insert(Record r){
		heap[heapSize] = r;
		heapSize++;
		upHeap(heapSize - 1);
	}
	
	private void upHeap(int index){
		int parentIndex = (index -1)/2;
		
		if(index > 0){
			if(heap[parentIndex].weight > heap[index].weight){  //if the parent is larger than the element, swap.
				Record temp = heap[parentIndex];
				heap[parentIndex] = heap[index];
				heap[index] = temp;
				upHeap(parentIndex);
			}
		}
	}
	
	public Record deleteMin(){
		Record min = heap[0];
		heapSize--;
		heap[0] = heap[heapSize];
		downHeap(0);
		return min;
	}
	
	private void downHeap(int m){
		int i = 0;
		if( (2*m) + 2 < heapSize){
			if(heap[(2*m) + 2].weight <= heap[(2*m)+1].weight){
				i = (2*m) + 2;
			} else{
				i = (2*m) + 1;
			}
		} else if ( (2*m) + 1 < heapSize){
			i = (2*m) + 1;
		}
		
		if(i > 0 && heap[m].weight > heap[i].weight){
			Record temp = heap[m];
			heap[m] = heap[i];
			heap[i] = temp;
			downHeap(i);
		}
		
		
	}
	
	public Record makeRecord(int v1, int v2, double w){
		return new Record(v1, v2, w);
	}
	
	public double getHeapElement(int i){
		if(heapSize == 0){
			return 0;
		} else{
			return heap[i].weight;
		}
	}
	
	public void printHeap(){
		for(int i = 0; i < heapSize; i++){
			System.out.printf("%d %d\n", heap[i].v1, heap[i].v2);
		}
	}
	
	public class Record{
		int v1;
		int v2;
		double weight;
		Record next;
		
		public Record(int v1, int v2, double weight){
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
			this.next = null;
		}
	}
}
