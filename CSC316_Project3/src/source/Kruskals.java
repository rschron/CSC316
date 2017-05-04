package source;

import java.util.Scanner;

import source.Heap.Record;

//import source.Heap.Record;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Kruskals {

	Record[] adjList;
	int size;
	
	public Kruskals(int x){
		adjList = new Record[x];
		size = x;
		for(int i = 0; i < adjList.length; i++){
			adjList[i] = null;
		}
	}
	
	private Record[] resize(){
		size *= 2;
		Record[] newList = new Record[size];
		for(int i = 0; i < adjList.length; i++){
			newList[i] = adjList[i];
		}
		return newList;
	}
	
	public void addRecords(Record r1, Record r2){
		if(r1.v1 >= adjList.length || r2.v2 >= adjList.length){
			adjList = resize();
		}
		
		if(adjList[r1.v1] == null){
			adjList[r1.v1] = r1;
		} else{
			Record iter = adjList[r1.v1];
			while(iter.next != null){
				iter = iter.next;
			}
			iter.next = r1;
		}
		
		if(adjList[r2.v2] == null){
			adjList[r2.v2] = r2;
		} else{
			Record iter = adjList[r2.v2];
			while(iter.next != null){
				iter = iter.next;
			}
			iter.next = r2;
		}
		
	}
	
	public void printList(){
		Record iter;
		for(int i = 0; i < adjList.length; i++){
			iter = adjList[i];
			if(iter == null){
				continue;
			}
			while(iter.next != null){
				if(iter.v1 != i){
					System.out.printf("%4d ", iter.v1);
				} else{
					System.out.printf("%4d ", iter.v2);
				}
				iter = iter.next;
			}
			if(iter.v1 != i){
				System.out.printf("%4d\n", iter.v1);
			} else{
				System.out.printf("%4d\n", iter.v2);
			}
			iter = iter.next;
		}
	}
	
	public static void main(String[] args) {
		Kruskals krus = new Kruskals(10);
		UpTree up = new UpTree();
		Heap heap = new Heap();
		Scanner sc = new Scanner(System.in);
		List<Record> mst = new ArrayList<Record>();
		Record r;
		
		int v2 = 0;
		double w = 0;
		int v1 = 0;
		
		while(sc.hasNextLine()){
			String[] arr = sc.nextLine().trim().split("\\s+");
			
			v1 = Integer.parseInt(arr[0]);
			if(v1 < 0){
				break;
			}
			v2 = Integer.parseInt(arr[1]);
			w = Double.parseDouble(arr[2]);
			r = heap.makeRecord(v1, v2, w);
			Record s = heap.makeRecord(v1, v2, w);
			Record t = heap.makeRecord(v1, v2, w);
			krus.addRecords(s, t);
			heap.insert(r);
			up.makeSet(v1);
			up.makeSet(v2);
		}
	
		heap.printHeap();
		
		while(up.getComponents() > 1){
			r = heap.deleteMin();
			v1 = up.find(r.v1);
			v2 = up.find(r.v2);
			
			if(v1 != v2){
				up.union(v1, v2);
				mst.add(r);
			}
		}
		
		//Swap vertices on edges
		for(Record rec: mst){
			if(rec.v1 > rec.v2){
				int tmp = rec.v1;
				rec.v1 = rec.v2;
				rec.v2 = tmp;
			}
		}
	
			Collections.sort(mst, new Comparator<Record>(){
				@Override
				public int compare(Record r2, Record r1){
					return r1.v1 < r2.v1 ? 1 : r1.v1 > r2.v1 ? -1 : 0;
				}
			});
			
			for(Record rec : mst){
				System.out.printf("%4d %4d\n", rec.v1, rec.v2);
			}
			
		krus.printList();
		sc.close();
	}

}