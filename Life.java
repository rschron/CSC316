import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Life{

	int[][] grid;
	int[][] nextGrid;
	int x;
	int y;
	
	public Life(int x, int y){
		this.x = x;
		this.y = y;
		grid = new int[x][y];
		nextGrid = new int[x][y];
	}
	
	public void runGame(int gens){
		ExampleThread[][] thr = new ExampleThread[x][y];
		
		
		//Print initial grid
		System.out.printf("Initial Grid(gen 0):\n");
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				System.out.printf("%d ", grid[i][j]);
			}
			System.out.printf("\n");
		}
		
		for(int k = 0; k < gens; k++){
			//Create threads
			for(int i = 0; i < x; i++){
				for(int j = 0; j < y; j++){
					thr[i][j] = new ExampleThread(i, j);
				}
			}
			
			//Start threads
			for(int i = 0; i < x; i++){
				for(int j = 0; j < y; j++){
					thr[i][j].start();
				}
			}
			
			try{
				for(int i = 0; i < x; i++){
					for(int j = 0; j < y; j++){
						thr[i][j].join();
					}
				}
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			
			System.out.printf("\n");
			System.out.printf("Generation %d:\n", k + 1);
			for(int i = 0; i < x; i++){
				for(int j = 0; j < y; j++){
					System.out.printf("%d ", nextGrid[i][j]);
				}
				System.out.printf("\n");
			}
			
			grid = nextGrid.clone();
		}
	}
	
	
	private class ExampleThread extends Thread{
		private int m;
		private int n;
		int [][] dirs = new int[][]{{-1,-1}, {-1,0}, {-1,1},  {0,1}, {1,1},  {1,0},  {1,-1},  {0, -1}};
		
		public ExampleThread(int m, int n){
			this.m = m;
			this.n= n;
		}
		
		public void run(){
			int counter = 0;
		
			//Find all neighboring values
			List<Integer> neighbors = new ArrayList<Integer>();
			for(int[] direction : dirs){
				int cx = x + direction[0];
		        int cy = y + direction[1];
		        if(cy >=0 && cy < grid.length)
		            if(cx >= 0 && cx < grid[cy].length)
		                neighbors.add(grid[cy][cx]);
			}
			
			//count surrounding 1's
			for(int val : neighbors){
				if(val == 1){
					counter++;
				}
			}
			
			if(grid[m][n] == 1){
				if(counter < 2 || counter > 3){
					nextGrid[m][n] = 0;
				}
			} else{
				if(counter == 3){
					nextGrid[m][m] = 1;
				}
			}
	}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = args[0];
		int gens = Integer.parseInt(args[1]);
		int x = 0;
		int y = 0;
		
		Scanner sc;
		
		try{
			sc = new Scanner(new File(file));
			
			x = sc.nextInt();
			y = sc.nextInt();
			
			Life life = new Life(x, y);
			
			for(int i = 0; i < x; i++){
				for(int j = 0; j < y; j++){
					life.grid[i][j] = sc.nextInt();
				}
			}
			
			life.runGame(gens);
		} catch(IOException e){
			e.printStackTrace();
		}
	}

}
