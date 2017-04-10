package source;

import java.util.Scanner;

import source.Heap.Record;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Kruskals {

	public static void main(String[] args) {
		UpTree up = new UpTree();
		Heap heap = new Heap();
		Scanner sc = new Scanner(System.in);
		List<Record> mst = new ArrayList<Record>();
		Record r;
		
		
		int v2;
		double w;
		int v1;
		String s;
		String[] vals = new String[3];
		
		int i = 1;
		while(sc.hasNextLine()){
			
			v1 = sc.nextInt();
			v2 = sc.nextInt();
			w = sc.nextDouble();
			
			r = heap.makeRecord(v1, v2, w);
			heap.insert(r);
			up.makeSet(v1);
			up.makeSet(v2);
			v1 = sc.nextInt();
			
			//heap.printHeap();
			System.out.println(i);
			i++;
			//System.out.println(heap.getSize());
		}
		
		
		heap.printHeap();
		
		/*
		while(up.getComponents() > 1){
			Record r = heap.deleteMin();
			int v1 = up.find(r.v1);
			int v2 = up.find(r.v2);
			
			if(v1 != v2){
				up.union(v1, v2);
				mst.add(r);
			}
		}
		
		
		Collections.sort(mst, new Comparator<Record>(){
			@Override
			public int compare(Record r2, Record r1){
				return r1.v1 < r2.v1 ? 1 : r1.v1 > r2.v1 ? -1 : 0;
			}
		});
		
		for(Record r : mst){
			System.out.printf("%d %d\n", r.v1, r.v2);
		}
		*/
		sc.close();
	}

}
