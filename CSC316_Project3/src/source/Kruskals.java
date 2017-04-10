package source;

import java.util.Scanner;

import source.Heap.Record;

//import source.Heap.Record;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
			
		sc.close();
	}

}