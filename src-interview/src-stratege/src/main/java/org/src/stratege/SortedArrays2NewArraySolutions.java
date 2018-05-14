package org.src.stratege;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 求多个有序集合的交集
 * 
 * @author huhu
 *
 */
public class SortedArrays2NewArraySolutions {

	public static void main(String[] args) {
		Integer[] a = { 1, 3, 5, 8, 20 };
		Integer[] b = { 0, 2, 8, 10, 22, 23 };
		List<Integer> arr = newArray(a, b);
		System.out.println(arr);
	}

	static List<Integer> newArray(Integer[] a, Integer[] b) {

		List<Integer> arr = new ArrayList<>();
		int top = 0, bottom = 0;
		boolean topFlag = true; // 当前遍历是在上还是在下
		/*while (top < a.length && bottom < b.length) {

			if (topFlag) {
				if (a[top] < b[bottom]) {
					arr.add(a[top]);
					++top;
				} else if (a[top] == b[bottom]) {
					arr.add(a[top]);
					++top;
					++bottom;
				} else {
					topFlag = false;
				}

			}
			if (!topFlag) {
				if (b[bottom] < a[top]) {
					arr.add(b[bottom]);
					++bottom;
				} else if (a[top] == b[bottom]) {
					arr.add(b[bottom]);
					++top;
					++bottom;
				} else {
					topFlag = true;
				}
			}
			System.out.println(String.format("top=%s, bottom=%s, topFlag=%s, arr=%s", top, bottom, topFlag, arr));
		}
		 */
		while (top < a.length && bottom < b.length) {

			if (a[top] < b[bottom]) {
				arr.add(a[top]);
				++top;
			} else if (a[top] == b[bottom]) {
				arr.add(a[top]);
				++top;
				++bottom;
			} else {
				arr.add(b[bottom]);
				++bottom;
			}
			System.out.println(String.format("top=%s, bottom=%s, topFlag=%s, arr=%s", top, bottom, topFlag, arr));
		}

		if (top < a.length) {
			arr.addAll(Arrays.asList(a).subList(top, a.length));
		}
		if (bottom < b.length) {
			arr.addAll(Arrays.asList(b).subList(bottom, b.length));
		}
		return arr;
	}
}