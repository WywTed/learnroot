package org.src.stratege;

/**
 * 快排
 * @author huhu
 *
 */
public class QuickSortSolution {
	public static void main(String[] args){
		int[] in = {54,45,87,12,95,56,32,15,0,25};
		int count = in.length;
		int low=0;
		int high=count-1;
		QKSort(in,low,high);
		for(int i=0;i<count;i++){
			System.out.print(in[i]+" ");
		}
	}
	public static void QKSort(int[] in,int low,int high){
		int pos;
		if(low<high){
			pos = QKpass(in,low,high);
			QKSort(in,low,pos-1);
			QKSort(in,pos+1,high);
		}
	}
	public static int QKpass(int[] in,int low,int high){
		int temp = in[low];
		while(low<high){
			while(low<high && in[high]>=temp){
				high--;
			}
			in[low] = in[high];
			// System.out.println(in[low]);
			//low++;
			while(low<high && in[low]<=temp){
				low++;
			}
			in[high] = in[low];
			// System.out.println(in[high]+"FF");
			// high--;
		}

		in[low] = temp;
		for(int i=0;i<in.length;i++){
			System.out.print(in[i]+" ");
		}
		System.out.println();
		return low;
	}

}
